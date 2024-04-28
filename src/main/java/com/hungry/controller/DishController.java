package com.hungry.controller;

import com.hungry.pojo.PageInfo;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Dish;
import com.hungry.service.impl.DishServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
@Slf4j
public class DishController {
    private final DishServiceImpl dishService;

    /*TODO 问题
    1. num为 1 的时候是从第二条数据开始查的
    2. 返回的total总记录数，是表里面的所有数据，而不是返回的记录数的总数居
    3. pageInfo只需要 total 和 data 两个属性
     */
    @GetMapping("/page")
    public Result<PageInfo<Dish>> page(@RequestParam int pageNum, @RequestParam int pageSize) {
        return Result.success(dishService.MyPageList(pageNum,pageSize));
    }

    @PostMapping("/edit/{id}")
    public Result<String> editDish(@RequestBody Dish dish,@PathVariable Integer id){
        return dishService.editDish(dish,id);
    }

    @PutMapping("/add")
    public Result<String> addDish(@RequestBody Dish dish){
        return dishService.addDish(dish);
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteDish(@PathVariable Integer id){
        return dishService.deleteDish(id);
    }

    @PostMapping("/{status}/{id}")
    public Result<String> editStatus(@PathVariable int status,
                                     @PathVariable Integer id){
        return dishService.editStatus(status,id);
    }
}
