package org.example.server.listener;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.api.ClashApi;
import org.example.api.exception.ClashApiException;
import org.example.api.pojo.ClanCapital;
import org.example.api.pojo.ClanResult;
import org.example.server.dao.RaidSeasonRepository;
import org.example.server.dao.entity.RaidSeasonDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyListener implements InitializingBean {


    @Autowired
    private ClashApi clashApi;

    @Autowired
    private RaidSeasonRepository raidSeasonRepository;

    @Override
    public void afterPropertiesSet() {
        try {
            ClanResult<ClanCapital> raidSeason = clashApi.getClanCapitalRaidSeason("#280Y0YGPJ", 1);
            ClanCapital clanCapital = raidSeason.getItems().get(0);
            RaidSeasonDao raidSeasonDao = this.toRaidSeason(clanCapital);

            if (!raidSeasonRepository.existsByRaidStartTime(raidSeasonDao.getRaidStartTime())) {
                //记录入库...
                raidSeasonRepository.save(raidSeasonDao);
            } else {
                log.warn("The Raid log entry for {} is already existed!",raidSeasonDao.getRaidStartTime());
            }
        } catch (ClashApiException e) {
            System.err.println("API调用出错：" + e.getMessage() + " 详情：" + e.getDetailMessage());
        }
    }

    private RaidSeasonDao toRaidSeason(ClanCapital clanCapital) {
        return new RaidSeasonDao()
                .setId(IdUtil.simpleUUID())
                .setCreateTime(DateUtil.date())
                .setRaidsCompleted(clanCapital.getRaidsCompleted())
                .setOffensiveReward(clanCapital.getOffensiveReward())
                .setDefensiveReward(clanCapital.getDefensiveReward())
                .setCapitalTotalLoot(clanCapital.getCapitalTotalLoot())
                .setTotalAttacks(clanCapital.getTotalAttacks())
                .setRaidStartTime(clanCapital.getStartTime())
                .setRaidEndTime(clanCapital.getEndTime())
                .setMembers(
                        clanCapital.getMembers()
                                .stream()
                                .map(item -> new RaidSeasonDao.MembersBean()
                                        .setTag(item.getTag())
                                        .setName(item.getName())
                                        .setAttacks(item.getAttacks())
                                        .setAttackLimit(item.getAttackLimit())
                                        .setBonusAttackLimit(item.getBonusAttackLimit())
                                        .setCapitalResourcesLooted(item.getCapitalResourcesLooted())
                                )
                                .toList()
                );

    }
}
