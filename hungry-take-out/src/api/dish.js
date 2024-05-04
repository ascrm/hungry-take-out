import request from '@/utils/request'

//分页查询
export const dishPageQueryService = ({ pageNum, pageSize }) =>
  request.get('/dish/page', {
    params: {
      pageNum,
      pageSize
    }
  })

//根据id查询菜品
export const dishQueryById = (id) => request.get(`/dish/select/${id}`)

//修改菜品
export const dishEditService = (data) =>
  request.post(`/dish/edit/${data.id}`, data)

//添加菜品
export const dishAddService = (data) => request.put('/dish/add', data)

//删除菜品
export const dishDeleteService = (id) => request.delete(`/dish/delete/${id}`)
