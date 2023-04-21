package org.example.server.services.impl;

import cn.hutool.http.HttpException;
import com.ankol.api.ClashApi;
import com.ankol.api.entity.ClanMember;
import com.ankol.api.entity.RaidSeason;
import lombok.extern.slf4j.Slf4j;
import org.example.server.config.ClashProperties;
import org.example.server.dao.RaidSeasonRepository;
import org.example.server.dao.entity.RaidSeasonEntity;
import org.example.server.services.ClashDataOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClashDataOperationServiceImpl implements ClashDataOperationService {
    @Autowired
    private ClashApi clashApi;
    @Autowired
    private RaidSeasonRepository raidSeasonRepository;
    @Autowired
    private ClashProperties clashProperties;

    @Override
    public void triggerRaidSeasonUpdate() {
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
        } catch (HttpException e) {
            log.error(e.getMessage());
        }
    }
}