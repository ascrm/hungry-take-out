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

    //前端要设置前四个字段必须传
    @Update("update dish set name = #{name}, price = #{price}, category = #{category}, image=#{image}," +
            "update_time=#{updatedTime} where id = #{id}")
    void editDish(Dish dish,Integer id);

    //前端除id和status和时间之外都必须设置要传
    @Insert("insert dish set id=#{id}, name = #{name}, price = #{price}, category=#{category}, image=#{image}," +
            "status = #{status},create_time=#{createTime},update_time=#{updatedTime}")
    void addDish(Dish dish);

    @Delete("delete from dish where id=#{id}")
    void deleteDish(Integer id);
}
