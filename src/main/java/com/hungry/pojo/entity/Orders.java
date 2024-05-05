package com.hungry.pojo.entity;

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
public class Orders implements Serializable {
    private final static long serialVersionUID = 1L;
    private int id;
    private String number;
    //status：订单状态：0 未完成 1 已完成
    private int status;
    private Integer userId;
    private String address;
    //type：0 微信支付 1 支付宝支付
    private Integer type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
