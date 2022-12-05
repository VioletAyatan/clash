package org.example.server.services;

import cn.hutool.core.collection.CollUtil;
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
        //检查数据库是否已经存在这条记录了.
        if (!raidSeasonRepository.existsByRaidStartTime(clanCapital.getStartTime())) {
            //数据结构转换.
            RaidSeasonDao raidSeasonDao = this.toRaidSeason(clanTag, clanCapital);
            //记录入库...
            raidSeasonRepository.save(raidSeasonDao);
        } else {
            log.info("Update raid season [{}]'s information.", clanCapital.getStartTime());
            RaidSeasonDao seasonDao = raidSeasonRepository.findByRaidStartTime(clanCapital.getStartTime());
            raidSeasonRepository.save(updateInformation(clanTag, seasonDao, clanCapital));
        }
        return true;
    }


    private RaidSeasonDao toRaidSeason(String clanTag, ClanCapital clanCapital) {
        List<RaidSeasonDao.UnAttackMember> allMembers = clashApi.getClanMembers(clanTag).getItems()
                .stream()
                .map(RaidSeasonDao.UnAttackMember::cover)
                .toList();//获取部落所有成员
        List<RaidSeasonDao.UnAttackMember> noAttackMembers = calcNoAttackMembers(allMembers, clanCapital); //计算未参与进攻的成员.
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
                .setAllMember(allMembers)
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
                .setNoAttackMembers(noAttackMembers);
    }

    private RaidSeasonDao updateInformation(String clanTag, RaidSeasonDao oldData, ClanCapital newData) {
        //更新的时候，如果不存在全体成员的话.
        if (CollUtil.isEmpty(oldData.getAllMember())) {
            List<RaidSeasonDao.UnAttackMember> allMember = clashApi.getClanMembers(clanTag)
                    .getItems().stream()
                    .map(RaidSeasonDao.UnAttackMember::cover)
                    .toList();
            oldData.setAllMember(allMember);
        }

        List<RaidSeasonDao.UnAttackMember> unAttackMembers = calcNoAttackMembers(oldData.getAllMember(), newData);
        return oldData
                .setState(newData.getState())
                .setRaidsCompleted(newData.getRaidsCompleted())
                .setOffensiveReward(newData.getOffensiveReward())
                .setDefensiveReward(newData.getDefensiveReward())
                .setCapitalTotalLoot(newData.getCapitalTotalLoot())
                .setTotalAttacks(newData.getTotalAttacks())
                .setRaidStartTime(newData.getStartTime())
                .setRaidEndTime(newData.getEndTime())
                .setMembers(newData.getMembers()
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
    private List<RaidSeasonDao.UnAttackMember> calcNoAttackMembers(List<RaidSeasonDao.UnAttackMember> allMember, ClanCapital clanCapital) {
        ArrayList<RaidSeasonDao.UnAttackMember> arrayList = new ArrayList<>(allMember);
        List<RaidSeasonDao.UnAttackMember> attackMembers = clanCapital.getMembers()
                .stream()
                .map(item -> new RaidSeasonDao.UnAttackMember()
                        .setName(item.getName())
                        .setTag(item.getTag()))
                .toList();

        arrayList.removeAll(attackMembers);
        return arrayList;
    }
}
