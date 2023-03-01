package org.example.api.pojo

/**
 * 部落
 */
data class Clan(
        /**
         * 标签
         */
        val tag: String,
        /**
         * 名称
         */
        val name: String,
        /**
         *
         */
        val type: String,
        /**
         * 描述
         */
        val description: String,
        /**
         * 部落区域
         */
        val location: Location,
        /**
         * 部落徽章链接
         */
        val badgeUrls: BadgeUrls,
        /**
         * 部落等级
         */
        val clanLevel: Short,
        /**
         * 部落积分
         */
        val clanPoints: Int,
        /**
         * 部落对抗赛积分
         */
        val clanVersusPoints: Int,
        /**
         * 所需奖杯
         */
        val requiredTrophies: Short,
        /**
         * 所需对抗赛奖杯
         */
        val requiredVersusTrophies: Short,
        /**
         * 所需大本营等级
         */
        val requiredTownhallLevel: Short,
        val warFrequency: String,
        val warWinStreak: Int,
        /**
         * 部落战胜利
         */
        val warWins: Int,
        val isWarLogPublic: Boolean,
        val warLeague: Any,
        val members: Short,
        /**
         * 成员列表
         */
        val memberList: List<ClanMember>,
        /**
         * todo
         */
        val labels: Any,
        /**
         * todo 都城信息
         */
        val clanCapital: Any,
        /**
         * todo 语言信息
         */
        val chatLanguage: Any,
) {
    data class Location(
            val id: Long,
            val name: String,
            val isCountry: Boolean,
            val countryCode: String,
    )

}
