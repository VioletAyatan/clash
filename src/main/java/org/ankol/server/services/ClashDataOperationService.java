package org.ankol.server.services;

import org.ankol.server.dao.entity.ClanMemberEntity;

import java.util.ArrayList;
import java.util.List;

public interface ClashDataOperationService {
    /**
     * 触发更新突袭周末数据的操作
     *
     * @return {@link Boolean} 成功或失败
     */
    boolean triggerRaidSeasonUpdate();

    /**
     * 更新部落成员信息
     * @return 最新的成员信息集合
     */
    List<ClanMemberEntity> triggerClanMemberUpdate();
}
