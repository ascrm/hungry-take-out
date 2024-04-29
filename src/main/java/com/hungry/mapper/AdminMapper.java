package com.hungry.mapper;

import com.hungry.pojo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where user_name=#{username}")
    Admin getOne(String username);
}
