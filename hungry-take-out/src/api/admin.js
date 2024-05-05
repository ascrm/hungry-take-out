import request from '@/utils/request'

//管理员注册
export const adminRegisterService = (admin) =>
  request.post('/admin/register', admin)

//管理员登录
export const adminLoginService = ({ username, password }) =>
  request.post('/admin/login', { username, password })

//退出登录
export const logoutService = () => request.post('/admin/logout')

//获取验证码
export const getCodeService = (admin) => request.post('/admin/mail', admin)
