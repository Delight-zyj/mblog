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

  </div>
  <el-backtop :right="400" bottom="100" style="width: 100px ; height: 70px; ">
    <div
      style="
        height: 100%;
        width: 100%;
        /* background-color: var(--el-bg-color-overlay); */
        box-shadow: var(--el-box-shadow-lighter);
        background: #ffffff;
        text-align: center;
        border-radius: 10px;
        color: #1989fa;
      "
    >
    <el-icon style="font-size: 30px;"><Top /></el-icon><br>
      回到顶部
    </div>
  </el-backtop>
</template>

<script setup>
// import { ElButton } from 'element-plus'
import { ref, onMounted,onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router'
// import axios, { Axios } from 'axios';
import one from '@/assets/1.png'
import loginUser from '@/router/index';
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
    // // 检查是否记住密码
    // const rememberMeValue = JSON.parse(localStorage.getItem('rememberMeValue') || 'false')
    
    // if(!rememberMeValue){
    //     // 如果没有记住密码，清除登录信息
    //     localStorage.removeItem('loginUser');
    // }

 // 跳转页面
    router.push('/login');

    handlePageHide();
   
  }).catch(() => {
    ElMessage.info({
    message: '您已取消退出'
});
  })
}


// 使用 pagehide 事件（仅在页面真正关闭时触发）
const handlePageHide = (event) => {
  // 检查是否记住密码
  const rememberMeValue = JSON.parse(localStorage.getItem('rememberMeValue') || 'false')
  
  if (!rememberMeValue) {
    // 如果没有记住密码，清除登录信息
    localStorage.removeItem('loginUser');
  }
}

onMounted(() => {
  window.addEventListener('pagehide', handlePageHide);
})

onBeforeUnmount(() => {
  window.removeEventListener('pagehide', handlePageHide);
})

</script>
<style scoped>
.home {
  min-height: 1000vh;
  background-color: #ffffff;
  border-radius: 10px;
  margin: 5px;
  border: 1px solid #656363;
  transition: all 1.5s ease;

}

.dark-mode .home {
  background-color: #000000;
  transition: all 1.5s ease;

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
    transition: all 1.5s ease;

}

.dark-mode .container {
  background-color: rgb(0, 0, 0, 0.5);
  transition: all 1.5s ease;

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
  transition: all 1.5s ease;

}

.dark-mode .nav {
  background-color: rgb(0, 0, 0, 0.7);
  transition: all 1.5s ease;

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
  transition: all 0.3s ease;

}

.dark-mode .nav-link {
  color: #e6e6e6;
  transition: all 1.5s ease;

}

.nav-link:hover {
  color: cornflowerblue;
  border-radius: 4px;
    transition: all 0.3s ease;


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
    transition: all 1.5s ease;


}
.dark-mode .loginname {
  color: #ffffff;
  transition: all 1.5s ease;

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
  transition: all 1.5s ease;

}
.dark-mode .quit {
  color: #ffffff;
  transition: all 1.5s ease;

}
.quit:hover{
  color: rgb(0, 0, 0) !important; /* 选中文字颜色 */
  background-color: #ff0000 !important; /* 可选：背景色 */
  border-radius: 10px;
  font-weight: bold;
  transition: all 1.5s ease;
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
  transition: all 1.5s ease;

}
.dark-mode .operate{
  background-color: #000000;
  border: 1px solid #333;
  transition: all 1.5s ease;

}

</style>
