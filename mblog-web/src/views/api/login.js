import request from '@/ustils/request';

// 登录接口
// export const loginApi = (data) => request.post('/login', data);

export const loginApi = (data) => request.post('/login', data, {
  headers: {
    'Content-Type': 'application/json'
  }
});
