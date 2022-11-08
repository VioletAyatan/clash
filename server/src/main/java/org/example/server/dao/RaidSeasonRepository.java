package org.example.server.dao;

import org.example.server.dao.entity.RaidSeasonDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaidSeasonRepository extends CrudRepository<RaidSeasonDao, String> {

    boolean existsByRaidStartTime(String raidStartTime);
}
