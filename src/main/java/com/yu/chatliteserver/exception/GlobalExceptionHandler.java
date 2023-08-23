/*
 * @Description: 
 * @Version: 1.0
 * @Author: wudongyu
 * @Date: 2023-05-27 20:59:19
 * @LastEditors: wudongyu
 * @LastEditTime: 2023-08-23 14:51:51
 */
package com.yu.chatliteserver.exception;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.chatliteserver.vo.R;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;

/**
 * @description: 自定义异常处理
 * @author: DT
 * @date: 2021/4/19 21:51
 * @version: v1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 全局异常拦截（拦截项目中的NotLoginException异常）
    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    public R handlerNotLoginException(NotLoginException nle)
            throws Exception {

        // 打印堆栈，以供调试
        nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return R.error(message);
    }

    /**
     * 处理其他异常
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R exceptionHandler(Request req, Exception e) {
        logger.error("未知异常！原因是:", e);
        return R.error();
    }

}
