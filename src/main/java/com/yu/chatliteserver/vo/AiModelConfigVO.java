package com.yu.chatliteserver.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(value = "UserVO", description = "ai模型配置响应实体实体")
public class AiModelConfigVO {
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "模型类型")
    private String type;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("price")
    private Double price;

    @ApiModelProperty("user_id")
    private String userId;

    @ApiModelProperty("description")
    private String description;

    @ApiModelProperty("model_id")
    private String modelId;

    @ApiModelProperty("useCount")
    private String useCount;

    @ApiModelProperty("pLength")
    private Double pLength;

    @ApiModelProperty("temperature")
    private Double temperature;

    @ApiModelProperty("max_tokens")
    private Double maxTokens;
}


