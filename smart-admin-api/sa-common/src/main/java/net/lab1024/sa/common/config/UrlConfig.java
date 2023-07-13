package net.lab1024.sa.common.config;

import cn.dev33.satoken.annotation.SaIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.common.common.annoation.NoNeedLogin;
import net.lab1024.sa.common.common.domain.RequestUrlVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * description
 *
 * @author Turbolisten
 * @date 2023/7/13 17:42
 */
@Slf4j
@Configuration
public class UrlConfig {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public static List<String> AUTH_URL_LIST = Lists.newArrayList();

    /**
     * 获取每个方法的请求路径
     *
     * @return
     */
    @Bean
    public Map<Method, Set<String>> methodUrlMap() {
        Map<Method, Set<String>> methodUrlMap = Maps.newHashMap();
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            RequestMappingInfo requestMappingInfo = entry.getKey();
            Set<String> urls = requestMappingInfo.getPatternsCondition().getPatterns();
            if (CollectionUtils.isEmpty(urls)) {
                continue;
            }
            HandlerMethod handlerMethod = entry.getValue();
            methodUrlMap.put(handlerMethod.getMethod(), urls);
        }
        return methodUrlMap;
    }

    /**
     * 需要进行url权限校验的方法
     *
     * @param methodUrlMap
     * @return
     */
    @Bean
    public List<RequestUrlVO> authUrl(Map<Method, Set<String>> methodUrlMap) {
        List<RequestUrlVO> authUrlList = Lists.newArrayList();
        for (Map.Entry<Method, Set<String>> entry : methodUrlMap.entrySet()) {
            Method method = entry.getKey();
            // 忽略权限
            SaIgnore ignore = method.getAnnotation(SaIgnore.class);
            if (null != ignore) {
                continue;
            }
            NoNeedLogin noNeedLogin = method.getAnnotation(NoNeedLogin.class);
            if (null != noNeedLogin) {
                continue;
            }
            Set<String> urlSet = entry.getValue();
            List<RequestUrlVO> requestUrlList = this.buildRequestUrl(method, urlSet);
            authUrlList.addAll(requestUrlList);

            AUTH_URL_LIST.addAll(urlSet);
        }
        log.info("需要权限校验的URL：{}", authUrlList.stream().map(RequestUrlVO::getUrl).collect(Collectors.toList()));
        return authUrlList;
    }

    private List<RequestUrlVO> buildRequestUrl(Method method, Set<String> urlSet) {
        List<RequestUrlVO> requestUrlList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(urlSet)) {
            return requestUrlList;
        }
        // swagger api 说明
        String methodComment = null;
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if (apiOperation != null) {
            methodComment = apiOperation.value();
        }

        for (String url : urlSet) {
            RequestUrlVO requestUrlVO = new RequestUrlVO();
            requestUrlVO.setUrl(url);
            requestUrlVO.setComment(methodComment);
            requestUrlList.add(requestUrlVO);
        }
        return requestUrlList;
    }

    /**
     * 获取无需登录可以匿名访问的url信息
     *
     * @return
     */
    @Bean
    public List<String> noNeedLoginUrlList(Map<Method, Set<String>> methodUrlMap) {
        List<String> noNeedLoginUrlList = Lists.newArrayList();
        for (Map.Entry<Method, Set<String>> entry : methodUrlMap.entrySet()) {
            Method method = entry.getKey();
            NoNeedLogin noNeedLogin = method.getAnnotation(NoNeedLogin.class);
            if (null == noNeedLogin) {
                continue;
            }
            noNeedLoginUrlList.addAll(entry.getValue());
        }
        log.info("不需要登录的URL：{}", noNeedLoginUrlList);
        return noNeedLoginUrlList;
    }
}
