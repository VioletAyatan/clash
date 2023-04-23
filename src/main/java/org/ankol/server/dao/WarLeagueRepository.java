package org.ankol.server.dao;

import org.ankol.server.dao.entity.WarLeagueGroupInfoDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarLeagueRepository extends CrudRepository<WarLeagueGroupInfoDao, String> {

    boolean existsBySeason(String season);

    WarLeagueGroupInfoDao findBySeason(String season);
}
