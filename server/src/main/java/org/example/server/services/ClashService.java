package org.example.server.services;

import org.example.api.ClashApi;
import org.example.api.pojo.ClanResult;
import org.example.api.pojo.Member;
import org.example.server.config.ClashProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClashService {
    @Autowired
    private ClashApi clashApi;
    @Autowired
    private ClashProperties clashProperties;

    public void getMembersInformation() {
        ClanResult<Member> members = clashApi.getClanMembers(clashProperties.getClanTag());

    }
}
