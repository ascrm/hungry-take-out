package com.hungry.mapper;

import com.hungry.pojo.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where username=#{username}")
    Admin getOne(String username);

    @Insert("insert into admin values (#{id},#{username},#{password},#{name},#{createTime},#{updateTime},#{permission})")
    void insertOne(Admin admin);
}
