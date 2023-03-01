package org.example.server.web.controller;

import org.example.api.ClashApi;
import org.example.api.pojo.Clan;
import org.example.api.pojo.Player;
import org.example.server.config.ClashProperties;
import org.example.server.dao.RaidSeasonRepository;
import org.example.server.dao.WarLeagueRepository;
import org.example.server.dao.entity.RaidSeasonDao;
import org.example.server.dao.entity.WarLeagueGroupInfoDao;
import org.example.server.services.ClashService;
import org.example.server.web.tools.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clash")
public class ClashController {
    @Autowired
    private RaidSeasonRepository raidSeasonRepository;
    @Autowired
    private WarLeagueRepository warLeagueRepository;
    @Autowired
    private ClashProperties clashProperties;
    @Autowired
    private ClashService clashService;
    @Autowired
    private ClashApi clashApi;

    @GetMapping("/raids")
    public Iterable<RaidSeasonDao> raids() {
        return raidSeasonRepository.findAll();
    }

    @GetMapping("/war_leagues")
    public Iterable<WarLeagueGroupInfoDao> warLeagues() {
        return warLeagueRepository.findAll();
    }


    @GetMapping("/information")
    public ResultWrapper<Clan> getInformation() {
        return ResultWrapper.success(clashApi.getClanInformation(clashProperties.getClanTag()));
    }

    /**
     * 获取部落成员信息
     *
     * @return
     */
    @GetMapping("/members")
    public ResultWrapper<List<Player>> members() {
        return ResultWrapper.success(clashService.getMembersInformation());
    }
}
