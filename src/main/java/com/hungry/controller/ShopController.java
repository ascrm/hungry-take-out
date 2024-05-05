package com.hungry.controller;

import com.hungry.pojo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.hungry.constant.MessageConstant.SHOP_UPDATE_STATUS;
import static com.hungry.constant.RedisKeyConstant.SHOP_STATUS;

/**
 * @Author:ascrm
 * @Date:2024/4/27
 */
@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
@Slf4j
public class ShopController {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 获取店铺状态
     */
    @GetMapping("/status")
    public Result<String> getStatus(){
        String status = stringRedisTemplate.opsForValue().get(SHOP_STATUS);
        return Result.success(status);
    }

    /**
     * 更新店铺状态
     */
    @PostMapping("/{status}")
    @PreAuthorize("hasAuthority('admin')")
    public Result<String> updateStatus(@PathVariable String status){
        stringRedisTemplate.opsForValue().set(SHOP_STATUS,status);
        return Result.success(SHOP_UPDATE_STATUS);
    }
}
