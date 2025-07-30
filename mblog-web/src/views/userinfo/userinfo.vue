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
         <el-avatar :src="avatar ? avatar : one"  class="avatar"/>
        <a class="loginname" >{{ loginName }}</a>
        <button class="change-username">     <el-icon><EditPen /></el-icon>  </button>
        <button class="change-userinfo">     修改个人信息>  </button>

        <span class="email"><el-icon><Message /></el-icon>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;449119501@qq.com</span>
        <span class="phone"><el-icon><Iphone /></el-icon>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;18888888888</span>
      </div>
  </div>
    <el-backtop :right="400" bottom="100" style="width: 100px ; height: 70px;">
    <div
      style="
        height: 100%;
        width: 100%;
        /* background-color: var(--el-bg-color-overlay); */
        box-shadow: var(--el-box-shadow-lighter);
        background: #ffffff;
        text-align: center;
        border-radius: 10px;
        /* line-height: 80px; */
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
  /* height: auto; */
  background-color: #ffffff;
  border-radius: 10px;
  margin: 5px ;
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
  transition: all 1.5s ease;
}

.dark-mode .nav-link {
  color: #e6e6e6;
  transition: all 1.5s ease;
}

.nav-link:hover {
  color: cornflowerblue;
  border-radius: 4px;
  transition: all 1.5s ease;
}

/* 按钮样式 */
.change {
  position: absolute;
  top: 24%;
  right: 19.5%;
  width: 150px;
  height: 44px;
  border-radius: 10px;
  transition: all 1.5s ease;
}
.list{
  margin-top: 120px; 
  width: 265px;
  min-height: 1000px;
  background-color: rgb(255, 255, 255, 0.5);
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 40px 7px 7px 40px;
  border: 1px solid #ffffff;
  backdrop-filter: blur(5px);
  position: absolute;
  left: 14%; /* 或者使用 margin-left */
  padding: 20px;
  transition: all 1.5s ease;

  
    
}
.dark-mode .list{
    background-color: rgba(0, 0, 0, 0.5);
    border: 1px solid #656565;
    box-shadow: 0 2px 10px rgba(255, 255, 255, 0.4);
    transition: all 1.5s ease;
}

.list-right-top{
    margin-top: 120px; 
    background-color: rgba(255, 255, 255, 0.5);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.4);
    border-radius: 7px 40px 7px 7px;
    width: 47%; 
    min-height: 200px;
    border: 1px solid #ffffff;
    backdrop-filter: blur(5px);
    position: absolute;
    left: 36%; 
    padding: 20px;
    transition: all 1.5s ease;
    

}
.dark-mode .list-right-top{
    background-color: rgba(0, 0, 0, 0.5);
    border: 1px solid #000000;
    box-shadow: 0 10px 20px rgba(255, 255, 255, 0.4);
    transition: all 1.5s ease;


}

.avatar{
  width: 90px;
  height: 90px;
  margin-top: 80px;
  margin-left: 30px;
}

.loginname{
  font-size: 30px;
  position: fixed;
  top: 40%;
  left: 23%;
  transition: all 1.5s ease;

}
.dark-mode .loginname{
  color: #e6e6e6;
  transition: all 1.5s ease;
}

.change-username{
  background: none;
  border: none;
  font-size: 20px;
  position: fixed;
  top: 45.5%;
  left: 40%;
  transition: all 0.5s ease;

  
}
.dark-mode .change-username{
  color: #e6e6e6;
  transition: all 0.5s ease;
}
.change-username:hover{
  font-size: 30px;
}

.change-userinfo{
  position: fixed;
  top: 63%;
  left: 23%;
  font-size: 16px;
  border: none;
  background: none;
  color: #1769f9;
  transition: all 0.5s ease;

}
.dark-mode .change-userinfo{
  color: #83e0fa;
  transition: all 1.5s ease;
}

.change-userinfo:hover{
  transition: all 0.5s ease;
  font-size: 20px;
}

.email{
  font-size: 16px;
  position: fixed;
  top: 47%;
  left: 65%;
  color: #000000;
  transition: all 1.5s ease;

}

.phone{
  font-size: 16px;
  position: fixed;
  top: 63%;
  left: 65%;
  color: #000000;
  transition: all 1.5s ease;

}

.dark-mode .email {
  color: #ffffff;
  transition: all 1.5s ease;

}
.dark-mode .phone {
  color: #ffffff;
  transition: all 1.5s ease;

}


</style>
