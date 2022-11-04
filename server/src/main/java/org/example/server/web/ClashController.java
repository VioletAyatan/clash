package org.example.server.web;

import org.example.server.dao.RaidSeasonRepository;
import org.example.server.dao.entity.RaidSeasonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClashController {
    @Autowired
    private RaidSeasonRepository raidSeasonRepository;

    @GetMapping("/raids")
    public Iterable<RaidSeasonDao> raids() {
        return raidSeasonRepository.findAll();
    }


}
