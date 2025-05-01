package net.lab1024.sa.base.config;

import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.base.constant.DataSourceTypeEnum;
import net.lab1024.sa.base.handler.DynamicDataSourceHandler;
import net.lab1024.sa.base.properties.DruidProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid 配置多数据源
 *
 * @Author 钟家兴
 * @Date 2025-05-01 14:49
 * @Wechat JavaerEngineer
 * @Email lab1024@163.com
 * @Copyright <a href="https://1024lab.net">1024创新实验室</a>
 */
@Slf4j
@Configuration
public class DruidConfig {

    /**
     * 这里以map的方式存储所有的自定义的数据源，其中key是枚举类的字符串值，value是创建好且赋值好的数据源对象
     */
    private static Map<Object, Object> targetDataSources = new HashMap<>();

    /**
     * DruidProperties(属性配置对象，见后面6 spring会根据配置文件的值将该对象的属性赋值
     * 然后调用dataSource()方法将创建好的数据源对象赋值好然后交给spring管理
     * 这里是配置的主数据源也是默认的数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties druidProperties) {
        DataSource dataSource = druidProperties.dataSource(DruidDataSourceBuilder.create().build());
        targetDataSources.put(DataSourceTypeEnum.MASTER.name(), dataSource);
        return dataSource;
    }

    /**
     * 配置从数据源，注意从数据源配置“name = "enabled", havingValue = "true"”参数
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties druidProperties) {
        DataSource dataSource = druidProperties.dataSource(DruidDataSourceBuilder.create().build());
        targetDataSources.put(DataSourceTypeEnum.SLAVE.name(), dataSource);
        return dataSource;
    }

    /**
     * 之后所有需要增加的数据源可以在这里从上到下依次添加
     * 只需要按照上面的方式（slaveDataSource的方法照葫芦画瓢）注入bean，然后再在配置文件中配置好相关的参数
     * 最后添加该数据源所对应的枚举类作为该数据源的key即可
     **/

    /**
     * 使用 @Qualifier 注解指定将两个数据源作为入参
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSourceHandler dataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Autowired(required = false) @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceTypeEnum.MASTER.name(), masterDataSource);
        log.info("加载动态数据源：{}", DataSourceTypeEnum.MASTER.name());

        // 如果从数据源不为空 则添加到 targetDataSources 中
        if (slaveDataSource != null) {
            targetDataSources.put(DataSourceTypeEnum.SLAVE.name(), slaveDataSource);
            log.info("加载动态数据源：{}", DataSourceTypeEnum.SLAVE.name());
        }

        return new DynamicDataSourceHandler(masterDataSource, targetDataSources);
    }
}
