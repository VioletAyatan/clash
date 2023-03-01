package org.example.server.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.pojo.ClanMember;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 周末突袭数据
 */
@Data
@Document("raidSeason")
public class RaidSeasonDao {
    @MongoId
    private String id;
    /**
     * 状态
     */
    private String state;
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
     * 所有部落成员
     */
    private List<UnAttackMember> allMember;
    /**
     * 参与进攻的成员
     */
    private List<MembersBean> members = Collections.emptyList();
    /**
     * 未参与进攻的成员.
     */
    private List<UnAttackMember> noAttackMembers = Collections.emptyList();
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

    @Data
    @NoArgsConstructor
    public static class UnAttackMember {
        private String tag;
        private String name;

        public UnAttackMember(String tag, String name) {
            this.tag = tag;
            this.name = name;
        }

        public static UnAttackMember cover(ClanMember clanMember) {
            return new UnAttackMember(clanMember.getTag(), clanMember.getName());
        }
    }
}
