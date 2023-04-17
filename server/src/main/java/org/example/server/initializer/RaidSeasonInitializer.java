package org.example.server.initializer;

import com.ankol.api.ClashApi;
import com.ankol.api.entity.RaidSeason;
import org.example.server.config.ClashProperties;
import org.example.server.dao.entity.RaidSeasonEntity;
import org.example.server.services.ClashServices;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaidSeasonInitializer implements InitializingBean {
    @Autowired
    private ClashApi clashApi;
    @Autowired
    private ClashProperties clashProperties;
    @Autowired
    private ClashServices clashServices;
    @Override
    public void afterPropertiesSet() {
        RaidSeason raidSeason = clashApi.clan.capitalRaidSeasons(clashProperties.getClanTag(), 1)
                .getItems()
                .get(0);
        //入库保存
        clashServices.saveRaidSeason(RaidSeasonEntity.from(raidSeason));
    }
}
