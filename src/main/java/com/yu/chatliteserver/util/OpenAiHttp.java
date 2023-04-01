package com.yu.chatliteserver.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;

import java.util.Map;

public  class OpenAiHttp {
    //添加我们需要输入的内容
    private static String proxyHost = "127.0.0.1";
    private static int proxyPort = 4780;

    private static String openAiKey = "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";
    public static String getReq(String path, Map<String, String> headers, JSONObject json) {
        HttpResponse response = HttpRequest.get("https://api.openai.com/" + path)
                .headerMap(headers, false)
                .bearerAuth(openAiKey)
                .timeout(5 * 60 * 1000)
                .setHttpProxy(proxyHost,proxyPort)
                .execute();
        System.out.println(response.body());
        return response.body();
    }
    public static HttpResponse postReq(String path, Map<String, String> headers, JSONObject json) {
        HttpResponse response = HttpRequest.post("https://api.openai.com/" + path)
                .headerMap(headers, false)
                .bearerAuth(openAiKey)
                .body(String.valueOf(json))
                .timeout(5 * 60 * 1000)
                .setHttpProxy(proxyHost,proxyPort)
                .execute();
        System.out.println(response);
        return response;
    }
}
