package org.ankol.server.services;

public interface ClashDataOperationService {
    /**
     * 触发更新突袭周末数据的操作
     *
     * @return {@link Boolean} 成功或失败
     */
    boolean triggerRaidSeasonUpdate();

    /**
     * 更新部落成员信息
     */
    void triggerClanMemberUpdate();
}
