import request from '@/ustils/request';


// 查询全部数码产品
export const getDigitalList = (digitalname, digitalbrand, type, begin, end, max, min) =>
     request.get(`/digital?digitalname=${digitalname}&digitalbrand=${digitalbrand}&type=${type}&begin=${begin}&end=${end}&max=${max}&min=${min}&`);

