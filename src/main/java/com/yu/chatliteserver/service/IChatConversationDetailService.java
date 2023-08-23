/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:03:12
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-06-22 00:48:32
 */
package com.yu.chatliteserver.service;

import com.yu.chatliteserver.entity.ChatConversationDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-04-09 15:03:12
 */
public interface IChatConversationDetailService extends IService<ChatConversationDetail> {

    ChatConversationDetail getByConversationId(String conversationId);

    ChatConversationDetail addConversationDetail(String conversationId);
    ChatConversationDetail addConversationDetail(String conversationId, String senceId);

}
