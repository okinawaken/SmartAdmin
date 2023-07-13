package net.lab1024.sa.admin.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import net.lab1024.sa.common.config.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * sa-token 配置
 *
 * @author Turbolisten
 * @date 2023/7/13 16:57
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 拦截器，定义详细认证规则
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SaInterceptor interceptor = new SaInterceptor(handler -> {

            // 每个路由 都做为一个权限点
            UrlConfig.URL_LIST.forEach(url -> SaRouter.match(url, r -> StpUtil.checkPermission(url)));

        });
        // 关闭注解鉴权 只做路由拦截校验
        interceptor.isAnnotation(false);
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }


}
