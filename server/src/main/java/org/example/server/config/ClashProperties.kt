package org.example.server.config

import lombok.Data
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Data
@Component
@ConfigurationProperties(prefix = ClashProperties.PREFIX)
class ClashProperties {
    /**
     * 设置需要管理的氏族.
     */
    val clanTag: String = ""

    /**
     * 访问官方Api接口所需的Token，如何获取请参考：[developer.clashofclans.com](https://developer.clashofclans.com/#/getting-started)
     */
    val apiAccessToken: String = ""

    companion object {
        const val PREFIX = "clash-manager"
    }
}