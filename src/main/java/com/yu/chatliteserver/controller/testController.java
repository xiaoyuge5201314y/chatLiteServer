/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-05-16 05:21:23
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-05-16 19:59:05
 */
package com.yu.chatliteserver.controller;

import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.plexpt.chatgpt.*;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.SseStreamListener;
import com.plexpt.chatgpt.util.*;
import com.yu.chatliteserver.constant.Constant;
import com.yu.chatliteserver.serverRequest.ChatHttp;
import com.yu.chatliteserver.util.OpenAiHttp;
import com.yu.chatliteserver.vo.R;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class testController {

    static String URL_SUB = "v1/dashboard/billing/subscription";
    static String URL_USEAGE = "v1/dashboard/billing/usage";

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(date);
    }

    @GetMapping("/useage")
    public R useage() {
        DateTime start = DateUtil.offsetMonth(new Date(), -3);
        DateTime end = DateUtil.offsetDay(new Date(), 1);

        Unirest.config().reset();
        Unirest.config().connectTimeout(1000000000);
        String response = Unirest.get("https://" + Constant.OPENAI_HOST + "/" + URL_USEAGE)
                .header("Authorization", "Bearer " + Constant.OPENAI_API_KEY)
                .header("Content-Type", "application/json")
                .queryString("start_date", formatDate(start))
                .queryString("end_date", formatDate(end))
                .asString()
                .getBody();
        System.out.println(response);
        return R.ok(response);
    }

    @GetMapping("/account")
    public R account() {
        DateTime start = DateUtil.beginOfMonth(new Date());
        DateTime end = DateUtil.offsetDay(new Date(), 1);

        Unirest.config().reset();
        Unirest.config().connectTimeout(1000000000);
        String response = Unirest.get("https://" + Constant.OPENAI_HOST + "/" + URL_SUB)
                .header("Authorization", "Bearer " + Constant.OPENAI_API_KEY)
                .header("Content-Type", "application/json")

                .asString()
                .getBody();
        System.out.println(response);
        return R.ok(response);
    }

    @GetMapping("/test")
    public R test() {
        // Proxy proxy = Proxys.http(Constant.OPENAI_HOST, 80);
        // socks5 代理
        // Proxy proxy = Proxys.socks5("127.0.0.1", 1080);

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(Constant.OPENAI_API_KEY)
                // .proxy(proxy)
                .apiHost("https://" + Constant.OPENAI_HOST + "/") // 反向代理地址
                .build()
                .init();

        String res = chatGPT.chat("写一段七言绝句诗，题目是：火锅！");
        return R.ok(res);
    }

    @GetMapping("/chat/sse")
    public SseEmitter sseEmitter(String prompt) {
        SseEmitter sseEmitter= ChatHttp.sseEmitter(prompt);
        return sseEmitter;
    }
}
