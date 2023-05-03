/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-23 20:48:52
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-04-23 21:27:04
 */
package com.yu.chatliteserver.controller.chatController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yu.chatliteserver.dto.openAiHttp.painting.ImageRequestDTO;
import com.yu.chatliteserver.request.painting.PaintingRequest;
import com.yu.chatliteserver.serverRequest.ImageHttp;
import com.yu.chatliteserver.vo.R;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/painting")
public class ChatPaintingController {
    
    @PostMapping("/message")
    public R createImage(@RequestBody PaintingRequest paintingRequest ) {
        ImageRequestDTO imageRequestDTO = new ImageRequestDTO();
        imageRequestDTO.setPrompt(paintingRequest.getPrompt());
        imageRequestDTO.setSize(paintingRequest.getSize());
        imageRequestDTO.setN(paintingRequest.getN());
        return R.ok(ImageHttp.generateImage(imageRequestDTO));
    }
    
}
