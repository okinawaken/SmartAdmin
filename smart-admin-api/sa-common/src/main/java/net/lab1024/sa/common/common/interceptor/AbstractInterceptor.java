package net.lab1024.sa.common.common.interceptor;

import cn.dev33.satoken.exception.NotLoginException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import net.lab1024.sa.common.common.annoation.NoNeedLogin;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 抽象拦截器
 * 只校验了登录处理
 * 自定义的拦截器 可以继承此类
 *
 * @author huke
 * @date 2023-07-12 21:56:14
 */
public abstract class AbstractInterceptor implements HandlerInterceptor {
    /**
     * 校验 token
     */
    public abstract void checkSaToken();

    /**
     * Token 获取当前请求用户信息
     *
     * @return
     */
    public abstract RequestUser getRequestUser();

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
        // 校验 token 获取当前请求用户信息
        ResponseDTO<RequestUser> res = this.checkTokenAndGetUser();
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
     * 校验 sa token
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
    public ResponseDTO<RequestUser> checkTokenAndGetUser() {
        try {
            /**
             * sa-token 会从当前请求header or body 中获取token
             * 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
             */
            this.checkSaToken();
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
        RequestUser requestUser = this.getRequestUser();
        return ResponseDTO.ok(requestUser);
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

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SmartRequestUtil.remove();
    }
}
