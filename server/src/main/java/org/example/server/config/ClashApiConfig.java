package org.example.server.config;

import cn.hutool.core.util.StrUtil;
import com.ankol.api.ClashApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ImportRuntimeHints(ClashApiRuntimeHitsRegistrar.class)
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
