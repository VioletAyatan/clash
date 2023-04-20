package org.example.server.initializer;

import org.example.server.services.ClashDataOperationService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaidSeasonInitializer implements InitializingBean {
    @Autowired
    private ClashDataOperationService clashDataOperationService;

    @Override
    public void afterPropertiesSet() {
        clashDataOperationService.triggerRaidSeasonUpdate();
    }
}
