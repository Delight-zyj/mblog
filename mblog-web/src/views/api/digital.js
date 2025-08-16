import request from '@/ustils/request';


// 查询全部数码产品
export const getDigitalList = (digitalname, brandid, typeid, begin, end, max, min) =>
     request.get(`/digital?digitalname=${digitalname}&brandid=${brandid}&typeid=${typeid}&begin=${begin}&end=${end}&max=${max}&min=${min}&`);


// 根据id查询
export const getDigitalById = (digitalId) => request.get(`/digital/${digitalId}`);

// 根据类型Id查询品牌
export const getDigitalBrandByTypeId = (typeid) => request.get(`/digital/brand/${typeid}`);

// 查询数码产品类型
export const getDigitalType = () => request.get(`/digital/type`);

