package org.ankol.server.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ankol.server.api.entity.ClanWar;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("clanWar")
@EqualsAndHashCode(callSuper = true)
public class ClanWarEntity extends ClanWar {
    @MongoId
    private String mongoId;
}
