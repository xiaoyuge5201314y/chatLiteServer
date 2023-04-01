package com.yu.chatliteserver.vo;

import com.yu.chatliteserver.entity.ChatPrompt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ApiModel(value = "UserVO", description = "消息响应实体")
public class ChatMessageVO {
    @ApiModelProperty(value = "消息ID")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "模型id")
    private String aiModelId;

    @ApiModelProperty(value = "历史消息")
    private List<ChatPrompt> prompts;
}
