package org.example.server.config;

import org.example.api.ClashApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClashApiConfig {

    @Bean
    public ClashApi clashApi() {
        return new ClashApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6" +
                "IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJ" +
                "zdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6Ijc4Yzg" +
                "5Yzg0LTcxMDEtNGVlNC04M2FmLWJmNDZmNTk2NDVkMiIsImlhdCI6MTY2NzUyNDc" +
                "0OSwic3ViIjoiZGV2ZWxvcGVyL2U5YWUxNzQwLThiNjgtYzAzZS04ZjIzLTkzODA" +
                "wNWU0YzA5OSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJk" +
                "ZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbI" +
                "jE3MS4yMTcuMTMyLjQiLCIxNzEuMjE3LjEzMS4xNDAiLCIxMTguMTEyLjg4LjIxMy" +
                "JdLCJ0eXBlIjoiY2xpZW50In1dfQ.z3C7rmKSK9Dd15WDBFUXW3kiiTZZ9m4tvpGn" +
                "1hwWPtoxrZfwY8ZK7kzT2CzAVEu3vb_f5lcDiU87w5Qt5EHUVw");
    }
}
