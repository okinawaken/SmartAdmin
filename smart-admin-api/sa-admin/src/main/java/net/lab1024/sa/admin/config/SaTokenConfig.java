package net.lab1024.sa.admin.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import net.lab1024.sa.admin.module.system.menu.service.MenuCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * sa-token 配置
 *
 * @author Turbolisten
 * @date 2023/7/13 16:57
 */
@Configuration
public class SaTokenConfig {

    @Autowired
    private MenuCacheService menuService;

    /**
     * 定义 Sa-Token 拦截器，定义详细认证规则
     */
    @Bean
    public SaInterceptor saInterceptor() {
        // 关闭注解鉴权 只做路由拦截校验
        return new SaInterceptor(handler -> {
            // 查询数据表中 需要校验权限的url
            List<String> urlList = menuService.queryNeedCheckPermissionsUrl();
            urlList.forEach(url -> SaRouter.match(url, r -> StpUtil.checkPermission(url)));
        }).isAnnotation(false);
    }
}
