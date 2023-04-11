package com.ankol.api.entity;

import lombok.Data;

import java.util.List;

@Data
public class RaidSeason {
    private String state;
    private String startTime;
    private String endTime;
    private Integer capitalTotalLoot;
    private Integer raidsCompleted;
    private Integer totalAttacks;
    private Integer enemyDistrictsDestroyed;
    private Integer offensiveReward;
    private Integer defensiveReward;
    private List<MembersDTO> members;
    private List<AttackLogDTO> attackLog;
    private List<DefenseLogDTO> defenseLog;

    @Data
    public static class MembersDTO {
        private String tag;
        private String name;
        private Integer attacks;
        private Integer attackLimit;
        private Integer bonusAttackLimit;
        private Integer capitalResourcesLooted;
    }

    @Data
    public static class AttackLogDTO {
        private DefenderDTO defender;
        private Integer attackCount;
        private Integer districtCount;
        private Integer districtsDestroyed;
        private List<DistrictsDTO> districts;

        @lombok.NoArgsConstructor
        @lombok.Data
        public static class DefenderDTO {
            private String tag;
            private String name;
            private Integer level;
            private BadgeUrlsDTO badgeUrls;

            @lombok.NoArgsConstructor
            @lombok.Data
            public static class BadgeUrlsDTO {
                private String small;
                private String large;
                private String medium;
            }
        }

        @lombok.NoArgsConstructor
        @lombok.Data
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
            @lombok.Data
            public static class AttacksDTO {
                private AttackerDTO attacker;
                private Integer destructionPercent;
                private Integer stars;

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class AttackerDTO {
                    private String tag;
                    private String name;
                }
            }
        }
    }

    @Data
    public static class DefenseLogDTO {
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

           @Data
            public static class BadgeUrlsDTO {
                private String small;
                private String large;
                private String medium;
            }
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
            @lombok.Data
            public static class AttacksDTO {
                private AttackerDTO attacker;
                private Integer destructionPercent;
                private Integer stars;

                @lombok.NoArgsConstructor
                @lombok.Data
                public static class AttackerDTO {
                    private String tag;
                    private String name;
                }
            }
        }
    }
}
