package org.ankol.server.schedule

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.date.DatePattern
import cn.hutool.core.date.DateUtil
import cn.hutool.http.HttpException
import lombok.extern.slf4j.Slf4j
import org.ankol.server.api.ClashApi
import org.ankol.server.config.ClashProperties
import org.ankol.server.dao.entity.ClanWarEntity
import org.ankol.server.services.ClashDataOperationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Slf4j
@Component
class RaidSeasonSchedule {
    private val log: Logger = LoggerFactory.getLogger(RaidSeasonSchedule::class.java)

    @Autowired
    private lateinit var clashDataOperationService: ClashDataOperationService

    @Autowired
    private lateinit var clashApi: ClashApi

    @Autowired
    private lateinit var clashProperties: ClashProperties

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    /**
     * 突袭周末定时任务.
     * 设定每周二零点运行.
     */
    @Scheduled(cron = "0 0 0 ? * 3")
    fun raidSeasonScheduling() {
        log.info("Starting collected raid-season information in ${DateUtil.date().toStringDefaultTimeZone()}")
        val success = clashDataOperationService.triggerRaidSeasonUpdate()
        if (success) {
            log.info("部落突袭周末信息更新完成...")
        } else {
            log.info("部落突袭周末信息更新失败...")
        }
    }

    @Scheduled(cron = "0 0 0 * *")
    fun setClashDataOperationService() {
        try {
            clashDataOperationService.triggerClanMemberUpdate()
        } catch (e: RuntimeException) {
            log.error("部落成员信息更新失败！", e)
        }
    }

    @Scheduled(fixedDelay = 1L, timeUnit = TimeUnit.DAYS)
    fun updateMembers() {
        try {
            val clanWar = clashApi.clan.currentWar(clashProperties.clanTag)
            val warEntity = ClanWarEntity()
            BeanUtil.copyProperties(clanWar, warEntity)
        } catch (e: HttpException) {
            throw RuntimeException(e)
        }
    }
}
