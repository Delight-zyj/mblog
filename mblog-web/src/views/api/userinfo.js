import request from '@/ustils/request'

// 根据id查询用户信息
export const getUserinfoByIdApi = (id) => request.get(`/userinfo/${id}`)

// 修改用户信息
export const updateUserinfoApi = (data) =>request.put('/userinfo', data)

// 获取当前用户信息
