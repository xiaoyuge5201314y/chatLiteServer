/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-05-03 10:41:36
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-05-27 19:06:21
 */
package com.yu.chatliteserver.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    private static final String SECRET_KEY = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    public static String generateToken(String userName) {
        Map<String, Object> claims = new HashMap();
        claims.put("userId", userName);
        return Jwts.builder()
                .setClaims(claims)
                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject("lin-user")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // 生成 refresh token
    public static String generateRefreshToken(String userName) {
        Map<String, Object> claims = new HashMap();
        claims.put("userId", userName);
        return Jwts.builder()
                .setClaims(claims)
                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject("lin-user")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }



    private static boolean isTokenExpired(String token) {
        final Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }
}
