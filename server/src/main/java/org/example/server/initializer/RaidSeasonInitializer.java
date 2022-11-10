package org.example.server.initializer;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.ClashApi;
import org.example.api.exception.ClashApiException;
import org.example.api.pojo.ClanCapital;
import org.example.api.pojo.ClanResult;
import org.example.server.dao.RaidSeasonRepository;
import org.example.server.dao.entity.RaidSeasonDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RaidSeasonInitializer implements InitializingBean {
    private final ClashApi clashApi;
    private final RaidSeasonRepository raidSeasonRepository;

    @Override
    public void afterPropertiesSet() {
        try {
            ClanResult<ClanCapital> raidSeason = clashApi.getClanCapitalRaidSeason("#280Y0YGPJ", 1);
            ClanCapital clanCapital = raidSeason.getItems().get(0);
            //数据结构转换.
            RaidSeasonDao raidSeasonDao = this.toRaidSeason(clanCapital);
            //检查数据库是否已经存在这条记录了.
            if (!raidSeasonRepository.existsByRaidStartTime(raidSeasonDao.getRaidStartTime())) {
                //记录入库...
                raidSeasonRepository.save(raidSeasonDao);
            } else {
                log.warn("The Raid log entry for {} is already existed!", raidSeasonDao.getRaidStartTime());
            }
        } catch (ClashApiException e) {
            System.err.println("API调用出错：" + e.getMessage() + " 详情：" + e.getDetailMessage());
        }
    }

    private RaidSeasonDao toRaidSeason(ClanCapital clanCapital) {
        //获取未进攻的成员信息👇
        List<RaidSeasonDao.UnAttackMember> allMembers = new ArrayList<>(clashApi.getClanMembers("#280Y0YGPJ")
                .getItems()
                .stream()
                .map(item -> new RaidSeasonDao.UnAttackMember()
                        .setName(item.getName())
                        .setTag(item.getTag()))
                .toList());

        List<RaidSeasonDao.UnAttackMember> attackMembers = clanCapital.getMembers()
                .stream()
                .map(item -> new RaidSeasonDao.UnAttackMember()
                        .setName(item.getName())
                        .setTag(item.getTag()))
                .toList();

        allMembers.removeAll(attackMembers);
        // 设值，准备入库
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
                .setMembers(clanCapital.getMembers()
                        .stream()
                        .map(item -> new RaidSeasonDao.MembersBean()
                                .setTag(item.getTag())
                                .setName(item.getName())
                                .setAttacks(item.getAttacks())
                                .setAttackLimit(item.getAttackLimit())
                                .setBonusAttackLimit(item.getBonusAttackLimit())
                                .setCapitalResourcesLooted(item.getCapitalResourcesLooted()))
                        .toList()
                )
                .setNoAttackMembers(allMembers);
    }
}
