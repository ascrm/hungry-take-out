package com.hungry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hungry.pojo.PageInfo;
import com.hungry.pojo.entity.EntityDish;

public interface DishService extends IService<EntityDish> {
    PageInfo<EntityDish> MyPageList(int pageNum, int pageSize);
}
