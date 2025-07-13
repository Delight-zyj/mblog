<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { Lock, Message } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

</script>
<!-- v-model="createForm.password" -->
<template>
  <div class="change-container">
    <div class="change">修改密码</div>
    <el-form ref="formRef" :model="registrationForm" :rules="rules" class="input">
      <el-form-item>
        <el-input class="input-i" type="email" :prefix-icon="Message" placeholder="请输入邮箱"></el-input>
      </el-form-item>&nbsp;
      <el-form-item>
        <el-input class="input-i" type="password" placeholder="输入新密码" :prefix-icon="Lock" />
      </el-form-item>&nbsp;
      <el-form-item>
        <el-input class="input-i" type="password" placeholder="再次输入新密码" :prefix-icon="Lock" />
      </el-form-item>&nbsp;

      <el-form-item class="changebutton">
        <el-button style=" width: 300px; height: 40px; border-radius: 50px; background-color: #000A7B;" type="primary"
          @click="change">修改</el-button>
        &nbsp;
      </el-form-item>
      <el-form-item class="changebutton">
        <router-link to="/login" style="text-decoration: none;">
          <el-button style=" width: 300px; height: 40px; border-radius: 50px; background-color: rgba(255, 255, 255, 0.5);"
            type="primary">返回登录</el-button>
          &nbsp;
          </router-link>
      </el-form-item>
    </el-form>
  </div>

</template>
<style scoped>
.change-container {
  width: 100%;
  height: 100vh;
  background: url("/src/assets/login.png") no-repeat center center fixed;
  background-size: cover;
}

.change {
  width: 300px;
  height: 103px;
  padding-left: 250px;
  padding-top: 125px;
  font-size: 40px;
  /* font-weight: 700; */
  color: #fff;
  /* 加粗 */
  font-weight: bold;
}

.input-i {
  width: 300px;
  height: 40px;
  /* padding: 0 15px; */
}

.input {
  padding-left: 183px;
  width: auto;
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

.loginbutton {
  color: rgba(0, 10, 123, 1);
  border-radius: 95px;
}
</style>
