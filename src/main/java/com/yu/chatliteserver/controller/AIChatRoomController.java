package com.yu.chatliteserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yu.chatliteserver.entity.ChatMessage;
import com.yu.chatliteserver.entity.ChatPrompt;
import com.yu.chatliteserver.service.IChatMessageService;
import com.yu.chatliteserver.service.IChatPromptService;
import com.yu.chatliteserver.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.color.CMMException;
import java.util.List;

/**
 * <p>
 *  聊天房间
 * </p>
 *
 * @author 吴东宇
 * @since 2023-03-10 03:04:09
 */
@RestController
@RequestMapping("/chatRoom")
public class AIChatRoomController {

    @Autowired
    private IChatMessageService chatMessageService;

    @Autowired
    private IChatPromptService chatPromptService;

    @GetMapping("/list")
    public R getRoomList(@RequestHeader(value = "userId") String userId) {

       List<ChatMessage> list = chatMessageService.listByUserId(userId);
         return R.ok(list);
    }

    @GetMapping("/historyChatRecordsList/{id}")
    public R getHistoryChatRecordsList(@PathVariable(value = "id") String messageId) {
        // 根据msgid查询历史对话
        List<ChatPrompt> prompts = chatPromptService.getList(messageId);
        return R.ok(prompts);
    }




}
