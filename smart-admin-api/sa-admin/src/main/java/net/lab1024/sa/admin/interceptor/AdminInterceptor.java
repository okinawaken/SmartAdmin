package net.lab1024.sa.admin.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.NumberWithFormat;
import com.google.common.collect.Lists;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import net.lab1024.sa.common.common.interceptor.AbstractInterceptor;
import net.lab1024.sa.common.common.util.SmartEnumUtil;
import net.lab1024.sa.common.module.support.token.TokenService;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * admin 拦截器
 *
 * @author: listen
 * @date: 2023/7/12 21:00
 */
@Configuration
public class AdminInterceptor extends AbstractInterceptor {

    /**
     * 配置拦截路径
     *
     * @return
     */
    @Override
    public List<String> pathPatterns() {
        return Lists.newArrayList("/**");
    }

    @Override
    public void checkSaToken() {
        StpUtil.checkLogin();
    }

    @Override
    public RequestUser getRequestUser() {
        // 获取额外数据
        Integer userType = ((NumberWithFormat) StpUtil.getExtra(TokenService.EXTRA_KEY_USER_TYPE)).intValue();
        UserTypeEnum userTypeEnum = SmartEnumUtil.getEnumByValue(userType, UserTypeEnum.class);
        String userName = (String) StpUtil.getExtra(TokenService.EXTRA_KEY_USER_NAME);
        String loginId = (String) StpUtil.getLoginId();

        // 当前请求对象
        RequestUser requestUser = new RequestUser();
        requestUser.setUserId(TokenService.getUserId(loginId));
        requestUser.setUserName(userName);
        requestUser.setUserType(userTypeEnum);
        return requestUser;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isHandle = super.preHandle(request, response, handler);
        if (!isHandle) {
            return false;
        }
        // 如有业务需处理 写在此处
        return true;
    }
}
