package org.example.server.initializer;

import com.ankol.api.ClashApi;
import com.ankol.api.entity.ItemResult;
import com.ankol.api.entity.RaidSeason;
import org.example.server.config.ClashProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaidSeasonInitializer implements InitializingBean {
    @Autowired
    private ClashApi clashApi;
    @Autowired
    private ClashProperties clashProperties;

    @Override
    public void afterPropertiesSet() {
    }
}
