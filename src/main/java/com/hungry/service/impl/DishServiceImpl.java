package com.hungry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hungry.mapper.DishMapper;
import com.hungry.pojo.PageInfo;
import com.hungry.pojo.entity.EntityDish;
import com.hungry.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DishServiceImpl extends ServiceImpl<DishMapper,EntityDish> implements DishService {
    private final DishMapper dishMapper;
    public PageInfo<EntityDish> MyPageList(int pageNum, int pageSize) {
        PageInfo<EntityDish> pageInfo = new PageInfo<>(pageNum, pageSize);
        List<EntityDish> listByPage = dishMapper.getListByPage(pageNum, pageSize);
        pageInfo.setData(listByPage);
        pageInfo.setTotal(listByPage.size());
        return pageInfo;
    }

}
