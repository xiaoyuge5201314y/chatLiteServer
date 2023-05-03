package com.yu.chatliteserver.service.impl;

import com.yu.chatliteserver.entity.ChatConversation;
import com.yu.chatliteserver.mapper.ChatConversationMapper;
import com.yu.chatliteserver.service.IChatConversationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
