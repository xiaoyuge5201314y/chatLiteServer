/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:01:51
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-05-03 09:35:52
 */
package com.yu.chatliteserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.chatliteserver.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService extends IService<User> {


    boolean saveUser(User user);

    boolean updateUser(User user, Integer version);

    boolean deleteUser(String id);

    boolean authenticate(String username, String password); // 校验账号


    String generateToken(String username);
    String generateRefreshToken(String username);

    boolean registerUser(User user);

    User loadUserByUsername(String username);

    int getTimesById(String id);
    int getTimesByUsername(String username);

    void setAvailableTimes(String id, int i);
    void resetUseTimes();
}