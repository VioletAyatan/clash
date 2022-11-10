package org.example.server.web.controller;

import org.example.server.dao.RaidSeasonRepository;
import org.example.server.dao.WarLeagueRepository;
import org.example.server.dao.entity.RaidSeasonDao;
import org.example.server.dao.entity.WarLeagueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clash")
public class ClashController {
    @Autowired
    private RaidSeasonRepository raidSeasonRepository;
    @Autowired
    private WarLeagueRepository warLeagueRepository;

    @GetMapping("/raids")
    public Iterable<RaidSeasonDao> raids() {
        return raidSeasonRepository.findAll();
    }

    @GetMapping("/war_leagues")
    public Iterable<WarLeagueDao> warLeagues() {
        return warLeagueRepository.findAll();
    }

}
