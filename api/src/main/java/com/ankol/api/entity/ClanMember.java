package com.ankol.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ClanMember {

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
