import request from '@/ustils/request';

// 登录接口
export default (data) => request.post('/login', data);
