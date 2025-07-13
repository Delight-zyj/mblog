<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { UserFilled, Lock, Message } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const createForm = ref({ username: '', password: '', email: '' });

const router = useRouter()
const value2 = ref({})

</script>
<template>
  <div class="create-container">
    <div class="create">注册</div>
    <el-form ref="formRef" :model="createForm" :rules="rules" class="input">
      <div>
        <el-form-item prop="username">
          <el-input class="input-i" type="text" v-model="createForm.username" placeholder="用户名"
            :prefix-icon="UserFilled" />
        </el-form-item>&nbsp;
        <el-form-item prop="password">
          <el-input class="input-i" type="password" v-model="createForm.password" placeholder="密码"
            :prefix-icon="Lock" />
        </el-form-item>&nbsp;
        <el-form-item prop="email">
          <el-input class="input-i" type="email" v-model="createForm.email" placeholder="邮箱"
            :prefix-icon="Message" />
        </el-form-item>
      </div>&nbsp;
      <el-form-item class="createbutton">
        <el-button style=" width: 300px; height: 40px; border-radius: 50px; background-color: #000A7B;" type="primary"
          @click="create">注册</el-button>
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
.create-container {
  width: 100%;
  height: 100vh;
  background: url("/src/assets/login.png") no-repeat center center fixed;
  background-size: cover;
}

.create {
  width: 300px;
  height: 103px;
  padding-left: 300px;
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

.createbutton {
  color: rgba(0, 10, 123, 1);
  border-radius: 95px;
}
</style>
