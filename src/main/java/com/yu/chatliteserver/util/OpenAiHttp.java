/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:01:51
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-04-24 13:07:21
 */
package com.yu.chatliteserver.util;

import com.yu.chatliteserver.constant.Constant;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class OpenAiHttp {

    public static <T> HttpResponse request(T body, String path) {
        Unirest.config().reset();
        Unirest.config().connectTimeout(1000000000);
        // Unirest.config().proxy(Constant.PROXY_HOST, Constant.PROXY_PORT);
        System.out.println(body);
        HttpResponse response = Unirest.post("https://"+Constant.OPENAI_HOST + "/" + path)
                .header("Authorization", "Bearer "+Constant.OPENAI_API_KEY)
                .header("Accept", "application/json")
                // .header("User-Agent", "Apifox/1.0.0 (https://www.apifox.cn)")
                .header("Content-Type", "application/json")
                .body(body)
                .asString();
        System.out.println(response);
        return response;
    }
}
