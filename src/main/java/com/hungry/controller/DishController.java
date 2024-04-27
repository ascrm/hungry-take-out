package com.hungry.controller;

import com.hungry.pojo.PageInfo;
import com.hungry.pojo.Result;
import com.hungry.service.impl.DishServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
@RequiredArgsConstructor
@Slf4j
public class DishController {
    private final DishServiceImpl dishService;
    @GetMapping
    public Result<PageInfo> page(@RequestParam int pageNum, @RequestParam int pageSize) {
        return Result.success(dishService.MyPageList(pageNum,pageSize));
    }
}
