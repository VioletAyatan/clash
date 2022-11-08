import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.api.ClashApi;
import org.example.api.exception.ClashApiException;
import org.example.api.pojo.ClanCapital;
import org.example.api.pojo.ClanResult;
import org.example.api.pojo.ClashErrorResponse;
import org.example.api.pojo.Member;
import org.example.api.tools.GsonUtil;
import org.junit.jupiter.api.Test;
import pojo.Mem;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyTest {

    private final ClashApi clashApi = new ClashApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTA" +
            "wMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6" +
            "ImQ4NzJlMzc0LTg4NjUtNDc3NS04OTk3LTkwNTI5NDJlNDA4OSIsImlhdCI6MTY2Nzg3ODE0Mywic3ViIjoiZGV2ZWxvcGVyL2U5YWUxN" +
            "zQwLThiNjgtYzAzZS04ZjIzLTkzODAwNWU0YzA5OSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZX" +
            "Ivc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjExOC4xMTMuMjI5LjI2Il0sInR5cGUiOiJjbGllbnQifV19.7j" +
            "qSTN2lCsWEw87WXZQEpQNxoZ6jEaq6DDHjVh7NBQgRYij_XODRScFxuCxgkiABN7uYVsaIEceahZ3pp2zSoQ");

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
}
