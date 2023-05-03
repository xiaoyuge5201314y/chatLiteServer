package com.yu.chatliteserver.vo.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@ApiModel(value = "UserVO", description = "ai模型配置响应实体实体")
public class ChatResponseVO {
    @ApiModelProperty("会话id")
    private String conversationId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("角色")
    private String role;
}


