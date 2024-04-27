package com.hungry.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PageInfo<T> {
    private int page;//分页起始页
    private int size;//每页记录数
    private List<T> data;//返回的记录集合
    private long total;//总记录条数
    public PageInfo(int page,int size){
        this.page=page;
        this.size=size;
    }

}
