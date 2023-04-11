package org.example.server.services;


import com.ankol.api.ClashApi;
import org.example.server.config.ClashProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClashService {
    @Autowired
    private ClashApi clashApi;
    @Autowired
    private ClashProperties clashProperties;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 更新氏族成员详细信息，自动进行数据库信息更新.
     *
     * @param clanTag 氏族标签
     * @return 是否成功
     */
    public boolean updateClanMembersInformation(String clanTag) {
        try {
            List<Object> objects = clashApi.clan.listMembers(clanTag).getItems()
                    .parallelStream()
                    .map(item -> clashApi.getPlayerInformation(item.getTag()))
                    .toList();
            mongoTemplate.remove(Player.class).all();
            mongoTemplate.insertAll(playerList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
