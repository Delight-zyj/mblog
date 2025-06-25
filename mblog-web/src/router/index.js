import { createRouter, createWebHashHistory } from 'vue-router';
import HomeView from '../views/home/home.vue';
import pageLogin from '../views/login/pageLogin.vue';
import notFind from '../views/404/404.vue';

const routes = [
  { path: '/', name: 'home', component: HomeView, },
  { path: '/login', name: 'login', component: pageLogin, },
  { path: '/notFind', name: 'notFind', component: notFind, },
  { path: '/:pathMatch(.*)*', redirect: '/notFind' }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

// 全局前置守卫
router.beforeEach((to, from, next) => {
  // 检测用户是否已登录
  // console.log(to, from);
  next();
});

export default router;
