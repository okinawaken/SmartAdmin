package net.lab1024.sa.base.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Druid 配置属性
 *
 * @Author 钟家兴
 * @Date 2025-05-01 14:49
 * @Wechat JavaerEngineer
 * @Email lab1024@163.com
 * @Copyright <a href="https://1024lab.net">1024创新实验室</a>
 */
@Configuration
public class DruidProperties {

    @Value("${spring.datasource.druid.initial-size}")
    private int initialSize;

    @Value("${spring.datasource.druid.min-idle}")
    private int minIdle;

    @Value("${spring.datasource.druid.max-active}")
    private int maxActive;

    @Value("${spring.datasource.druid.max-wait}")
    private int maxWait;

    @Value("${spring.datasource.druid.connect-timeout}")
    private int connectTimeout;

    @Value("${spring.datasource.druid.socket-timeout}")
    private int socketTimeout;

    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.max-evictable-idle-time-millis}")
    private int maxEvictableIdleTimeMillis;

    /**
     * 构建datasource
     */
    public DruidDataSource dataSource(DruidDataSource datasource)
    {
        // 配置初始化大小、最小、最大
        datasource.setInitialSize(initialSize);
        datasource.setMaxActive(maxActive);
        datasource.setMinIdle(minIdle);

        // 配置获取连接等待超时的时间
        datasource.setMaxWait(maxWait);

        // 配置驱动连接超时时间，检测数据库建立连接的超时时间，单位是毫秒
        datasource.setConnectTimeout(connectTimeout);

        // 配置网络超时时间，等待数据库操作完成的网络超时时间，单位是毫秒
        datasource.setSocketTimeout(socketTimeout);

        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        // 配置一个连接在池中最小、最大生存的时间，单位是毫秒
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        return datasource;
    }
}
