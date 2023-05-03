/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:01:51
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-04-24 10:43:52
 */
package com.yu.chatliteserver.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.yu.chatliteserver.dto.openAiHttp.chat.ChatRequestDTO;
import com.yu.chatliteserver.dto.openAiHttp.chat.Message;
import com.yu.chatliteserver.serverRequest.ChatHttp;
import com.yu.chatliteserver.util.OpenAiHttp;
import com.yu.chatliteserver.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * chatgpt 前端控制器
 * </p>
 *
 * @author 吴东宇
 * @since 2022-06-11 14:26:33
 */
@RestController
@RequestMapping("ai")
@Api(tags = "原生openai相关接口")
public class OpenAiController {
//    @ApiOperation(value = "获取答案 这个用于测试", notes = "开发中")
//    @PostMapping("result")
//    public R getResults(@RequestBody String msg) {
//        // 请求
//        ChatRequestDTO chatRequestDTO = new ChatRequestDTO();
//        Message[] messages = new Message[1];
//        messages[0] = new Message();
//        messages[0].setRole("user");
//        messages[0].setContent(msg);
//        chatRequestDTO.setMessages(messages);
//        chatRequestDTO.setModel("gpt-3.5-turbo");
//        // 响应
//        Message messageResponse = ChatHttp.chat(chatRequestDTO);
//        return R.ok(messageResponse);
//    }

    @ApiOperation(value = "获取所有的model列表", notes = "开发中")
    @GetMapping("modelList")
    public R getModelList() {
        // Map<String, String> map = new HashMap();
        // String body = OpenAiHttp.getReq("v1/models", map, new JSONObject());
        // com.alibaba.fastjson2.JSONObject jsonObject = JSON.parseObject(body);
        // Object innerData = jsonObject.get("data");
        // return R.ok(innerData);
        return R.ok();
    }
}
