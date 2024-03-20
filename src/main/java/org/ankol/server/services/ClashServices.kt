package org.ankol.server.services;

import org.ankol.server.dao.entity.ClanMemberEntity;
import org.ankol.server.dao.entity.RaidSeasonEntity;

import java.util.List;

/**
 * COC数据接口
 *
 * @author Administrator
 */
public interface ClashServices {
    /**
     * 保存或更新突袭周末战绩
     * <p>注意：如果实体类的ID在数据库中已存在的情况下，则视为更新操作.</p>
     *
     * @param raidSeason 突袭周末战绩实体
     */
    void saveRaidSeason(RaidSeasonEntity raidSeason);

    /**
     * 查询所有突袭周末战绩
     *
     * @return 突袭周末战绩列表
     */
    List<RaidSeasonEntity> listRaidSeason();

    /**
     * 根据ID查询突袭周末
     *
     * @return 突袭周末战绩
     */
    RaidSeasonEntity findRaidSeasonById(String id);

    List<ClanMemberEntity> getClanMember();
}
