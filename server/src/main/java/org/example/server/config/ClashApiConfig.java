package org.example.server.config;

import org.example.api.ClashApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClashApiConfig {

    @Bean
    public ClashApi clashApi() {
        String clashToken = System.getenv("CLASH_TOKEN"); //读取环境变量里的token
        System.out.println("Token = " + clashToken);
        return new ClashApi(clashToken);
    }
}
