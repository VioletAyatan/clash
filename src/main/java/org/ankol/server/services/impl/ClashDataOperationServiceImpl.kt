package org.ankol.server.services.impl

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.collection.CollUtil
import cn.hutool.http.HttpException
import org.ankol.server.api.ClashApi
import org.ankol.server.api.entity.RaidSeason.RaidSeasonMember
import org.ankol.server.config.ClashProperties
import org.ankol.server.dao.ClanMemberRepository
import org.ankol.server.dao.RaidSeasonRepository
import org.ankol.server.dao.entity.ClanMemberEntity
import org.ankol.server.dao.entity.RaidSeasonEntity
import org.ankol.server.dao.entity.RaidSeasonEntity.AttackMember
import org.ankol.server.dao.entity.RaidSeasonEntity.AttackMember.AttackLog
import org.ankol.server.dao.entity.RaidSeasonEntity.UnAttackMember
import org.ankol.server.services.ClashDataOperationService
import org.ankol.server.tools.ClashUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class ClashDataOperationServiceImpl : ClashDataOperationService {
    private val log: Logger = LoggerFactory.getLogger(ClashDataOperationServiceImpl::class.java)

    @Autowired
    private lateinit var clashApi: ClashApi

    @Autowired
    private lateinit var raidSeasonRepository: RaidSeasonRepository

    @Autowired
    private lateinit var clanMemberRepository: ClanMemberRepository

    @Autowired
    private lateinit var clashProperties: ClashProperties

    override fun triggerRaidSeasonUpdate(): Boolean {
        try {
            val saveEntity = RaidSeasonEntity()
            //获取部落总成员数量
            val clanMembers = clashApi.clan.listMembers(clashProperties.clanTag).items
            //获取本赛季突袭周末数据
            val raidSeason = clashApi.clan.capitalRaidSeasons(clashProperties.clanTag, 1).items[0]
            //临时变量.
            val attackMemberHashMap: MutableMap<String, AttackMember> = ConcurrentHashMap()
            raidSeason.members.parallelStream()
                .forEach { member: RaidSeasonMember ->
                    attackMemberHashMap.putIfAbsent(
                        member.tag,
                        AttackMember()
                            .setTag(member.tag)
                            .setName(member.name)
                            .setAttacks(member.attacks)
                            .setAttackLimit(member.attackLimit)
                            .setBonusAttackLimit(member.bonusAttackLimit)
                            .setCapitalResourcesLooted(member.capitalResourcesLooted)
                    )
                }
            //获取未进攻成员数量
            clanMembers.removeIf { item -> attackMemberHashMap.containsKey(item.tag) }
            saveEntity.setNoAttackMembers(clanMembers.stream()
                .map { member -> UnAttackMember.of(member) }
                .toList()
            )

            //1️⃣第一重循环（都城进攻日志，对应本周打了多少个都城）
            for (i in raidSeason.attackLog.indices) {
                val raidSeasonAttackLog = raidSeason.attackLog[i]
                //2️⃣第二重循环（子城进攻信息，倒序遍历，即从子城到主城的顺序.）
                for (j in raidSeasonAttackLog.districts.size downTo 1) {
                    val districts = raidSeasonAttackLog.districts[j - 1]
                    //3️⃣第三重循环（获取每个子城的进攻详情，每个成员的进攻细节.）
                    for (k in districts.attacks.size downTo 1) {
                        //进攻信息
                        val attackInfo = districts.attacks[k - 1]
                        //获取进攻的成员信息.
                        val member = attackMemberHashMap[attackInfo.attacker.tag]
                        //这里判断此成员是不是初次记录进攻日志.
                        if (CollUtil.isEmpty(member!!.attackLogs)) {
                            val logLinkedList = LinkedList<AttackLog>()
                            logLinkedList.add(
                                AttackLog()
                                    .setSequence(1)
                                    .setStars(attackInfo.stars)
                                    .setDestructionPercent(attackInfo.destructionPercent)
                            )
                            member.setAttackLogs(logLinkedList)
                        } else {
                            //不是初次记录，获取最新一条记录
                            val latest = member.attackLogs[member.attackLogs.size - 1]
                            //新增下一条.
                            member.attackLogs
                                .add(
                                    AttackLog()
                                        .setSequence(latest.sequence + 1)
                                        .setStars(attackInfo.stars)
                                        .setDestructionPercent(attackInfo.destructionPercent)
                                )
                        }
                    }
                }
            }
            //赋值
            saveEntity.setId(ClashUtil.getRaidSeasonId(raidSeason.startTime))
                .setState(raidSeason.state)
                .setCreateTime(Date())
                .setDefensiveReward(raidSeason.defensiveReward)
                .setMembers(
                    attackMemberHashMap.values.stream().sorted { o1: AttackMember, o2: AttackMember ->
                        Integer.compare(
                            o2.capitalResourcesLooted,
                            o1.capitalResourcesLooted
                        )
                    }
                        .toList())
                .setRaidStartTime(
                    ClashUtil.formatterRaidSeasonTime(raidSeason.startTime).toString(TimeZone.getDefault())
                )
                .setRaidEndTime(ClashUtil.formatterRaidSeasonTime(raidSeason.endTime).toString(TimeZone.getDefault()))
                .setOffensiveReward(raidSeason.offensiveReward)
                .setDefensiveReward(raidSeason.defensiveReward)
                .setTotalAttacks(raidSeason.totalAttacks)
                .setRaidsCompleted(raidSeason.raidsCompleted)
                .setCapitalTotalLoot(raidSeason.capitalTotalLoot)
            //入库.
            raidSeasonRepository.save(saveEntity)
            return true
        } catch (e: HttpException) {
            log.error(e.message)
            return false
        }
    }

    override fun triggerClanMemberUpdate(): List<ClanMemberEntity> {
        val clanMembers = clashApi.clan.listMembers(clashProperties.clanTag).items

        val saveEntity = mutableListOf<ClanMemberEntity>()

        for (clanMember in clanMembers) {
            val playerDetail = clashApi.player.getPlayerDetail(clanMember.tag)
            val clanMemberEntity = ClanMemberEntity()
            //拷贝属性
            BeanUtil.copyProperties(clanMember, clanMemberEntity, true)

            clanMemberEntity
                .setTownHallLevel(playerDetail.townHallLevel)
                .setTownHallWeaponLevel(playerDetail.townHallWeaponLevel)

            saveEntity.add(clanMemberEntity)
        }

        clanMemberRepository.saveAll(saveEntity)

        return saveEntity
    }
}
