package org.example.server.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class RaidSeasonDao {
    @Id
    private String id;
    /**
     * 此记录创建时间
     */
    private String createTime;
    /**
     * 突袭开始时间
     */
    private String raidStartTime;
    /**
     * 突袭结束时间
     */
    private String raidEndTime;
    /**
     * 进攻奖励突袭币
     */
    private Long offensiveReward;
    /**
     * 防御奖励突袭币
     */
    private Long defensiveReward;
    /**
     * 参与进攻的成员
     */
    private MembersBean members;
    /**
     * 总进攻次数
     */
    private Integer totalAttacks;
    /**
     * 突袭完成次数.
     */
    private Integer raidsCompleted;
    /**
     * 获取的总突袭币
     */
    private Long capitalTotalLoot;

    @Data
    private class MembersBean {
        private String tag;
        private String name;
        private Integer attacks = 0;
        private Integer attackLimit = 5;
        private Integer bonusAttackLimit = 0;
        private Long capitalResourcesLooted;
    }
}
