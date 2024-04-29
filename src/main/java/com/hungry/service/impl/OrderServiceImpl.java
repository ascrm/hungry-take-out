package com.hungry.service.impl;

import com.hungry.Enum.OperationType;
import com.hungry.annotation.AutoFill;
import com.hungry.mapper.OrderMapper;
import com.hungry.pojo.PageInfo;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Orders;
import com.hungry.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.hungry.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    public PageInfo<Orders> MyPageList(int pageNum, int pageSize) {
        PageInfo<Orders> pageInfo = new PageInfo<>();
        pageInfo.setData(orderMapper.getListByPage(pageNum-1, pageSize));
        pageInfo.setTotal(orderMapper.getTotal());
        return pageInfo;
    }
    @Override
    @AutoFill(OperationType.ORDER_INSERT)
    public Result<String> addOrder(Orders orders) {
        orderMapper.addOrder(orders);
        return Result.success(ORDER_ADD);
    }

    @Override
    public Result<String> deleteOrder(Integer id) {
        orderMapper.deleteOrder(id);
        return Result.success(ORDER_DELETE);
    }

    @Override
    @AutoFill(OperationType.ORDER_UPDATE)
    public Result<String> editOrder(Orders orders, Integer id) {
        orders.setId(id);
        orderMapper.editOrder(orders);
        return Result.success(ORDER_EDIT);
    }

    @Override
    public Result<String> editStatus(int status, Integer id) {
        orderMapper.editStatus(status,id);
        return Result.success(ORDER_EDIT_STATUS);
    }
}
