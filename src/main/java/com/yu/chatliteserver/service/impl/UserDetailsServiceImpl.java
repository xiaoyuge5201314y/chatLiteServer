package com.yu.chatliteserver.service.impl;

import com.yu.chatliteserver.auth.AdminUser;
import com.yu.chatliteserver.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // MOCK 模拟从数据库 根据用户名查询用户
        User adminUser = new User();
        // 构建 UserDetails 的实现类 => AdminUser
        return new AdminUser(adminUser.getUsername());
    }

}
