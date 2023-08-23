
/*
* @Description: 
* @Version: 1.0
* @Author: wudongyu
* @Date: 2023-04-23 20:48:52
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-06-21 23:17:16
*/
package com.yu.chatliteserver.controller.chatController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatRequestDTO;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatResponseDTO;
import com.yu.chatliteserver.dto.openAiHttp.chat.Message;
import com.yu.chatliteserver.dto.openAiHttp.painting.ImageRequestDTO;
import com.yu.chatliteserver.request.painting.PaintingRequest;
import com.yu.chatliteserver.serverRequest.ChatHttp;
import com.yu.chatliteserver.serverRequest.ImageHttp;
import com.yu.chatliteserver.service.IGPTService;
import com.yu.chatliteserver.vo.R;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/gpt")
public class GPTController {

    @Autowired
    private IGPTService igptService;

    @PostMapping("/message")
    public R createImage(@RequestBody PaintingRequest paintingRequest) {
        ImageRequestDTO imageRequestDTO = new ImageRequestDTO();
        imageRequestDTO.setPrompt(paintingRequest.getPrompt());
        imageRequestDTO.setSize(paintingRequest.getSize());
        imageRequestDTO.setN(paintingRequest.getN());
        return R.ok(ImageHttp.generateImage(imageRequestDTO));

    }

    @PostMapping("/ask")
    public R ask(@RequestBody ChatRequestDTO chatRequestDTO) {

        ChatCompletionResponse chatCompletionResponse = igptService.chat(chatRequestDTO);

        return R.ok(chatCompletionResponse);
    }

}
