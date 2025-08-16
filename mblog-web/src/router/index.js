import { createRouter, createWebHashHistory } from 'vue-router';
import { getUserinfoByIdApi, updateUserinfoApi } from '@/views/api/userinfo'
import { ElMessage } from 'element-plus';
import HomeView from '../views/home/home.vue';
import pageLogin from '../views/login/pageLogin.vue';
import notFind from '../views/404/404.vue';
import create from '../views/create/create.vue';
import change from '../views/change/change.vue';
import blog from '../views/blog/blog.vue';
import author from '../views/author/author.vue';
import userinfo from '../views/userinfo/userinfo.vue';
import user1 from '../views/user-1/user-1.vue';
import user2 from '../views/user-2/user-2.vue';
import user3 from '../views/user-3/user-3.vue';
import digital from '../views/digital/digital.vue'

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
  { path: '/blog', name: 'blog', component: blog, },
  { path: '/author', name: 'author', component: author, },
  { path: '/userinfo', name: 'userinfo', component: userinfo, },
  { path: '/user-1', name: 'user1', component: user1, },
  { path: '/user-2', name: 'user2', component: user2, },
  { path: '/user-3', name: 'user3', component: user3, },
  { path: '/digital', name: 'digital', component: digital, },
];

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
});

// 全局前置守卫
router.beforeEach((to, from, next) => {
  const loginUser = localStorage.getItem('loginUser'); // 或从 Vuex 获取登录状态
  const requireAuth = ['/userinfo']; // 需要登录的页面
console.log('loginUser', loginUser);
console.log(to.path);
  if (requireAuth.includes(to.path)) {
    if (loginUser) {
      next(); // 已登录，允许访问
    } else {
      ElMessage.error('请先登录');
      next('/login'); // 未登录，跳转登录页
    }
  } else {
    next(); // 不需要登录的页面直接放行
  }
});


export default router;
