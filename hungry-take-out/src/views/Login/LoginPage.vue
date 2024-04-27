<script setup>
import { ref } from 'vue'
import { adminLoginService } from '@/api/admin'
import { useAdminStore } from '@/stores'
import router from '@/router'

const adminStore = useAdminStore()
const isRegister = ref(false)
const admin = ref({
  username: '',
  password: '',
  repassword: ''
})

const changeRegister = () => {
  if (isRegister.value === true) {
    isRegister.value = false
  } else {
    isRegister.value = true
  }
}

const login = async () => {
  const res = await adminLoginService(admin.value)
  adminStore.setToken(res.data.data)
  ElMessage.success('登陆成功')
  router.push('/')
}
</script>

<template>
  <div class="box">
    <el-row>
      <el-col :span="6" :offset="9">
        <el-form v-if="isRegister" :model="admin" size="large">
          <el-form-item>
            <h1>注册</h1>
          </el-form-item>
          <el-form-item>
            <el-input placeholder="请输入账号" v-model="admin"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input placeholder="请再次输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary">注册</el-button>
          </el-form-item>
          <el-form-item>
            <el-link type="info" :underline="false" @click="changeRegister">已有帐号？登录</el-link>
          </el-form-item>
        </el-form>

        <el-form v-else :model="admin">
          <el-form-item>
            <h1>登录</h1>
          </el-form-item>
          <el-form-item prop="username">
            <el-input placeholder="请输入账号" v-model="admin.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input placeholder="请输入密码" v-model="admin.password"></el-input>
          </el-form-item>
          <el-form-item>
            <div class="flex">
              <el-checkbox>记住密码</el-checkbox>
              <el-link type="info" :underline="false">忘记密码</el-link>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login">登录</el-button>
          </el-form-item>
          <el-form-item>
            <el-link type="info" :underline="false" @click="changeRegister">没有账号？注册</el-link>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped lang="scss">
.box {
  background: url('@/assets/2.jpg') no-repeat center center/auto auto;
}

.el-col {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;

  .flex {
    width: 100%;
    display: flex;
    justify-content: space-between;
  }

  .el-button {
    width: 100%;
  }
}
</style>
