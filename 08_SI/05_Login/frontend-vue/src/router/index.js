import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('../views/HomeView.vue')
  },
  // 부서
  {
    path: '/',
    alias: "/dept", // 별명을 하나 더 줄수 있음(추가 url)
    component: () => import('../views/basic/dept/DeptList.vue')
  },
  // 추가 페이지
  {
    path: '/add-dept',
    component: () => import('../views/basic/dept/AddDept.vue')
  },
  // 수정 페이지(상세조회 : dno(부서번호))
  {
    // TODO: 사용법 - path: "/dept/:변수명"
    path: '/dept/:dno',
    component: () => import('../views/basic/dept/DeptDetail.vue')
  },
  // 사원
  {
    path: '/emp',
    component: () => import('../views/basic/emp/EmpList.vue')
  },
  // 추가 페이지
  {
    path: '/add-emp',
    component: () => import('../views/basic/emp/AddEmp.vue')
  },
  // 수정 페이지(상세조회 : eno(사원번호))
  {
    // TODO: 사용법 - path: "/emp/:변수명"
    path: '/emp/:eno',
    component: () => import('../views/basic/emp/EmpDetail.vue')
  },
  // 로그인
  {
    path: '/login',
    component: () => import('../views/auth/LoginView.vue')
  },
  // 회원가입
  {
    path: '/register',
    component: () => import('../views/auth/RegisterView.vue')
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router