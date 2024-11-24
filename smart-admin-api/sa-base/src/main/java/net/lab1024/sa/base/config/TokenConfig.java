package net.lab1024.sa.base.config;

import cn.dev33.satoken.config.SaTokenConfig;
import net.lab1024.sa.base.module.support.securityprotect.service.Level3ProtectConfigService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
public class TokenConfig {

    @Resource
    private Level3ProtectConfigService level3ProtectConfigService;

    // 此配置会覆盖 sa-base.yaml 中的配置
    @Resource
    public void configSaToken(SaTokenConfig config) {

        config.setActiveTimeout(level3ProtectConfigService.getLoginActiveTimeoutSeconds());
    }
}
