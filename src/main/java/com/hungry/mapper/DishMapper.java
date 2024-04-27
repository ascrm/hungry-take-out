package com.hungry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hungry.pojo.entity.EntityDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<EntityDish> {
    List<EntityDish> getListByPage(int pageNum, int pageSize);
}
