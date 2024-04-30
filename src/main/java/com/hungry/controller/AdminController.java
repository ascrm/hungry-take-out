package com.hungry.controller;

import com.hungry.pojo.LoginDto;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Admin;
import com.hungry.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public Result<Map<String,String>> login(@RequestBody LoginDto loginDto) {
        return Result.success("登录成功",adminService.login(loginDto));
    }

    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success(adminService.logout());
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('admin')")
    public Result<String> register(@RequestBody Admin admin) {
        return Result.success(adminService.register(admin));
    }

}
