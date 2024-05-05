package com.hungry.service.impl;


import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hungry.Enum.OperationType;
import com.hungry.annotation.AutoFill;
import com.hungry.mapper.DishMapper;
import com.hungry.pojo.DTO.DishDto;
import com.hungry.pojo.PageInfo;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Dish;
import com.hungry.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.hungry.constant.MessageConstant.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishMapper dishMapper;

    public PageInfo<Dish> MyPageList(DishDto dishDto) {
        String name=dishDto.getName();
        if(StrUtil.isNotEmpty(name)){
            name="%"+name+"%";
        }
        dishDto.setName(name);


        PageHelper.startPage(dishDto.getPageNum(),dishDto.getPageSize());
        Page<Dish> page = (Page<Dish>)dishMapper.getListByPage(dishDto);

        PageInfo<Dish> pageInfo = new PageInfo<>();
        pageInfo.setData(page.getResult());
        pageInfo.setTotal(page.getTotal());
        log.info("数据shi：{}",pageInfo.getData());
        log.info("数据shi：{}",pageInfo.getTotal());
        return pageInfo;
    }

    @AutoFill(OperationType.DISH_UPDATE)
    public Result<String> editDish(Dish dish,Integer id) {
        dish.setId(id);
        dishMapper.editDish(dish);
        return Result.success(DISH_EDIT);
    }

    @AutoFill(OperationType.DISH_INSERT)
    public Result<String> addDish(Dish dish) {
        dishMapper.addDish(dish);
        return Result.success(DISH_ADD);
    }

    public Result<String> deleteDish(Integer id) {
        dishMapper.deleteDish(id);
        return Result.success(DISH_DELETE);
    }

    public Result<String> editStatus(int status,Integer id){
        dishMapper.editStatus(status,id);
        return Result.success(DISH_EDIT_STATUS);
    }

    public Result<Dish> queryDishById(Integer id) {
        Dish dish = dishMapper.queryDishById(id);
        return Result.success(dish);
    }
}
