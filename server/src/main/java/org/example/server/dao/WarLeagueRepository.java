package org.example.server.dao;

import org.example.server.dao.entity.WarLeagueDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarLeagueRepository extends CrudRepository<WarLeagueDao, String> {

}
