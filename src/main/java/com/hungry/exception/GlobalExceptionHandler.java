package com.hungry.exception;

import com.hungry.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
import java.sql.SQLException;

@ControllerAdvice
@Slf4j
/**
 * 全局异常处理
 */
public class GlobalExceptionHandler {
    @ExceptionHandler(ConnectException.class)
    public Result<String> handleConnectException(ConnectException e){
        log.info("无法连接到服务器",e);
        return Result.fail("无法连接到服务器");
    }

    @ExceptionHandler(SQLException.class)
    public Result<String> handleSQLException(SQLException e){
        log.info("SQL错误",e);
        return Result.fail("后端SQL错误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        log.info("数据请求不可读",e);
        return Result.fail("数据请求不可读"+e.getMessage());
    }

}
