package org.ankol.server.services.impl;

import org.ankol.server.dao.RaidSeasonRepository;
import org.ankol.server.dao.entity.RaidSeasonEntity;
import org.ankol.server.services.ClashServices;
import org.ankol.server.tools.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClashServicesImpl implements ClashServices {

    @Autowired
    private RaidSeasonRepository raidSeasonRepository;

    @Override
    public void saveRaidSeason(RaidSeasonEntity raidSeason) {
        raidSeasonRepository.save(raidSeason);
    }

    @Override
    public List<RaidSeasonEntity> listRaidSeason() {
        return raidSeasonRepository.findAll();
    }

    @Override
    public RaidSeasonEntity findRaidSeasonById(String id) {
        return raidSeasonRepository.findById(id)
                .orElseThrow(() -> new BusinessException("未查找找到对应的突袭周末战绩数据"));
    }
}
