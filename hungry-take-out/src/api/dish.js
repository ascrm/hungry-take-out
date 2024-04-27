import request from '@/utils/request'

//分页查询
export const dishPageQueryService = ({ pageNum, pageSize }) =>
  request.get('/dish/page', {
    params: {
      pageNum,
      pageSize
    }
  })

//修改菜品
export const dishEditService = () => request.post('/dish/edit', {})

//添加菜品
export const dishAddService = () => request.put('/dish/add')

//删除菜品
export const dishDeleteService = () => request.delete('/dish/delete/{id}')
