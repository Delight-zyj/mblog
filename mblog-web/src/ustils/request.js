// eslint-disable-next-line import/no-extraneous-dependencies
import axios from 'axios';
import { ElMessage } from 'element-plus';
import router from '../router'
import sysconf  from '@/config/config.js';

// 创建axios实例对象
const request = axios.create({
    // baseURL: process.env.VUE_APP_BASE_API, // api 的 base_url
    baseURL:  "/api",
    timeout: 50000, // request timeout
    headers:{
       'Content-Type': 'application/json;charset=UTF-8'
     }
});

// 请求拦截器
request.interceptors.request.use(
    (config) => {
        // 请求前拦截
        console.log('config', config);
        const loginUser = JSON.parse(localStorage.getItem('loginUser'));
        if (loginUser && loginUser.token) {
            config.headers.token = loginUser.token;
        }
        console.log('config', config);
        return config;
    },
    (error) => {
        console.log(error);
        // Promise.reject(error)
        return Promise.reject(error)
    }
);

// axios的响应 response 拦截器
// request.interceptors.response.use(
//     (response) => {
//         // 响应成功拦截
//         if (response.data.code === 200) {
//             ElMessage.success(response.data.msg);
//         } else {
//             ElMessage.error(response.data.msg);
//         }
//         return response.data;
//     },
//     (error) => {
//         console.log(error);
//         Promise.reject(error)
//     }
// );

request.interceptors.response.use(
    // 成功回调
    (response) => {
    console.log('response', response);
    return response.data
  },
  (error) => { // 失败回调
    if (error.response.status === 401) { // 三个等号：全等（类型，数值都相等）
      // 提示信息
      ElMessage.error('请先登录');
      // 跳转到登录页面
      router.push('/login');
    }
    return Promise.reject(error)
  }
)

export default request;
