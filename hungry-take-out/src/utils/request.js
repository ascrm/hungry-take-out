import axios from 'axios'
import { useAdminStore } from '@/stores/modules/admin'
import router from '@/router'

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
    if (err?.response.status === 403) {
      ElMessage.error('用户未登录')
      router.push('/login')
      return
    } else if (err?.response.status === 404) {
      ElMessage.error('访问资源不存在')
      return
    }
    ElMessage.error('服务错误')
    return Promise.reject(err)
  }
)

export default instance
export { baseURL }
