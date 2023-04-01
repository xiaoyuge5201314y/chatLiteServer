package com.yu.chatliteserver.config;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class OpenAIConfig {

    //    JSONObject json = new JSONObject();
//    //添加我们需要输入的内容
    private Map<String, String> headers;
    private String model;
    private String prompt;
    private double temperature;
    private int max_tokens;
    private int top_p;
    private double frequency_penalty;
    private double presence_penalty;
    private String proxyHost = "127.0.0.1";
    private int proxyPort = 4780;

    public OpenAIConfig() {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        this.setHeaders(headers);

        this.setModel("text-davinci-003");
        this.setPrompt("你真能");
        this.setTemperature(1);
        this.setMax_tokens(2048);
        this.setTop_p(1);
        this.setFrequency_penalty(1);
        this.setPresence_penalty(1);
    }

    public String getResult (String prompt) {
        return this.send(prompt);
    }

    private String send(String prompt) {
        System.out.println(prompt);
        JSONObject json = new JSONObject();
        json.set("model",this.model);
        //添加我们需要输入的内容
        json.set("prompt",prompt);
        json.set("temperature",this.temperature);
        json.set("max_tokens",this.max_tokens);
        json.set("top_p",this.top_p);
        json.set("frequency_penalty",this.frequency_penalty);
        json.set("presence_penalty",this.presence_penalty);
        HttpResponse response = HttpRequest.post("https://api.openai.com/v1/14")
                .headerMap(this.headers, false)
                .bearerAuth("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")
                .body(String.valueOf(json))
                .timeout(5 * 60 * 1000)
                .setHttpProxy(proxyHost,proxyPort)
                .execute();
        System.out.println(response);
        return response.body();
    }
}
