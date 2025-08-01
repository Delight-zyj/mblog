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
      <router-link to="/userinfo" class="user-0">个人信息</router-link>
      <router-link to="/user-1" class="user-0">个人博客</router-link>
      <router-link to="/user-2" class="user-0">个人主页</router-link>
        
        
      </div>
      <div class="list-right-top">
         <el-avatar :src="avatar ? avatar : one"  class="avatar"/>
        <a class="loginname" >{{ loginName }}</a>
        <button class="change-username">     <el-icon><EditPen /></el-icon>  </button>
        <button class="change-userinfo" @click="edit(id)">     修改个人信息>  </button>

        <span class="email"><el-icon><Message /></el-icon>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ email ?email :'未设置邮箱' }}</span>
        <span class="phone"><el-icon><Iphone /></el-icon>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ phone ? phone : '未设置手机号'}}</span>
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
      <!-- <el-row :gutter="20">
        <el-col :span="3"></el-col>
        <el-col :span="12">
          <el-form-item label="最近修改日期">
            <el-date-picker v-model="userinfo.updateTime" type="date" style="width: 100%;" placeholder="选择日期"
              format="YYYY-MM-DD" value-format="YYYY-MM-DD" disabled></el-date-picker>
          </el-form-item>
          <el-form-item label="注册日期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
            <el-date-picker v-model="userinfo.createTime" type="date" style="width: 100%;" placeholder="选择日期"
              format="YYYY-MM-DD" value-format="YYYY-MM-DD" disabled></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row> -->

      <!-- 第五行 -->
      <el-row :gutter="20">
        <el-col :span="3"></el-col>
        <el-col :span="12">
          <el-form-item label="头像">
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
        <el-button type="primary" @click="changeuserinfo = false">
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

//性别列表数据
const genders = ref([{ name: '男', value: 1 }, { name: '女', value: 2 },{ name: '未说明性别', value: 3 },{ name: '未知性别', value: 4 }])

const dialogVisible = ref(false)
const changeuserinfo = ref(false)

// 获取用户信息
const id = ref('');
const loginName = ref('');
const age = ref('');
const phone = ref('');
const email = ref('');
const gender = ref('');
const author = ref('');
const createTime = ref('');
const updateTime = ref('');
const avatar = ref('');
const router = useRouter();
onMounted(() => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  // 获取用户id信息
  if (loginUser && loginUser.id) {
    id.value = loginUser.id;
  }
  // 获取用户名信息
  if (loginUser && loginUser.username) {
    loginName.value = loginUser.username;
  }
  // 获取用户头像信息
  if (loginUser && loginUser.avatar) {
    avatar.value = loginUser.avatar;
  }
  // 获取用户年龄信息
  if (loginUser && loginUser.age) {
    age.value = loginUser.age;
  }
  // 获取用户手机信息
  if (loginUser && loginUser.phone) {
    phone.value = loginUser.phone;
  }
  // 获取用户邮箱信息
  if (loginUser && loginUser.email) {
    email.value = loginUser.email;
  }
  // 获取用户类型信息
  if (loginUser && loginUser.author) {
    if(loginUser.author === 0){
      author.value = '超级管理员';
    }
    if(loginUser.author === 1){
      author.value = '普通用户';
    }
  }
  // 获取用户性别信息
  if (loginUser && loginUser.gender) {
    if(loginUser.gender === 1){
      gender.value = '男';
    }
    if(loginUser.gender === 2){
      gender.value = '女';
    }
    if(loginUser.gender === 3){
      gender.value = '未说明性别';
    }
    if(loginUser.gender === 4){
      gender.value = '未知性别';
    }
  }
  // 获取用户创建时间信息
  if (loginUser && loginUser.createTime) {
    createTime.value = loginUser.createTime;
  }
  // 获取用户修改时间信息
  if (loginUser && loginUser.updateTime) {
    updateTime.value = loginUser.updateTime;
  }
})

const edit = async (username) => {
  const result = await getUserinfoByIdApi(id.value);
  changeuserinfo.value = true;
  userinfo.value = result.data;

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


const search = async () => {
  const result = await SelectUserInfo(
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
  top: 5px;
  left: 50%;
  transform: translateX(-50%);
  width: 96.65%;
  margin: 0 auto;
  z-index: 2;
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
.list-right-bottom{
     margin-top: 400px; 
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

.change-dialog{
  backdrop-filter: blur(5px);
  
}
.dark-mode .change-dialog{
  background-color: rgba(0, 0, 0, 0.5);
  transition: all 1.5s ease;

}


</style>
