package com.yu.chatliteserver.dto.openAiHttp.chat;

import lombok.Data;

@Data
public class Choice {
    private String finishReason;
    private Long index;
    private Message message;
}