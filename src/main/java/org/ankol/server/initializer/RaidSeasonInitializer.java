package org.ankol.server.initializer;

import org.ankol.server.services.ClashDataOperationService;
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
