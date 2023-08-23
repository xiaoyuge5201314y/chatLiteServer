package com.yu.chatliteserver.service.impl;

import com.yu.chatliteserver.entity.UserChat;
import com.yu.chatliteserver.mapper.UserChatMapper;
import com.yu.chatliteserver.service.IUserChatService;

import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-05-27 23:48:35
 */
@Service
public class UserChatServiceImpl extends ServiceImpl<UserChatMapper, UserChat> implements IUserChatService {

    // 根据用户id查询账号信息
    @Override
    public UserChat getByUserId() {
        LambdaQueryWrapper<UserChat> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserChat::getUserId, StpUtil.getLoginId());
        return getOne(queryWrapper);

    }

}
