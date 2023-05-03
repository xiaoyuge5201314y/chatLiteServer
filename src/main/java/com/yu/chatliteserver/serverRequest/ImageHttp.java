/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-23 20:59:23
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-04-24 10:41:45
 */
package com.yu.chatliteserver.serverRequest;
import com.yu.chatliteserver.dto.openAiHttp.painting.ImageRequestDTO;
import com.yu.chatliteserver.util.OpenAiHttp;

import kong.unirest.HttpResponse;

public class ImageHttp {

    public static Object generateImage (ImageRequestDTO chatBody) {
        HttpResponse response = OpenAiHttp.request(chatBody, "v1/images/generations");
        return response.getBody();
    }
}
