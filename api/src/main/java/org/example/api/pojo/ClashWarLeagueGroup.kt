package org.example.api.pojo

data class ClashWarLeagueGroup(
    /**
     * 标签
     */
    val tag: String,
    /**
     * 状态
     */
    val state: String,
    /**
     * 赛季
     */
    val season: String,
    /**
     * 参展部落信息
     */
    val clans: List<ClanWarLeagueClan>,
    /**
     * 回合数
     */
    val rounds: List<RoundsBean>,
) {
    data class RoundsBean(
        var warTags: List<String> = emptyList()
    )


    data class ClanWarLeagueClan(
        val tag: String,
        val name: String,
        val clanLevel: Int,
        val members: List<ClanWarLeagueClanMember>,
        val badgeUrls: BadgeUrls,
    )

    data class ClanWarLeagueClanMember(
        val tag: String,
        val townHallLevel: Int,
        val name: String,
    )
}
