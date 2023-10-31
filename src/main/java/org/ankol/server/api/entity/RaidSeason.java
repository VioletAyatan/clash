package org.ankol.server.api.entity;

import lombok.Data;
import org.ankol.server.api.entity.common.BadgeUrlsDTO;

import java.util.List;

@Data
public class RaidSeason {
    private String state;
    private String startTime;
    private String endTime;
    /**
     * 当前赛季获取的总突袭币数量
     */
    private Long capitalTotalLoot;
    private Integer raidsCompleted;
    private Integer totalAttacks;
    private Integer enemyDistrictsDestroyed;
    private Integer offensiveReward;
    private Long defensiveReward;
    private List<RaidSeasonMember> members;
    private List<RaidSeasonAttackLog> attackLog;
    private List<RaidSeasonDefenseLog> defenseLog;

    @Data
    public static class RaidSeasonMember {
        private String tag;
        private String name;
        private Integer attacks;
        private Integer attackLimit;
        private Integer bonusAttackLimit;
        private Integer capitalResourcesLooted;
    }

    @Data
    public static class RaidSeasonAttackLog {
        private DefenderDTO defender;
        private Integer attackCount;
        private Integer districtCount;
        private Integer districtsDestroyed;
        private List<DistrictsDTO> districts;

        @lombok.NoArgsConstructor
        @Data
        public static class DefenderDTO {
            private String tag;
            private String name;
            private Integer level;
            private BadgeUrlsDTO badgeUrls;
        }

        @lombok.NoArgsConstructor
        @Data
        public static class DistrictsDTO {
            private Integer id;
            private String name;
            private Integer districtHallLevel;
            private Integer destructionPercent;
            private Integer stars;
            private Integer attackCount;
            private Integer totalLooted;
            private List<AttacksDTO> attacks;

            @lombok.NoArgsConstructor
            @Data
            public static class AttacksDTO {
                private AttackerDTO attacker;
                private Integer destructionPercent;
                private Integer stars;

                @lombok.NoArgsConstructor
                @Data
                public static class AttackerDTO {
                    private String tag;
                    private String name;
                }
            }
        }
    }

    @Data
    public static class RaidSeasonDefenseLog {
        private AttackerDTO attacker;
        private Integer attackCount;
        private Integer districtCount;
        private Integer districtsDestroyed;
        private List<DistrictsDTO> districts;

        @Data
        public static class AttackerDTO {
            private String tag;
            private String name;
            private Integer level;
            private BadgeUrlsDTO badgeUrls;
        }

        @Data
        public static class DistrictsDTO {
            private Integer id;
            private String name;
            private Integer districtHallLevel;
            private Integer destructionPercent;
            private Integer stars;
            private Integer attackCount;
            private Integer totalLooted;
            private List<AttacksDTO> attacks;

            @lombok.NoArgsConstructor
            @Data
            public static class AttacksDTO {
                private AttackerDTO attacker;
                private Integer destructionPercent;
                private Integer stars;

                @lombok.NoArgsConstructor
                @Data
                public static class AttackerDTO {
                    private String tag;
                    private String name;
                }
            }
        }
    }
}
