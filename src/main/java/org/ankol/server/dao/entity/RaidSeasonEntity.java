package org.ankol.server.dao.entity;

import com.ankol.api.entity.ClanMember;
import com.ankol.api.entity.RaidSeason;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ankol.server.tools.ClashUtil;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 周末突袭数据
 */
@Data
@Document("raidSeason")
public class RaidSeasonEntity {
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
    private Integer offensiveReward;
    /**
     * 防御奖励突袭币
     */
    private Long defensiveReward;
    /**
     * 所有部落成员
     */
    private List<UnAttackMember> allMember = Collections.emptyList();
    /**
     * 参与进攻的成员
     */
    private List<RaidSeason.RaidSeasonMember> members = Collections.emptyList();
    /**
     * 未参与进攻的成员.
     */
    private List<UnAttackMember> noAttackMembers = Collections.emptyList();
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
    @NoArgsConstructor
    public static class UnAttackMember {
        private String tag;
        private String name;

        /**
         * 数据结构转换
         *
         * @param member 成员 {@link com.ankol.api.entity.RaidSeason.RaidSeasonMember}
         * @return {@link UnAttackMember}
         */
        public static UnAttackMember convertFrom(RaidSeason.RaidSeasonMember member) {
            return new UnAttackMember()
                    .setTag(member.getTag())
                    .setName(member.getName());
        }

        /**
         * 数据结构转换
         *
         * @param member 成员 {@link ClanMember}
         * @return {@link UnAttackMember}
         */
        public static UnAttackMember convertFrom(ClanMember member) {
            return new UnAttackMember()
                    .setTag(member.getTag())
                    .setName(member.getName());
        }
    }

    public static RaidSeasonEntity convertFrom(RaidSeason raidSeason) {

        List<RaidSeason.RaidSeasonMember> members = raidSeason.getMembers();
        members.sort((o1, o2) -> Integer.compare(o2.getCapitalResourcesLooted(), o1.getCapitalResourcesLooted()));

        return new RaidSeasonEntity()
                .setId(ClashUtil.getRaidSeasonId(raidSeason.getStartTime()))
                .setState(raidSeason.getState())
                .setCreateTime(new Date())
                .setDefensiveReward(raidSeason.getDefensiveReward())
                .setMembers(members)
                .setRaidStartTime(ClashUtil.formatterRaidSeasonTime(raidSeason.getStartTime()).toString(TimeZone.getDefault()))
                .setRaidEndTime(ClashUtil.formatterRaidSeasonTime(raidSeason.getEndTime()).toString(TimeZone.getDefault()))
                .setOffensiveReward(raidSeason.getOffensiveReward())
                .setDefensiveReward(raidSeason.getDefensiveReward())
                .setTotalAttacks(raidSeason.getTotalAttacks())
                .setRaidsCompleted(raidSeason.getRaidsCompleted())
                .setCapitalTotalLoot(raidSeason.getCapitalTotalLoot());
    }

    /**
     * 转换为{@link RaidSeasonEntity}
     *
     * @param raidSeason  API获取的突袭周末战绩
     * @param clanMembers 部落所有成员
     * @return {@link RaidSeasonEntity}
     */
    public static RaidSeasonEntity convertFrom(RaidSeason raidSeason, List<ClanMember> clanMembers) {
        RaidSeasonEntity raidSeasonEntity = convertFrom(raidSeason);

        List<UnAttackMember> attackMemberList = raidSeasonEntity.getMembers().stream().map(UnAttackMember::convertFrom).toList();
        List<UnAttackMember> allMemberList = clanMembers.stream().map(UnAttackMember::convertFrom).collect(Collectors.toList());

        //未进攻成员列表
        ArrayList<UnAttackMember> unAttackMembers = new ArrayList<>(allMemberList);
        //所有成员中移除已进攻成员，剩余的即未进攻成员.
        unAttackMembers.removeAll(attackMemberList);

        return raidSeasonEntity
                .setAllMember(allMemberList)
                .setNoAttackMembers(unAttackMembers);
    }
}
