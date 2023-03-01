package org.example.api.pojo

import com.squareup.moshi.Json

data class Player(
        @Json(name = "achievements")
        val achievements: List<Achievement?>?,
        @Json(name = "attackWins")
        val attackWins: Int?,
        @Json(name = "bestTrophies")
        val bestTrophies: Int?,
        @Json(name = "bestVersusTrophies")
        val bestVersusTrophies: Int?,
        @Json(name = "builderHallLevel")
        val builderHallLevel: Int?,
        @Json(name = "clan")
        val clan: Clan?,
        @Json(name = "clanCapitalContributions")
        val clanCapitalContributions: Int?,
        @Json(name = "defenseWins")
        val defenseWins: Int?,
        @Json(name = "donations")
        val donations: Int?,
        @Json(name = "donationsReceived")
        val donationsReceived: Int?,
        @Json(name = "expLevel")
        val expLevel: Int?,
        @Json(name = "heroes")
        val heroes: List<Heroe?>?,
        @Json(name = "labels")
        val labels: List<Label?>?,
        @Json(name = "league")
        val league: League?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "playerHouse")
        val playerHouse: PlayerHouse?,
        @Json(name = "role")
        val role: String?,
        @Json(name = "spells")
        val spells: List<Spell?>?,
        @Json(name = "tag")
        val tag: String?,
        @Json(name = "townHallLevel")
        val townHallLevel: Int?,
        @Json(name = "townHallWeaponLevel")
        val townHallWeaponLevel: Int?,
        @Json(name = "troops")
        val troops: List<Troop?>?,
        @Json(name = "trophies")
        val trophies: Int?,
        @Json(name = "versusBattleWinCount")
        val versusBattleWinCount: Int?,
        @Json(name = "versusBattleWins")
        val versusBattleWins: Int?,
        @Json(name = "versusTrophies")
        val versusTrophies: Int?,
        @Json(name = "warPreference")
        val warPreference: String?,
        @Json(name = "warStars")
        val warStars: Int?
) {
    data class Achievement(
            @Json(name = "completionInfo")
            val completionInfo: String?,
            @Json(name = "info")
            val info: String?,
            @Json(name = "name")
            val name: String?,
            @Json(name = "stars")
            val stars: Int?,
            @Json(name = "target")
            val target: Int?,
            @Json(name = "value")
            val value: Int?,
            @Json(name = "village")
            val village: String?
    )

    data class Clan(
            @Json(name = "badgeUrls")
            val badgeUrls: BadgeUrls?,
            @Json(name = "clanLevel")
            val clanLevel: Int?,
            @Json(name = "name")
            val name: String?,
            @Json(name = "tag")
            val tag: String?
    ) {
        data class BadgeUrls(
                @Json(name = "large")
                val large: String?,
                @Json(name = "medium")
                val medium: String?,
                @Json(name = "small")
                val small: String?
        )
    }

    data class Heroe(
            @Json(name = "level")
            val level: Int?,
            @Json(name = "maxLevel")
            val maxLevel: Int?,
            @Json(name = "name")
            val name: String?,
            @Json(name = "village")
            val village: String?
    )

    data class Label(
            @Json(name = "iconUrls")
            val iconUrls: IconUrls?,
            @Json(name = "id")
            val id: Int?,
            @Json(name = "name")
            val name: String?
    ) {
        data class IconUrls(
                @Json(name = "medium")
                val medium: String?,
                @Json(name = "small")
                val small: String?
        )
    }

    data class League(
            @Json(name = "iconUrls")
            val iconUrls: IconUrls?,
            @Json(name = "id")
            val id: Int?,
            @Json(name = "name")
            val name: String?
    ) {
        data class IconUrls(
                @Json(name = "medium")
                val medium: String?,
                @Json(name = "small")
                val small: String?,
                @Json(name = "tiny")
                val tiny: String?
        )
    }

    data class PlayerHouse(
            @Json(name = "elements")
            val elements: List<Element?>?
    ) {
        data class Element(
                @Json(name = "id")
                val id: Int?,
                @Json(name = "type")
                val type: String?
        )
    }

    data class Spell(
            @Json(name = "level")
            val level: Int?,
            @Json(name = "maxLevel")
            val maxLevel: Int?,
            @Json(name = "name")
            val name: String?,
            @Json(name = "village")
            val village: String?
    )

    data class Troop(
            @Json(name = "level")
            val level: Int?,
            @Json(name = "maxLevel")
            val maxLevel: Int?,
            @Json(name = "name")
            val name: String?,
            @Json(name = "village")
            val village: String?
    )
}