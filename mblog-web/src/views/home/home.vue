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

      <!-- 深色模式控制按钮 -->
      <el-button @click="toggleDark" class="change">切换深色/浅色模式</el-button>
      
      <div class="operate">
      <router-link :to="loginUser ? '/userinfo' : '/login'">
        <button class="avatar">
          <el-avatar :src="avatar ? avatar : one" />
        </button>
      </router-link>

      <a class="loginname">
        {{ loginName }}
      </a>

       <a href="javascript:;" @click="logout" class="quit">
           {{ loginName ? '退出登录' : '去登录' }} 
          </a>
      </div>
    </div>
    <div class="block text-center"> 
      <h1 class="h1-hello">{{ loginName ? '你好' : '欢迎' }}</h1>
      <h4 class="h2-hello">{{ loginName ? '欢迎登录我的博客' : '欢迎访问我的博客' }}</h4>
    </div>

    <div class="block text-center">
      <el-carousel :interval="4000" type="card" height="610px" style="margin-top: 162px;">
    <el-carousel-item v-for="(item,index) in carouselImages" :key="index">
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
import { ref, onMounted, h  } from 'vue'
import { useRouter } from 'vue-router';
// import axios, { Axios } from 'axios';
import loginUser from '@/router/index';
import {
  ElMessage,
  ElMessageBox,
  ElButton,
  ElCarousel,
  ElCarouselItem,
  ElNotification
} from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// import {
//   SelectAllBlog,
//   fetchCarouselImages
// } from '../api/home'

import one from '@/assets/1.png'
import two from '@/assets/2.png'
import three from '@/assets/3.png'
import four from '@/assets/4.png'
import five from '@/assets/5.png'
import six from '@/assets/6.png'
import seven from '@/assets/7.png'
import eight from '@/assets/8.png'
import nine from '@/assets/9.png'
import ten from '@/assets/10.png'
import eleven from '@/assets/11.png'
import twelve from '@/assets/12.png'
import thirteen from '@/assets/13.png'
import fourteen from '@/assets/14.png'
import fifteen from '@/assets/15.png'


import {
  darkMode,
  toggleDark
} from '../api/blackAndWhire'

const carouselImages = ref([
  { src: one, alt: '轮播图1' },
  { src: two, alt: '轮播图2' },
  { src: three, alt: '轮播图3' },
  { src: four, alt: '轮播图4' },
  { src: five, alt: '轮播图5' },
  { src: six, alt: '轮播图6' },
  // { src: seven, alt: '轮播图7' },
  // { src: eight, alt: '轮播图8' },
  // { src: nine, alt: '轮播图9' },
  // { src: ten, alt: '轮播图10' },
  // { src: eleven, alt: '轮播图11' },
  // { src: twelve, alt: '轮播图12' },
  // { src: thirteen, alt: '轮播图13' },
  // { src: fourteen, alt: '轮播图14' },
  // { src: fifteen, alt: '轮播图15' }

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

const loginName = ref('');
const avatar = ref("");
const router = useRouter();
// 钩子函数(获取用户名信息)
onMounted(() => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if (loginUser && loginUser.username) {
    loginName.value = loginUser.username;
  }
})

onMounted(() => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if (loginUser && loginUser.avatar) {
    avatar.value = loginUser.avatar;
  }
})

// 退出登录
const logout = async () => {
   if (!loginName.value) {
    // 未登录，直接跳转登录页
    router.push('/login');
    return;
  }
   // 已登录，弹出确认框
  ElMessageBox.confirm('确认退出登录吗?', '提示',
    { confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning', }
  ).then(async () => {
    ElMessage.success('退出成功');
    // 检查是否记住密码
    const rememberMeValue = JSON.parse(localStorage.getItem('rememberMeValue ') || 'false')
    
    if(!rememberMeValue){
        // 如果没有记住密码，清除登录信息
        localStorage.removeItem('loginUser');
    }
    // 跳转页面
    router.push('/login');
  }).catch(() => {
    ElMessage.info({
    message: '您已取消退出'
});
  })
}

// 页面加载时触发
onMounted(() => {
  if(!loginName.value){
    open1()
  }
})


</script>
<style scoped>


.home {
  min-height: 1000vh;
  background-color: #ffffff;
  border-radius: 10px;
  margin: 5px;
  border: 1px solid #656363;
  transition: all 0.5s ease;
}

.dark-mode .home {
  background-color: #000000;
  transition: all 0.5s ease;
}

.container {
  /* position: relative; */
  position: fixed;
  top: 5px;
  left: 50%;
  transform: translateX(-50%);
  width: 96.65%;
  margin: 0 auto;
  z-index: 9999;
  background-color: rgb(255, 255, 255, 0.5);
  padding: 10px 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  border-radius: 10px 10px 0 0;
  border: 1px solid #656363;
  backdrop-filter: blur(5px);
  transition: all 0.5s ease;
}

.dark-mode .container {
  background-color: rgb(0, 0, 0, 0.5);
  transition: all 0.5s ease;
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
  left: 50%;
  transition: all 0.5s ease;
}

.dark-mode .nav {
  background-color: rgb(0, 0, 0, 0.7);
  transition: all 0.5s ease;
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
  transition: all 0.5s ease;
}

.nav-link.router-link-active {
  color: rgb(255, 255, 255) !important; /* 选中文字颜色 */
  background-color: #3bbde9 !important; /* 可选：背景色 */
  border-radius: 20px;
  font-weight: bold;
  transition: all 0.5s ease;
}

.dark-mode .nav-link {
  color: #e6e6e6;
  transition: all 0.5s ease;
}

.nav-link:hover {
  color: cornflowerblue;
  border-radius: 4px;
  transition: all 0.5s ease;
}

/* 按钮样式 */
.change {
  position: absolute;
  top: 24%;
  right: 19.5%;
  width: 150px;
  height: 44px;
  border-radius: 10px;
}

.avatar {
  position: absolute;
  top: 13%;
  left: 5%;
  width: 44px;
  height: 44px;
  border-radius: 50%;
}

.loginname{
  position: absolute;
  top: 0%;
  left: 50%;
  width: 100px;
  transition: all 0.5s ease;

}
.dark-mode .loginname {
  color: #ffffff;
  transition: all 0.5s ease;
}


.quit{
  position: absolute;
  top: 50%;
  right: 11.5%;
  text-decoration: none;
  color: #000000;
  /* background-color: #ffffff !important; */
  transition: all 0.5s ease;
  width: 85px ;
  height: 30px;
  border-radius: 10px;
  line-height: 30px;
  text-align: center;
}
.dark-mode .quit {
  color: #ffffff;
  transition: all 0.5s ease;
}
.quit:hover{
  color: rgb(0, 0, 0) !important; /* 选中文字颜色 */
  background-color: #ff0000 !important; /* 可选：背景色 */
  border-radius: 10px;
  font-weight: bold;
  transition: all 0.5s ease;
}

.operate{
  position: absolute;
  top: 14%;
  right: 3.5%;
  background-color: #ffffff;
  border-radius: 10px;
  height: 60px;
  width: 170px;
  border: 1px solid #d6d6d6;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  transition: all 0.5s ease;
}
.dark-mode .operate{
  background-color: #000000;
  border: 1px solid #333;
  transition: all 0.5s ease;
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

.h1-hello{
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20vw;
  font-weight: bold;
  background: url("@/assets/5.png") no-repeat center/cover;
  background-clip: text;
  color: transparent;
  margin-top: 150px;
  margin-bottom: 70px;
  animation: scale 3s ease-in-out ;
  
}

.h2-hello{
  display: flex;
  justify-content: center;
  align-items: center;
}

.dark-mode .h2-hello{
  color: white;
}

@keyframes scale{
  from{
    font-size: 755px;
  }
  to{
    font-size: 20vw;
  }
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
