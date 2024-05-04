import request from '@/utils/request'

//文件上传
export const UploadFileService = (file) =>
  request.post('/file/upload', file, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
