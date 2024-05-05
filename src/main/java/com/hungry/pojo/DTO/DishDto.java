package com.hungry.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:ascrm
 * @Date:2024/5/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {
    private int pageNum;
    private int pageSize;
    private String name;
    private String category;
    private String status;
}
