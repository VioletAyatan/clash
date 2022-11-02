package org.example.server.config;

import org.example.api.ClashApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClashApiConfig {

    @Bean
    public ClashApi clashApi() {
        return new ClashApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6I" +
                "jI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJz" +
                "dXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjRhYzlm" +
                "MzJiLWI1MWItNDcyZC1iOGUzLWVjMDVmMGQzN2ZjZCIsImlhdCI6MTY2NzIwNzYz" +
                "Niwic3ViIjoiZGV2ZWxvcGVyL2U5YWUxNzQwLThiNjgtYzAzZS04ZjIzLTkzODAw" +
                "NWU0YzA5OSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJk" +
                "ZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpb" +
                "IjE3MS4yMTcuMTMxLjE0MCIsIjExOC4xMTIuODguMjEzIl0sInR5cGUiOiJjbGll" +
                "bnQifV19.gso6W0mPwHW7yNus7fTVsaDuARNQaSmApHvTk21i1OuxRdkShexUfKi" +
                "2jS1QXI2wH3MqVLyqRLW4VjMM5uk2rw");
    }
}
