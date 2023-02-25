package com.yu.chatliteserver.service;

import com.yu.chatliteserver.entity.ChatMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.chatliteserver.request.ChatMessageRequest;
import com.yu.chatliteserver.vo.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-23 05:17:00
 */
public interface IChatMessageService extends IService<ChatMessage> {

    List<ChatMessage> getByUserId(String userId);

    boolean updateChatMessageById(ChatMessageRequest chatMessageRequest, String id);

}
