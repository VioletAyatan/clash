package org.ankol.server.services

import org.ankol.server.dao.entity.ClanMemberEntity

interface ClashDataOperationService {
    /**
     * 触发更新突袭周末数据的操作
     *
     * @return [Boolean] 成功或失败
     */
    fun triggerRaidSeasonUpdate(): Boolean

    /**
     * 更新部落成员信息
     * @return 最新的成员信息集合
     */
    fun triggerClanMemberUpdate(): List<ClanMemberEntity>
}
