package com.yu.chatliteserver.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessageRequest {
    private String prompt;
    private String messageId;
    private String aiModelId;
}