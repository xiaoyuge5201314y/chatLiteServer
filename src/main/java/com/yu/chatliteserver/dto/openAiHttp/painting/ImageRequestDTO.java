/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-23 21:06:19
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-04-23 21:16:16
 */
/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-23 21:06:19
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-04-23 21:10:27
 */
package com.yu.chatliteserver.dto.openAiHttp.painting;

import lombok.Data;


@Data
public class ImageRequestDTO {
   /**
     * 要生成的图像数。必须介于 1 和 10 之间。
     */
    private Long n;
    /**
     * 所需图像的文本描述。最大长度为 1000 个字符。
     */
    private String prompt;
    /**
     * 生成图像的大小。必须是256x256、512x512或 之一1024x1024。
     */
    private String size;
}

