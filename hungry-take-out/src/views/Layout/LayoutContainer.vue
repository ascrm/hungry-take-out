<script setup>
import { Menu, ShoppingCart, Operation, Bell } from '@element-plus/icons-vue'
import { useAdminStore } from '@/stores'
import { getStatusService, updateStatusService } from '@/api/shop'
import router from '@/router'
import { ref } from 'vue'
import headImage from '@/assets/6.jpg'

const statusMsg = ref('')
const type = ref('')
const adminStore = useAdminStore()

const getStatus = async () => {
  const res = await getStatusService()

  if (res.data.data == 1) {
    statusMsg.value = '营业中'
    type.value = 'success'
  } else {
    statusMsg.value = '休息中'
    type.value = 'info'
  }
}
// 进入页面立即查询营业状态
getStatus()

const updateStatus = async () => {
  await ElMessageBox.confirm('你确认要修改营业状态吗？', '温馨提示', {
    type: 'warning',
    confirmButtonText: '确认',
    cancelButtonText: '取消'
  })
  if (statusMsg.value === '营业中') {
    await updateStatusService(0)
  } else {
    await updateStatusService(1)
  }
  getStatus()
}

const handleCommand = async (key) => {
  if (key === 'logout') {
    // 退出操作
    await ElMessageBox.confirm('你确认要进行退出吗？', '温馨提示', {
      type: 'warning',
      confirmButtonText: '确认',
      cancelButtonText: '取消'
    })

    // 清除本地的数据 (token + user信息)
    adminStore.removeToken()
    router.push('/login')
    ElMessage.success('退出成功')
  } else {
    // 跳转操作
    router.push(`/admin/${key}`)
  }
}
</script>

<template>
  <el-container class="layout-container">
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
        <el-icon size="2em">
          <Menu />
        </el-icon>

        <el-icon size="2em">
          <ShoppingCart />
        </el-icon>

        <el-icon size="2em">
          <Operation />
        </el-icon>

        <el-icon size="2em">
          <Bell />
        </el-icon>

        <div class="avatar">
          <el-button :type="type" @click="updateStatus">{{
            statusMsg
          }}</el-button>

          <el-dropdown placement="bottom-end" @command="handleCommand">
            <!-- 展示给用户，默认看到的 -->
            <span class="el-dropdown__box">
              <el-avatar :src="headImage"></el-avatar>
              <el-icon>
                <CaretBottom />
              </el-icon>
            </span>

            <!-- 折叠的下拉部分 -->
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="profile" :icon="User">基本资料</el-dropdown-item>
                <el-dropdown-item command="password" :icon="Crop">修改密码</el-dropdown-item>
                <el-dropdown-item command="account" :icon="EditPen">切换账号</el-dropdown-item>
                <el-dropdown-item command="logout" :icon="SwitchButton">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>

      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>饥饿 ©2024 Created by ascrm</el-footer>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped>
.el-container {
  height: 100vh;

  .el-aside {
    background-color: #232323;
    width: 200px;

    .title {
      height: 50px;
      font-size: 1.3em;
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
      height: 50px;
      width: 98%;
      display: flex;
      justify-content: space-between;
      align-items: center;

      .avatar {
        width: 180px;
        display: flex;
        justify-content: space-between;

        .el-dropdown__box {
          display: flex;
          align-items: center;

          .el-icon {
            color: #999;
            margin-left: 10px;
          }

          &:active,
          &:focus {
            outline: none;
          }
        }
      }
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
