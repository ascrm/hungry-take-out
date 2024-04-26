# Object





## 1. entity

> 实体类版本：1.0



### `Admin`

> 管理员实体

| 字段       | 类型          | 描述       | 是否唯一 | 是否可为空 | 默认值   |
| ---------- | ------------- | ---------- | -------- | ---------- | -------- |
| id         | int           | 主键       | yes      | no         | null     |
| userName   | String        | 账号       | yes      | no         | null     |
| password   | String        | 密码       | yes      | no         | null     |
| name       | String        | 管理员昵称 | no       | yes        | null     |
| createTime | LocalDateTime | 创建时间   | no       | yes        | 当前时间 |
| updateTime | LocalDateTime | 修改时间   | no       | yes        | 当前时间 |



### `Dish`

> 菜品实体

| 字段        | 类型          | 描述     | 是否唯一 | 是否可为空 | 默认值   |
| ----------- | ------------- | -------- | -------- | ---------- | -------- |
| id          | int           | 主键     | yes      | no         | null     |
| name        | String        | 名称     | yes      | no         | null     |
| category    | String        | 分类     | no       | yes        | null     |
| price       | double        | 价格     | no       | yes        | null     |
| image       | String        | 图片     | no       | yes        | null     |
| status      | int           | 状态     | no       | no         | 1        |
| description | String        | 描述     | no       | yes        | null     |
| createTime  | LocalDateTime | 创建时间 | no       | yes        | 当前时间 |
| updateTime  | LocalDateTime | 修改时间 | no       | yes        | 当前时间 |

- category可选项：主食类，特色类，中餐类，素菜类，小吃类，汤菜类，酒水饮料
- status：菜品状态： 0 停售     1 出售



### `Orders`

> 订单实体

| 字段      | 类型          | 描述     | 是否唯一 | 是否可为空 | 默认值   |
| --------- | ------------- | -------- | -------- | ---------- | -------- |
| id        | int           | 主键     | yes      | no         | null     |
| number    | String        | 订单号   | yes      | no         | null     |
| status    | int           | 状态     | no       | no         | 0        |
| userId    | Integer       | 用户id   | no       | yes        | null     |
| address   | String        | 地址     | no       | yes        | null     |
| type      | Integer       | 支付方式 | no       | yes        | null     |
| startTime | LocalDateTime | 下单时间 | no       | yes        | 当前时间 |
| endTime   | LocalDateTime | 结单时间 | no       | yes        | null     |

- status：订单状态：0 未完成    1 已完成
- type：0 微信支付   1  支付宝支付









