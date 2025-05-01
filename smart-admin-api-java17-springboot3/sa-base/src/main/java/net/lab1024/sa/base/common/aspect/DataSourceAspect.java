package net.lab1024.sa.base.common.aspect;

import net.lab1024.sa.base.common.annoation.DataSource;
import net.lab1024.sa.base.handler.DynamicDataSourceContextHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 多数据源处理
 *
 * @Author 钟家兴
 * @Date 2025-05-01 14:49
 * @Wechat JavaerEngineer
 * @Email lab1024@163.com
 * @Copyright <a href="https://1024lab.net">1024创新实验室</a>
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    /**
     * aop切点，有相关注解的方法才做增强处理
     */
    @Pointcut("@annotation(net.lab1024.sa.base.common.annoation.DataSource) || @within(net.lab1024.sa.base.common.annoation.DataSource)")
    public void dsPointCut() {
    }

    /**
     * 环绕通知
     */
    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = getDataSource(point);

        if (dataSource != null) {
            DynamicDataSourceContextHandler.setDataSourceType(dataSource.value().name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHandler.clearDataSourceType();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataSource dataSource = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);

        // 方法注解为空，则取类注解
        if (Objects.nonNull(dataSource)) {
            return dataSource;
        }

        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
    }
}
