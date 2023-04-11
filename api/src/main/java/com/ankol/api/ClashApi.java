package com.ankol.api;

public class ClashApi extends AbstractApi {
    public ClashApi(String authorization) {
        super(authorization);
    }

    public final ClanApi clan = new ClanApi(authorization);


}
