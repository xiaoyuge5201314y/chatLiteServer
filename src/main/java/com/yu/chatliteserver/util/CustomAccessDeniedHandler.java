package com.yu.chatliteserver.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 设置 HTTP 状态码为 403
        response.setContentType("application/json;charset=UTF-8");

        // 自定义返回的错误信息
        String errorMessage = "您没有权限访问此资源";

        // 将错误信息写入响应流
        response.getWriter().write(errorMessage);
    }
}
