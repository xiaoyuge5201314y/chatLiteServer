package com.yu.chatliteserver.controller;

import cn.hutool.system.oshi.CpuInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yu.chatliteserver.config.OpenAIConfig;
import com.yu.chatliteserver.dto.ai.AiResponseDTO;
import com.yu.chatliteserver.dto.ai.AiResponseTextDto;
import com.yu.chatliteserver.entity.ChatMessage;
import com.yu.chatliteserver.entity.ChatPrompt;
import com.yu.chatliteserver.entity.User;
import com.yu.chatliteserver.request.ChatMessageRequest;
import com.yu.chatliteserver.service.IChatMessageService;
import com.yu.chatliteserver.service.IChatPromptService;
import com.yu.chatliteserver.vo.ChatMessageVO;
import com.yu.chatliteserver.vo.R;
import com.yu.chatliteserver.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

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

    @Autowired
    private IChatPromptService chatPromptService;

    /**
     * @param userId
     * @return
     * @desc 根据用户id查询聊天历史消息不分页
     */
    @GetMapping("list/{userId}")
    public R getByUserId(@PathVariable("userId") String userId) {
        List<ChatMessage> chatMessages = chatMessageService.getByUserId(userId);
//        获取消息记录

        List<ChatMessageVO> list = chatMessages.stream().map(chatMessage -> {
            ChatMessageVO chatMessageVO = new ChatMessageVO();
            BeanUtils.copyProperties(chatMessage, chatMessageVO);
            List<ChatPrompt> prompts = chatPromptService.getList(chatMessage.getId());
            chatMessageVO.setPrompts(prompts);
            return chatMessageVO;
        }).collect(Collectors.toList());
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
        chatMessage.setUserId("1");
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
    @Transactional
    public R ask(@PathVariable String id, @RequestBody ChatMessageRequest chatMessageRequest) throws JsonProcessingException {
        // 保存请求对话
        String requestPrompt = chatMessageRequest.getPrompt();
        chatPromptService.add(id, "user", requestPrompt);
        // 根据msgid查询历史对话
        List<ChatPrompt> prompts = chatPromptService.getList(id);

//        prompts.add(chatPrompt);
        String str = "[";
        for (int i = 0; i < prompts.size(); i++) {
            ChatPrompt e = prompts.get(i);
            str += "{role:" + e.getPromptRole() + ", content: " + e.getContent() + "}, ";
        }
        str += "]";

        OpenAIConfig openAIConfig = new OpenAIConfig();
        String resultBody = openAIConfig.getResult(str);

        // 添加响应对话
        ObjectMapper objectMapper = new ObjectMapper();
        AiResponseDTO aiResponseDTO = objectMapper.readValue(resultBody, AiResponseDTO.class);
        String text = aiResponseDTO.getChoices()[0].getText();
        AiResponseTextDto aiResponseTextDto = new AiResponseTextDto(text);
        // 保存响应对话
        chatPromptService.add(id, aiResponseTextDto.getRole(), aiResponseTextDto.getContent());
        return R.ok(aiResponseTextDto);
    }
}
