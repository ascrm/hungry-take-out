import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      component: () => import('@/views/Login/LoginPage.vue')
    },
    {
      path: '/',
      component: () => import('@/views/Layout/LayoutContainer.vue'),
      redirect: '/dish/manage',
      children: [
        {
          path: '/dish/manage',
          component: () => import('@/views/Dish/DishMag.vue')
        },
        {
          path: '/order/manage',
          component: () => import('@/views/Order/OrderMag.vue')
        },
        {
          path: '/employee/manage',
          component: () => import('@/views/Employee/EmployeeMag.vue')
        },
        {
          path: '/category/manage',
          component: () => import('@/views/Category/CategoryMag.vue')
        },
        {
          path: '/data/manage',
          component: () => import('@/views/DataPage/DataMag.vue')
        },
        {
          path: '/admin/profile',
          component: () => import('@/views/Admin/AdminProfile.vue')
        },
        {
          path: '/admin/password',
          component: () => import('@/views/Admin/AdminPassword.vue')
        },
        {
          path: '/admin/account',
          component: () => import('@/views/Admin/AdminAccount.vue')
        }
      ]
    }
  ]
})

export default router
