package org.example.server.schedule;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.server.services.ClashDataOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RaidSeasonSchedule {

    @Autowired
    private ClashDataOperationService clashDataOperationService;

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
        }else {
            log.info("部落突袭周末信息更新失败...");
        }
    }


}
