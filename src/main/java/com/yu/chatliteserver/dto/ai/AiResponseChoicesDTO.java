package com.yu.chatliteserver.dto.ai;

import lombok.Data;

@Data
public class AiResponseChoicesDTO {
    private String finish_reason;
    private int index;
    private String text;
    private Object logprobs;
}
