package org.example.api.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerInfo(
    @Json(name = "achievements")
    val achievements: List<Achievement>,
    @Json(name = "attackWins")
    val attackWins: Int, // 107
    @Json(name = "bestTrophies")
    val bestTrophies: Int, // 5074
    @Json(name = "bestVersusTrophies")
    val bestVersusTrophies: Int, // 4479
    @Json(name = "builderHallLevel")
    val builderHallLevel: Int, // 9
    @Json(name = "clan")
    val clan: Clan,
    @Json(name = "clanCapitalContributions")
    val clanCapitalContributions: Int, // 328076
    @Json(name = "defenseWins")
    val defenseWins: Int, // 8
    @Json(name = "donations")
    val donations: Int, // 9539
    @Json(name = "donationsReceived")
    val donationsReceived: Int, // 342
    @Json(name = "expLevel")
    val expLevel: Int, // 218
    @Json(name = "heroes")
    val heroes: List<Heroe>,
    @Json(name = "labels")
    val labels: List<Label>,
    @Json(name = "league")
    val league: League,
    @Json(name = "legendStatistics")
    val legendStatistics: LegendStatistics,
    @Json(name = "name")
    val name: String, // 请你哥乌恩
    @Json(name = "role")
    val role: String, // leader
    @Json(name = "spells")
    val spells: List<Spell>,
    @Json(name = "tag")
    val tag: String, // #LLPR82G9P
    @Json(name = "townHallLevel")
    val townHallLevel: Int, // 14
    @Json(name = "townHallWeaponLevel")
    val townHallWeaponLevel: Int, // 4
    @Json(name = "troops")
    val troops: List<Troop>,
    @Json(name = "trophies")
    val trophies: Int, // 5035
    @Json(name = "versusBattleWinCount")
    val versusBattleWinCount: Int, // 1499
    @Json(name = "versusBattleWins")
    val versusBattleWins: Int, // 1499
    @Json(name = "versusTrophies")
    val versusTrophies: Int, // 4126
    @Json(name = "warPreference")
    val warPreference: String, // in
    @Json(name = "warStars")
    val warStars: Int // 889
) {
    @JsonClass(generateAdapter = true)
    data class Achievement(
        @Json(name = "completionInfo")
        val completionInfo: String, // Highest Gold Storage level: 14
        @Json(name = "info")
        val info: String, // Upgrade a Gold Storage to level 10
        @Json(name = "name")
        val name: String, // Bigger Coffers
        @Json(name = "stars")
        val stars: Int, // 3
        @Json(name = "target")
        val target: Int, // 10
        @Json(name = "value")
        val value: Int, // 14
        @Json(name = "village")
        val village: String // home
    )

    @JsonClass(generateAdapter = true)
    data class Clan(
        @Json(name = "badgeUrls")
        val badgeUrls: BadgeUrls,
        @Json(name = "clanLevel")
        val clanLevel: Int, // 15
        @Json(name = "name")
        val name: String, // 星際源
        @Json(name = "tag")
        val tag: String // #280Y0YGPJ
    ) {
        @JsonClass(generateAdapter = true)
        data class BadgeUrls(
            @Json(name = "large")
            val large: String, // https://api-assets.clashofclans.com/badges/512/lZVVzOvWFvo_CGOwg46m_Ax6BDhFlI7Zn-cUZ09xmjU.png
            @Json(name = "medium")
            val medium: String, // https://api-assets.clashofclans.com/badges/200/lZVVzOvWFvo_CGOwg46m_Ax6BDhFlI7Zn-cUZ09xmjU.png
            @Json(name = "small")
            val small: String // https://api-assets.clashofclans.com/badges/70/lZVVzOvWFvo_CGOwg46m_Ax6BDhFlI7Zn-cUZ09xmjU.png
        )
    }

    @JsonClass(generateAdapter = true)
    data class Heroe(
        @Json(name = "level")
        val level: Int, // 65
        @Json(name = "maxLevel")
        val maxLevel: Int, // 85
        @Json(name = "name")
        val name: String, // Barbarian King
        @Json(name = "village")
        val village: String // home
    )

    @JsonClass(generateAdapter = true)
    data class Label(
        @Json(name = "iconUrls")
        val iconUrls: IconUrls,
        @Json(name = "id")
        val id: Int, // 57000008
        @Json(name = "name")
        val name: String // Active Donator
    ) {
        @JsonClass(generateAdapter = true)
        data class IconUrls(
            @Json(name = "medium")
            val medium: String, // https://api-assets.clashofclans.com/labels/128/MvL0LDt0yv9AI-Vevpu8yE5NAJUIV05Ofpsr4IfGRxQ.png
            @Json(name = "small")
            val small: String // https://api-assets.clashofclans.com/labels/64/MvL0LDt0yv9AI-Vevpu8yE5NAJUIV05Ofpsr4IfGRxQ.png
        )
    }

    @JsonClass(generateAdapter = true)
    data class League(
        @Json(name = "iconUrls")
        val iconUrls: IconUrls,
        @Json(name = "id")
        val id: Int, // 29000022
        @Json(name = "name")
        val name: String // Legend League
    ) {
        @JsonClass(generateAdapter = true)
        data class IconUrls(
            @Json(name = "medium")
            val medium: String, // https://api-assets.clashofclans.com/leagues/288/R2zmhyqQ0_lKcDR5EyghXCxgyC9mm_mVMIjAbmGoZtw.png
            @Json(name = "small")
            val small: String, // https://api-assets.clashofclans.com/leagues/72/R2zmhyqQ0_lKcDR5EyghXCxgyC9mm_mVMIjAbmGoZtw.png
            @Json(name = "tiny")
            val tiny: String // https://api-assets.clashofclans.com/leagues/36/R2zmhyqQ0_lKcDR5EyghXCxgyC9mm_mVMIjAbmGoZtw.png
        )
    }

    @JsonClass(generateAdapter = true)
    data class LegendStatistics(
        @Json(name = "bestSeason")
        val bestSeason: BestSeason,
        @Json(name = "currentSeason")
        val currentSeason: CurrentSeason,
        @Json(name = "legendTrophies")
        val legendTrophies: Int // 0
    ) {
        @JsonClass(generateAdapter = true)
        data class BestSeason(
            @Json(name = "id")
            val id: String, // 2022-02
            @Json(name = "rank")
            val rank: Int, // 626306
            @Json(name = "trophies")
            val trophies: Int // 4972
        )

        @JsonClass(generateAdapter = true)
        data class CurrentSeason(
            @Json(name = "rank")
            val rank: Int, // 418
            @Json(name = "trophies")
            val trophies: Int // 5035
        )
    }

    @JsonClass(generateAdapter = true)
    data class Spell(
        @Json(name = "level")
        val level: Int, // 9
        @Json(name = "maxLevel")
        val maxLevel: Int, // 10
        @Json(name = "name")
        val name: String, // Lightning Spell
        @Json(name = "village")
        val village: String // home
    )

    @JsonClass(generateAdapter = true)
    data class Troop(
        @Json(name = "level")
        val level: Int, // 9
        @Json(name = "maxLevel")
        val maxLevel: Int, // 11
        @Json(name = "name")
        val name: String, // Barbarian
        @Json(name = "village")
        val village: String // home
    )
}