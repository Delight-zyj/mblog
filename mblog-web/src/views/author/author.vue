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
        <router-link to="/digital" class="nav-link">数码</router-link>
        <router-link to="/" class="nav-link">博客</router-link>
      </nav>

      <!-- 控制按钮 -->
       <el-switch  @click="toggleDark" v-model="value5" class="change" width="" size="large" inline-prompt active-text="" inactive-text="" 
       :active-action-icon="Moon" :inactive-action-icon="Sunny"
        style="--el-switch-on-color: #000;  "
      />
      <div class="operate">
        

      <router-link :to="loginUser ? '/userinfo' : '/login'">
        <button class="avatar">
          <el-avatar :src="userinfo.avatar ? userinfo.avatar : one" />
        </button>
      </router-link>

     <div style="margin-left: 65px; width: 85px;">
        <span class="loginname">{{ userinfo.username }}</span>
      </div>

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
import { getUserinfoByIdApi, updateUserinfoApi } from '@/views/api/userinfo'
import {
  Document,
  Menu as IconMenu,
  Location,
  Moon,
  Setting,
  Sunny,
} from '@element-plus/icons-vue'

import {
  ElMessage,
  ElMessageBox,
  ElButton,
  ElCarousel,
  ElCarouselItem
} from 'element-plus'

import {
  darkMode,
  toggleDark,
  value5
} from '../api/blackAndWhire'
// 已登录时否，获取用户信息
onMounted(() => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if (loginUser && loginUser.username) {
    loginName.value = loginUser.username;
    id.value = loginUser.id;
    fetchUserInfo(id.value);
  }
})
// 新增获取用户信息的方法
const fetchUserInfo = async (id) => {
  const result = await getUserinfoByIdApi(id);
  if (result.code) {
    userinfo.value = result.data;
  }
}
const userinfo = ref({
  username: '',
  gender: '',
  email: '',
  phone: '',
  authorType: '',
  createTime: '',
  updateTime: ''
})
const search = async () => {
  const result = await getUserinfoByIdApi(
    searchForm.value.username,
    searchForm.value.gender,
    searchForm.value.age,
    searchForm.value.authorType,
    searchForm.value.email,
    searchForm.value.phone,
    searchForm.value.authorType,
    searchForm.value.createTime,
    searchForm.value.updateTime,
  );
}

// 搜索表单对象
const searchForm = ref({ username: '', gender: '', email:'', phone:'',authorType:'',createTime: '', updateTime: '' })

const id = ref('');


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
  border: 1px solid #ffffff;
  transition: all 1.5s ease;

}

.dark-mode .home {
  background-color: #000000;
  transition: all 1.5s ease;

}

.container {
    /* position: relative; */
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  width: 92.65%;
  margin: 0 auto;
  z-index: 9999;
  background-color: rgb(255, 255, 255, 0.5);
  padding: 10px 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  border-radius: 10px ;
  /* border: 1px solid #656363; */
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
  padding: 8px 16px;
  border-radius: 8px;
  position: absolute;
  top: 18%;
  left: 50%;
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
  right: 24.5%;
  transition: all 1.3s ease;

}
/* 添加自定义切换动画速度 */
.change :deep(.el-switch__core) {
  transition-duration: 0.6s; /* 调整为您想要的速度，例如 0.2s */
  border: 1px solid rgba(255, 255, 255, 0.5);
}

.change :deep(.el-switch__action) {
  transition-duration: 0.6s; /* 保持与核心元素一致 */
  border: 1px solid rgba(0, 0, 0, 0.5);

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
   display: flex;
  justify-content: center;
  width: 100%;
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
