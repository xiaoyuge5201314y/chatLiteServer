package com.yu.chatliteserver.dto.openAiHttp.chat;

import lombok.Data;

@Data
public class Usage {
    private long completionTokens;
    private long promptTokens;
    private long totalTokens;
}