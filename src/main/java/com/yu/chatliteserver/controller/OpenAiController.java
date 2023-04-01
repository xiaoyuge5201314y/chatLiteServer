package com.yu.chatliteserver.controller;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.yu.chatliteserver.config.OpenAIConfig;
import com.yu.chatliteserver.request.ChatRequest;
import com.yu.chatliteserver.util.OpenAiHttp;
import com.yu.chatliteserver.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * chatgpt 前端控制器
 * </p>
 *
 * @author 吴东宇
 * @since 2022-06-11 14:26:33
 */
@RestController
@RequestMapping("ai")
@Api(tags = "原生openai相关接口")
public class OpenAiController {
    @ApiOperation(value = "获取答案 这个用于测试", notes = "开发中")
    @PostMapping("result")
    public R getResults(@RequestBody ChatRequest chatRequest) {
        OpenAIConfig openAIConfig = new OpenAIConfig();
        String prompt = chatRequest.getPrompt();
        String resultBody = openAIConfig.getResult(prompt);
        return R.ok(resultBody);
    }

    @ApiOperation(value = "获取所有的model列表", notes = "开发中")
    @GetMapping("modelList")
    public R getModelList() {
        Map<String, String> map = new HashMap();
        String body = OpenAiHttp.getReq("v1/models",map,new JSONObject());
        com.alibaba.fastjson2.JSONObject jsonObject = JSON.parseObject(body);
        Object innerData = jsonObject.get("data");
        return R.ok(innerData);
    }
}
