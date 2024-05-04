package com.hungry.service;

import com.hungry.pojo.DTO.LoginDto;
import com.hungry.pojo.DTO.RegisterDto;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Admin;

import java.util.Map;

public interface AdminService {
    Map<String,String> login(LoginDto loginDto);

    String logout();

    String register(RegisterDto registerDto);

    Result<String> sendMail(Admin admin);
}
