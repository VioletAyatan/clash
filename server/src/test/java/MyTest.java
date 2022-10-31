import org.example.api.ClashApi;
import org.example.api.pojo.ClanCapital;
import org.example.api.pojo.ClanResult;
import org.junit.jupiter.api.Test;

public class MyTest {

    @Test
    void test() {
        ClashApi clashApi = new ClashApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImYzY2ZmYjEzLWUwZWUtNDM5ZC1hZWI5LTg1MzEzMjk1ZTYwNyIsImlhdCI6MTY2NzIwNDA2OCwic3ViIjoiZGV2ZWxvcGVyL2U5YWUxNzQwLThiNjgtYzAzZS04ZjIzLTkzODAwNWU0YzA5OSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjExOC4xMTIuODguMjEzIl0sInR5cGUiOiJjbGllbnQifV19.Xo5V-zS0g9y9NqB0lz5FaWzgR86FLO4I1xOL60W_outCII9brhM7ki5RqqqKNscSLNISg1iMtjRovy575jjTDA");

        ClanResult<ClanCapital> clanCapitalRaidSeason = clashApi.getClanCapitalRaidSeason("#280Y0YGPJ", 1);
    }
}
