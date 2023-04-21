package com.ankol.test;

import cn.hutool.core.io.FileUtil;
import com.ankol.api.ClanApi;
import com.ankol.api.ClashApi;
import com.ankol.api.entity.ClanMember;
import com.ankol.api.entity.ItemResult;
import com.ankol.api.entity.RaidSeason;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class ApiTest {
    private static final String CLAN_TAG = "#280Y0YGPJ";
    private final ClashApi clashApi = new ClashApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjU5YjQ1YTZiLWJkNzMtNGQ4MC1iZWE5LTcxOWJjZjRlOWNkMCIsImlhdCI6MTY4MTcxMjQwNywic3ViIjoiZGV2ZWxvcGVyL2U5YWUxNzQwLThiNjgtYzAzZS04ZjIzLTkzODAwNWU0YzA5OSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjE3MS4yMTYuODkuMTUzIl0sInR5cGUiOiJjbGllbnQifV19.Cpqjk6UXDFU64BeDIArL54OvkZTg-RU_e9uP8z8Xj_qknOC_5mILO_sE9o3leQV0QNklaHIJ6WIVE4FZbcG8AA");

    @Test
    public void clanApiTest() {
        ClanApi clanApi = clashApi.clan;
        String path = "C:\\Users\\Administrator\\Desktop\\Projects\\Clash\\api\\src\\test\\resources";

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        //成员列表
        ItemResult<ClanMember> itemResult = clanApi.listMembers(CLAN_TAG);
        FileUtil.writeString(
                gson.toJson(itemResult),
                new File(path + File.separator + "output" + File.separator + "clanMember.json"),
                StandardCharsets.UTF_8
        );
        //突袭周末
        ItemResult<RaidSeason> seasonItemResult = clanApi.capitalRaidSeasons(CLAN_TAG, 5);
        FileUtil.writeString(
                gson.toJson(seasonItemResult),
                new File(path + File.separator + "output" + File.separator + "raidSeason.json"),
                StandardCharsets.UTF_8
        );
    }
}
