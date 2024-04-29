package com.hungry.service;

import com.hungry.pojo.LoginDto;

import java.util.Map;

public interface AdminService {
    Map<String,String> login(LoginDto loginDto);

    String logout();
}
