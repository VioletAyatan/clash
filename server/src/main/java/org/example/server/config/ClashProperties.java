package org.example.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = ClashProperties.PREFIX)
public class ClashProperties {
    public static final String PREFIX = "clash";
    /**
     * 设置需要管理的氏族.
     */
    private String clanTag;
}
