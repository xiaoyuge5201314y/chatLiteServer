package com.yu.chatliteserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.chatliteserver.entity.User;

public interface IUserService extends IService<User> {


    boolean saveUser(User user);

    boolean updateUser(User user, Integer version);

    boolean deleteUser(String id);
}