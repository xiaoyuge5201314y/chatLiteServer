package com.yu.chatliteserver.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "UserRequest", description = "用户请求实体")
public class UserRequest {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    @ApiModelProperty(value = "VIP等级", required = true)
    private Integer vip;

    @ApiModelProperty(value = "版本号", required = true)
    private Integer version;
}
