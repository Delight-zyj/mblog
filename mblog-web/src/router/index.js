import { createRouter, createWebHashHistory } from 'vue-router';
import HomeView from '../views/home/home.vue';
import pageLogin from '../views/login/pageLogin.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/login',
    name: 'login',
    component: pageLogin,
  },

];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
