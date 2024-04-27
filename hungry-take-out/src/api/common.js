import request from '@/utils/request'

//文件上传
export const uploadFile = () => request.post('/file/upload')
