package org.example.server.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RaidSeasonSchedule {

//    @Autowired
//    private RaidSeasonRepository raidSeasonRepository;
//    @Autowired
//    private ClashApi clashApi;
//
//    /**
//     * 突袭周末定时任务.
//     * 设定每周二零点运行.
//     */
//    @Scheduled(cron = "0 0 0 ? * 3")
//    public void raidSeasonScheduling() {
//        log.info("Starting collected raid-season information in [{}]", DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
//        try {
//            ClanResult<ClanCapital> raidSeason = clashApi.getClanCapitalRaidSeason("#280Y0YGPJ", 1);
//        } catch (ClashApiException e) {
//            System.err.println("API接口调用出错，原因：" + e.getMessage() + " 详情：" + e.getDetailMessage());
//        }
//    }
}
