package org.example.api

import org.example.api.pojo.Player
import cn.hutool.core.util.URLUtil
import cn.hutool.http.HttpRequest
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.example.api.exception.ClashApiException
import org.example.api.pojo.*
import org.example.api.tools.GsonUtil
import java.nio.charset.Charset

class ClashApi(
        private val TOKEN: String
) {

    private val moshi = Moshi
            .Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    companion object {
        const val CLASH_API = "https://api.clashofclans.com/v1"
    }

    /**
     * 通过部落标签获取单个部落的信息.
     * @throws ClashApiException 如果调用api发生了错误，则抛出此异常.
     */
    fun getClanInformation(clanTag: String): Clan? {
        return when (val response = this.get("$CLASH_API/clans/${encodeParam(clanTag)}")) {
            is String -> GsonUtil.fromJson(response, Clan::class.java)
            else -> null
        }
    }


    /**
     * 获取指定部落周末突袭相关信息
     * @param clanTag ClanTag
     * @throws ClashApiException 如果调用api发生了错误，则抛出此异常.
     */
    @JvmOverloads
    fun getClanCapitalRaidSeason(
            clanTag: String,
            limit: Int? = null,
            after: String? = null,
            before: String? = null
    ): ClanResult<ClanCapital> {
        val response = this.get(
                "$CLASH_API/clans/${encodeParam(clanTag)}/capitalraidseasons${
                    builderParameter(
                            limit,
                            after,
                            before
                    )
                }"
        )
        val jsonAdapter = moshi.adapter<ClanResult<ClanCapital>>(
                Types.newParameterizedType(
                        ClanResult::class.java,
                        ClanCapital::class.java
                )
        )
        return jsonAdapter.fromJson(response)!!
    }

    /**
     * 获取部落成员信息.
     * @throws ClashApiException 如果调用api发生了错误，则抛出此异常.
     */
    @JvmOverloads
    fun getClanMembers(
            clanTag: String, limit: Int? = null,
            after: String? = null,
            before: String? = null
    ): ClanResult<ClanMember> {
        val response = this.get(
                "$CLASH_API/clans/${encodeParam(clanTag)}/members${
                    builderParameter(
                            limit,
                            after,
                            before
                    )
                }"
        )
        val jsonAdapter =
                moshi.adapter<ClanResult<ClanMember>>(Types.newParameterizedType(ClanResult::class.java, ClanMember::class.java))
        return jsonAdapter.fromJson(response)!!
    }

    /**
     * 查询部落当前的联赛组信息
     * @throws ClashApiException 如果调用api发生了错误，则抛出此异常.
     */
    fun getClansCurrentWarLeaguegroup(clanTag: String): ClashWarLeagueGroup {
        val response = this.get("$CLASH_API/clans/${encodeParam(clanTag)}/currentwar/leaguegroup")
        val jsonAdapter = moshi.adapter(ClashWarLeagueGroup::class.java)
        return jsonAdapter.fromJson(response)!!
    }

    /**
     * 查询部落指定联赛场次信息
     * @throws ClashApiException 如果调用api发生了错误，则抛出此异常.
     */
    fun getClanWarLeagueInformation(warTag: String): ClanWarLeagueInfo {
        val response = this.get("$CLASH_API/clanwarleagues/wars/${encodeParam(warTag)}")
        val jsonAdapter = moshi.adapter(ClanWarLeagueInfo::class.java)
        return jsonAdapter.fromJson(response)!!
    }

    /**
     * 通过玩家标签获取玩家的信息。
     * 玩家标签可以在游戏中找到，也可以在氏族成员名单中找到。
     * 请注意，玩家标签以哈希字符'#'开头，需要正确地进行URL编码才能在URL中发挥作用。
     * 例如，玩家标签'#2ABC'在URL中会变成'%232ABC'。
     */
    fun getPlayerInformation(playerTag: String): Player? {
        val response = this.get("$CLASH_API/players/${encodeParam(playerTag)}")
        val jsonAdapter = moshi.adapter(Player::class.java)
        return jsonAdapter.fromJson(response)
    }


    private fun get(url: String): String {
        val response = HttpRequest.get(url)
                .bearerAuth(TOKEN)
                .execute()
        return when {
            response.isOk -> response.body()
            else -> throw ClashApiException(GsonUtil.fromJson(response.body(), ClashErrorResponse::class.java))
        }
    }

    private fun post(url: String): String {
        val response = HttpRequest.post(url)
                .bearerAuth(TOKEN)
                .execute()
        return when {
            response.isOk -> response.body()
            else -> throw ClashApiException(GsonUtil.fromJson(response.body(), ClashErrorResponse::class.java))
        }
    }

    /**
     * 构建查询字符串
     */
    private fun builderParameter(
            limit: Int? = null,
            after: String? = null,
            before: String? = null
    ): String {
        val params = mutableMapOf<String, Any>()
        limit?.let { i: Int -> params["limit"] = i }
        after?.let { s -> params["after"] = s }
        before?.let { s -> params["before"] = s }
        val buildQuery = URLUtil.buildQuery(params, Charset.defaultCharset())
        return if (buildQuery.isNotBlank()) "?$buildQuery" else ""
    }

    /**
     * Encoding incoming parameter
     */
    private fun encodeParam(param: String): String? {
        return URLUtil.encode(param)
    }
}