package net.lab1024.sa.admin.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import net.lab1024.sa.common.config.UrlConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sa-token 配置
 *
 * @author Turbolisten
 * @date 2023/7/13 16:57
 */
@Configuration
public class SaTokenConfig {

    /**
     * 定义 Sa-Token 拦截器，定义详细认证规则
     */
    @Bean
    public SaInterceptor saInterceptor() {
        // 关闭注解鉴权 只做路由拦截校验
        return new SaInterceptor(handler -> {
            /**
             * 每个路由 都做为一个权限点
             * TODO listen from定义的api
             *             from menu数据表已选择的api
             */
            UrlConfig.AUTH_URL_LIST.forEach(url -> SaRouter.match(url, r -> StpUtil.checkPermission(url)));
        }).isAnnotation(false);
    }
}
