package com.hungry.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dish")
public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    //category可选项：主食类，特色类，中餐类，素菜类，小吃类，汤菜类，酒水饮料
    private String category;
    private double price;
    private String image;
    //status：菜品状态： 0 停售 1 出售
    private int status;
    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
