package com.hungry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hungry.Enum.OperationType;
import com.hungry.annotation.AutoFill;
import com.hungry.mapper.DishMapper;
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
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    private final DishMapper dishMapper;

    public PageInfo<Dish> MyPageList(int pageNum, int pageSize) {
        PageInfo<Dish> pageInfo = new PageInfo<>();
        pageInfo.setData(dishMapper.getListByPage(pageNum-1, pageSize));
        pageInfo.setTotal(dishMapper.getTotal());
        return pageInfo;
    }

    @Override
    @AutoFill(OperationType.DISH_UPDATE)
    public Result<String> editDish(Dish dish,Integer id) {
        dishMapper.editDish(dish,id);
        return Result.success(DISH_EDIT);
    }

    @Override
    @AutoFill(OperationType.DISH_INSERT)
    public Result<String> addDish(Dish dish) {
        dishMapper.addDish(dish);
        return Result.success(DISH_ADD);
    }

    @Override
    public Result<String> deleteDish(Integer id) {
        dishMapper.deleteDish(id);
        return Result.success(DISH_DELETE);
    }

    @Override
    public Result<String> editStatus(int status,Integer id){
        dishMapper.editStatus(status,id);
        return Result.success(DISH_EDIT_STATUS);
    }
}
