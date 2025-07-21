import request from '@/ustils/request'
import { ref, onMounted } from 'vue'

// 轮播图效果
// export const fetchCarouselImages = async () => {
//   const res = await request.get('/？？？');
//   return res.data.map((item) => ({
//     src: item.imageUrl,
//     alt: item.description
//   }));
// };
// const carouselImages = ref([])
// onMounted(async () => {
//   carouselImages.value = await fetchCarouselImages()
// })

// 查询全部博客数据
// export const SelectAllBlog = () => request.get('/hello')
