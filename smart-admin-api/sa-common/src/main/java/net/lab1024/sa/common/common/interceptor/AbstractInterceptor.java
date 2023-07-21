package net.lab1024.sa.common.common.interceptor;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import net.lab1024.sa.common.common.annoation.NoNeedLogin;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.constant.RequestHeaderConst;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.domain.SystemEnv;
import net.lab1024.sa.common.common.enumeration.SystemEnvEnum;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import net.lab1024.sa.common.common.util.SmartRequestUtil;
import net.lab1024.sa.common.module.support.token.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 抽象拦截器
 * 只校验了登录处理
 * 自定义的拦截器 可以继承此类
 *
 * @author huke
 * @date 2023-07-12 21:56:14
 */
public abstract class AbstractInterceptor implements HandlerInterceptor {

    @Autowired
    private SystemEnv systemEnv;

    /**
     * 校验 token
     *
     * @param userId
     */
    public abstract RequestUser getDevUser(Long userId);

    /**
     * 校验 当前服务用户类型
     */
    public abstract UserTypeEnum getUserType();

    /**
     * 拦截路径
     *
     * @return
     */
    public abstract List<String> pathPatterns();

    /**
     * 忽略的url集合
     *
     * @return
     */
    public List<String> getIgnoreUrlList() {
        List<String> ignoreUrlList = Lists.newArrayList();
        ignoreUrlList.add("/swagger-ui.html");
        ignoreUrlList.add("/swagger-resources/**");
        ignoreUrlList.add("/webjars/**");
        ignoreUrlList.add("/druid/**");
        ignoreUrlList.add("/*/api-docs");
        return ignoreUrlList;
    }

    /**
     * 拦截处理登录 token
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // OPTIONS请求直接return
        if (StringUtils.equalsIgnoreCase(HttpMethod.OPTIONS.name(), request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        boolean isHandler = handler instanceof HandlerMethod;
        if (!isHandler) {
            return true;
        }
        // 校验 token
        ResponseDTO<RequestUser> res = this.checkTokenAndGetUser(request);
        if (res.getOk()) {
            SmartRequestUtil.setUser(res.getData());
            return true;
        }
        // 不需要登录
        NoNeedLogin noNeedLogin = ((HandlerMethod) handler).getMethodAnnotation(NoNeedLogin.class);
        if (null != noNeedLogin) {
            return true;
        }
        this.outputResult(response, res);
        return false;
    }

    /**
     * 判断 sa-token 未登录场景值
     * 自己根据业务在下面 switch 添加分支判断
     * NotLoginException.NOT_TOKEN 无token
     * NotLoginException.INVALID_TOKEN token无效
     * NotLoginException.TOKEN_TIMEOUT token过期
     * NotLoginException.NO_PREFIX token缺少前缀
     * NotLoginException.KICK_OUT 已被踢下线
     * NotLoginException.TOKEN_FREEZE 已被冻结
     * <p>
     * ps :之所以没有在全局异常里处理 是因为后续还有操作
     */
    public ResponseDTO<RequestUser> checkTokenAndGetUser(HttpServletRequest request) {
        /**
         * 处理【非生产环境】的测试 token ，便于开发调试
         * 如不需要 可以删除此段判断代码
         */
        if (SystemEnvEnum.PROD != systemEnv.getCurrentEnv()) {
            String tokenValue = StpUtil.getTokenValue();
            if (NumberUtils.isDigits(tokenValue)) {
                RequestUser user = this.getDevUser(NumberUtils.createLong(tokenValue));
                this.handleRequestIpAndAgent(user, request);
                // sa token 登录身份临时切换
                StpUtil.switchTo(TokenService.generateLoginId(user.getUserId(), user.getUserType()));
                return ResponseDTO.ok(user);
            }
        }

        try {
            /**
             * sa-token 会从当前请求 header or body 中获取token
             * 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
             */
            StpUtil.checkLogin();
        } catch (NotLoginException e) {
            switch (e.getType()) {
                case NotLoginException.BE_REPLACED:
                    // token 已被顶下线
                    return ResponseDTO.error(UserErrorCode.LOGIN_FROM_OTHER);
                // case NotLoginException.TOKEN_FREEZE:
                // case NotLoginException.KICK_OUT:
                default:
                    return ResponseDTO.error(UserErrorCode.LOGIN_STATE_INVALID);
            }
        }
        // 校验token的用户类型
        UserTypeEnum systemUserTypeEnum = this.getUserType();
        RequestUser user = this.buildCurrentUser(request);
        if (null == user || systemUserTypeEnum != user.getUserType()) {
            return ResponseDTO.error(UserErrorCode.LOGIN_STATE_INVALID);
        }
        return ResponseDTO.ok(user);
    }

    /**
     * 错误输出
     *
     * @param response
     * @param responseDTO
     * @throws IOException
     */
    private void outputResult(HttpServletResponse response, ResponseDTO responseDTO) throws IOException {
        String msg = JSONObject.toJSONString(responseDTO);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(msg);
        response.flushBuffer();
    }

    /**
     * build 当前请求用户
     *
     * @param request
     * @return
     */
    public RequestUser buildCurrentUser(HttpServletRequest request) {
        // 获取额外数据
        SaSession session = StpUtil.getSession();
        UserTypeEnum userTypeEnum = (UserTypeEnum) session.get(TokenService.EXTRA_KEY_USER_TYPE);
        String userName = session.getString(TokenService.EXTRA_KEY_USER_NAME);

        // 当前请求对象
        RequestUser user = new RequestUser();
        user.setUserId(TokenService.getUserId((String) StpUtil.getLoginId()));
        user.setUserName(userName);
        user.setUserType(userTypeEnum);
        this.handleRequestIpAndAgent(user, request);
        return user;
    }

    /**
     * 设置 当前请求ip agent
     *
     * @param requestUser
     * @param request
     */
    private void handleRequestIpAndAgent(RequestUser requestUser, HttpServletRequest request) {
        requestUser.setUserAgent(ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT));
        requestUser.setIp(ServletUtil.getClientIP(request));
    }
}
