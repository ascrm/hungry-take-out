import request from '@/utils/request'

//管理员登陆
export const adminRegisterService = ({ userName, password }) =>
  request.post('/admin/register', { userName, password })

//管理员注册
export const adminLoginService = ({ userName, password }) =>
  request.post('/admin/login', { userName, password })

//退出登录
export const logout = () => request.post('/admin/logout')
