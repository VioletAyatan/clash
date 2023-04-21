package com.ankol.api;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import com.ankol.api.entity.Clan;
import com.ankol.api.entity.ClanMember;
import com.ankol.api.entity.ItemResult;
import com.ankol.api.entity.RaidSeason;
import com.ankol.tools.GsonUtil;
import com.google.gson.reflect.TypeToken;

public class ClanApi extends AbstractApi {
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
     * @return {@link ItemResult<RaidSeason>}
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
     * @return {@link ItemResult<RaidSeason>}
     */
    public ItemResult<RaidSeason> capitalRaidSeasons(String clanTag) throws HttpException {
        return this.capitalRaidSeasons(clanTag, null, null, null);
    }

    /**
     * 获取部落突袭周末数据
     *
     * @param clanTag 部落标签.
     * @param limit   响应中的返回项目限制数量.
     * @return {@link ItemResult<RaidSeason>}
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
     * @return {@link ItemResult<ClanMember>}
     */
    public ItemResult<ClanMember> listMembers(String clanTag, Integer limit, String after, String before) {
        HttpResponse response = super.doGet(BASE_URL + "/clans/" + clanTag + "/members",
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
     * 列举部落成员
     *
     * @param clanTag 部落标签
     * @return {@link ItemResult<ClanMember>}
     */
    public ItemResult<ClanMember> listMembers(String clanTag) {
        return this.listMembers(clanTag, null, null, null);
    }
}
