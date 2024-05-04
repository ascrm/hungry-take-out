import axios from 'axios'
import { useAdminStore } from '@/stores/modules/admin'
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
      console.log(adminStore.token)
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
    if (err?.response.code === 401) {
      ElMessage.err('用户未登录')
    } else if (err?.response.code === 404) {
      ElMessage.err('访问资源不存在')
    }

    ElMessage.err('服务错误')
    return Promise.reject(err)
  }
)

export default instance
export { baseURL }
