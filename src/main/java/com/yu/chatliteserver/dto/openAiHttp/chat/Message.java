package com.yu.chatliteserver.dto.openAiHttp.chat;

import lombok.Data;

@Data
public class Message {
    private String content;
    private String role;
}
