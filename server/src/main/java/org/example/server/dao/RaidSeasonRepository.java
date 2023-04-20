package org.example.server.dao;

import org.example.server.dao.entity.RaidSeasonEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaidSeasonRepository extends ListCrudRepository<RaidSeasonEntity, String> {

    boolean existsByRaidStartTime(String raidStartTime);

    RaidSeasonEntity findByRaidStartTime(String raidStartTime);

    Optional<RaidSeasonEntity> findFirstByOrderByCreateTimeDesc();
}
