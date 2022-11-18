package org.example.server.initializer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.exception.ClashApiException;
import org.example.server.services.RaidSeasonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RaidSeasonInitializer implements InitializingBean {
    private final RaidSeasonService raidSeasonService;

    @Override
    public void afterPropertiesSet() {
        try {
            raidSeasonService.updateRaidSeasonInformation("#280Y0YGPJ");
        } catch (ClashApiException e) {
            System.err.println("API调用出错：" + e.getMessage() + " 详情：" + e.getDetailMessage());
        }
    }
}
