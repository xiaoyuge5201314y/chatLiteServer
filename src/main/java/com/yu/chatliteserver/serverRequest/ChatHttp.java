/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:01:51
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-05-16 19:57:21
 */
package com.yu.chatliteserver.serverRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import com.plexpt.chatgpt.listener.SseStreamListener;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.Message;
import com.yu.chatliteserver.constant.Constant;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatRequestDTO;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatResponseDTO;
import com.yu.chatliteserver.util.OpenAiHttp;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.collection.CollUtil.Consumer;
import cn.hutool.log.Log;
import kong.unirest.HttpResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天相关的请求封装
 */
@Slf4j
public class ChatHttp {

    // 聊天
    public static com.yu.chatliteserver.dto.openAiHttp.chat.Message chat(ChatRequestDTO chatBody) {

        HttpResponse response = OpenAiHttp.request(chatBody, "v1/chat/completions");

        System.out.println(response.getBody());

        Gson gson = new Gson();
        ChatResponseDTO chatResponseDTO = gson.fromJson(response.getBody().toString(), ChatResponseDTO.class);
        return chatResponseDTO.getChoices()[0].getMessage();
    }

    public static SseEmitter sseEmitter(String prompt) {
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .timeout(600)
                .apiKey(Constant.OPENAI_API_KEY)
                .apiHost("https://" + Constant.OPENAI_HOST + "/") // 反向代理地址
                .build()
                .init();

        SseEmitter sseEmitter = new SseEmitter(-1L);
        SseStreamListener listener = new SseStreamListener(sseEmitter);
        Message message = Message.of("你是谁");

        listener.setOnComplate(msg -> {
            // 回答完成，可以做一些事情
            log.info("chat接口调用成功: " + msg);
        });
        System.out.println(message);
        chatGPTStream.streamChatCompletion(Arrays.asList(message), listener);
        return sseEmitter;
    }

}
