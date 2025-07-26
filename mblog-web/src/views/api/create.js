import request from '@/ustils/request';


export const createApi = (data) => request.post('/create', data);
