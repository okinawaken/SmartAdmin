package net.lab1024.sa.admin.interceptor;

import com.google.common.collect.Lists;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import net.lab1024.sa.common.common.interceptor.AbstractInterceptor;
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
     * 此处可根据需要
     * 自行查询用户信息
     */
    @Override
    public RequestUser getDevUser(Long userId) {
        RequestUser requestUser = new RequestUser();
        requestUser.setUserId(userId);
        requestUser.setUserName("dev");
        requestUser.setUserType(this.getUserType());
        return requestUser;
    }

    @Override
    public UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN_EMPLOYEE;
    }

    /**
     * 配置拦截路径
     *
     * @return
     */
    @Override
    public List<String> pathPatterns() {
        return Lists.newArrayList("/**");
    }

    /**
     * 如果没有需要处理的业务
     * 那就没有必要重写了 可以删除这个方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
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
