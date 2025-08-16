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
    </div>
     <div class="list">
      <router-link to="/userinfo" class="user-0">个人信息</router-link>
      <router-link to="/user-1" class="user-0">个人博客</router-link>
      <router-link to="/user-2" class="user-0">个人主页2</router-link>    
      <router-link to="/user-3" class="user-0">个人主页3</router-link>       

      </div>

      <div class="list-right-top">
         <el-avatar :src="userinfo.avatar ? userinfo.avatar : one" class="avatar"/>
        <a class="loginname" >{{ userinfo.username }}</a>
        <!-- <button class="change-username">     <el-icon><EditPen /></el-icon>  </button> -->
        
        <button class="change-userinfo" @click="edit(id)">     修改个人信息>  </button>

        <span class="create-time" >注册时间：{{ $formatDate(userinfo.createTime)}}</span>
        <span class="update-time" >最近修改：{{ $formatDate(userinfo.updateTime)}}</span>
      </div>

      <div class="list-right-bottom"> 
      
      </div>

  </div>

  <el-dialog
    v-model="changeuserinfo"
    title="修改个人信息"
    width="500"
    draggable
    overflow
    class="change-dialog"
  >

  <el-form :model="userinfo" :rules="rules" ref="userinforef">
       <!-- 第一行 -->
       <el-row :gutter="20">
        <el-col :span="3"></el-col>
        <el-col :span="12">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userinfo.username" placeholder="请输入员工用户名，2-20个字"></el-input>
          </el-form-item>
        </el-col>
       </el-row>
       <!-- 第二行 -->
       <el-row :gutter="20">
        <el-col :span="3"></el-col>
        <el-col :span="12">
          <el-form-item label="性别&nbsp;&nbsp;&nbsp;&nbsp;" prop="gender">
            <el-select v-model="userinfo.gender" placeholder="请选择性别" style="width: 100%;">
              <el-option v-for="(g) in genders" :key="g.value" :label="g.name" :value="g.value"></el-option>
            </el-select>
          </el-form-item>
       </el-col>
      </el-row>
      <!-- 第三行 -->
      <el-row :gutter="20">
        <el-col :span="3"></el-col>
        <el-col :span="12">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="userinfo.phone" placeholder="请输入手机号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第四行 -->
      <el-row :gutter="20">
        <el-col :span="3"></el-col>
        <el-col :span="12">
          <el-form-item label="邮箱&nbsp;&nbsp;&nbsp;&nbsp;" prop="email">
            <el-input v-model="userinfo.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
        </el-col>    
      </el-row>

     

      <!-- 第五行 -->
      <el-row :gutter="20">
        <el-col :span="3"></el-col>
        <el-col :span="12">
          <el-form-item label="&nbsp;&nbsp;&nbsp;头像">
            <el-upload class="avatar-uploader" action="/api/upload" :headers="{'token': token}" :show-file-list="false"
              :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
              <img v-if="userinfo.avatar" :src="userinfo.avatar" class="change-avatar" />
              <el-icon v-else class="avatar-uploader-icon">
                <Plus />
              </el-icon>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>


  </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="changeuserinfo = false">退出</el-button>
        <el-button type="primary" @click="save">
          提交
        </el-button>
      </div>
    </template>
  </el-dialog>
   

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
import { ref, onMounted,onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router'
import { getUserinfoByIdApi, updateUserinfoApi } from '@/views/api/userinfo'
import one from '@/assets/1.png'
import {
  ElMessage,
  ElMessageBox,
  ElButton,
  ElCarousel,
  ElCarouselItem
} from 'element-plus'

import {
  Document,
  Menu as IconMenu,
  Location,
  Moon,
  Setting,
  Sunny,
} from '@element-plus/icons-vue'


import {
  darkMode,
  toggleDark,
  value5
} from '../api/blackAndWhire'

// 页面加载时触发
const id = ref('');
onMounted(() => {
   const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  // 获取用户id信息
  if (loginUser && loginUser.id) {
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
//性别列表数据
const genders = ref([{ name: '男', value: 1 }, { name: '女', value: 2 },{ name: '未说明性别', value: 3 },{ name: '未知性别', value: 4 }])

// 用户类型列表数据
const authorTypes = ref([{ name: '超级管理员', value: 0 }, { name: '普通用户', value: 1 }])

const changeuserinfo = ref(false)

const router = useRouter();



const edit = async (id) => {
  const result = await getUserinfoByIdApi(id);
  if (result.code) {
    changeuserinfo.value = true;
    userinfo.value = result.data;
  }
}
  // const primary = async () => {
  //     let result;
  //     if (userinfo.value.username) {//修改
  //       result = await updateUserinfoApi(userinfo.value);
  //     } 
  //     if (result && result.code) {//成功
  //       ElMessage.success('修改成功');
  //       dialogVisible.value = false;
  //       search();
  //     } else {//失败
  //       ElMessage.error(result ? result.msg : '操作失败');
  //     }
    
  // }


const userinforef = ref()
// 保存修改用户信息
const save = async () => {
  // 表单校验
  if (!userinforef.value) return;
  await userinforef.value.validate(async (valid) => {//valid:是否校验通过 true:通过 false:不通过
    if (valid) {//通过
      let result;
      result = await updateUserinfoApi(userinfo.value);  
      if (result.code) {//成功
        ElMessage.success('保存成功');
        changeuserinfo.value = false;
        // search();
      } else {//失败
        ElMessage.error(result.msg);
      }
    } else {//不通过
      ElMessage.error('表单校验不通过');
    }
  })
}

//表单校验规则
const rules = ref({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 5, message: '用户名长度应在2到5个字符之间', trigger: 'blur' }
  ],
  gender: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    /*
    正则表达式：
    ^：以什么开头
    $：以什么结尾
    [3-9]：匹配3-9之间的数字
    \d:数字0到9之间
    {9}:量词
    */
    { pattern: /^1[3-9]\d{9}$/, message: '请输入有效的手机号', trigger: 'blur' }
  ],
  email:[
    { required: true, message: '请输入有效邮箱', trigger: 'blur' },
    { pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '请输入有效的邮箱', trigger: 'blur' }
  ],
  // age: [
  //   { required: true, message: '请输入年龄', trigger: 'blur' },
  //   // { type: 'number', message: '年龄必须为数字值', trigger: 'blur' },
  //   { min: 1, max: 120, message: '年龄在1到120之间', trigger: 'blur' }
  // ]
  
});

//文件上传
// 图片上传成功后触发
const handleAvatarSuccess = (response) => {
  console.log(response);
  userinfo.value.avatar = response.data;
}
// 文件上传之前触发
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('只支持上传图片')
    return false
  } else if (rawFile.size / 1024 / 1024 > 4) {
    ElMessage.error('只能上传4M以内图片')
    return false
  }
  return true
}

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



// 搜索表单对象
// const searchForm = ref({ username: '', gender: '', email:'', phone:'',authorType:'',createTime: '', updateTime: '' })


const userinfo = ref({
  username: '',
  gender: '',
  email: '',
  phone: '',
  authorType: '',
  createTime: '',
  updateTime: ''
})

const clear = () => {}

</script>
<style scoped>
*{
  transition: all 1.5s ease;

}
.home {
  min-height: 200vh;
  /* height: auto; */
  /* background-color: #323232; */
  background: url("@/assets/background-imge.png") center/cover  no-repeat;
  background-color: rgb(255, 255, 255,0);
  background-blend-mode: overlay;
  background-attachment: scroll; /* 背景图片随页面滚动 */
  border-radius: 10px;
  margin: 5px ;
  border: 1px solid #656363;
  transition: all 1.5s ease;

}


.dark-mode .home {
  transition: all 1.5s ease;
  background-color: rgba(0, 0, 0, 0.5);
  background-blend-mode: overlay;

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
  background-color:none;
  padding: 8px 16px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  position: absolute;
  top: 18%;
  left: 50%;
  transition: all 1.5s ease;
}

.dark-mode .nav {
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
  transition:  0.3s ease;
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
.list{
  margin-top: 140px; 
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
.user-0{
  text-decoration: none;
  display: flex;
  
  min-height: 20px;


  color: #000000;
  font-weight: 300;
  font-size: 30px;
  transition: color 0.3s ease;
 /* 确保每个链接项都统一高度并垂直+水平居中 */
  /* position: fixed; */
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
  height: 70px; /* 固定高度 */
  min-width: 305px; /* 统一最小宽度，保持对齐 */
  text-align: center; /* 文本居中 */
  line-height: normal; /* 防止line-height影响布局 */
  transition: all 1s ease;

  margin-top: 30px;
  margin-left: -30.5px;
  

}
.dark-mode .user-0{ 
  color: #e6e6e6;

}
.user-0.router-link-active{
  transition: all 1s ease;
  color: #3bbde9; /* 选中文字颜色 */
  font-weight: bold;
  border-radius: 10px;
  border-left:  10px solid #3bbde9;

}
.user-0:hover{
  color: #3bbde9;
  transition: all 1s ease;
}



.list-right-top{
    margin-top: 140px; 
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
.list-right-bottom{
     margin-top: 420px; 
    background-color: rgba(255, 255, 255, 0.5);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.4);
    border-radius: 7px 7px 40px 7px;
    width: 47%; 
    min-height: 720px;
    border: 1px solid #ffffff;
    backdrop-filter: blur(5px);
    position: absolute;
    left: 36%; 
    padding: 20px;
    transition: all 1.5s ease;
}
.dark-mode .list-right-bottom{
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
.change-avatar{
  width: 160px;
  height: 160px;
  margin-left: 15px;

}

.loginname{
  font-size: 30px;
  position: fixed;
  top: 48%;
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
  top: 53.5%;
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

.create-time{
  font-size: 16px;
  position: fixed;
  top: 47%;
  left: 65%;
  color: #000000;
  transition: all 1.5s ease;

}

.update-time{
  font-size: 16px;
  position: fixed;
  top: 63%;
  left: 65%;
  color: #000000;
  transition: all 1.5s ease;

}

.dark-mode .update-time {
  color: #ffffff;
  transition: all 1.5s ease;

}
.dark-mode .create-time {
  color: #ffffff;
  transition: all 1.5s ease;

}

.change-dialog{
  backdrop-filter: blur(5px);
  
}
.dark-mode .change-dialog{
  background-color: rgba(0, 0, 0, 0.5);
  transition: all 1.5s ease;

}


</style>
