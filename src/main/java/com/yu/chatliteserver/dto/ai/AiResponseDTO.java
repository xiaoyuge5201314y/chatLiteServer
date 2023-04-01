package com.yu.chatliteserver.dto.ai;

import lombok.Data;

@Data
public class AiResponseDTO {

    private String id;
    private String created;
    private String model;
    private String object;

    private AiResponseChoicesDTO[] choices;
    private AiResponseUsageDTO usage;


}
