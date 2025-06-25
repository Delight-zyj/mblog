// eslint-disable-next-line import/no-extraneous-dependencies
import axios from 'axios';
import { ElMessage } from 'element-plus';
import router from '../router'

const request = axios.create({
    // baseURL: process.env.VUE_APP_BASE_API, // api 的 base_url
    baseURL: '/api',
    timeout: 5000 // request timeout
});

// 请求拦截器
request.interceptors.request.use(
    (config) => {
        // 请求前拦截
    },
    (error) => {
        console.log(error);
        Promise.reject(error)
    }
);

// 响应拦截器
request.interceptors.response.use(
    (response) => {
        // 响应成功拦截
        if (response.data.code === 200) {
            ElMessage.success(response.data.msg);
        } else {
            ElMessage.error(response.data.msg);
        }
        return response.data;
    },
    (error) => {
        console.log(error);
        Promise.reject(error)
    }
);

export default request;
