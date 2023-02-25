package com.yu.chatliteserver.request;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName LoginRequest
 * @Description 登录参数
 * @Author 吴东宇
 * @Date 2022/6/12 4:40
 * @Version 1.0
 **/
@Data
@Builder
public class ChatRequest {

    private String prompt;

    private String type;

}
