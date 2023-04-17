package org.example.server.dao.entity;

import com.ankol.api.entity.RaidSeason;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.server.tools.ClashUtil;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    }

    public static RaidSeasonEntity from(RaidSeason raidSeason) {

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

    /*public static void main(String[] args) {
        DateTime parse = DateUtil.parse("20230414T070000.000Z", DatePattern.createFormatter("yyyyMMdd'T'HHmmss.SSS'Z'"));
        System.out.println("parse = " + parse);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(parse.toInstant(), ZoneId.systemDefault());
        System.out.println("zonedDateTime = " + zonedDateTime);

        DateTime dateTime = DateUtil.convertTimeZone(parse, ZoneId.of("Asia/Shanghai"));
        System.out.println("dateTime = " + dateTime);
    }*/
}
