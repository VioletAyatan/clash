package org.ankol.server.api;

public class ClashApi extends AbstractApi {
    public ClashApi(String authorization) {
        super(authorization);
    }

    public final ClanApi clan = new ClanApi(authorization);


}
