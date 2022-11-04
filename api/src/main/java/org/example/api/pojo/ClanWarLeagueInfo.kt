package org.example.api.pojo

data class ClanWarLeagueInfo(
    val clan: Clan,
    val endTime: String,
    val opponent: Opponent,
    val preparationStartTime: String,
    val startTime: String,
    val state: String,
    val teamSize: Int,
    val warStartTime: String
) {
    data class Clan(
        val attacks: Int,
        val badgeUrls: BadgeUrls,
        val clanLevel: Int,
        val destructionPercentage: Double,
        val members: List<Member>,
        val name: String,
        val stars: Int,
        val tag: String
    ) {
        data class BadgeUrls(
            val large: String,
            val medium: String,
            val small: String
        )

        data class Member(
            val attacks: List<Attack>,
            val bestOpponentAttack: BestOpponentAttack,
            val mapPosition: Int,
            val name: String,
            val opponentAttacks: Int,
            val tag: String,
            val townhallLevel: Int
        ) {
            data class Attack(
                val attackerTag: String,
                val defenderTag: String,
                val destructionPercentage: Int,
                val duration: Int,
                val order: Int,
                val stars: Int
            )

            data class BestOpponentAttack(
                val attackerTag: String,
                val defenderTag: String,
                val destructionPercentage: Int,
                val duration: Int,
                val order: Int,
                val stars: Int
            )
        }
    }

    data class Opponent(
        val attacks: Int,
        val badgeUrls: BadgeUrls,
        val clanLevel: Int,
        val destructionPercentage: Double,
        val members: List<Member>,
        val name: String,
        val stars: Int,
        val tag: String
    ) {
        data class BadgeUrls(
            val large: String,
            val medium: String,
            val small: String
        )

        data class Member(
            val attacks: List<Attack>,
            val bestOpponentAttack: BestOpponentAttack,
            val mapPosition: Int,
            val name: String,
            val opponentAttacks: Int,
            val tag: String,
            val townhallLevel: Int
        ) {
            data class Attack(
                val attackerTag: String,
                val defenderTag: String,
                val destructionPercentage: Int,
                val duration: Int,
                val order: Int,
                val stars: Int
            )

            data class BestOpponentAttack(
                val attackerTag: String,
                val defenderTag: String,
                val destructionPercentage: Int,
                val duration: Int,
                val order: Int,
                val stars: Int
            )
        }
    }
}