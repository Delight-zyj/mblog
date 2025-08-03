import { createApp } from 'vue';
// 添加 elementUI
import ELmenetPlus from 'element-plus';
import 'element-plus/dist/index.css';
// 注册所有elementplus图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 添加App
import App from './App.vue';
// 添加路由管理器
import router from './router';
// 添加状态管理器
import store from './store';
// 添加全局混入
import mixin from './config/minix';

// 挂载
const app = createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
// 添加全局日期格式化方法
app.config.globalProperties.$formatDate = function(dateString, format = 'YYYY-MM-DD') {
  if (!dateString) return ''
  const date = new Date(dateString)
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  return `${year}-${month}-${day}`
}
app.use(ELmenetPlus).use(store).use(router);
app.mixin(mixin);
app.mount('#app');
