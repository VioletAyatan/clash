package org.example.server.listener;

import org.example.api.ClashApi;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyListener implements InitializingBean {


    @Autowired
    private ClashApi clashApi;

    @Override
    public void afterPropertiesSet() {
    }
}
