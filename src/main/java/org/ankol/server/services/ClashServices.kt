package org.ankol.server.services

import org.ankol.server.dao.entity.ClanMemberEntity
import org.ankol.server.dao.entity.RaidSeasonEntity

/**
 * COC数据接口
 *
 * @author Administrator
 */
interface ClashServices {
    /**
     * 保存或更新突袭周末战绩
     *
     * 注意：如果实体类的ID在数据库中已存在的情况下，则视为更新操作.
     *
     * @param raidSeason 突袭周末战绩实体
     */
    fun saveRaidSeason(raidSeason: RaidSeasonEntity)

    /**
     * 查询所有突袭周末战绩
     *
     * @return 突袭周末战绩列表
     */
    fun listRaidSeason(): List<RaidSeasonEntity>

    /**
     * 根据ID查询突袭周末
     *
     * @return 突袭周末战绩
     */
    fun findRaidSeasonById(id: String): RaidSeasonEntity

    fun getClanMember(): List<ClanMemberEntity>
}
