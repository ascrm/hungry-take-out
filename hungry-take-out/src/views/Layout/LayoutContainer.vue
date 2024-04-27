<script setup>
import { Menu, ShoppingCart, Operation, Bell } from '@element-plus/icons-vue'
</script>

<template>
  <div class="layout-container">
    <el-container>
      <el-aside>
        <div class="title">饥饿外卖</div>
        <el-menu router text-color="#fff" active-text-color="#ffd04b" background-color="#232323">
          <el-menu-item index="/dish/manage">菜品管理</el-menu-item>
          <el-menu-item index="/order/manage">订单管理</el-menu-item>
          <el-menu-item index="/employee/manage">员工管理</el-menu-item>
          <el-menu-item index="/category/manage">分类管理</el-menu-item>
          <el-menu-item index="/data/manage">数据管理</el-menu-item>
          <el-sub-menu index="/admin">
            <template #title>
              <span>管理员信息</span>
            </template>

            <el-menu-item index="/admin/profile">基本资料</el-menu-item>
            <el-menu-item index="/admin/password">修改密码</el-menu-item>
            <el-menu-item index="/admin/account">切换账号</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>

      <el-container class="inside">
        <el-header>
          <el-icon size="2.5em">
            <Menu />
          </el-icon>

          <el-icon size="2.5em">
            <ShoppingCart />
          </el-icon>

          <el-icon size="2.5em">
            <Operation />
          </el-icon>

          <el-icon size="2.5em">
            <Bell />
          </el-icon>

          <el-avatar src="@/assets.5.jpg"></el-avatar>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
        <el-footer>饥饿 ©2024 Created by ascrm</el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="scss" scoped>
.el-container {
  height: 100vh;

  .el-aside {
    background-color: #232323;

    .title {
      height: 100px;
      font-size: 2em;
      display: flex;
      justify-content: center;
      align-items: center;
      cursor: default;
      user-select: none;
      border-bottom-style: solid;
      border-bottom-color: rgba(31, 26, 24, 0.502);
    }

    .el-menu {
      border-right: none;
    }
  }

  .inside {
    .el-header {
      height: 80px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .el-footer {
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 14px;
      color: #666;
    }
  }
}
</style>
