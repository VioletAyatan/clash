package com.ankol.api;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import com.ankol.api.entity.ItemResult;
import com.ankol.api.entity.ClanMember;
import com.ankol.api.entity.RaidSeason;
import com.ankol.tools.GsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class ClanApi extends AbstractApi {
    public ClanApi(String authorization) {
        super(authorization);
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
                Map.of("limit", limit, "after", after, "before", before)
        );
        if (response.isOk()) {
            return GsonUtil.fromJson(response.body(), new TypeToken<>() {
            });
        }
        throw new HttpException("");
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
    public ItemResult<RaidSeason> capitalRaidSeasons(String clanTag, Integer limit) {
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
                Map.of("limit", limit, "after", after, "before", before)
        );
        if (response.isOk()) {
            return GsonUtil.fromJson(response.body(), new TypeToken<>() {
            });
        }
        throw new HttpException("");
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
