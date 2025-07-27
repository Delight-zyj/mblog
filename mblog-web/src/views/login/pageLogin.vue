<template>
  <div class="login-container">
    <div class="welcome">Welcome</div>
    <el-form ref="formRef" :model="loginForm" :rules="rules" class="input">
      <div>
        <el-form-item prop="username">
          <el-input class="input-i" type="text" v-model="loginForm.username" placeholder="用户名"
            :prefix-icon="UserFilled" />
        </el-form-item>&nbsp;
        <el-form-item prop="password">
          <el-input class="input-i" type="password" v-model="loginForm.password" placeholder="密码" :prefix-icon="Lock" />
        </el-form-item>
      </div>&nbsp;
      <el-form-item class="loginbutton">
        <el-button style=" width: 300px; height: 40px; border-radius: 50px; background-color: #000A7B;" type="primary"
          @click="login">登录</el-button>
        &nbsp;
      </el-form-item>
      <el-form-item class="loginbutton">
        <router-link to="/home">
          <el-button style=" width: 300px; height: 40px; border-radius: 50px; background-color: rgba(255, 255, 255, 0.5)" type="primary"
          @click="rem"
          >游客登录</el-button>
        </router-link>
        
        &nbsp;
      </el-form-item>

      <el-switch v-model="rememberMe" class="ml-2"
        style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949; padding-left: 10px;" />
      <span style="font-size: 13px; padding-top: 2px; padding-left: 10px; color: #fff;">记住用户名</span>
      <router-link to="/change">
        <el-button
          style="font-size: 13px;  border: 0; padding: 2px; margin-left: 100px; background-color:  transparent;">
          <span style="color: #fff;">忘记密码</span>
        </el-button>
      </router-link>
      <br>
      <router-link to="/create">
      <el-button style="font-size: 13px;  border: 0; padding: 2px; margin-left: 160px; background-color:  transparent;">
        <span style="color: #fff;">没有账号？点我注册</span>
      </el-button>
      </router-link>
      <br><br><br>
      <span style="padding-left: 65px; color: #fff;">— &nbsp;&nbsp;其他登录方式&nbsp;&nbsp; —</span>
      <br>
      <el-icon size="30" style="padding-left: 80px; padding-top: 10px;">
        <Iphone color="#fff" />
      </el-icon>
      <el-icon size="30" style="padding-left: 10px; padding-top: 10px;">
        <Iphone color="#fff" />
      </el-icon>
      <el-icon size="30" style="padding-left: 10px; padding-top: 10px;">
        <Iphone color="#fff" />
      </el-icon>
    </el-form>

  </div>

  <!-- 对话框 -->
  <el-dialog v-model="centerDialogVisible" title="忘记密码" width="500" center>
    <span style="display: block; width: 240px; margin: 0 auto;">
      此功能暂未开发
    </span>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="centerDialogVisible = false">退出</el-button>
        <el-button type="primary" @click="centerDialogVisible = false">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>
<script setup>
import { ref, watch  } from 'vue'
import { useRouter } from 'vue-router'
import { UserFilled, Lock, Iphone } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus';
import {  onMounted } from 'vue';

import { loginApi } from '../api/login'

const loginForm = ref({ username: '' , password: '' })
const router = useRouter()

// 记住密码状态
const rememberMe = ref(false)

// 监听 rememberMe 变化并保存到 localStorage
watch(rememberMe, (newValue) => {
  localStorage.setItem('rememberMeValue ', JSON.stringify(newValue))
}, { immediate: true })

const centerDialogVisible = ref(false)

// 登录
const login = async () => {
  if (!loginForm.value.username || !loginForm.value.password) {
  ElMessage.error('用户名或密码不能为空');
  return;
}
const result = await loginApi(loginForm.value);
  if (result.code) { // 成功
    // 1、提示信息：登录成功
    ElMessage.success('登录成功');
    // 2、 存储当前员工登录信息
    localStorage.setItem('loginUser', JSON.stringify(result.data))
    // 3、跳转首页
    router.push('/')
  } else { // 失败
    ElMessage.error(result.msg);
  }
}

const rem = async () => {
  localStorage.removeItem('loginUser');
}
// 页面加载时检查 localStorage 中是否有保存的登录信息
// onMounted(() => {
//  // 优先检查记住的用户名和密码
//   const rememberedUser = localStorage.getItem('rememberedUser');
//   if (rememberMe.value === true) {
//     const userData = JSON.parse(rememberedUser);
//     loginForm.value = {
//       // username: userData.username,
//       // password: userData.password
//       username: loginForm.value.username,
//       password: loginForm.value.password
//     };
//   } else {
//     // 检查是否已登录但没有记住密码
//     const savedUser = localStorage.getItem('loginUser');
//     if (savedUser) {
//       const user = JSON.parse(savedUser);
//       loginForm.value.username = user.username;
//       loginForm.value.password = user.password;
//       // 注意：不设置密码，因为安全原因通常不会存储密码
//     }
//   }
// });


onMounted(() => {
  // 1. 从 localStorage 中读取保存的登录信息
  const savedUser = localStorage.getItem('loginUser');

  if (savedUser) {
    try {
      const user = JSON.parse(savedUser);
      // 自动填充用户名和密码
      loginForm.value.username = user.username;
      loginForm.value.password = user.password;

      // 自动勾选“记住密码”
      rememberMe.value = true;
    } catch (e) {
      console.error('解析保存的用户信息失败:', e);
    }
  }
  // 如果没有保存的信息，loginForm 保持为空，无需处理
});

// 重置
// const clear = () => { loginForm.value = { username: '', password: '' } }


</script>
<style scoped>
.login-container {
  width: 100%;
  height: 100vh;
  background: url("/src/assets/login.png") no-repeat center center fixed;
  background-size: cover;
}

.welcome {
  width: 313px;
  height: 103px;
  padding-left: 246px;
  padding-top: 125px;
  font-size: 40px;
  /* font-weight: 700; */
  color: #fff;
  /* 加粗 */
  font-weight: bold;
}

.input {
  padding-left: 183px;
  width: auto;
}

.loginbutton {
  color: rgba(0, 10, 123, 1);
  border-radius: 95px;
}

.input-i {
  width: 300px;
  height: 40px;
  /* padding: 0 15px; */
}

.el-input:deep(.el-input__wrapper) {
  border-radius: 25px;
  background-color: #fff;
  outline: none;
  border: 0;
  /* box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.5); */
}

.custom-radius .input-i {
  border: 1px solid #ccc;
}
</style>
