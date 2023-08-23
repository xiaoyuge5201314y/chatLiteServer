/**
 * @Description: 用户服务接口
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-04-09 15:01:51
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-05-27 19:05:44
 */
package com.yu.chatliteserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.chatliteserver.entity.User;

public interface IUserService extends IService<User> {

    /**
     * 保存用户
     * @param user 用户实体
     * @return 是否保存成功
     */
    boolean saveUser(User user);

    /**
     * 更新用户
     * @param user 用户实体
     * @param version 版本号
     * @return 是否更新成功
     */
    boolean updateUser(User user, Integer version);

    /**
     * 删除用户
     * @param id 用户id
     * @return 是否删除成功
     */
    boolean deleteUser(String id);

    /**
     * 校验账号
     * @param username 用户名
     * @param password 密码
     * @return 用户实体
     */
    User authenticate(String username, String password);

    /**
     * 生成token
     * @param username 用户名
     * @return token
     */
    String generateToken(String username);

    /**
     * 生成refreshToken
     * @param username 用户名
     * @return refreshToken
     */
    String generateRefreshToken(String username);

    /**
     * 注册用户
     * @param user 用户实体
     * @return 是否注册成功
     */
    boolean registerUser(User user);

    /**
     * 根据用户名加载用户
     * @param username 用户名
     * @return 用户实体
     */
    User loadUserByUsername(String username);

    /**
     * 根据id获取使用次数
     * @param id 用户id
     * @return 使用次数
     */
    int getTimesById(String id);

    /**
     * 根据用户名获取使用次数
     * @param username 用户名
     * @return 使用次数
     */
    int getTimesByUsername(String username);

    /**
     * 设置可用次数
     * @param id 用户id
     * @param i 可用次数
     */
    void setAvailableTimes(String id, int i);

    /**
     * 重置使用次数
     */
    void resetUseTimes();

    /**
     * 获取当前登录用户id
     */
    String getLoginId();
}

