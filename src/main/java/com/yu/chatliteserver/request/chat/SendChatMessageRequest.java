package com.yu.chatliteserver.request.chat;

import lombok.Data;

@Data
public class SendChatMessageRequest {
    private String content; // 消息内容
    private String conversationId; // 会话id
}
