//package com.yu.chatliteserver.config;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.yu.chatliteserver.entity.User;
//import com.yu.chatliteserver.service.IUserService;
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @ClassName JWTConfig
// * @Description jwt
// * @Author 吴东宇
// * @Date 2022/6/12 4:04
// * @Version 1.0
// **/
//@Component
//public class JWTConfig {
//
//    /**
//     * 两个常量： 过期时间；秘钥
//     */
//    public  final long EXPIRE = 1000 * 60 * 60 * 24;
//    public  final String SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";
//
//    @Autowired
//    private IUserService iUserService;
//
//
//    /**
//     * 生成token字符串的方法
//     *
//     * @param userName
//     * @return
//     */
//    public  String getJwtToken(String userName) {
//        Map<String, Object> claims = new HashMap();
//        claims.put("userId", userName);
//
//        String JwtToken = Jwts.builder()
//                //JWT头信息
//                .setHeaderParam("typ", "JWT")
//                .setHeaderParam("alg", "HS2256")
//                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
//                .setSubject("lin-user")
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
//                //设置token主体信息，存储用户信息
//                .signWith(SignatureAlgorithm.HS256, SECRET)
//                //.signWith(SignatureAlgorithm.ES256, SECRET)
//                .setClaims(claims)
//                .compact();
//        return JwtToken;
//    }
//
//    /**
//     * 判断token是否存在与有效
//     *
//     * @Param jwtToken
//     */
//    public  Map<String, Object> checkToken(String jwtToken) {
//        try {
//            //验证token
//            Jwt parse = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken);
//            return (Map<String, Object>) parse.getBody();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public Authentication getAuthentication(String token) {
//        // 从 JWT Token 中解析出用户名
//        String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
//        // 根据用户名查询用户信息
//        QueryWrapper<User> usernameWrapper = new QueryWrapper<>();
//        usernameWrapper.eq("username", username);
//        User usernameUser = iUserService.getOne(usernameWrapper);
//        // 将用户信息封装为一个 Authentication 对象并返回
//        return new UsernamePasswordAuthenticationToken(usernameUser, "");
//    }
//
//    /**
//     * 判断token是否存在与有效
//     *
//     * @Param request
//     */
//    public  boolean checkToken(HttpServletRequest request) {
//        try {
//            String token = request.getHeader("token");
//            if (StringUtils.isEmpty(token)) {
//                return false;
//            }
//            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 根据token获取会员id
//     *
//     * @Param request
//     */
//    public  String getMemberIdByJwtToken(HttpServletRequest request) {
//        String token = request.getHeader("token");
//        if (StringUtils.isEmpty(token)) {
//            return "";
//        }
//        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
//        Claims body = claimsJws.getBody();
//        return (String) body.get("id");
//    }
//
//
//    public  void main(String[] args) {
////        String token = JWTConfig.getJwtToken(1L);
////        System.out.println(token);
////        Map<String,Object> b = JWTConfig.checkToken(token);
////        System.out.println(b.get("userId"));
//    }
//
//
//}
