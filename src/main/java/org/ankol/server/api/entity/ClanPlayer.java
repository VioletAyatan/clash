package org.ankol.server.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ClanPlayer {

    private String tag;
    private String name;
    private Short townHallLevel;
    private Short townHallWeaponLevel;
    private Integer expLevel;
    private Integer trophies;
    private Integer bestTrophies;
    private Integer warStars;
    private Integer attackWins;
    private Integer defenseWins;
    private Integer builderHallLevel;
    private Integer builderBaseTrophies;
    private Integer versusTrophies;
    private Integer bestBuilderBaseTrophies;
    private Integer bestVersusTrophies;
    private Integer versusBattleWins;
    private String role;
    private String warPreference;
    private Integer donations;
    private Integer donationsReceived;
    private Integer clanCapitalContributions;
    private ClanDTO clan;
    private LeagueDTO league;
    private BuilderBaseLeagueDTO builderBaseLeague;
    private LegendStatisticsDTO legendStatistics;
    private List<AchievementsDTO> achievements;
    private PlayerHouseDTO playerHouse;
    private List<LabelsDTO> labels;
    private List<TroopsDTO> troops;
    private List<HeroesDTO> heroes;
    private List<SpellsDTO> spells;

    @NoArgsConstructor
    @Data
    public static class ClanDTO {
        private String tag;
        private String name;
        private Integer clanLevel;
        private BadgeUrlsDTO badgeUrls;

        @NoArgsConstructor
        @Data
        public static class BadgeUrlsDTO {
            private String small;
            private String large;
            private String medium;
        }
    }

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

    @NoArgsConstructor
    @Data
    public static class LegendStatisticsDTO {
        private Integer legendTrophies;
        private PreviousSeasonDTO previousSeason;
        private BestSeasonDTO bestSeason;
        private CurrentSeasonDTO currentSeason;

        @NoArgsConstructor
        @Data
        public static class PreviousSeasonDTO {
            private String id;
            private Integer rank;
            private Integer trophies;
        }

        @NoArgsConstructor
        @Data
        public static class BestSeasonDTO {
            private String id;
            private Integer rank;
            private Integer trophies;
        }

        @NoArgsConstructor
        @Data
        public static class CurrentSeasonDTO {
            private Integer rank;
            private Integer trophies;
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

    @NoArgsConstructor
    @Data
    public static class AchievementsDTO {
        private String name;
        private Integer stars;
        private Integer value;
        private Integer target;
        private String info;
        private String completionInfo;
        private String village;
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

    @NoArgsConstructor
    @Data
    public static class TroopsDTO {
        private String name;
        private Integer level;
        private Integer maxLevel;
        private String village;
    }

    @NoArgsConstructor
    @Data
    public static class HeroesDTO {
        private String name;
        private Integer level;
        private Integer maxLevel;
        private String village;
    }

    @NoArgsConstructor
    @Data
    public static class SpellsDTO {
        private String name;
        private Integer level;
        private Integer maxLevel;
        private String village;
    }
}
