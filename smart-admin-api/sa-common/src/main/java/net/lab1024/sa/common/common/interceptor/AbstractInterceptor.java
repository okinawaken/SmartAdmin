package net.lab1024.sa.common.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import net.lab1024.sa.common.common.annoation.NoNeedLogin;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.constant.RequestHeaderConst;
import net.lab1024.sa.common.common.constant.StringConst;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.domain.SystemEnv;
import net.lab1024.sa.common.common.enumeration.SystemEnvEnum;
import net.lab1024.sa.common.common.util.SmartRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 抽象拦截器
 * 如果有额外的拦截处理 可以继承此类
 *
 * @Author 1024创新实验室: 罗伊
 * @Date 2021-10-09 20:56:14
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
public abstract class AbstractInterceptor implements HandlerInterceptor {

    @Autowired
    private SystemEnv systemEnv;

    /**
     * Token获取用户信息
     *
     * @return
     */
    public abstract RequestUser checkTokenAndGetUser();

    /**
     * 获取 dev 开发用户
     *
     * @param token
     * @return
     */
    public abstract RequestUser getDevRequestUser(String token);

    /**
     * 拦截路径
     *
     * @return
     */
    public abstract String[] pathPatterns();

    /**
     * 忽略的url集合
     *
     * @return
     */
    protected List<String> getIgnoreUrlList() {
        List<String> ignoreUrlList = Lists.newArrayList();
        ignoreUrlList.add("/swagger-ui.html");
        ignoreUrlList.add("/swagger-resources/**");
        ignoreUrlList.add("/webjars/**");
        ignoreUrlList.add("/druid/**");
        ignoreUrlList.add("/*/api-docs");
        return ignoreUrlList;
    }

    /**
     * 拦截服务器端响应处理ajax请求返回结果
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
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }

        boolean isHandler = handler instanceof HandlerMethod;
        if (!isHandler) {
            return true;
        }
        //放行的Uri前缀
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String target = uri.replaceFirst(contextPath, StringConst.EMPTY);
        if (this.contain(this.getIgnoreUrlList(), target)) {
            return true;
        }
        // 检查是否包含 token
        String xRequestToken = request.getParameter(RequestHeaderConst.TOKEN);
        String xHeaderToken = request.getHeader(RequestHeaderConst.TOKEN);
        String xAccessToken = StringUtils.isNotBlank(xRequestToken) ? xRequestToken : xHeaderToken;
        // 包含token 则获取用户信息 并保存
        if (StringUtils.isNotBlank(xAccessToken)) {
            // TODO listen 待处理有token 已失效的情况
            RequestUser requestUser;
            // 开发环境 token 处理 不需要的话 可以去掉
            if (SystemEnvEnum.DEV == systemEnv.getCurrentEnv() && NumberUtils.isDigits(xAccessToken)) {
                requestUser = this.getDevRequestUser(xAccessToken);
            } else {
                requestUser = this.checkTokenAndGetUser();
            }
            SmartRequestUtil.setUser(requestUser);
        }

        // 是否需要登录
        NoNeedLogin noNeedLogin = ((HandlerMethod) handler).getMethodAnnotation(NoNeedLogin.class);
        if (null != noNeedLogin) {
            return true;
        }
        if (StringUtils.isBlank(xAccessToken)) {
            this.outputResult(response, ResponseDTO.error(UserErrorCode.LOGIN_STATE_INVALID));
            return false;
        }
        return true;
    }

    public Boolean contain(List<String> ignores, String uri) {
        if (CollectionUtils.isEmpty(ignores)) {
            return false;
        }
        for (String ignoreUrl : ignores) {
            if (uri.startsWith(ignoreUrl)) {
                return true;
            }
        }
        return false;
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
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(msg);
        response.flushBuffer();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SmartRequestUtil.remove();
    }
}
