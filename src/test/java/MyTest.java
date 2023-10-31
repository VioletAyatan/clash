import org.ankol.server.api.ClashApi;
import org.ankol.server.api.entity.ClanMember;
import org.ankol.server.api.entity.ItemResult;
import org.ankol.server.api.entity.RaidSeason;
import org.junit.jupiter.api.Test;
import pojo.Mem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MyTest {

    private static final String CLAN_TAG = "#280Y0YGPJ";
    private final ClashApi clashApi = new ClashApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjU5YjQ1YTZiLWJkNzMtNGQ4MC1iZWE5LTcxOWJjZjRlOWNkMCIsImlhdCI6MTY4MTcxMjQwNywic3ViIjoiZGV2ZWxvcGVyL2U5YWUxNzQwLThiNjgtYzAzZS04ZjIzLTkzODAwNWU0YzA5OSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjE3MS4yMTYuODkuMTUzIl0sInR5cGUiOiJjbGllbnQifV19.Cpqjk6UXDFU64BeDIArL54OvkZTg-RU_e9uP8z8Xj_qknOC_5mILO_sE9o3leQV0QNklaHIJ6WIVE4FZbcG8AA");
    @Test
    void test() {
        try {
            ItemResult<ClanMember> members = clashApi.clan.listMembers(CLAN_TAG);
            List<Mem> member = new ArrayList<>(members.getItems()
                    .stream()
                    .map(Mem::create)
                    .toList());

            System.out.println("部落总成员：" + member.size());

            ItemResult<RaidSeason> raidSeason = clashApi.clan.capitalRaidSeasons("#280Y0YGPJ", 1);

            List<Mem> raidMembers = raidSeason.
                    getItems()
                    .get(0)
                    .getMembers()
                    .stream()
                    .map(Mem::create)
                    .toList();

            System.out.println("突袭周末参赛成员：" + raidMembers.size());

            member.removeAll(raidMembers);

            System.out.println("未参与突袭的成员：" + member);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void average() {
        RaidSeason raidSeason = clashApi.clan.capitalRaidSeasons(CLAN_TAG, 1)
                .getItems().get(0);

        IntStream allResourceLooted = raidSeason.getMembers()
                .stream()
                .mapToInt(RaidSeason.RaidSeasonMember::getCapitalResourcesLooted);

        allResourceLooted.average()
                .ifPresent(value -> {
                    long round = Math.round(value);
                    System.out.println("平均值 = " + round);
                });
    }
}
