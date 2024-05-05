package com.hungry.controller;

import com.hungry.pojo.DTO.LoginDto;
import com.hungry.pojo.DTO.RegisterDto;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Admin;
import com.hungry.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDto loginDto) {
        String jwt = adminService.login(loginDto);
        return Result.success("登录成功",jwt);
    }

    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success(adminService.logout());
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDto registerDto) {
        return Result.success(adminService.register(registerDto));
    }

    @PostMapping("/mail")
    public Result<String> sendMail(@RequestBody Admin admin) {
        return adminService.sendMail(admin);
    }
}
