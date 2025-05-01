package net.lab1024.sa.base.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源
 *
 * @Author 钟家兴
 * @Date 2025-05-01 14:49
 * @Wechat JavaerEngineer
 * @Email lab1024@163.com
 * @Copyright <a href="https://1024lab.net">1024创新实验室</a>
 */
@Slf4j
public class DynamicDataSourceHandler extends AbstractRoutingDataSource {

    /**
     * targetDataSources:将所有的数据源以Map的形式传入到AbstractRoutingDataSource的实现类中，以供后面进行动态数据源选择
     */
    public DynamicDataSourceHandler(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 系统每次选择数据源的时候会执行这个方法拿到key，再通过key去内部的数据源targetDataSources Map中找到对应的数据源对象
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHandler.getDataSourceType();
    }
}
