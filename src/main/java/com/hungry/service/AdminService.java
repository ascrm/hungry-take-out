package com.hungry.service;

import com.hungry.pojo.LoginDto;
import com.hungry.pojo.entity.Admin;

import java.util.Map;

public interface AdminService {
    Map<String,String> login(LoginDto loginDto);

    String logout();

    String register(Admin admin);
}
