package org.ankol.server.config;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ankol.server.api.ClashApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ClashApiConfig {

    private final ClashProperties clashProperties;
    private static final String ENV_TOKEN_NAME = "CLASH_TOKEN";

    @Bean
    public ClashApi clashApi() {
        String clashToken;
        if (StrUtil.isNotBlank(clashProperties.getApiAccessToken())) {
            clashToken = clashProperties.getApiAccessToken();
        } else {
            clashToken = System.getenv(ENV_TOKEN_NAME);
        }
        log.debug("[DEBUG] Clash-API is initializing with access-token [{}]", clashToken);
        return new ClashApi(clashToken);
    }
}
