package com.hungry.mapper;

import com.hungry.pojo.DTO.DishDto;
import com.hungry.pojo.entity.Dish;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {

    List<Dish> getListByPage(DishDto dishDto);

    @Update("update dish set name = #{name}, price = #{price}, category = #{category}, image=#{image}," +
            "status=#{status},description=#{description}, update_time=#{updateTime} where id = #{id}")
    void editDish(Dish dish);

    @Insert("insert dish set id=#{id}, name = #{name}, price = #{price}, category=#{category}, image=#{image}," +
            "status = #{status},description=#{description},create_time=#{createTime},update_time=#{updateTime}")
    void addDish(Dish dish);

    @Delete("delete from dish where id=#{id}")
    void deleteDish(Integer id);

    @Update("update dish set status = #{status} where id = #{id}")
    void editStatus(int status,Integer id);

    @Select("select count(*) from dish")
    long getTotal();

    @Select("select * from dish where id=#{id}")
    Dish queryDishById(Integer id);
}
