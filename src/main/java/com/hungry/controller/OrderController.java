package com.hungry.controller;

import com.hungry.pojo.PageInfo;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Orders;
import com.hungry.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/page")
    public Result<PageInfo<Orders>> page(@RequestParam int pageNum, @RequestParam int pageSize) {
        return Result.success(orderService.MyPageList(pageNum,pageSize));
    }

    @PutMapping("/add")
    public Result<String> addOrder(@RequestBody Orders orders){
        log.info("添加订单");
        return orderService.addOrder(orders);
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteOrder(@PathVariable Integer id){
        return orderService.deleteOrder(id);
    }

    @PostMapping("/edit/{id}")
    public Result<String> editOrder(@RequestBody Orders orders,@PathVariable Integer id){
        return orderService.editOrder(orders,id);
    }

    @PostMapping("/{status}/{id}")
    public Result<String> editStatus(@PathVariable int status,
                                     @PathVariable Integer id){
        return orderService.editStatus(status,id);
    }

}
