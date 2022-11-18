package org.example.server.web.controller;

import org.example.server.services.RaidSeasonService;
import org.example.server.web.tools.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raidSeason")
public class RaidSeasonController {
    @Autowired
    private RaidSeasonService raidSeasonService;

    @PostMapping("/force_update")
    public ResultWrapper<Boolean> forceUpdate() {
        return ResultWrapper.success(raidSeasonService.updateRaidSeasonInformation("#280Y0YGPJ"));
    }
}
