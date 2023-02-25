package com.yu.chatliteserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yu.chatliteserver.config.OpenAIConfig;
import com.yu.chatliteserver.entity.ChatMessage;
import com.yu.chatliteserver.request.ChatMessageRequest;
import com.yu.chatliteserver.service.IChatMessageService;
import com.yu.chatliteserver.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器 聊天历史记录
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-23 05:17:00
 */
@RestController
@RequestMapping("/chatMessage")
public class ChatMessageController {

    @Autowired
    private IChatMessageService chatMessageService;

    /**
     * @param userId
     * @return
     * @desc 根据用户id查询聊天历史消息不分页
     */
    @GetMapping("list/{userId}")
    public R getByUserId(@PathVariable("userId") String userId) {
        List<ChatMessage> list = chatMessageService.getByUserId(userId);
        return R.ok(list);
    }

    /**
     * @param request
     * @return
     * @desc 新增一条chat记录
     */
    @PostMapping("")
    public R addChatMessage(@RequestBody ChatMessageRequest request) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setCreateTime(LocalDateTime.now());
        chatMessage.setUpdateTime(chatMessage.getCreateTime());
        chatMessage.setPrompt("[]");
        chatMessage.setUserId(request.getUserId());
        chatMessage.setAiModelId("1");
        chatMessage.setVersion(1);
        chatMessageService.save(chatMessage);
        return R.ok(chatMessage.getId());
    }

    /**
     * @param chatMessageRequest
     * @param id
     * @return
     * @desc 修改chat记录
     */
    @PutMapping("/{id}")
    public R updateChatMessageById(@RequestBody ChatMessageRequest chatMessageRequest, @PathVariable String id) {

        boolean result = chatMessageService.updateChatMessageById(chatMessageRequest, id);
        if (result) {
            return R.ok("Chat message has been updated successfully.");
        } else {
            throw new RuntimeException("修改失败");
        }
    }

    /**
     * @desc 询问问题
     */
    @PostMapping("ask/{id}")
    public R ask(@PathVariable String id, @RequestBody ChatMessageRequest chatMessageRequest) {
        OpenAIConfig openAIConfig = new OpenAIConfig();
        String prompt = chatMessageRequest.getPrompt();
        String answer = openAIConfig.getResult(prompt);


        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> answerMap = objectMapper.convertValue(answer, new TypeReference<Map<String, Object>>(){});
        List<Map<String, String>> choices = (List<Map<String, String>>) answerMap.get("choices");
        for (Map<String, String> choice : choices) {
            String text = choice.get("text");
            // 这里处理 text 的逻辑
        }

        ChatMessage chatMessage = chatMessageService.getById(id);

        // 读取聊天记录的prompt值，并将其转换为Java对象
        String promptJson = chatMessage.getPrompt();
        JSONArray promptArray = JSON.parseArray(promptJson);


        // 创建新的聊天记录对象
        JSONObject chatRecord = new JSONObject();
        chatRecord.put("question", chatMessageRequest.getPrompt());
        chatRecord.put("answer", answer);

        // 将新的聊天记录追加到prompt数组中
        promptArray.add(chatRecord);

        // 将更新后的prompt数组转换为JSON字符串
        String updatedPromptJson = JSON.toJSONString(promptArray);

        // 更新数据库中的聊天记录
        chatMessage.setPrompt(updatedPromptJson);
        chatMessageService.updateById(chatMessage);

        return R.ok(answer);
    }


}
