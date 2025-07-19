<template>
  <div class="home">
    <div class="container">
      <!-- Logo -->
      <img src="../../assets/logo.png" alt="logo" class="logo" />

      <!-- 导航栏 -->
      <nav class="nav">
        <router-link to="/home" class="nav-link">首页</router-link>
        <a href="#" class="nav-link">页面1</a>
        <a href="#" class="nav-link">页面2</a>
        <a href="#" class="nav-link">页面3</a>
        <a href="#" class="nav-link">页面4</a>
      </nav>

      <!-- 控制按钮 -->
      <el-button @click="toggleDark" class="change">切换深色/浅色模式</el-button>
      <button class="avatar">
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      </button>
    </div>

    <div class="block text-center">
      <!-- <span class="demonstration">Motion blur the switch (default)</span> -->
        <el-carousel height="600px" motion-blur>
        <el-carousel-item v-for="item in 4" :key="item">
          <h3 class="small justify-center" text="2xl">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>
    <!-- <p class="text-center demonstration">Vertical effect</p>
    <el-carousel
      height="200px"
      direction="vertical"
      motion-blur
      :autoplay="true"
    >
    <el-carousel-item v-for="item in 4" :key="item">
      <h3 text="2xl" justify="center">{{ item }}</h3>
    </el-carousel-item>
  </el-carousel> -->
  </div>
</template>

<script>
import { ElButton } from 'element-plus'

export default {
  data() {
    return {
      darkMode: false
    };
  },
  methods: {
    toggleDark() {
      this.darkMode = !this.darkMode;
      if (this.darkMode) {
        document.documentElement.classList.add('dark-mode');
      } else {
        document.documentElement.classList.remove('dark-mode');
      }
    }
  }
};
</script>
<style scoped>
.home {
  min-height: 100vh;
  background-color: #ffffff;
  border-radius: 10px;
  margin: 5px;
  border: 1px solid #656363;
}

.dark-mode .home {
  background-color: #000000;
}

.container {
  position: relative;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px 10px 0 0;
  border-bottom: 1px solid #656363;
}

.dark-mode .container {
  background-color: #000000;
}

.logo {
  width: 47px;
  height: 47px;
  margin: 0px;
}

/* 导航栏 Flex 布局 */
.nav {
  display: flex;
  gap: 30px;
  justify-content: center;
  transform: translateX(-50%);
  background-color: #fff;
  padding: 8px 16px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  position: absolute;
  top: 18%;
  left: 60%;
}

.dark-mode .nav {
  background-color: #1e1e1e;
}

.nav-link {
  text-decoration: none;
  color: #000000;
  font-weight: 500;
  transition: color 0.3s ease;
 /* 确保每个链接项都统一高度并垂直+水平居中 */
  display: flex;
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
  height: 40px; /* 固定高度 */
  min-width: 80px; /* 统一最小宽度，保持对齐 */
  text-align: center; /* 文本居中 */
  line-height: normal; /* 防止line-height影响布局 */
}

.dark-mode .nav-link {
  color: #e6e6e6;
}

.nav-link:hover {
  color: cornflowerblue;
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

.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 2000px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

</style>
