<template>
  <div class="home">
    <div class="container">
      <!-- Logo -->
      <img src="../../assets/logo.png" alt="logo" class="logo" />

      <!-- 导航栏 -->
      <nav class="nav">
        <router-link to="/home" class="nav-link">首页</router-link>
        <router-link to="/blog" class="nav-link">博客</router-link>
        <router-link to="/author" class="nav-link">作者</router-link>
        <router-link to="/" class="nav-link">博客</router-link>
        <router-link to="/" class="nav-link">博客</router-link>
      </nav>

      <!-- 控制按钮 -->
      <el-button @click="toggleDark" class="change">切换深色/浅色模式</el-button>
      <button class="avatar">
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      </button>
    </div>

    <div class="block text-center">
      <!-- <span class="demonstration">Motion blur the switch (default)</span>
        <el-carousel height="703px" motion-blur>
        <el-carousel-item v-for="(item, index) in 9" :key="index">
          <h3 class="small justify-center" text="2xl">{{ item }}</h3>
          <img :src="item.src" :alt="item.alt" class="carousel-image" />
        </el-carousel-item>
      </el-carousel> -->

      <el-carousel :interval="4000" type="card" height="610px" style="margin-top: 102px;">
    <el-carousel-item v-for="(item,index) in carouselImages" :key="index">
      <!-- <h3 text="2xl" justify="center">{{ item }}</h3> -->
       <img :src="item.src" :alt="item.alt" class="carousel-image" />
    </el-carousel-item>
  </el-carousel>
    </div>

    <div >
    <el-table :data="deptList" border style="" class="blog">
      <el-table-column prop="name" label="部门名称" width="100" align="center" />
      <el-table-column prop="createTime" label="最后操作时间" width="35" align="center" />
       <el-table-column prop="name" label="部门名称" width="35" align="center" />
      <el-table-column prop="createTime" label="最后操作时间" width="35" align="center" />
      <el-table-column prop="address" label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="edit(scope.row.id)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  </div>
</template>

<script setup>
// import { ElButton } from 'element-plus'
import { ref, onMounted, h } from 'vue'
// import axios, { Axios } from 'axios';
import {
  ElMessage,
  ElMessageBox,
  ElButton,
  ElCarousel,
  ElCarouselItem,
  ElNotification
} from 'element-plus'

// import {
//   SelectAllBlog,
//   fetchCarouselImages
// } from '../api/home'

import one from '@/assets/1.png'
import two from '@/assets/2.png'
import three from '@/assets/3.png'

import {
  darkMode,
  toggleDark
} from '../api/blackAndWhire'

const carouselImages = ref([
  { src: one, alt: '轮播图1' },
  { src: two, alt: '轮播图2' },
  { src: three, alt: '轮播图3' }
])

// 弹窗函数
const open1 = () => {
  ElNotification({
    title: '欢迎访问',
    message: h('i', { style: 'color: teal' }, '我知道你为什么而来'),
    duration: 4000,
    type: 'success',
    zIndex: 99999
  })
}

// 页面加载时触发
onMounted(() => {
  open1()
})
</script>
<style scoped>
.home {
  min-height: 1000vh;
  background-color: #ffffff;
  border-radius: 10px;
  margin: 5px;
  border: 1px solid #656363;
}

.dark-mode .home {
  background-color: #000000;
}

.container {
  /* position: relative; */
  position: fixed;
  top: 5px;
  left: 50%;
  transform: translateX(-50%);
  width: 96.6%;
  margin: 0 auto;
  z-index: 9999;
  background-color: rgb(255, 255, 255, 0.5);
  padding: 10px 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  border-radius: 10px 10px 0 0;
  border: 1px solid #656363;
}

.dark-mode .container {
  background-color: rgb(0, 0, 0, 0.5);
}

.logo {
  width: 47px;
  height: 47px;
  margin: 0px;
  border-radius:7px 20px 7px 7px ;
}

/* 导航栏 Flex 布局 */
.nav {
  display: flex;
  gap: 30px;
  justify-content: center;
  transform: translateX(-50%);
  background-color: rgb(255, 255, 255, 0.7);
  padding: 8px 16px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  position: absolute;
  top: 18%;
  left: 60%;
}

.dark-mode .nav {
  background-color: rgb(0, 0, 0, 0.7);
}

.nav-link {
  text-decoration: none;
  color: #000000;
  font-weight: 300;
  transition: color 0.3s ease;
 /* 确保每个链接项都统一高度并垂直+水平居中 */
  display: flex;
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
  height: 40px; /* 固定高度 */
  min-width: 80px; /* 统一最小宽度，保持对齐 */
  text-align: center; /* 文本居中 */
  line-height: normal; /* 防止line-height影响布局 */
  transition: all 0.3s ease;
}

.nav-link.router-link-active {
  color: rgb(255, 255, 255) !important; /* 选中文字颜色 */
  background-color: #3bbde9 !important; /* 可选：背景色 */
  border-radius: 20px;
  font-weight: bold;
}

.dark-mode .nav-link {
  color: #e6e6e6;
}

.nav-link:hover {
  color: cornflowerblue;
  border-radius: 4px;
}

/* 按钮样式 */
.change {
  position: absolute;
  top: 24%;
  right: 9.5%;
  width: 150px;
  height: 44px;
  border-radius: 10px;
}

.avatar {
  position: absolute;
  top: 24%;
  right: 5%;
  width: 44px;
  height: 44px;
  border-radius: 50%;
}

.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 2000px;
  margin: 1000px 0 0 0 ;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 图片裁剪以适应容器 */
  display: block;
}

.blog{
  width: 70%;
  margin-left: 15%;
  margin-top: 5%;
  background-color: #fdfbfb;
  border: #000000 1px solid;
  border-radius: 10px;
  display: flex;
  flex-direction: column-reverse;
  align-items: center;
  gap: 40px;

}

.dark-mode .blog{
  background-color: #000000;
  color: #ffffff;
  border: #ffffff 1px solid;
}

.blog-title{
   background-color: #ffffff;
   color: #000000;
}

.dark-mode .blog-title{
  background-color: #fffdfd;
  color: #000000;
}

</style>
