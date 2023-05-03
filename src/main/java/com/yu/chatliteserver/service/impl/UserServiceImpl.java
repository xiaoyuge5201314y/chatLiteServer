package com.yu.chatliteserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.chatliteserver.entity.User;
import com.yu.chatliteserver.mapper.UserMapper;
import com.yu.chatliteserver.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yu.chatliteserver.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 新增用户 （这个是管理后台手动新增的 和注册一样效果）
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public boolean saveUser(User user) {
        // 检查用户名是否重复
        QueryWrapper<User> usernameWrapper = new QueryWrapper<>();
        usernameWrapper.eq("username", user.getUsername());
        User usernameUser = this.getOne(usernameWrapper);
        if (usernameUser != null) {
            return false;
//            throw new RuntimeException("用户名已存在");
        }
        // 检查邮箱是否重复
        QueryWrapper<User> emailWrapper = new QueryWrapper<>();
        emailWrapper.eq("email", user.getEmail());
        User emailUser = this.getOne(emailWrapper);
        if (emailUser != null) {
            return false;
//            throw new RuntimeException("邮箱已存在");
        }
        // 将加密后的密码存储到数据库
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setVip(1);
        user.setVersion(0);
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
        // 不能修改用户名
        if (!oldUser.getVersion().equals(version)) {
            throw new RuntimeException("数据已过期，请刷新数据后再尝试更新。");
        }

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

    /**
     * 校验账号
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean authenticate(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper
        );
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    /**
     * 根据用户名生成token
     *
     * @param username
     * @return
     */
    @Override
    public String generateToken(String username) {
        // 使用 JWT 或其他库生成 token，此处省略具体实现
        return TokenUtil.generateToken(username);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public boolean registerUser(User user) {
        return this.saveUser(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        QueryWrapper<User> usernameWrapper = new QueryWrapper<>();
        usernameWrapper.eq("username", username);
        User usernameUser = this.getOne(usernameWrapper);
        if (usernameUser != null) {
            return usernameUser;
        }
        return null;
    }

    @Override
    public int getTimesById(String id) {
        return this.getById(id).getAvailableTimes();
    }

    @Override
    public int getTimesByUsername(String username) {
        QueryWrapper<User> usernameWrapper = new QueryWrapper<>();
        usernameWrapper.eq("username", username);
        User usernameUser = this.getOne(usernameWrapper);
        return usernameUser.getAvailableTimes();
    }

    @Override
    /**
     * 设置剩余聊天次数
     */
    public void setAvailableTimes(String userId, int i) {
        User user = this.getById(userId);
        user.setAvailableTimes(i);
        this.updateUser(user,user.getVersion());

    }

    @Override
    public void resetUseTimes() {
        // 查询所有用户
        List<User> users = this.list();

        // 更新每个用户的 useTimes
        for (User user : users) {
            int newUseTimes = user.getAvailableTimes() + 10;
            if (newUseTimes > 10) {
                newUseTimes = 10;
            }
            user.setAvailableTimes(newUseTimes);
            this.updateById(user);
        }
    }

    @Scheduled(cron = "0 0 0-23 * * ?")
    public void scheduledResetUseTimes() {
        resetUseTimes();
    }
}
