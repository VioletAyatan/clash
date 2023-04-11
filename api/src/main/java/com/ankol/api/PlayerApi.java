package com.ankol.api;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import com.ankol.api.entity.ClanPlayer;
import com.ankol.tools.GsonUtil;

public class PlayerApi extends AbstractApi {
    public PlayerApi(String authorization) {
        super(authorization);
    }

    /**
     * 通过玩家标签获取单个玩家的信息。
     * 玩家标签可以在游戏中找到，也可以从氏族成员名单中找到。
     * 注意，玩家标签以哈希字符'#'开头，需要正确地进行URL编码才能在URL中发挥作用。
     * 例如，玩家标签'#2ABC'在URL中会变成'%232ABC'。
     *
     * @param playerTag 玩家标签
     * @return
     */
    public ClanPlayer getPlayerInfo(String playerTag) {
        HttpResponse response = doGet(BASE_URL + "/players/" + playerTag);
        if (response.isOk()) {
            return GsonUtil.fromJson(response.body(), ClanPlayer.class);
        }
        throw new HttpException("");
    }
}
