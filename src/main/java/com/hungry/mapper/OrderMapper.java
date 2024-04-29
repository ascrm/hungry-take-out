package com.hungry.mapper;

import com.hungry.pojo.entity.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from orders limit #{pageNum},#{pageSize}")
    List<Orders> getListByPage(int pageNum, int pageSize);


    @Select("select count(*) from orders")
    long getTotal();

    @Insert("insert into orders (number,status,user_id,address,type,start_time,end_time) " +
            "values (#{number},#{status},#{userId},#{address},#{type},#{startTime},#{endTime})")
    void addOrder(Orders orders);

    @Delete("delete from orders where id=#{id}")
    void deleteOrder(Integer id);

    @Update("update orders set number = #{number}, user_id = #{userId}, type = #{type}, end_time=#{endTime} where id = #{id}")
    void editOrder(Orders orders);

    @Update("update orders set status = #{status} where id = #{id}")
    void editStatus(int status, Integer id);
}
