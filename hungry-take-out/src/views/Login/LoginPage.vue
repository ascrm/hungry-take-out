<script setup>
import { ref, watch } from 'vue'
import { adminLoginService } from '@/api/admin'
import { useAdminStore } from '@/stores'
import { User, Lock, Promotion, Key } from '@element-plus/icons-vue'
import { adminRegisterService, getCodeService } from '@/api/admin'
import router from '@/router'

const adminStore = useAdminStore()
const isRegister = ref(true)
const form = ref(null)

//表单数据
const admin = ref({
  username: '',
  password: '',
  repassword: '',
  inputCode: '',
  code: '',
  mail: ''
})

//校验规则
const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 4, max: 10, message: '用户名必须是 4-10位 的字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    {
      pattern: /^\S{4,15}$/,
      message: '密码必须是 4-15位 的非空字符',
      trigger: 'blur'
    }
  ],
  repassword: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    {
      pattern: /^\S{1,100}$/,
      message: '密码必须是 4-15位 的非空字符',
      trigger: 'blur'
    },
    {
      validator: (rule, value, callback) => {
        // 判断 value 和 当前 form 中收集的 password 是否一致
        if (value !== admin.value.password) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback() // 就算校验成功，也需要callback
        }
      },
      trigger: 'blur'
    }
  ],
  mail: [
    { required: true, message: '邮箱不能为空', trigger: 'blur' },
    {
      pattern: /^[a-zA-Z0-9_]+@[qQ][qQ]\.com$/,
      message: 'qq邮箱格式有误',
      trigger: 'blur'
    }
  ],
  inputCode: [
    { required: true, message: '验证码不能为空', trigger: 'blur' },
    {
      pattern: /^\d{6}$/,
      message: '验证码为六位数字',
      trigger: 'blur'
    }
  ]
}

//注册功能
const register = async () => {
  //注册成功之前校验
  await form.value.validate()
  await adminRegisterService(admin.value)
  ElMessage.success('注册成功')
  isRegister.value = true
}

//登录功能
const login = async () => {
  //登录前校验
  await form.value.validate()
  const res = await adminLoginService(admin.value)
  adminStore.setToken(res.data.data)
  ElMessage.success('登陆成功')
  router.push('/')
}

//获取验证码
const getCode = async () => {
  const res = await getCodeService(admin.value)
  admin.value.code = res.data.data
  ElMessage.success('验证码获取成功')
}

watch(isRegister, () => {
  admin.value = {
    username: '',
    password: '',
    mail: '',
    permission: 'admin',
    code: '',
    inputCode: '',
    repassword: ''
  }
})
</script>

<template>
  <div class="box">
    <el-row>
      <el-col :span="6" :offset="9">
        <el-card shadow="hover">
          <!-- 获取验证码 -->

          <!-- 登录表单 -->
          <el-form ref="form" size="large" v-if="isRegister" :model="admin" :rules="rules">
            <el-form-item>
              <h1>登录</h1>
            </el-form-item>
            <el-form-item prop="username">
              <el-input :prefix-icon="User" placeholder="请输入账号" v-model="admin.username"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input :prefix-icon="Lock" show-password="true" placeholder="请输入密码" v-model="admin.password">
              </el-input>
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
              <el-link type="info" :underline="false" @click="isRegister = false">没有账号？注册</el-link>
            </el-form-item>
          </el-form>

          <!-- 注册表单 -->
          <el-form ref="form" v-else :model="admin" size="large" :rules="rules">
            <el-form-item>
              <h1>注册</h1>
            </el-form-item>
            <el-form-item prop="username">
              <el-input :prefix-icon="User" placeholder="请输入账号" v-model="admin.username"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input :prefix-icon="Lock" show-password="true" placeholder="请输入密码" v-model="admin.password">
              </el-input>
            </el-form-item>
            <el-form-item prop="repassword">
              <el-input :prefix-icon="Lock" show-password="true" placeholder="请再次输入密码" v-model="admin.repassword">
              </el-input>
            </el-form-item>
            <el-form-item prop="mail">
              <div class="mail">
                <el-input :prefix-icon="Promotion" placeholder="请输入qq邮箱" v-model="admin.mail"></el-input>
                <el-button type="primary" @click="getCode" plain>获取验证码</el-button>
              </div>
            </el-form-item>
            <el-form-item prop="inputCode">
              <el-input :prefix-icon="Key" placeholder="请输入验证码" v-model="admin.inputCode"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="register" type="primary">注册</el-button>
            </el-form-item>
            <el-form-item>
              <el-link type="info" :underline="false" @click="isRegister = true">已有帐号？登录</el-link>
            </el-form-item>
          </el-form>
        </el-card>
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

  // .el-card {
  //   background-color: rgb(136, 121, 32);
  // }
  .mail {
    width: 100%;
    display: flex;
    justify-content: space-between;

    .el-input {
      width: 60%;
    }

    .el-button {
      width: 40%;
    }
  }

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
