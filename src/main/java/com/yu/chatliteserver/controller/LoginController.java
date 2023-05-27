package com.yu.chatliteserver.controller;

import com.yu.chatliteserver.entity.User;
import com.yu.chatliteserver.request.user.LoginRequest;
import com.yu.chatliteserver.request.user.RegisterRequest;
import com.yu.chatliteserver.service.IUserService;
import com.yu.chatliteserver.util.TokenUtil;
import com.yu.chatliteserver.vo.R;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IUserService userService;

    // 处理登录请求
    @PostMapping("/login")
    public R login(@RequestBody LoginRequest loginRequest) {
        // 判断是否校验通过
        boolean isAuthenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            // 根据实际需求生成并返回 token
            String token = userService.generateToken(loginRequest.getUsername());
            String refreshToken = userService.generateRefreshToken(loginRequest.getUsername());
            User user = userService.loadUserByUsername(loginRequest.getUsername());

            Map<String, Object> map = new HashMap();
            map.put("accessToken", token);
            map.put("token", token);
            map.put("userInfo", user);
            map.put("expires", 1000 * 60 * 60 * 10);
            map.put("refreshToken", refreshToken);
            map.put("roles", null);
            return R.ok(map);
        } else {
            return R.error("登录失败，用户名或密码错误");
        }
    }

    // 处理注册请求
    @PostMapping("/register")
    public R register(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());

        boolean success = userService.registerUser(user);

        if (success) {
            return R.ok("注册成功");
        } else {
            return R.error("注册失败，用户名已被占用或其他原因");
        }
    }

    // 获取用户信息
    @GetMapping("/getUserInfo")
    public R getUserInfo () {
        String accessToken = request.getHeader("authorization");
        Claims claims =  TokenUtil.getClaimsFromToken(accessToken);
        Object username = claims.get("userId");
        User user = userService.loadUserByUsername((String)username);
        return R.ok(user);
    }
}






