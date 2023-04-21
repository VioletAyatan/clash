package com.ankol.api.entity;

import com.ankol.api.entity.common.BadgeUrlsDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 部落信息
 */
@NoArgsConstructor
@Data
public class Clan {
    /**
     * 部落标签
     */
    private String tag;
    /**
     * 部落名称
     */
    private String name;
    /**
     *
     */
    private String type;
    /**
     * 部落简介
     */
    private String description;
    /**
     * 部落所在地区
     */
    private LocationDTO location;
    /**
     *
     */
    private Boolean isFamilyFriendly;
    /**
     * 部落头像
     */
    private BadgeUrlsDTO badgeUrls;
    /**
     * 部落等级
     */
    private Integer clanLevel;
    /**
     * 部落奖杯数
     */
    private Integer clanPoints;
    /**
     * 部落夜世界奖杯数
     */
    private Integer clanVersusPoints;
    /**
     * 部落都城奖杯数
     */
    private Integer clanCapitalPoints;
    /**
     * 部落都城联赛等级
     */
    private CapitalLeagueDTO capitalLeague;
    /**
     * 加入所需奖杯
     */
    private Integer requiredTrophies;
    /**
     * 部落战频率
     */
    private String warFrequency;
    /**
     * 部落战连胜次数
     */
    private Integer warWinStreak;
    /**
     * 部落战胜利次数
     */
    private Integer warWins;
    /**
     * 部落是否公开战争日志
     */
    private Boolean isWarLogPublic;
    /**
     * 部落联赛等级
     */
    private WarLeagueDTO warLeague;
    /**
     * 成员数
     */
    private Integer members;
    /**
     * 成员列表
     */
    private List<MemberListDTO> memberList;
    /**
     * 部落标签
     */
    private List<LabelsDTO> labels;
    /**
     * 加入所需夜世界奖杯
     */
    private Integer requiredVersusTrophies;
    /**
     * 加入所需大本营等级
     */
    private Integer requiredTownhallLevel;
    /**
     * 部落都城信息
     */
    private ClanCapitalDTO clanCapital;
    /**
     * 聊天语言
     */
    private ChatLanguageDTO chatLanguage;

    @NoArgsConstructor
    @Data
    public static class LocationDTO {
        private Integer id;
        private String name;
        private Boolean isCountry;
        private String countryCode;
    }

    @NoArgsConstructor
    @Data
    public static class CapitalLeagueDTO {
        private Integer id;
        private String name;
    }

    @NoArgsConstructor
    @Data
    public static class WarLeagueDTO {
        private Integer id;
        private String name;
    }

    @NoArgsConstructor
    @Data
    public static class ClanCapitalDTO {
        private Integer capitalHallLevel;
        private List<DistrictsDTO> districts;

        @NoArgsConstructor
        @Data
        public static class DistrictsDTO {
            private Integer id;
            private String name;
            private Integer districtHallLevel;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ChatLanguageDTO {
        private Integer id;
        private String name;
        private String languageCode;
    }

    @NoArgsConstructor
    @Data
    public static class MemberListDTO {
        private String tag;
        private String name;
        private String role;
        private Integer expLevel;
        private LeagueDTO league;
        private Integer trophies;
        private Integer versusTrophies;
        private Integer clanRank;
        private Integer previousClanRank;
        private Integer donations;
        private Integer donationsReceived;
        private PlayerHouseDTO playerHouse;

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
        public static class PlayerHouseDTO {
            private List<ElementsDTO> elements;

            @NoArgsConstructor
            @Data
            public static class ElementsDTO {
                private String type;
                private Integer id;
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class LabelsDTO {
        private Integer id;
        private String name;
        private IconUrlsDTO iconUrls;

        @NoArgsConstructor
        @Data
        public static class IconUrlsDTO {
            private String small;
            private String medium;
        }
    }
}
