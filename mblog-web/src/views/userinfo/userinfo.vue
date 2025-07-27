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
    </div>
     <div class="list">
        <button>按钮1</button>
        <button>按钮2</button>
        <button>按钮3</button>
      </div>
      <div class="list-right-top">

      </div>
  </div>
</template>

<script setup>
// import { ElButton } from 'element-plus'
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
// import axios, { Axios } from 'axios';
import one from '@/assets/1.png'
import {
  ElMessage,
  ElMessageBox,
  ElButton,
  ElCarousel,
  ElCarouselItem
} from 'element-plus'

import {
  darkMode,
  toggleDark
} from '../api/blackAndWhire'


const loginName = ref('');
const avatar = ref('');
const router = useRouter();
// 钩子函数(获取用户名信息)
onMounted(() => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if (loginUser && loginUser.username) {
    loginName.value = loginUser.username;
  }
})

// 获取用户头像
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
    // 移除token信息
    localStorage.removeItem('loginUser');
    // 跳转页面
    router.push('/login');
  }).catch(() => {
    ElMessage.info({
    message: '您已取消退出'
});
  })
}

</script>
<style scoped>
.home {
  min-height: 1000vh;
  border-radius: 10px;
  margin: 5px;
  border: 1px solid #656363;
  transition: all 0.5s ease;
  backdrop-filter: blur(5px);
}

.dark-mode .home {
  background-color: #000000;
  transition: all 0.5s ease;
}

.container {
  /* position: relative; */
  position: fixed;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 97.4%;
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
  display: flex;
  align-items: center; /* 垂直居中 */
  justify-content: space-between; /* 元素间均匀分布 */
  
}

.dark-mode .container {
  background-color: rgb(0, 0, 0, 0.5);
  transition: all 0.5s ease;
  border: 1px solid #7d7d7d;
  box-shadow: 0 2px 10px rgba(255, 255, 255, 0.4);
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
  transition: all 0.5s ease;
}

.list{
  margin-top: 120px; 
  width: 265px;
  min-height: 1000px;
  background-color: rgb(255, 255, 255, 0.5);
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 40px 0 0 10px;
  border: 1px solid #ffffff;
  backdrop-filter: blur(5px);
  position: absolute;
  left: 14%; /* 或者使用 margin-left */
  padding: 20px;
  transition: all 0.5s ease;

  
    
}
.dark-mode .list{
    background-color: rgba(0, 0, 0, 0.5);
    border: 1px solid #656565;
    box-shadow: 0 2px 10px rgba(255, 255, 255, 0.4);
}

.list-right-top{
    margin-top: 120px; 
    background-color: rgba(255, 255, 255, 0.5);
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    border-radius: 0 40px 0 0;
    width: 47%; 
    min-height: 200px;
    border: 1px solid #ffffff;
    backdrop-filter: blur(5px);
    position: absolute;
    left: 36%; 
    padding: 20px;
    transition: all 0.5s ease;
    

}
.dark-mode .list-right-top{
    background-color: rgba(0, 0, 0, 0.5);
    border: 1px solid #656565;
    box-shadow: 0 2px 10px rgba(255, 255, 255, 0.4);

}

</style>
