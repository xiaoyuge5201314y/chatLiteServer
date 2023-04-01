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
 * @since 2023-03-10 03:04:09
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_chat_prompt")
@ApiModel(value = "ChatPrompt对象", description = "")
public class ChatPrompt extends Model<ChatPrompt> {

    @TableId("id")
    private String id;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("prompt_role")
    private String promptRole;

    @TableField("content")
    private String content;

    @TableField("message_id")
    private String messageId;

    @TableField("ord")
    private Integer ord;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
