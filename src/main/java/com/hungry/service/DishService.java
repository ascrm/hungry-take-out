package com.hungry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hungry.pojo.PageInfo;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Dish;

public interface DishService extends IService<Dish> {
    PageInfo<Dish> MyPageList(int pageNum, int pageSize);

    Result<String> editDish(Dish dish,Integer id);

    Result<String> addDish(Dish dish);

    Result<String> deleteDish(Integer id);
}
