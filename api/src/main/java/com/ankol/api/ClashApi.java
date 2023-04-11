package com.ankol.api;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import com.ankol.api.entity.ItemResult;
import com.ankol.api.entity.RaidSeason;
import com.ankol.tools.GsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class ClashApi extends AbstractApi {
    public ClashApi(String authorization) {
        super(authorization);
    }

    public final ClanApi clan = new ClanApi(authorization);


}
