package com.yu.chatliteserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2023-04-09 15:03:11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_chat_conversation")
@ApiModel(value = "ChatConversation对象", description = "")
public class ChatConversation extends Model<ChatConversation> {

    @TableId("id")
    private String id;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("user_id")
    private String userId;

    @TableField(value = "ai_model_id", exist = true)
    @ApiModelProperty(value = "AI model ID")
    private String aiModelId;

    @TableField("version")
    private Integer version;

    @TableField("sence_id")
    private String senceId;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}