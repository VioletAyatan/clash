package org.ankol.server.api;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import com.google.gson.reflect.TypeToken;
import org.ankol.server.api.entity.*;
import org.ankol.server.tools.GsonUtil;

public class ClashApi extends AbstractApi {
    public ClashApi(String authorization) {
        super(authorization);
    }

    public final ClanApi clan = new ClanApi(authorization);
    public final Player player = new Player(authorization);

    public static class ClanApi extends AbstractApi {
        public ClanApi(String authorization) {
            super(authorization);
        }

        /**
         * 通过部落标签获取单个氏族的信息，部落标签可以通过部落搜索找到。
         * <p>请注意，部落标签以哈希字符'#'开头，需要正确地进行URL编码才能在URL中发挥作用。</p>
         * <p>例如，部落标签'#2ABC'在URL中会变成'%232ABC'。</p>
         *
         * @param clanTag 部落标签
         * @return 单个部落信息
         */
        public Clan clan(String clanTag) {
            HttpResponse response = super.doGet(BASE_URL + "/clans/" + clanTag);
            if (response.isOk()) {
                return GsonUtil.fromJson(response.body(), Clan.class);
            }
            throw new HttpException("Error response status [{}] Message: {}", response.getStatus(), response.body());
        }

        /**
         * 获取部落当前的部落战信息
         *
         * @param clanTag 部落标签
         */
        public void currentWar(String clanTag) {

        }

        /**
         * 获取部落突袭周末数据
         *
         * @param clanTag 部落标签.
         * @param limit   响应中的返回项目限制数量.
         * @param after   Return only items that occur after this marker.
         *                Before marker can be found from the response, inside the 'paging' property.
         *                Note that only after or before can be specified for a request, not both.
         * @param before  Return only items that occur before this marker.
         *                Before marker can be found from the response, inside the 'paging' property.
         *                Note that only after or before can be specified for a request, not both.
         * @return {@link ItemResult <RaidSeason>}
         */
        public ItemResult<RaidSeason> capitalRaidSeasons(String clanTag, Integer limit, String after, String before) throws HttpException {
            HttpResponse response = super.doGet(BASE_URL + "/clans/" + clanTag + "/capitalraidseasons",
                    MapUtil.<String, Object>builder("limit", limit)
                            .put("after", after)
                            .put("before", before)
                            .build()
            );
            if (response.isOk()) {
                return GsonUtil.fromJson(response.body(), new TypeToken<>() {
                });
            }
            throw new HttpException("Error response status [{}] Message: {}", response.getStatus(), response.body());
        }

        /**
         * 获取部落突袭周末数据
         *
         * @param clanTag 部落标签.
         * @return {@link ItemResult < RaidSeason >}
         */
        public ItemResult<RaidSeason> capitalRaidSeasons(String clanTag) throws HttpException {
            return this.capitalRaidSeasons(clanTag, null, null, null);
        }

        /**
         * 获取部落突袭周末数据
         *
         * @param clanTag 部落标签.
         * @param limit   响应中的返回项目限制数量.
         * @return {@link ItemResult < RaidSeason >}
         */
        public ItemResult<RaidSeason> capitalRaidSeasons(String clanTag, Integer limit) throws HttpException {
            return this.capitalRaidSeasons(clanTag, limit, null, null);
        }

        /**
         * 列举部落成员
         *
         * @param clanTag 部落标签
         * @param limit   响应中的返回项目限制数量.
         * @param after   Return only items that occur after this marker.
         *                Before marker can be found from the response, inside the 'paging' property.
         *                Note that only after or before can be specified for a request, not both.
         * @param before  Return only items that occur before this marker.
         *                Before marker can be found from the response, inside the 'paging' property.
         *                Note that only after or before can be specified for a request, not both.
         * @return {@link ItemResult <  ClanMember  >}
         */
        public ItemResult<ClanMember> listMembers(String clanTag, Integer limit, String after, String before) {
            try (HttpResponse response = super.doGet(BASE_URL + "/clans/" + clanTag + "/members",
                    MapUtil.<String, Object>builder("limit", limit)
                            .put("after", after)
                            .put("before", before)
                            .build()
            )) {
                if (response.isOk()) {
                    return GsonUtil.fromJson(response.body(), new TypeToken<>() {
                    });
                } else {
                    throw new HttpException(response.body());
                }
            }
        }

        /**
         * 列举部落成员
         *
         * @param clanTag 部落标签
         * @return {@link ItemResult < ClanMember >}
         */
        public ItemResult<ClanMember> listMembers(String clanTag) {
            return this.listMembers(clanTag, null, null, null);
        }
    }

    public static class Player extends AbstractApi {
        public Player(String authorization) {
            super(authorization);
        }

        /**
         * 通过玩家标签获取单个玩家的信息。
         * 玩家标签可以在游戏中找到，也可以从氏族成员名单中找到。
         * 注意，玩家标签以哈希字符'#'开头，需要正确地进行URL编码才能在URL中发挥作用。
         * 例如，玩家标签'#2ABC'在URL中会变成'%232ABC'。
         *
         * @param playerTag 玩家标签
         * @return {@link ClanPlayer}
         */
        public ClanPlayer getPlayerDetail(String playerTag) {
            try (HttpResponse response = super.doGet(BASE_URL + "/players/" + playerTag)) {
                if (response.isOk()) {
                    return GsonUtil.fromJson(response.body(), ClanPlayer.class);
                } else {
                    throw new HttpException(response.body());
                }
            }
        }
    }
}
