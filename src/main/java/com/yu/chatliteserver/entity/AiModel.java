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
import org.springframework.data.annotation.Id;

/**
 * <p>
 * AiModel基础表
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-17 22:45:05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_ai_model")
@ApiModel(value = "AiModel对象", description = "")
public class AiModel extends Model<AiModel> {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("name")
    private String name;

    @TableField("type")
    private String type;

    @TableField("price")
    private Double price;

    @TableField("create_time")
    private LocalDateTime createTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
