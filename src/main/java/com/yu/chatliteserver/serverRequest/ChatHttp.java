/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:01:51
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-04-24 10:41:09
 */
package com.yu.chatliteserver.serverRequest;

import com.google.gson.Gson;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatRequestDTO;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatResponseDTO;
import com.yu.chatliteserver.dto.openAiHttp.chat.Message;
import com.yu.chatliteserver.util.OpenAiHttp;

import kong.unirest.HttpResponse;

/**
 * 聊天相关的请求封装
 */
public class ChatHttp {
  
    public static Message chat (ChatRequestDTO chatBody) {

        HttpResponse response = OpenAiHttp.request(chatBody, "v1/chat/completions");
    
        System.out.println(response.getBody());

        Gson gson = new Gson();
        ChatResponseDTO chatResponseDTO = gson.fromJson(response.getBody().toString(), ChatResponseDTO.class);
        return chatResponseDTO.getChoices()[0].getMessage();
    }

}
