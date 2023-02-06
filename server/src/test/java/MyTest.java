import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.api.ClashApi;
import org.example.api.exception.ClashApiException;
import org.example.api.pojo.ClanCapital;
import org.example.api.pojo.ClanResult;
import org.example.api.pojo.ClanWarLeagueInfo;
import org.example.api.pojo.Member;
import org.junit.jupiter.api.Test;
import pojo.Mem;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyTest {

    private final ClashApi clashApi = new ClashApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImM3Y2Q4MGMwLTBkMWUtNDA4MC1hOTBmLTU2NzNhMjJkNDdiMyIsImlhdCI6MTY3NTY3MjgwNiwic3ViIjoiZGV2ZWxvcGVyL2U5YWUxNzQwLThiNjgtYzAzZS04ZjIzLTkzODAwNWU0YzA5OSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjE3MS4yMTcuMTI2LjE0NSIsIjE3MS4yMTYuODkuMTQ3Il0sInR5cGUiOiJjbGllbnQifV19.whPQ_0CTNFS4lgbDC3tsaj587zWT8UMLRC7J1k9rlntFcJ_QEk3v0Lw4Vq94jmePsPYTY9ZNenqVF6W4A3mJjw");

    @Test
    void test() {
        try {
            ClanResult<Member> members = clashApi.getClanMembers("#280Y0YGPJ");
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
        } catch (ClashApiException e) {
            System.err.println("API调用出错，原因：" + e.getMessage() + " 细节：" + e.getDetailMessage());
        }
    }

    @Test
    void test2() {
        try {
            ClanResult<Member> clanMembers = clashApi.getClanMembers("#280Y0YGPJ");
            System.out.println("clanMembers = " + clanMembers);
        } catch (ClashApiException e) {
            System.err.println("API调用出错，原因：" + e.getMessage() + " 细节：" + e.getDetailMessage());
        }
    }

    @Test
    void export() {
        try {

            ClanResult<ClanCapital> clanCapitalRaidSeason = clashApi.getClanCapitalRaidSeason("#280Y0YGPJ", 1);

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            String json = gson.toJson(clanCapitalRaidSeason);

            FileUtil.writeString(json, new File("raids/" + DateUtil.format(new Date(), "yyyyMMdd") + ".json"), Charset.defaultCharset());

        } catch (ClashApiException e) {
            System.err.println("API调用出错，原因：" + e.getMessage() + " 细节：" + e.getDetailMessage());
        }
    }

    @Test
    void warLeague() {
        ClanWarLeagueInfo leagueInformation = clashApi.getClanWarLeagueInformation("#280Y0YGPJ");
        System.out.println("leagueInformation = " + leagueInformation);
    }
}
