/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:03:12
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-06-22 00:58:35
 */
package com.yu.chatliteserver.service;

import com.yu.chatliteserver.entity.ChatConversation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-04-09 15:03:11
 */
public interface IChatConversationService extends IService<ChatConversation> {


    ChatConversation addConversation();

    ChatConversation addSenceConversation(String scenariosId);

}
