package org.ankol.server.services.impl;

import cn.hutool.core.bean.BeanUtil;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            //获取部落总成员数量
            List<ClanMember> clanMembers = clashApi.clan.listMembers(clashProperties.getClanTag()).getItems();
            //获取本赛季突袭周末数据
            RaidSeason raidSeason = clashApi.clan.capitalRaidSeasons(clashProperties.getClanTag(), 1)
                    .getItems()
                    .get(0);
            //生成数据库对象
            RaidSeasonEntity raidSeasonEntity = RaidSeasonEntity.convertFrom(raidSeason, clanMembers);
            //入库
            raidSeasonRepository.save(raidSeasonEntity);
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
