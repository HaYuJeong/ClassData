import { createRouter, createWebHistory } from 'vue-router'

//  메뉴와 url 연결작업 하는 곳 : 라우터
const routes = [
  // 부서
  {
    path: '/',
    component: () => import('../views/basic/dept/DeptList.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router