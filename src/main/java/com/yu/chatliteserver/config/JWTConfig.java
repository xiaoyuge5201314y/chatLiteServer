package com.yu.chatliteserver.config;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JWTConfig
 * @Description jwt
 * @Author 吴东宇
 * @Date 2022/6/12 4:04
 * @Version 1.0
 **/
public class JWTConfig {

    /**
     * 两个常量： 过期时间；秘钥
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /**
     * 生成token字符串的方法
     *
     * @param userId
     * @return
     */
    public static String getJwtToken(Long userId) {
        Map<String, Object> claims = new HashMap();
        claims.put("userId", userId);

        String JwtToken = Jwts.builder()
                //JWT头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS2256")
                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject("lin-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //设置token主体信息，存储用户信息
                .signWith(SignatureAlgorithm.HS256, SECRET)
                //.signWith(SignatureAlgorithm.ES256, SECRET)
                .setClaims(claims)
                .compact();
        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     *
     * @Param jwtToken
     */
    public static Map<String, Object> checkToken(String jwtToken) {
        try {
            //验证token
            Jwt parse = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken);
            return (Map<String, Object>) parse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断token是否存在与有效
     *
     * @Param request
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     *
     * @Param request
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return (String) body.get("id");
    }


    public static void main(String[] args) {
//        String token = JWTConfig.getJwtToken(1L);
//        System.out.println(token);
//        Map<String,Object> b = JWTConfig.checkToken(token);
//        System.out.println(b.get("userId"));
    }
}
