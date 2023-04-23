package org.ankol.server.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Data
@Document("warLeagueLogs")
public class WarLeagueLogDao {

    private String state;
    private Integer teamSize;
    private String preparationStartTime;
    private String startTime;
    private String endTime;
    private ClanDTO clan;
    private OpponentDTO opponent;
    private String warStartTime;

    @NoArgsConstructor
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

        @NoArgsConstructor
        @Data
        public static class BadgeUrlsDTO {
            private String small;
            private String large;
            private String medium;
        }

        @NoArgsConstructor
        @Data
        public static class MembersDTO {
            private String tag;
            private String name;
            private Integer townhallLevel;
            private Integer mapPosition;
            private List<AttacksDTO> attacks;
            private Integer opponentAttacks;
            private BestOpponentAttackDTO bestOpponentAttack;

            @NoArgsConstructor
            @Data
            public static class BestOpponentAttackDTO {
                private String attackerTag;
                private String defenderTag;
                private Integer stars;
                private Integer destructionPercentage;
                private Integer order;
                private Integer duration;
            }

            @NoArgsConstructor
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

    @NoArgsConstructor
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

        @NoArgsConstructor
        @Data
        public static class BadgeUrlsDTO {
            private String small;
            private String large;
            private String medium;
        }

        @NoArgsConstructor
        @Data
        public static class MembersDTO {
            private String tag;
            private String name;
            private Integer townhallLevel;
            private Integer mapPosition;
            private List<AttacksDTO> attacks;
            private Integer opponentAttacks;
            private BestOpponentAttackDTO bestOpponentAttack;

            @NoArgsConstructor
            @Data
            public static class BestOpponentAttackDTO {
                private String attackerTag;
                private String defenderTag;
                private Integer stars;
                private Integer destructionPercentage;
                private Integer order;
                private Integer duration;
            }

            @NoArgsConstructor
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
