package com.hungry.controller;

import com.hungry.pojo.LoginDto;
import com.hungry.pojo.Result;
import com.hungry.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public Result<Map<String,String>> login(@RequestBody LoginDto loginDto) {
        return Result.success(adminService.login(loginDto));
    }

    @DeleteMapping("/logout")
    public Result<String> logout() {
        return Result.success(adminService.logout());
    }
}
