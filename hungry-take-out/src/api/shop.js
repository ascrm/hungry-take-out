import request from '@/utils/request'

export const getStatusService = () => request.get('/shop/status')

export const updateStatusService = (status) => {
  request.post(`/shop/${status}`)
}
