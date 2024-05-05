package com.hungry.service.impl;

import com.alibaba.fastjson.JSON;
import com.hungry.pojo.Result;
import com.hungry.utils.security.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String json ;
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        if(authException instanceof BadCredentialsException){
            Result result = new Result(HttpStatus.PAYMENT_REQUIRED.value(), "用户名或密码错误");
            response.setStatus(HttpStatus.PAYMENT_REQUIRED.value());
            json = JSON.toJSONString(result);
        }else{
            Result result = new Result(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            json = JSON.toJSONString(result);
        }
        response.getWriter().print(json);
    }
}


