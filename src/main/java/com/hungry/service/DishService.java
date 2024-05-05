package com.hungry.service;

import com.hungry.pojo.DTO.DishDto;
import com.hungry.pojo.PageInfo;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Dish;

public interface DishService{
    PageInfo<Dish> MyPageList(DishDto dishDto);

    Result<String> editDish(Dish dish,Integer id);

    Result<String> addDish(Dish dish);

    Result<String> deleteDish(Integer id);

    Result<String> editStatus(int status,Integer id);

    Result<Dish> queryDishById(Integer id);
}
