package net.lab1024.sa.common.config.satoken;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sa token 配置
 *
 * @author: listen
 * @date: 2023/7/12 20:46
 */
@Configuration
public class SaTokenConfig {

    /**
     * 整合 jwt
     *
     * @return
     * @see <a>https://sa-token.cc/doc.html#/plugin/jwt-extend</a>
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        // Simple 简单模式
        return new StpLogicJwtForSimple();
    }

}
