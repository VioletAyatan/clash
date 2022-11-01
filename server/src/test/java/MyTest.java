import org.example.api.ClashApi;
import org.example.api.pojo.ClanCapital;
import org.example.api.pojo.ClanResult;
import org.example.api.pojo.Member;
import org.junit.jupiter.api.Test;
import pojo.Mem;

import java.util.ArrayList;
import java.util.List;

public class MyTest {

    @Test
    void test() {
        ClashApi clashApi = new ClashApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjRhYzlmMzJiLWI1MWItNDcyZC1iOGUzLWVjMDVmMGQzN2ZjZCIsImlhdCI6MTY2NzIwNzYzNiwic3ViIjoiZGV2ZWxvcGVyL2U5YWUxNzQwLThiNjgtYzAzZS04ZjIzLTkzODAwNWU0YzA5OSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjE3MS4yMTcuMTMxLjE0MCIsIjExOC4xMTIuODguMjEzIl0sInR5cGUiOiJjbGllbnQifV19.gso6W0mPwHW7yNus7fTVsaDuARNQaSmApHvTk21i1OuxRdkShexUfKi2jS1QXI2wH3MqVLyqRLW4VjMM5uk2rw");
        ClanResult<Member> members = clashApi.getClanMembers("#280Y0YGPJ");
        assert members != null;
        List<Mem> member = new ArrayList<>(members.getItems()
                .stream()
                .map(Mem::create)
                .toList());

        System.out.println("部落总成员：" + member.size());

        ClanResult<ClanCapital> raidSeason = clashApi.getClanCapitalRaidSeason("#280Y0YGPJ", 1);
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

    }
}
