package net.lab1024.sa.base.module.support.cache.config;

import net.lab1024.sa.base.module.support.cache.service.CacheService;
import net.lab1024.sa.base.module.support.cache.service.CaffeineCacheServiceImpl;
import net.lab1024.sa.base.module.support.cache.service.RedisCacheServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @author zhoumingfa
 * @date 2025/03/28
 */
@Configuration
public class CacheConfig {

    private static final String REDIS_CACHE = "redis";
    private static final String CAFFEINE_CACHE = "caffeine";


    @Bean
    @ConditionalOnProperty(prefix = "spring.cache", name = {"type"}, havingValue = REDIS_CACHE)
    public CacheService redisCacheService() {
        return new RedisCacheServiceImpl();
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.cache", name = {"type"}, havingValue = CAFFEINE_CACHE)
    public CacheService caffeineCacheService() {
        return new CaffeineCacheServiceImpl();
    }

}
