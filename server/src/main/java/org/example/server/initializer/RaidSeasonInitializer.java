package org.example.server.initializer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.exception.ClashApiException;
import org.example.server.config.ClashProperties;
import org.example.server.services.RaidSeasonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 突袭周末数据初始化器
 * @author Ankol
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RaidSeasonInitializer implements InitializingBean {
    private final RaidSeasonService raidSeasonService;
    private final ClashProperties clashProperties;

    @Override
    public void afterPropertiesSet() {
        try {
            raidSeasonService.updateRaidSeasonInformation(clashProperties.getClanTag());
        } catch (ClashApiException e) {
            System.err.println("API调用出错：" + e.getMessage() + " 详情：" + e.getDetailMessage());
        }
    }
}
