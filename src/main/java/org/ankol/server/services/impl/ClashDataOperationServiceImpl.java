package org.ankol.server.services.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ankol.server.api.ClashApi;
import org.ankol.server.api.entity.ClanMember;
import org.ankol.server.api.entity.ClanPlayer;
import org.ankol.server.api.entity.RaidSeason;
import org.ankol.server.config.ClashProperties;
import org.ankol.server.dao.ClanMemberRepository;
import org.ankol.server.dao.RaidSeasonRepository;
import org.ankol.server.dao.entity.ClanMemberEntity;
import org.ankol.server.dao.entity.RaidSeasonEntity;
import org.ankol.server.services.ClashDataOperationService;
import org.ankol.server.tools.ClashUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClashDataOperationServiceImpl implements ClashDataOperationService {
    private final ClashApi clashApi;
    private final RaidSeasonRepository raidSeasonRepository;
    private final ClanMemberRepository clanMemberRepository;
    private final ClashProperties clashProperties;

    @Override
    public boolean triggerRaidSeasonUpdate() {
        try {
            RaidSeasonEntity saveEntity = new RaidSeasonEntity();
            //获取部落总成员数量
//            List<ClanMember> clanMembers = clashApi.clan.listMembers(clashProperties.getClanTag()).getItems();
            //获取本赛季突袭周末数据
            RaidSeason raidSeason = clashApi.clan.capitalRaidSeasons(clashProperties.getClanTag(), 1)
                    .getItems()
                    .get(0);

            Map<String, RaidSeasonEntity.AttackMember> attackMemberHashMap = new ConcurrentHashMap<>();
            raidSeason.getMembers().parallelStream()
                    .forEach(member -> {
                        attackMemberHashMap.putIfAbsent(member.getTag(),
                                new RaidSeasonEntity.AttackMember()
                                        .setTag(member.getTag())
                                        .setName(member.getName())
                                        .setAttacks(member.getAttacks())
                                        .setAttackLimit(member.getAttackLimit())
                                        .setBonusAttackLimit(member.getBonusAttackLimit())
                                        .setCapitalResourcesLooted(member.getCapitalResourcesLooted())
                        );
                    });

            //1️⃣第一重循环（都城进攻日志，对应本周打了多少个都城）
            for (int i = 0; i < raidSeason.getAttackLog().size(); i++) {
                RaidSeason.RaidSeasonAttackLog raidSeasonAttackLog = raidSeason.getAttackLog().get(i);
                //2️⃣第二重循环（子城进攻信息，倒序遍历，即从子城到主城的顺序.）
                for (int j = raidSeasonAttackLog.getDistricts().size(); j > 0; j--) {
                    RaidSeason.RaidSeasonAttackLog.DistrictsDTO districts = raidSeasonAttackLog.getDistricts().get(j - 1);
                    //3️⃣第三重循环（获取每个子城的进攻详情，每个成员的进攻细节.）
                    for (int k = districts.getAttacks().size(); k > 0; k--) {
                        //进攻信息
                        RaidSeason.RaidSeasonAttackLog.DistrictsDTO.AttacksDTO attackInfo = districts.getAttacks().get(k - 1);
                        //获取进攻的成员信息.
                        RaidSeasonEntity.AttackMember member = attackMemberHashMap.get(attackInfo.getAttacker().getTag());
                        //这里判断此成员是不是初次记录进攻日志.
                        if (CollUtil.isEmpty(member.getAttackLogs())) {
                            LinkedList<RaidSeasonEntity.AttackMember.AttackLog> logLinkedList = new LinkedList<>();
                            logLinkedList.add(new RaidSeasonEntity.AttackMember.AttackLog()
                                    .setSequence(1)
                                    .setStars(attackInfo.getStars())
                                    .setDestructionPercent(attackInfo.getDestructionPercent())
                            );
                            member.setAttackLogs(logLinkedList);
                        } else {
                            //不是初次记录，获取最新一条记录
                            RaidSeasonEntity.AttackMember.AttackLog latest = member.getAttackLogs().get(member.getAttackLogs().size() - 1);
                            //新增下一条.
                            member.getAttackLogs()
                                    .add(new RaidSeasonEntity.AttackMember.AttackLog()
                                            .setSequence(latest.getSequence() + 1)
                                            .setStars(attackInfo.getStars())
                                            .setDestructionPercent(attackInfo.getDestructionPercent())
                                    );
                        }
                    }
                }
            }
            //赋值
            saveEntity.setId(ClashUtil.getRaidSeasonId(raidSeason.getStartTime()))
                    .setState(raidSeason.getState())
                    .setCreateTime(new Date())
                    .setDefensiveReward(raidSeason.getDefensiveReward())
                    .setMembers(attackMemberHashMap.values().stream().sorted((o1, o2) -> {
                        return Integer.compare(o2.getCapitalResourcesLooted(), o1.getCapitalResourcesLooted());
                    }).toList())
                    .setRaidStartTime(ClashUtil.formatterRaidSeasonTime(raidSeason.getStartTime()).toString(TimeZone.getDefault()))
                    .setRaidEndTime(ClashUtil.formatterRaidSeasonTime(raidSeason.getEndTime()).toString(TimeZone.getDefault()))
                    .setOffensiveReward(raidSeason.getOffensiveReward())
                    .setDefensiveReward(raidSeason.getDefensiveReward())
                    .setTotalAttacks(raidSeason.getTotalAttacks())
                    .setRaidsCompleted(raidSeason.getRaidsCompleted())
                    .setCapitalTotalLoot(raidSeason.getCapitalTotalLoot());
            //入库.
            raidSeasonRepository.save(saveEntity);
            return true;
        } catch (HttpException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<ClanMemberEntity> triggerClanMemberUpdate() {
        List<ClanMember> clanMembers = clashApi.clan.listMembers(clashProperties.getClanTag()).getItems();

        ArrayList<ClanMemberEntity> saveEntity = new ArrayList<>(clanMembers.size());

        for (ClanMember clanMember : clanMembers) {
            ClanPlayer playerDetail = clashApi.player.getPlayerDetail(clanMember.getTag());
            ClanMemberEntity clanMemberEntity = new ClanMemberEntity();
            //拷贝属性
            BeanUtil.copyProperties(clanMember, clanMemberEntity, true);

            clanMemberEntity
                    .setTownHallLevel(playerDetail.getTownHallLevel())
                    .setTownHallWeaponLevel(playerDetail.getTownHallWeaponLevel());

            saveEntity.add(clanMemberEntity);
        }

        clanMemberRepository.saveAll(saveEntity);

        return saveEntity;
    }
}
