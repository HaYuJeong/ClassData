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
    path: '/add-dept',
    component: () => import('../views/basic/dept/AddDept.vue')
  },
  {
    // TODO: 사용법 - path: "/dept/:변수명"
    path: '/dept/:dno',
    component: () => import('../views/basic/dept/DeptDetail.vue')
  },
  {
    path: '/emp',
    component: () => import('../views/basic/emp/EmpList.vue')
  },
  {
    path: '/add-emp',
    component: () => import('../views/basic/emp/AddEmp.vue')
  },
  {
    // TODO: 사용법 - path: "/emp/:변수명"
    path: '/emp/:eno',
    component: () => import('../views/basic/emp/EmpDetail.vue')
  },
  {
    // TODO: fileDb
    path: '/fileDb',
    component: () => import('../views/advanced/fileDb/FileDbList.vue')
  },
  {
    // TODO: fileDb
    path: '/add-fileDb',
    component: () => import('../views/advanced/fileDb/AddFileDb.vue')
  },
  {
    // TODO: gallery
    path: '/gallery',
    component: () => import('../views/advanced/gallery/GalleryList.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router