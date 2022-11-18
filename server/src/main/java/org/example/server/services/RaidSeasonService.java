package org.example.server.services;

import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.ClashApi;
import org.example.api.exception.ClashApiException;
import org.example.api.pojo.ClanCapital;
import org.example.api.pojo.ClanResult;
import org.example.server.dao.RaidSeasonRepository;
import org.example.server.dao.entity.RaidSeasonDao;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RaidSeasonService {

    private final ClashApi clashApi;
    private final RaidSeasonRepository raidSeasonRepository;

    public boolean updateRaidSeasonInformation(String clanTag) throws ClashApiException {
        ClanResult<ClanCapital> raidSeason = clashApi.getClanCapitalRaidSeason(clanTag, 1);
        ClanCapital clanCapital = raidSeason.getItems().get(0);
        //数据结构转换.
        RaidSeasonDao raidSeasonDao = this.toRaidSeason(clanTag, clanCapital);
        //检查数据库是否已经存在这条记录了.
        if (!raidSeasonRepository.existsByRaidStartTime(raidSeasonDao.getRaidStartTime())) {
            //记录入库...
            raidSeasonRepository.save(raidSeasonDao);
        } else if (clanCapital.getState().equalsIgnoreCase("ongoing")) {
            log.info("Update raid season [{}]'s information.", clanCapital.getStartTime());
            RaidSeasonDao seasonDao = raidSeasonRepository.findByRaidStartTime(clanCapital.getStartTime());
            raidSeasonRepository.save(updateInformation(clanTag, seasonDao, clanCapital));
        }
        return true;
    }


    private RaidSeasonDao toRaidSeason(String clanTag, ClanCapital clanCapital) {
        List<RaidSeasonDao.UnAttackMember> allMembers = calcNoAttackMembers(clanTag, clanCapital);
        // 设值，准备入库
        return new RaidSeasonDao()
                .setCreateTime(DateUtil.date())
                .setState(clanCapital.getState())
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

    private RaidSeasonDao updateInformation(String clanTag, RaidSeasonDao oldData, ClanCapital clanCapital) {
        List<RaidSeasonDao.UnAttackMember> unAttackMembers = calcNoAttackMembers(clanTag, clanCapital);
        return oldData
                .setState(clanCapital.getState())
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
                .setNoAttackMembers(unAttackMembers);
    }


    @NotNull
    private List<RaidSeasonDao.UnAttackMember> calcNoAttackMembers(String clanTag, ClanCapital clanCapital) {
        //获取未进攻的成员信息👇
        List<RaidSeasonDao.UnAttackMember> allMembers = new ArrayList<>(clashApi.getClanMembers(clanTag)
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
        return allMembers;
    }
}
