/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:03:12
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-06-22 00:50:44
 */
package com.yu.chatliteserver.service.impl;

import com.yu.chatliteserver.entity.ChatConversationDetail;
import com.yu.chatliteserver.entity.Scene;
import com.yu.chatliteserver.mapper.ChatConversationDetailMapper;
import com.yu.chatliteserver.service.IChatConversationDetailService;
import com.yu.chatliteserver.service.ISceneService;

import cn.hutool.core.lang.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-04-09 15:03:12
 */
@Service
public class ChatConversationDetailServiceImpl extends ServiceImpl<ChatConversationDetailMapper, ChatConversationDetail>
        implements IChatConversationDetailService {

    @Autowired
    private ChatConversationDetailMapper conversationDetailMapper;

    @Autowired
    private ISceneService iSceneService;

    // 新增会话
    @Override
    public ChatConversationDetail getByConversationId(String conversationId) {
        QueryWrapper<ChatConversationDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("conversation_id", conversationId);
        ChatConversationDetail conversationDetail = conversationDetailMapper.selectOne(queryWrapper);
        return conversationDetail;
    }

    // 新增会话详情
    @Override
    public ChatConversationDetail addConversationDetail(String conversationId) {

        ChatConversationDetail conversationDetail = new ChatConversationDetail();
        conversationDetail.setId(UUID.randomUUID().toString());
        conversationDetail.setConversationId(conversationId);
        conversationDetail.setMessages("[]");
        conversationDetail.setCreateTime(LocalDateTime.now());
        conversationDetail.setUpdateTime(LocalDateTime.now());
        conversationDetail.setVersion(0);
        conversationDetailMapper.insert(conversationDetail);
        return conversationDetail;
    }

    // 新增会话详情（场景）
    @Override
    public ChatConversationDetail addConversationDetail(String conversationId, String sencenId) {

        Scene scene = iSceneService.getById(sencenId);

        ChatConversationDetail conversationDetail = new ChatConversationDetail();
        conversationDetail.setId(UUID.randomUUID().toString());
        conversationDetail.setConversationId(conversationId);
        conversationDetail.setMessages("[{\"role\":\"system\",\"content\":\"" + scene.getScenePrompt() + "\"}]");
        conversationDetail.setCreateTime(LocalDateTime.now());
        conversationDetail.setUpdateTime(LocalDateTime.now());
        conversationDetail.setVersion(0);
        conversationDetailMapper.insert(conversationDetail);
        return conversationDetail;
    }

}
