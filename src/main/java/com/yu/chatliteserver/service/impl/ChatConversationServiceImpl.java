package com.yu.chatliteserver.service.impl;

import com.yu.chatliteserver.entity.ChatConversation;
import com.yu.chatliteserver.mapper.ChatConversationMapper;
import com.yu.chatliteserver.service.IChatConversationService;

import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-04-09 15:03:11
 */
@Service
public class ChatConversationServiceImpl extends ServiceImpl<ChatConversationMapper, ChatConversation> implements IChatConversationService {

    @Autowired
    private ChatConversationMapper chatConversationMapper;




    @Override
    public ChatConversation addConversation() {
         ChatConversation conversation = new ChatConversation();
         conversation.setId(UUID.randomUUID().toString());
         conversation.setUserId(StpUtil.getLoginId().toString());
         conversation.setAiModelId("2");
         conversation.setCreateTime(LocalDateTime.now());
         conversation.setUpdateTime(LocalDateTime.now());
         conversation.setVersion(1);
         chatConversationMapper.insert(conversation);
         return conversation;
    }

    @Override
    public ChatConversation addSenceConversation(String senceId) {
         ChatConversation conversation = new ChatConversation();
         conversation.setId(UUID.randomUUID().toString());
         conversation.setUserId(StpUtil.getLoginId().toString());
         conversation.setAiModelId("2");
         conversation.setSenceId(senceId);
         conversation.setCreateTime(LocalDateTime.now());
         conversation.setUpdateTime(LocalDateTime.now());
         conversation.setVersion(1);
         chatConversationMapper.insert(conversation);
         return conversation;
    }

  

}
