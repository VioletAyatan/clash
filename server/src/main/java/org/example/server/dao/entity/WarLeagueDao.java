package org.example.server.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@NoArgsConstructor
@Data
@Document("warLeagues")
public class WarLeagueDao {
    @MongoId
    private String id;

    private String season;
    private List<WarLeagueDaoClan> clans;
    private List<WarLeagueDaoRound> rounds;

    @NoArgsConstructor
    @Data
    public static class WarLeagueDaoClan {
        private String tag;
        private String name;
        private Integer clanLevel;
        private BadgeUrlsDTO badgeUrls;
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
            private Integer townHallLevel;
        }
    }

    @NoArgsConstructor
    @Data
    public static class WarLeagueDaoRound {
        private List<String> warTags;
    }
}
