/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:01:51
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-06-21 18:56:49
 */
package com.yu.chatliteserver.service;

import com.yu.chatliteserver.entity.ChatPrompt;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.chatliteserver.vo.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-03-10 03:04:09
 */
public interface IChatPromptService extends IService<ChatPrompt> {

    List<ChatPrompt> getList (String messageId);

    void add(String messageId, String role, String prompt);
    
    
}
