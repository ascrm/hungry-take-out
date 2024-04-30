package com.hungry.mapper;

import com.hungry.pojo.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where user_name=#{username}")
    Admin getOne(String username);

    @Insert("insert into admin (user_name,password,name,create_time,update_time,permission) values (#{userName},#{passWord},#{name},#{createTime},#{updateTime},#{permission})")
    void insertOne(Admin admin);
}
