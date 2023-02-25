package com.yu.chatliteserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.chatliteserver.entity.User;
import com.yu.chatliteserver.mapper.UserMapper;
import com.yu.chatliteserver.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 吴东宇
 * @since 2023-02-16 01:26:07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean saveUser(User user) {
        // 检查用户名是否重复
        QueryWrapper<User> usernameWrapper = new QueryWrapper<>();
        usernameWrapper.eq("username", user.getUsername());
        User usernameUser = this.getOne(usernameWrapper);
        if (usernameUser != null) {
            throw new RuntimeException("用户名已存在");
        }    // 检查邮箱是否重复
        QueryWrapper<User> emailWrapper = new QueryWrapper<>();
        emailWrapper.eq("email", user.getEmail());
        User emailUser = this.getOne(emailWrapper);
        if (emailUser != null) {
            throw new RuntimeException("邮箱已存在");
        }

        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return this.save(user);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user, Integer version) {

        // 检查邮箱是否重复
        QueryWrapper<User> emailWrapper = new QueryWrapper<>();
        emailWrapper.eq("email", user.getEmail());
        User emailUser = this.getOne(emailWrapper);
        if (emailUser != null && !emailUser.getId().equals(user.getId())) {
            throw new RuntimeException("邮箱已存在");
        }

        // 不能修改用户名
        User oldUser = this.getById(user.getId());
        user.setUsername(oldUser.getUsername());
        user.setUpdateTime(LocalDateTime.now());
        return this.updateById(user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUser(String id) {
        return this.removeById(id);
    }
}
