package org.example.api.pojo

data class ClanCapital(
    val state: String,
    val startTime: String,
    val endTime: String,
    val capitalTotalLoot: Long,
    val raidsCompleted: Long,
    val totalAttacks: Long,
    val enemyDistrictsDestroyed: Long,
    val offensiveReward: Long,
    val defensiveReward: Long,
    val members: List<ClanCapitalMember>,
) {
    data class ClanCapitalMember(
        val tag: String,
        val name: String,
        val attacks: Int,
        val attackLimit: Short,
        val bonusAttackLimit: Short,
        val capitalResourcesLooted: Int,
    )
}
