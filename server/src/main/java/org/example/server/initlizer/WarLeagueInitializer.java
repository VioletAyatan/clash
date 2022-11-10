package org.example.server.initlizer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.ClashApi;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WarLeagueInitializer implements InitializingBean {

    private final ClashApi clashApi;

    @Override
    public void afterPropertiesSet() {
        clashApi.getClanWarLeagueInformation("");
    }
}
