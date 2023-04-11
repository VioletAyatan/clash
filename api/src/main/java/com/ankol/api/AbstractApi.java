package com.ankol.api;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.util.Map;

public abstract class AbstractApi {
    protected String authorization;
    protected final String BASE_URL;

    /**
     * 初始化一个API类实例
     * @param authorization
     * @param baseUrl
     */
    protected AbstractApi(String authorization, String baseUrl) {
        this.authorization = authorization;
        BASE_URL = baseUrl;
    }

    /**
     * 执行GET请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return {@link HttpResponse}
     */
    protected HttpResponse doGet(String url, Map<String, Object> params) {
        return HttpUtil.createGet(url)
                .auth(authorization)
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
        return HttpUtil.createGet(url)
                .auth(authorization)
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
        return HttpUtil.createGet(url)
                .auth(authorization)
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
        return HttpUtil.createGet(url)
                .auth(authorization)
                .executeAsync();
    }
}
