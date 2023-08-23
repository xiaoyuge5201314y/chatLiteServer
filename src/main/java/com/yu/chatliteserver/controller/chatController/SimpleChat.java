/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:01:51
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-04-23 20:48:27
 */
package com.yu.chatliteserver.controller.chatController;

import com.yu.chatliteserver.dto.openAiHttp.chat.ChatRequestDTO;
import com.yu.chatliteserver.dto.openAiHttp.chat.Message;
import com.yu.chatliteserver.serverRequest.ChatHttp;
import com.yu.chatliteserver.vo.R;
import com.yu.chatliteserver.request.chat.SimpleChatRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Data
@Controller
@RequestMapping("openai/simpleChat")
@Api(tags = "简单聊天模块")
public class SimpleChat {

    // @ApiOperation(value = "询问问题")
    // @PostMapping("asking")
    // public R asking (@RequestBody SimpleChatRequest simpleChatRequest) {
    //     // 请求
    //     ChatRequestDTO chatRequestDTO = new ChatRequestDTO();
    //     List<Message> messages= new ArrayList<>();
    //     Message message  = new Message();
    //     message.setRole("user");
    //     message.setContent(simpleChatRequest.getContent());
    //     messages.add(message);
    //     chatRequestDTO.setMessages(messages);
    //     chatRequestDTO.setModel("gpt-3.5-turbo");
    //     // 响应
    //     Message messageResponse = ChatHttp.chat(chatRequestDTO);
    //     return R.ok(messageResponse);
    // }

}
