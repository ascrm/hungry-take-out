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
