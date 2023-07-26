package net.lab1024.sa.common.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import com.google.common.collect.Sets;
import net.lab1024.sa.common.common.interceptor.AbstractInterceptor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * web相关配置
 *
 * @Author 1024创新实验室-主任: 卓大
 * @Date 2021-09-02 20:21:10
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    private List<AbstractInterceptor> interceptorList;

    @Autowired(required = false)
    private List<SaInterceptor> saInterceptorList;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 先注册 登录拦截器
        Set<String> ignoreUrlSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(interceptorList)) {
            interceptorList.forEach(e -> {
                ignoreUrlSet.addAll(e.getIgnoreUrlList());
                registry.addInterceptor(e).addPathPatterns(e.pathPatterns()).excludePathPatterns(e.getIgnoreUrlList());
            });
        }

        // 后注册 sa-token 权限拦截器 不需要可以删除
        if (CollectionUtils.isNotEmpty(saInterceptorList)) {
            saInterceptorList.forEach(i -> {
                registry.addInterceptor(i).addPathPatterns("/**").excludePathPatterns(new ArrayList<>(ignoreUrlSet));
            });
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/preview/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/druidMonitor").setViewName("redirect:druid/index.html");
        registry.addViewController("/swaggerApi").setViewName("redirect:swagger-ui.html");
    }
}
