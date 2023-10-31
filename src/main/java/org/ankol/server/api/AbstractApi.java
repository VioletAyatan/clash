package org.ankol.server.api;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * A simple wrapper of HTTP-CLIENT that provides methods commonly used to call the COC-Api.
 * <p>Four methods implemented: doGet, doPost, doPut, doDelete</p>
 *
 * @author Administrator
 */
public abstract class AbstractApi {
    protected String authorization;
    protected static final String BASE_URL = "https://api.clashofclans.com/v1";

    /**
     * 初始化一个API类实例
     *
     * @param authorization
     */
    protected AbstractApi(String authorization) {
        this.authorization = authorization;
    }

    /**
     * 执行GET请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return {@link HttpResponse}
     */
    protected HttpResponse doGet(String url, Map<String, Object> params) {
        return HttpUtil.createGet(URLUtil.encode(url, StandardCharsets.UTF_8))
                .bearerAuth(authorization)
                .form(params)
                .executeAsync();
    }

    /**
     * 执行GET请求
     *
     * @param url 请求地址
     * @return {@link HttpResponse}
     */
    protected HttpResponse doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 执行POST请求
     *
     * @param url  请求地址
     * @param json 请求体参数（必须为json格式）
     * @return {@link HttpResponse}
     */
    protected HttpResponse doPost(String url, String json) {
        return HttpUtil.createGet(URLEncoder.encode(url, StandardCharsets.UTF_8))
                .bearerAuth(authorization)
                .body(json)
                .executeAsync();
    }

    /**
     * 执行POST请求
     *
     * @param url 请求地址
     * @return {@link HttpResponse}
     */
    protected HttpResponse doPost(String url) {
        return doPost(url, null);
    }

    /**
     * 执行PUT请求
     *
     * @param url  请求地址
     * @param json 请求体参数（必须为json格式）
     * @return {@link HttpResponse}
     */
    protected HttpResponse doPut(String url, String json) {
        return HttpUtil.createGet(URLUtil.encode(url, StandardCharsets.UTF_8))
                .bearerAuth(authorization)
                .body(json)
                .executeAsync();
    }

    /**
     * 执行DELETE请求
     *
     * @param url 请求地址
     * @return {@link HttpResponse}
     */
    protected HttpResponse doDelete(String url) {
        return HttpUtil.createGet(URLUtil.encode(url, StandardCharsets.UTF_8))
                .bearerAuth(authorization)
                .executeAsync();
    }
}
