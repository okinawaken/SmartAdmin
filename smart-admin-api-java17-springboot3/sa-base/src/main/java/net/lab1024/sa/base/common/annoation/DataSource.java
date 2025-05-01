package net.lab1024.sa.base.common.annoation;

import net.lab1024.sa.base.constant.DataSourceTypeEnum;

import java.lang.annotation.*;

/**
 * 切换数据源名称
 *
 * @Author 钟家兴
 * @Date 2025-05-01 14:49
 * @Wechat JavaerEngineer
 * @Email lab1024@163.com
 * @Copyright <a href="https://1024lab.net">1024创新实验室</a>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {

    /**
     * 切换数据源名称
     */
    public DataSourceTypeEnum value() default DataSourceTypeEnum.SLAVE;
}
