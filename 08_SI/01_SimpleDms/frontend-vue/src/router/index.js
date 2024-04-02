import { createRouter, createWebHistory } from 'vue-router'

//  메뉴와 url 연결작업 하는 곳 : 라우터
const routes = [
  // 부서
  {
    path: '/',
    alias: "/dept", // 별명을 하나 더 줄수 있음(추가 url)
    component: () => import('../views/basic/dept/DeptList.vue')
  },
  {
    path: '/emp',
    component: () => import('../views/basic/emp/EmpList.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router