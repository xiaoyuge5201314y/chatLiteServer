package com.yu.chatliteserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yu.chatliteserver.entity.ChatMessage;
import com.yu.chatliteserver.mapper.ChatMessageMapper;
import com.yu.chatliteserver.request.ChatMessageRequest;
import com.yu.chatliteserver.service.IChatMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.chatliteserver.util.DateUtil;
import com.yu.chatliteserver.vo.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-23 05:17:00
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements IChatMessageService {

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Override
    public List<ChatMessage> getByUserId(String userId) {
        return baseMapper.selectByUserId(userId);
    }


    @Override
    public boolean updateChatMessageById(ChatMessageRequest chatMessageRequest, String id) {
        ChatMessage chatMessage = new ChatMessage();
        BeanUtils.copyProperties(chatMessageRequest, chatMessage);
        chatMessage.setId(id);
        chatMessage.setUpdateTime(LocalDateTime.now());
        int rows = chatMessageMapper.updateById(chatMessage);
        return rows > 0;
    }

    @Override
    public List<ChatMessage> listByUserId(String userId) {
        LambdaQueryWrapper<ChatMessage> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ChatMessage::getUserId, userId);
        return this.list();
    }
}
