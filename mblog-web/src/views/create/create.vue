<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { UserFilled, Lock, Message } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { createApi } from '/src/views/api/create.js'


const createForm = ref({ username: '', password1: '', password2:'', email: '' });

const router = useRouter()

const create = async () => {
  if (!createForm.value.username) {
  ElMessage.error('用户名不能为空');
  return;
  }
  if(!createForm.value.password1){
    ElMessage.error('密码不能为空');
    return;
  }
  if(!createForm.value.password2){
    ElMessage.error('确认密码不能为空');
    return;
  }
  if(createForm.value.password1 !== createForm.value.password2){
    ElMessage.error('两次密码输入不一致');
    return;
  }
  if(!createForm.value.email){
    ElMessage.error('邮箱不能为空');
    return;
  }
  
  const result = await createApi(createForm.value);
  if(result.code){
    ElMessage.success('注册成功');
    localStorage.setItem('createUser', JSON.stringify(result.data));
  }else{
    ElMessage.error(result.msg);
  }
}





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
          <el-input class="input-i" type="password" v-model="createForm.password1" placeholder="密码"
            :prefix-icon="Lock" />
        </el-form-item>&nbsp;
        <el-form-item prop="password">
          <el-input class="input-i" type="password" v-model="createForm.password2" placeholder="确认密码"
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
