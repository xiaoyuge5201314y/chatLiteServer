
/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-06-21 21:11:31
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-06-23 18:31:57
 */
/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-06-21 21:11:31
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-06-21 23:12:59
 */
package com.yu.chatliteserver.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.yu.chatliteserver.constant.Constant;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatRequestDTO;
import com.yu.chatliteserver.service.IGPTService;

@Service
public class GPTServiceImpl implements IGPTService {

    private ChatGPT chatGPT;

    public GPTServiceImpl() {
        chatGPT = ChatGPT.builder()
                .apiKey(Constant.OPENAI_API_KEY)
                .apiHost("https://" + Constant.OPENAI_HOST + "/") // 反向代理地址
                .build()
                .init();
    }

    // 聊天
    public ChatCompletionResponse chat(ChatRequestDTO requestDTO) {
        
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                .messages(requestDTO.getMessages())
                .maxTokens(requestDTO.getMaxTokens().intValue())
                .temperature(requestDTO.getTemperature())
                // .topP(requestDTO.getTopP())
                // .stop(Arrays.asList(requestDTO.getStop()))
                // .stream(requestDTO.getStream())
                .build();
        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
        return response;
    }
}


