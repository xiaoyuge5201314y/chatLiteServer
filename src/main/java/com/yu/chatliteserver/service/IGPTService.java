/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-06-21 21:10:41
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-06-21 23:02:52
 */
package com.yu.chatliteserver.service;

import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatRequestDTO;

public interface IGPTService {

    ChatCompletionResponse chat(ChatRequestDTO requestDTO);

}
