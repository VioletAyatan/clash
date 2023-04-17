package org.example.server.dao;

import org.example.server.dao.entity.RaidSeasonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaidSeasonRepository extends CrudRepository<RaidSeasonEntity, String> {

    boolean existsByRaidStartTime(String raidStartTime);

    RaidSeasonEntity findByRaidStartTime(String raidStartTime);
}
