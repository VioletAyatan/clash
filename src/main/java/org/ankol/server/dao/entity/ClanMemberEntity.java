package org.ankol.server.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@Data
@Document("clanMember")
public class ClanMemberEntity {
    /**
     * 玩家标签
     */
    @MongoId
    private String tag;
    /**
     * 玩家名称
     */
    private String name;
    /**
     * 玩家在部落中的角色
     */
    private String role;
    /**
     * 等级
     */
    private Integer expLevel;
    /**
     * 联赛级别
     */
    private LeagueDTO league;
    /**
     * 奖杯
     */
    private Integer trophies;
    /**
     * 大本等级
     */
    private Short townHallLevel;
    /**
     * 大本武器级别
     */
    private Short townHallWeaponLevel;
    private Integer builderBaseTrophies;
    private Integer versusTrophies;
    private Integer clanRank;
    private Integer previousClanRank;
    private Integer donations;
    private Integer donationsReceived;
    private BuilderBaseLeagueDTO builderBaseLeague;

    @NoArgsConstructor
    @Data
    public static class LeagueDTO {
        private Integer id;
        private String name;
        private IconUrlsDTO iconUrls;

        @NoArgsConstructor
        @Data
        public static class IconUrlsDTO {
            private String small;
            private String tiny;
            private String medium;
        }
    }

    @NoArgsConstructor
    @Data
    public static class BuilderBaseLeagueDTO {
        private Integer id;
        private String name;
    }
}
