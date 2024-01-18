package org.ankol.server.api.entity;

import lombok.Data;

import java.util.List;

@Data
public class ClanWar {

    private String state;
    private Integer teamSize;
    private Integer attacksPerMember;
    private String preparationStartTime;
    private String startTime;
    private String endTime;
    private ClanDTO clan;
    private OpponentDTO opponent;

    @Data
    public static class ClanDTO {
        private String tag;
        private String name;
        private BadgeUrlsDTO badgeUrls;
        private Integer clanLevel;
        private Integer attacks;
        private Integer stars;
        private Double destructionPercentage;
        private List<MembersDTO> members;

        @Data
        public static class BadgeUrlsDTO {
            private String small;
            private String large;
            private String medium;
        }

        @Data
        public static class MembersDTO {
            private String tag;
            private String name;
            private Integer townhallLevel;
            private Integer mapPosition;
            private Integer opponentAttacks;
            private List<AttacksDTO> attacks;
            private BestOpponentAttackDTO bestOpponentAttack;

            @Data
            public static class BestOpponentAttackDTO {
                private String attackerTag;
                private String defenderTag;
                private Integer stars;
                private Integer destructionPercentage;
                private Integer order;
                private Integer duration;
            }

            @Data
            public static class AttacksDTO {
                private String attackerTag;
                private String defenderTag;
                private Integer stars;
                private Integer destructionPercentage;
                private Integer order;
                private Integer duration;
            }
        }
    }

    @Data
    public static class OpponentDTO {
        private String tag;
        private String name;
        private BadgeUrlsDTO badgeUrls;
        private Integer clanLevel;
        private Integer attacks;
        private Integer stars;
        private Double destructionPercentage;
        private List<MembersDTO> members;

        @Data
        public static class BadgeUrlsDTO {
            private String small;
            private String large;
            private String medium;
        }

        @Data
        public static class MembersDTO {
            private String tag;
            private String name;
            private Integer townhallLevel;
            private Integer mapPosition;
            private List<AttacksDTO> attacks;
            private Integer opponentAttacks;
            private BestOpponentAttackDTO bestOpponentAttack;

            @Data
            public static class BestOpponentAttackDTO {
                private String attackerTag;
                private String defenderTag;
                private Integer stars;
                private Integer destructionPercentage;
                private Integer order;
                private Integer duration;
            }

            @Data
            public static class AttacksDTO {
                private String attackerTag;
                private String defenderTag;
                private Integer stars;
                private Integer destructionPercentage;
                private Integer order;
                private Integer duration;
            }
        }
    }
}
