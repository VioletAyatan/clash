package org.example.api.pojo

/**
 * 成员
 */
data class ClanMember(
    /**
     * 标签
     */
    val tag: String,
    /**
     * 成员名称
     */
    val name: String,
    /**
     * 成员职位
     */
    val role: String,
    /**
     * 经验等级
     */
    val expLevel: Short,

    val league: Any,
    /**
     * 奖杯
     */
    val trophies: Short,
    /**
     * 对抗赛奖杯
     */
    val versusTrophies: Short,
    val clanRank: Short,
    val previousClanRank: Short,
    /**
     * 捐兵数
     */
    val donations: Int,
    /**
     * 收兵数
     */
    val donationsReceived: Short,
)
