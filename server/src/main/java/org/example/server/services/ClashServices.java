package org.example.server.services;

import org.example.server.dao.entity.RaidSeasonEntity;

public interface ClashServices {
    /**
     * 保存突袭周末战绩
     * @param raidSeason 突袭周末战绩实体
     */
    void saveRaidSeason(RaidSeasonEntity raidSeason);
}
