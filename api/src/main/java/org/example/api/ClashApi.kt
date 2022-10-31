package org.example.api

import cn.hutool.core.util.URLUtil
import cn.hutool.http.HttpRequest
import com.google.gson.reflect.TypeToken
import org.example.api.pojo.Clan
import org.example.api.pojo.ClanCapital
import org.example.api.pojo.ClanResult
import org.example.api.tools.GsonUtil
import java.nio.charset.Charset

class ClashApi(
    private val TOKEN: String
) {

    companion object {
        const val CLASH_API = "https://api.clashofclans.com/v1"
    }


    private fun get(url: String): String {
        val body = HttpRequest.get(url)
            .bearerAuth(TOKEN)
            .execute()
            .body()
//        println("body = ${body}")
        return body
    }

    private fun post(url: String): String {
        return HttpRequest.post(url)
            .bearerAuth(TOKEN)
            .execute()
            .body()
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

    /**
     * 通过部落标签获取单个部落的信息。
     */
    fun getClansInformation(clanTag: String): Clan {
        val response = HttpRequest.get("$CLASH_API/clans/${encodeParam(clanTag)}")
            .bearerAuth(TOKEN)
            .execute().body()
        return GsonUtil.fromJson(response, Clan::class.java)
    }


    /**
     * 获取部落周末突袭相关信息
     * @param clanTag ClanTag
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

        return GsonUtil.fromJson(response, object : TypeToken<ClanResult<ClanCapital>>() {})
    }
}