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
app.use(ELmenetPlus).use(store).use(router);
app.mixin(mixin);
app.mount('#app');
