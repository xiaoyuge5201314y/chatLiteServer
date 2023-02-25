package com.yu.chatliteserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * aiModel配置表
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-17 22:45:05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_ai_model_config")
@ApiModel(value = "AiModelConfig对象", description = "")
public class AiModelConfig extends Model<AiModelConfig> {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("name")
    private String name;

    @TableField("type")
    private String type;

    @TableField("price")
    private Double price;

    @TableField("user_id")
    private String userId;

    @TableField("description")
    private String description;

    @TableField("model_id")
    private String modelId;

    @TableField("useCount")
    private String useCount;

    @TableField("pLength")
    private Double pLength;

    @TableField("temperature")
    private Double temperature;

    @TableField("max_tokens")
    private Double maxTokens;

    @TableField("create_time")
    private LocalDateTime createTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
