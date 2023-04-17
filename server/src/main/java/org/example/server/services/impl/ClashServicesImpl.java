package org.example.server.services.impl;

import org.example.server.dao.RaidSeasonRepository;
import org.example.server.dao.entity.RaidSeasonEntity;
import org.example.server.services.ClashServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClashServicesImpl implements ClashServices {

    @Autowired
    private RaidSeasonRepository raidSeasonRepository;
    @Override
    public void saveRaidSeason(RaidSeasonEntity raidSeason) {
        raidSeasonRepository.save(raidSeason);
    }
}
