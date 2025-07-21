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
      <button class="avatar">
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      </button>
    </div>
      <el-col :span="12" style="margin: 100px 0 0 5%;" class="type">
      <el-menu
        active-text-color="#ffffff"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="2"
        text-color="#fff"
        @open="handleOpen"
        @close="handleClose"
      >
        <el-sub-menu index="1" >
          <template #title>
            <el-icon><location /></el-icon>
            <span>分类</span>
          </template>
          <el-menu-item-group title="编程">
            <el-menu-item index="1-1">Java</el-menu-item>
            <el-menu-item index="1-2">前端</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="其他分类">
            <el-menu-item index="1-3">分类1</el-menu-item>
          </el-menu-item-group>
          <el-sub-menu index="1-4">
            <template #title>item four</template>
            <el-menu-item index="1-4-1">item one</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>

        <el-sub-menu index="2">
          <template #title>
            <el-icon><location /></el-icon>
            <span>作者</span>
          </template>
          <el-menu-item-group title="Group One">
            <el-menu-item index="2-1">item one</el-menu-item>
            <el-menu-item index="2-2">item two</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group Two">
            <el-menu-item index="2-3">item three</el-menu-item>
          </el-menu-item-group>
          <el-sub-menu index="2-4">
            <template #title>item four</template>
            <el-menu-item index="2-4-1">item one</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>

        <el-sub-menu index="3">
          <template #title>
            <el-icon><location /></el-icon>
            <span>Navigator One</span>
          </template>
          <el-menu-item-group title="Group One">
            <el-menu-item index="3-1">item one</el-menu-item>
            <el-menu-item index="3-2">item two</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group Two">
            <el-menu-item index="3-3">item three</el-menu-item>
          </el-menu-item-group>
          <el-sub-menu index="3-4">
            <template #title>item four</template>
            <el-menu-item index="3-4-1">item one</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
      </el-menu>
    </el-col>
  </div>
</template>

<script setup>
// import { ElButton } from 'element-plus'
import { ref, onMounted } from 'vue'
// import axios, { Axios } from 'axios';
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
  Setting,
} from '@element-plus/icons-vue'

import {
  darkMode,
  toggleDark
} from '../api/blackAndWhire'

const isCollapse = ref(true)
const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
}

</script>
<style scoped>
.home {
  min-height: 1000vh;
  background-color: #ffffff;
  border-radius: 10px;
  margin: 5px;
  border: 1px solid #656363;
}

.dark-mode .home {
  background-color: #000000;
}

.container {
  /* position: relative; */
  position: fixed;
  top: 5px;
  left: 50%;
  transform: translateX(-50%);
  width: 96.6%;
  margin: 0 auto;
  z-index: 9999;
  background-color: rgb(255, 255, 255, 0.5);
  padding: 10px 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  border-radius: 10px 10px 0 0;
  border: 1px solid #656363;
}

.dark-mode .container {
  background-color: rgb(0, 0, 0, 0.5);
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
  left: 60%;
}

.dark-mode .nav {
  background-color: rgb(0, 0, 0, 0.7);
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
}

.dark-mode .nav-link {
  color: #e6e6e6;
}

.nav-link:hover {
  color: cornflowerblue;
  border-radius: 4px;
}

/* 按钮样式 */
.change {
  position: absolute;
  top: 24%;
  right: 9.5%;
  width: 150px;
  height: 44px;
  border-radius: 10px;
}

.avatar {
  position: absolute;
  top: 24%;
  right: 5%;
  width: 44px;
  height: 44px;
  border-radius: 50%;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.type{
  position: fixed;
  top: 5px;
  left: 30%;
  transform: translateX(-50%);
  width: 96.6%;
  margin: 0 auto;
  z-index: 9999;
}

</style>
