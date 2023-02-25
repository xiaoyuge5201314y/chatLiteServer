package com.yu.chatliteserver.controller;

import com.yu.chatliteserver.config.OpenAIConfig;
import com.yu.chatliteserver.request.ChatRequest;
import com.yu.chatliteserver.vo.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class OpenAiController {
    /**
     * 获取答案 这个用于测试
     * @param chatRequest
     * @return
     */
        @PostMapping("result")
    public R getResults (@RequestBody ChatRequest chatRequest) {
        OpenAIConfig openAIConfig = new OpenAIConfig();
        String prompt = chatRequest.getPrompt();
        String resultBody = openAIConfig.getResult(prompt);
        return R.ok(resultBody);
    }

    // 获取所有的model列表

    // 创建一个聊天室
    //

}
