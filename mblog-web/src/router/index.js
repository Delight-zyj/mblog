import { createRouter, createWebHashHistory } from 'vue-router';
import HomeView from '../views/home/home.vue';
import pageLogin from '../views/login/pageLogin.vue';
import notFind from '../views/404/404.vue';
import create from '../views/create/create.vue';
import change from '../views/change/change.vue';

const routes = [
   {
    path: '/',
    redirect: '/home' // 当访问根路径时重定向到/home
  },
  { path: '/login', name: 'login', component: pageLogin, },
  { path: '/home', name: 'home', component: HomeView, },
  { path: '/notFind', name: 'notFind', component: notFind, },
  { path: '/:pathMatch(.*)*', redirect: '/notFind' },
  { path: '/create', name: 'create', component: create, },
  { path: '/change', name: 'change', component: change, },
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
