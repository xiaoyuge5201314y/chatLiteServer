package com.yu.chatliteserver.vo;

import lombok.Data;
import lombok.Generated;

@Data
public class R {
    private Integer code;
    private String message;

    private Object data;

    public R(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static R ok() {
        return new R(200, "请求成功");
    }

    public static R ok(Object data) {
        return new R(200, "请求成功", data);
    }

    public static R error(int code, String message) {
        return new R(code, message);
    }
    public static R error(String message) {
        return new R(500, message);
    }
    public static R error() {
        return new R(500,"未知错误请联系管理员");
    }
}
