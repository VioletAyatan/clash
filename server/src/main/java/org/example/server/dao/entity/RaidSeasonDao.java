package org.example.server.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * 周末突袭数据
 */
@Data
@Document("raidSeason")
public class RaidSeasonDao {
    @Id
    private String id;
    /**
     * 此记录创建时间
     */
    private Date createTime;
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
    private List<MembersBean> members;
    /**
     * 总进攻次数
     */
    private Long totalAttacks;
    /**
     * 突袭完成次数.
     */
    private Long raidsCompleted;
    /**
     * 获取的总突袭币
     */
    private Long capitalTotalLoot;

    @Data
    public static class MembersBean {
        private String tag;
        private String name;
        private Integer attacks = 0;
        private Short attackLimit = 5;
        private Short bonusAttackLimit = 0;
        private Integer capitalResourcesLooted;
    }
}
