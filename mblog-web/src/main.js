import { createApp } from 'vue';
<<<<<<< Updated upstream
=======
// 添加 elementUI
import ELmenetPlus from 'element-plus';
import 'element-plus/dist/index.css';
// 注册所有elementplus图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 添加App
>>>>>>> Stashed changes
import App from './App.vue';
import router from './router';
import store from './store';

createApp(App).use(store).use(router).mount('#app');
