# API

> API 版本 1.0
>
> 
>
> 统一响应数据封装：{code，msg，data}
>
> - code ：200 成功     0 失败
> - msg：自行根据负责的功能自定义msg内容
> - data：按照接口文档要求



## 1. 管理员模块

> **根路径："/admin"**

### 1.1 注册

- 请求路径："/register"

- 请求方式：POST

- 请求参数：{userName，password}

- 响应data：`null`

### 1.2 登录

- 请求路径："/login"

- 请求方式：POST

- 请求参数：{userName，password}

- 响应data：`jwt令牌`



### 1.3 登出

- 路径："/logout"
- 方式：POST
- 参数：无
- 响应data：`null`





## 2. 菜品模块

> **根路径："/Dish"**

### 2.1 分页查询所有菜品

- 路径："/page"
- 方式：GET
- 参数：{pageNum，pageSize}
- 响应data：`List<Dish>`



### 2.2 修改菜品信息(admin才能操作)

- 路径："/edit/ {id} "
- 方式：POST
- 参数：Dish实体
- 响应data：`null`



### 2.3 添加菜品(admin才能操作)

- 路径："/add "
- 方式：PUT
- 参数：Dish实体
- 响应data：`null`



### 2.4 删除菜品(admin才能操作)

- 路径："/delete/ {id} "
- 方式：DELETE
- 参数：无
- 响应data：`null`



## 3. 订单模块(admin才能操作的和dish里一样)

> **根路径 ："/order"**



和员工模块一样，除了根路径和请求参数，自行判断如何修改





## 4. 文件模块

> **根路径**："/file"

### 4.1 文件上传

- 路径："/upload"
- 方式：POST
- 参数：{ file }
- 响应data：`null`



## 5. 店铺模块

> **根路径："/shop"**



### 5.1 获取店铺状态

- 路径："/status"
- 方式：GET
- 参数：无
- 响应data：`{status}`



### 5.2 修改店铺状态(admin才能操作)

- 路径："/{status}"
- 方式：POST
- 参数： 
- 响应data：`null`













# 通用模块

## 1. 拦截器



## 2. 全局异常处理器

