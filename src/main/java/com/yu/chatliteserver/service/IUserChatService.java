/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-05-27 23:48:35
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-06-21 21:10:47
 */
/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-05-27 23:48:35
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-05-27 23:52:11
 */
package com.yu.chatliteserver.service;

import com.yu.chatliteserver.entity.UserChat;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-05-27 23:48:35
 */
public interface IUserChatService extends IService<UserChat> {

    UserChat getByUserId();

}
