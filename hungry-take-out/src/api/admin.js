import request from '@/utils/request'

//管理员注册
export const adminRegisterService = ({ username, password }) =>
  request.post('/admin/register', { username, password })

//管理员登录
export const adminLoginService = ({ username, password }) =>
  request.post('/admin/login', { username, password })

//退出登录
export const logout = () => request.post('/admin/logout')