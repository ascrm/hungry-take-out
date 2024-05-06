import axios from 'axios'
import { useAdminStore } from '@/stores/modules/admin'
import router from '@/router'
import { ElMessage } from 'element-plus'

const adminStore = useAdminStore()
const baseURL = '/api'
const instance = axios.create({
  baseURL,
  timeout: 10000
})

//请求拦截器
instance.interceptors.request.use(
  (config) => {
    if (adminStore.token) {
      config.headers.Authorization = adminStore.token
    }
    return config
  },
  (err) => Promise.reject(err)
)

//响应拦截器
instance.interceptors.response.use(
  (res) => {
    if (res.data.code == 200) {
      return res
    }
    ElMessage.err(res.data.msg || '服务异常')
    return Promise.reject(res.data.msg)
  },
  (err) => {
    console.log(err?.response.status)
    if (err?.response.status === 401) {
      ElMessage.error('用户未登录')
      router.push('/login')
      return Promise.reject(err)
    } else if (err?.response.status === 402) {
      ElMessage.error('密码错误')
      return Promise.reject(err)
    } else if (err?.response.status === 403) {
      ElMessage.error('权限不足')
      return Promise.reject(err)
    }
    ElMessage.error('服务错误')
    return Promise.reject(err)
  }
)

export default instance
export { baseURL }
