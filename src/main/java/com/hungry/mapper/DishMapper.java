package com.hungry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hungry.pojo.entity.Dish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
    List<Dish> getListByPage(int pageNum, int pageSize);

    @Update("update dish set name = #{name}, price = #{price}, category = #{category}, image=#{image}," +
            "status=#{status}, update_time=#{updateTime} where id = #{id}")
    void editDish(Dish dish);

    @Insert("insert dish set id=#{id}, name = #{name}, price = #{price}, category=#{category}, image=#{image}," +
            "status = #{status},create_time=#{createTime},update_time=#{updatedTime}")
    void addDish(Dish dish);

    @Delete("delete from dish where id=#{id}")
    void deleteDish(Integer id);

    @Update("update dish set status = #{status} where id = #{id}")
    void editStatus(int status,Integer id);
}
