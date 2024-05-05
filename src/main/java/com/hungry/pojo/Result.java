package com.hungry.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:ascrm
 * @Date:2024/4/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    /**
     * 状态码  成功：200  失败：0
     */
    private int code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public static <T>Result<T> success() {
        return new Result<>(200,null,null);
    }

    public static <T>Result<T> success(T data) {
        return new Result<>(200,null,data);
    }

    public static <T>Result<T> success(String msg,T data) {
        return new Result<>(200,msg,data);
    }

    public static <T>Result<T> fail() {
        return new Result<>(0,null,null);
    }

    public static <T>Result<T> fail(String msg) {
        return new Result<>(0,msg,null);
    }
}
