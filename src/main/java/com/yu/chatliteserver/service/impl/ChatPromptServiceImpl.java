package com.yu.chatliteserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.chatliteserver.entity.ChatPrompt;
import com.yu.chatliteserver.mapper.ChatPromptMapper;
import com.yu.chatliteserver.service.IChatPromptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-03-10 03:04:09
 */
@Service
public class ChatPromptServiceImpl extends ServiceImpl<ChatPromptMapper, ChatPrompt> implements IChatPromptService {

    /**
     * @desc 根据messageId获取所有的对话
     * @param messageId
     * @return
     */
    @Override
    public List<ChatPrompt> getList(String messageId) {
        LambdaQueryWrapper<ChatPrompt> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ChatPrompt::getMessageId, messageId);
        List<ChatPrompt> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public void add(String messageId, String role, String prompt) {
        ChatPrompt chatPrompt = new ChatPrompt();
        chatPrompt.setCreateTime(LocalDateTime.now());
        chatPrompt.setMessageId(messageId);
        chatPrompt.setOrd(this.list().size() + 1);
        chatPrompt.setPromptRole(role);
        chatPrompt.setContent(prompt);
        this.save(chatPrompt);
    }
}
