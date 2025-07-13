<template>
  <div class="login-container">
    <div class="welcome">Welcome</div>
    <el-form ref="formRef" :model="loginForm" :rules="rules" class="input">
      <div>
        <el-form-item prop="username">
          <!-- <el-icon size="37"><UserFilled color="#fff" /></el-icon> -->
          <el-input class="input-i" v-model="loginForm.username" type="text" placeholder="用户名"
            :prefix-icon="UserFilled" />
        </el-form-item>&nbsp;
        <el-form-item label="" prop="password">
          <el-input class="input-i" type="password" v-model="loginForm.password" placeholder="密码" :prefix-icon="Lock" />
        </el-form-item>
      </div>&nbsp;
      <el-form-item class="loginbutton">
        <el-button style=" width: 300px; height: 40px; border-radius: 50px; background-color: #000A7B;" type="primary"
          @click="login">登录</el-button>
        &nbsp;
      </el-form-item>

      <el-switch v-model="value2" class="ml-2"
        style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949; padding-left: 10px;" />
      <span style="font-size: 13px; padding-top: 2px; padding-left: 10px; color: #fff;">记住密码</span>
      <!-- <span style="font-size: 13px; padding-top: 2px; padding-left: 110px; color: #fff;">忘记密码</span> -->
      <router-link to="/change">
        <el-button
          style="font-size: 13px;  border: 0; padding: 2px; margin-left: 110px; background-color:  transparent;">
          <span style="color: #fff;">忘记密码</span>
        </el-button>
      </router-link>
      <br>
      <!-- <span style="font-size: 13px; padding-top: 2px; padding-left: 160px; color: #fff;">没有账号？点我注册</span> -->
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
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { UserFilled, Lock, Iphone } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus';

import { loginApi } from '../api/login'

const loginForm = ref({ username: '', password: '' })
const router = useRouter()

const value2 = ref(true)

const centerDialogVisible = ref(false)

// 登录
const login = async () => {
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

// 重置
const clear = () => { loginForm.value = { username: '', password: '' } }

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
