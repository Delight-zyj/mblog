import { createRouter, createWebHashHistory } from 'vue-router';
<<<<<<< Updated upstream
import HomeView from '../views/HomeView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue'),
  },
=======
import HomeView from '../views/home/home.vue';
import pageLogin from '../views/login/pageLogin.vue';
import notFind from '../views/404/404.vue';

const routes = [
  { path: '/', name: 'home', component: HomeView, },
  { path: '/login', name: 'login', component: pageLogin, },
  { path: '/notFind', name: 'notFind', component: notFind, },
  { path: '/:pathMatch(.*)*', redirect: '/notFind' }
>>>>>>> Stashed changes
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
