package com.ankol.api;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import com.ankol.api.entity.ItemResult;
import com.ankol.api.entity.Member;
import com.ankol.api.entity.RaidSeason;
import com.ankol.tools.GsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class ClanApi extends AbstractApi {
    public ClanApi(String authorization) {
        super(authorization);
    }

    /**
     * Retrieve clan's capital raid seasons
     *
     * @param clanTag Tag of the clan.
     * @param limit   Limit the number of items returned in the response.
     * @param after   Return only items that occur after this marker.
     *                Before marker can be found from the response, inside the 'paging' property.
     *                Note that only after or before can be specified for a request, not both.
     * @param before  Return only items that occur before this marker.
     *                Before marker can be found from the response, inside the 'paging' property.
     *                Note that only after or before can be specified for a request, not both.
     * @return {@link ItemResult<RaidSeason>}
     */
    public ItemResult<RaidSeason> capitalRaidSeasons(String clanTag, Integer limit, String after, String before) {
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
     * Retrieve clan's capital raid seasons
     *
     * @param clanTag Tag of the clan.
     * @return {@link ItemResult<RaidSeason>}
     */
    public ItemResult<RaidSeason> capitalRaidSeasons(String clanTag) {
        return this.capitalRaidSeasons(clanTag, null, null, null);
    }

    /**
     * List clan members.
     *
     * @param clanTag Tag of the clan.
     * @param limit   Limit the number of items returned in the response.
     * @param after   Return only items that occur after this marker.
     *                Before marker can be found from the response, inside the 'paging' property.
     *                Note that only after or before can be specified for a request, not both.
     * @param before  Return only items that occur before this marker.
     *                Before marker can be found from the response, inside the 'paging' property.
     *                Note that only after or before can be specified for a request, not both.
     * @return {@link ItemResult<Member>}
     */
    public ItemResult<Member> listMembers(String clanTag, Integer limit, String after, String before) {
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
     * List clan members.
     *
     * @param clanTag Tag of the clan.
     * @return {@link ItemResult<Member>}
     */
    public ItemResult<Member> listMembers(String clanTag) {
        return this.listMembers(clanTag, null, null, null);
    }
}
