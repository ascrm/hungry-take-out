package com.hungry.service;


import com.hungry.pojo.PageInfo;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Orders;

public interface OrderService {
    PageInfo<Orders> MyPageList(int pageNum, int pageSize);

    Result<String> addOrder(Orders orders);

    Result<String> deleteOrder(Integer id);

    Result<String> editOrder(Orders orders, Integer id);

    Result<String> editStatus(int status, Integer id);
}
