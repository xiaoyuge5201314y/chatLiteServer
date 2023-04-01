package com.yu.chatliteserver.dto.ai;

import lombok.Data;

@Data
public class AiResponseUsageDTO {
    private Integer completion_tokens;
    private Integer prompt_tokens;
    private Integer total_tokens;
}
