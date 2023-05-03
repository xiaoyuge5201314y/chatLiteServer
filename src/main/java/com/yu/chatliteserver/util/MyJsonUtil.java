package com.yu.chatliteserver.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yu.chatliteserver.dto.openAiHttp.chat.Message;

import java.util.List;

public class MyJsonUtil {

    public static Object toJSONObject(String jsonString) {
        // 使用 JsonParser 将 JSON 字符串解析为 JsonElement
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(jsonString);
        Gson gson = new Gson();
        Object jsonObject = gson.fromJson(jsonElement, Object.class);
        return jsonObject;
    }

    public static String stringify(Object obj) {

        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
}
