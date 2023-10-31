package org.ankol.server.dao;

import org.ankol.server.dao.entity.ClanMemberEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClanMemberRepository extends ListCrudRepository<ClanMemberEntity, String> {
}
