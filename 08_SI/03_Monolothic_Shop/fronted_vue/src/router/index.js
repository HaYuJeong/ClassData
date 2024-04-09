import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/',
    component: () => import('../views/HomeView.vue')
  },
  // simple-product : 전체조회
  {
    path: '/simple-product',
    component: () => import('../views/simple-product/SimpleProductList.vue')
  },
  // simple-cart : 장바구니 상세화면
  {
    path: '/simple-cart/:spno',
    component: () => import('../views/simple-product/SimpleCartDetail.vue')
  },
  {
    path: '/product',
    component: () => import('../views/product/ProductList.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
