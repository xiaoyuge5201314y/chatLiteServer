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
 * @since 2023-05-27 23:48:35
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_user_chat")
@ApiModel(value = "UserChat对象", description = "")
public class UserChat extends Model<UserChat> {

    @TableId("id")
    private String id;

    @TableField("all_tokens")
    private Integer allTokens;

    @TableField("available_tokens")
    private Integer availableTokens;

    @TableField("user_id")
    private String userId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
