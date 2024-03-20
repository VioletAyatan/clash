package org.ankol.server.schedule;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.ankol.server.api.ClashApi;
import org.ankol.server.api.entity.ClanWar;
import org.ankol.server.config.ClashProperties;
import org.ankol.server.dao.entity.ClanWarEntity;
import org.ankol.server.services.ClashDataOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RaidSeasonSchedule {

    @Autowired
    private ClashDataOperationService clashDataOperationService;
    @Autowired
    private ClashApi clashApi;
    @Autowired
    private ClashProperties clashProperties;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 突袭周末定时任务.
     * 设定每周二零点运行.
     */
    @Scheduled(cron = "0 0 0 ? * 3")
    public void raidSeasonScheduling() {
        log.info("Starting collected raid-season information in [{}]", DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        boolean success = clashDataOperationService.triggerRaidSeasonUpdate();
        if (success) {
            log.info("部落突袭周末信息更新完成...");
        } else {
            log.info("部落突袭周末信息更新失败...");
        }
    }

    @Scheduled(cron = "0 0 0 * *")
    public void setClashDataOperationService() {
        try {
            clashDataOperationService.triggerClanMemberUpdate();
        } catch (RuntimeException e) {
            log.error("部落成员信息更新失败！", e);
        }
    }

    @Scheduled(fixedDelay = 1L, timeUnit = TimeUnit.DAYS)
    public void updateMembers() {
        try {
            ClanWar clanWar = clashApi.clan.currentWar(clashProperties.getClanTag());
            ClanWarEntity warEntity = new ClanWarEntity();
            BeanUtil.copyProperties(clanWar, warEntity);
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }
}
