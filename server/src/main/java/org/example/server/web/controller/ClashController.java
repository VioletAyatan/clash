package org.example.server.web.controller;

import org.example.server.dao.entity.RaidSeasonEntity;
import org.example.server.services.ClashServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clash")
public class ClashController {
    @Autowired
    private ClashServices clashServices;

    @GetMapping("/raids")
    public List<RaidSeasonEntity> raids() {
        return clashServices.listRaidSeason();
    }

//    @GetMapping("/war_leagues")
//    public Iterable<WarLeagueGroupInfoDao> warLeagues() {
//        return warLeagueRepository.findAll();
//    }
}
