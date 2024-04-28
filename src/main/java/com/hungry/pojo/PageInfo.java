package com.hungry.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PageInfo<T> {
    private List<T> data;//返回的记录集合
    private long total;//总记录条数
}
