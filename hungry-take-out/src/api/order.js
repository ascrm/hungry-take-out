import request from '@/utils/request'

//分页查询
export const orderPageQueryService = ({ pageNum, pageSize }) =>
  request.get('/order/page', {
    params: {
      pageNum,
      pageSize
    }
  })

//修改订单
export const orderEditService = () => request.post('/order/edit', {})

//添加订单
export const orderAddService = () => request.put('/order/add')

//删除订单
export const orderDeleteService = () => request.delete('/order/delete/{id}')
