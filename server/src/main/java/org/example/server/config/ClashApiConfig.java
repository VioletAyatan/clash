package org.example.server.config;

import org.example.api.ClashApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClashApiConfig {

    @Bean
    public ClashApi clashApi() {
        return new ClashApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjczM2QwNDg0LTlhZDItNDAzMy05Yzk2LTRmOTM3NWRkY2FkNyIsImlhdCI6MTY2Nzg4ODM1MSwic3ViIjoiZGV2ZWxvcGVyL2U5YWUxNzQwLThiNjgtYzAzZS04ZjIzLTkzODAwNWU0YzA5OSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjIxOC44OS4yMzcuMTI2IiwiMTE4LjExMy4yMjkuMjYiXSwidHlwZSI6ImNsaWVudCJ9XX0.hHgTzfM98kdURcDizvA2a00kW0Wf2YQY-R3Maesi_TFgXAKtrPSPENugUKXRaM6rlnWRE0pFSI_ZUiB575lcHA");
    }
}
