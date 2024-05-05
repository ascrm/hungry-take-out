package com.hungry.mapper;

import com.hungry.Enum.OperationType;
import com.hungry.annotation.AutoFill;
import com.hungry.pojo.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where username=#{username}")
    Admin getOne(String username);

    @Insert("insert into admin values (#{id},#{username},#{password},#{name},#{mail},#{permission},#{createTime},#{updateTime})")
    void insertOne(Admin admin);
}
