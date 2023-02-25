package com.yu.chatliteserver.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-23 05:17:00
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_chat_message")
@ApiModel(value = "ChatMessage对象", description = "")
public class ChatMessage extends Model<ChatMessage> {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("prompt")
    private JsonNode prompt;  // prompt字段使用JsonNode类型存储JSON数据

    @TableField("user_id")
    private String userId;

    @TableField("ai_model_id")
    private String aiModelId;

//    @Version
    private Integer version;


    @Override
    public Serializable pkVal() {
        return this.id;
    }
    public String getPrompt() {
        try {
            return new ObjectMapper().writeValueAsString(prompt);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON序列化异常", e);
        }
    }

    public void setPrompt(String prompt) {
        try {
            this.prompt = new ObjectMapper().readValue(prompt, JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON反序列化异常", e);
        }
    }
}
