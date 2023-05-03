package com.yu.chatliteserver.dto.openAiHttp.chat;
import lombok.Data;

/**
 * @desc 聊天完成的返回结构
 */
@Data
public class ChatResponseDTO {
        private Choice[] choices;
        private long created;
        private String id;
        private String object;
        private Usage usage;
}
