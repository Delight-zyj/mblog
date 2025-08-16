<template>
  <div class="home">
    <div class="container">
      <!-- Logo -->
       <button style="background: none; border: none;">
      <img src="../../assets/logo.png" alt="logo" class="logo" />
      <audio ref="audioPlayer" :src="audioSrc"></audio>
        
       </button>

      <!-- 导航栏 -->
      <nav class="nav">
        <router-link to="/home" class="nav-link">首页</router-link>
        <router-link to="/blog" class="nav-link">博客</router-link>
        <router-link to="/author" class="nav-link">作者</router-link>
        <router-link to="/digital" class="nav-link">数码</router-link>
        <router-link to="/" class="nav-link">博客</router-link>
      </nav>

      <!-- 深色模式控制按钮 -->
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
           {{ userinfo.username ? '退出登录' : '去登录' }} 
          </a>
      </div>
    </div>

<el-form :inline="true" :model="searchDigita" class="el-form-1">
      <el-form-item label="型号">
        <el-input style="width: 235px; margin-bottom: 10px;" v-model="searchDigita.digitalname" placeholder="请输入型号" clearable />
      </el-form-item>
      <el-form-item label="类型">
        <div class="digital-type">
          <el-select @change="brandListLoad(searchDigita.typeid)" style="width: 220px; margin-bottom: 10px; border-radius: 20px !important;"  v-model="searchDigita.typeid" placeholder="请选择类型" placement="right-start" :teleported="false" clearable>
            <el-option v-for="type in typeList" :key="type.typeid" :label="type.typename" :value="type.typeid"></el-option>
        </el-select>
        </div>
        
      </el-form-item>
      <el-form-item label="品牌">
        <div class="digital-brand">
          <el-select  style="width: 220px; margin-bottom: 10px; border-radius: 20px  !important; text-align: center;" v-model="searchDigita.brandid" placeholder="请选择品牌" placement="right-start" clearable>
              <el-option  v-for="brand in brandList" :key="brand.brandid" :label="brand.brandname" :value="brand.brandid"></el-option>
          </el-select>
        </div>
        
      </el-form-item>
        <el-form-item label="发布时间">
          <br/>
          <div class="el-date-picker">
            <el-date-picker  style="margin-left: -70px; width: 244px;" v-model="searchDigita.release_time" type="daterange" range-separator="到" start-placeholder="开始日期"
                end-placeholder="结束日期" value-format="YYYY-MM-DD" />
          </div>      
      </el-form-item>
      <el-form-item label="电池容量" style="color: black;" class="price">
        <br/>
        <el-input v-model="searchDigita.min" type="number" placeholder="请输入最低容量"  :precision="2" style="width: 300px; margin-left: -65px;"/>
        <br/>
        <el-input v-model="searchDigita.max" type="number" placeholder="请输入最高容量"  :precision="2" style="width: 300px; margin-left: -65px;"/>
      </el-form-item>
      <el-form-item>
        <el-button style="margin-left: 60px;" type="primary" @click="search1">查询</el-button>
        <el-button type="info" @click="clear1">清空</el-button>
      </el-form-item>
    </el-form>

    <div class="digitalTable" style=" margin: 200px 30px 20px 400px; width: 1239px;">
    <el-table :data="digitalList" border style="width: 100%">
      <el-table-column type="index" prop="digitalId" label="序号" width="60" align="center" />
      <el-table-column prop="digitalname" label="型号" width="130" align="center" />
      <el-table-column prop="brandname" label="品牌" width="60" align="center"/>
     
      <el-table-column prop="typename" label="类型" width="60" align="center" />
        
      <el-table-column prop="digitalimage" label="图片" width="120" align="center">
        <template #default="scope">
          <div class="digitalimg">
          <img :src="scope.row.digitalimg" height="90px" />
          </div>
        </template>
      </el-table-column>/>
      <el-table-column prop="digitalsoc" label="处理器型号" width="120" align="center" />
      <el-table-column prop="digitalprice" label="发售价格" width="163" align="center" />
      <el-table-column prop="digitalbattery" label="电池容量（mAh）" width="120" align="center" />
      <el-table-column prop="releaseTime" label="发布时间" width="120" align="center" />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" v-if="userinfo.authorType===0" size="small" @click="select(scope.row.digitalId)">修改</el-button>
          <el-button type="primary" size="small" @click="select(scope.row.digitalId)">查看详细参数</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
 
  </div>
  <div class="digitalinfo">
    <el-dialog v-model="digitalinfodialog" title="详细信息" :top="'130px'">
    <el-form :model="digitalinfo"  ref="digital" label-width="80px">

    <el-row :gutter="20">
        <el-col :span="13">
          <el-form-item >
            
              <img v-if="digitalinfo.digitalimg"  :src="digitalinfo.digitalimg" style="width: 320px; height: 320px; " class="digitalimg"/>
              <img v-else src="@/assets/10.png" style="width: 320px; height: 320px;"/>
            
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item  prop="digitalname">
            <el-text style="font-size: 20px; font-weight: 1000;">{{ digitalinfo.digitalname }}</el-text>
          </el-form-item>
          <el-form-item  prop="digitalbrand">
            <el-text>品牌：{{ digitalinfo.brandname }}</el-text>
          </el-form-item>
          <el-form-item  prop="digitaltype">
            <el-text>电池：{{ digitalinfo.digitalbattery }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mAh</el-text>
          </el-form-item>
          <el-form-item  prop="digitaltype">
            <el-text>处理器：{{ digitalinfo.digitalsoc }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</el-text>
          </el-form-item>
          <el-form-item  prop="digitaltype">
            <el-text>价格：<br/>{{ digitalinfo.digitalprice }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</el-text>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item  prop="digitaltype">
            <el-text>发布时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ digitalinfo.releaseTime }}</el-text>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item  prop="digitalInternet">
            <el-text>网络规格&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ digitalinfo.digitalInternet }}</el-text>
          </el-form-item>
        </el-col>
        

        <el-col :span="5"> 
           <el-form-item >
            <el-text>影像参数</el-text>
           
          </el-form-item>
        </el-col>
        <el-col :span="16"> 
           <el-form-item  prop="digitalcamera">
             <el-text>{{ digitalinfo.digitalcamera }}</el-text>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20"> 
          <el-col :span="5"> 
           <el-form-item >
            <el-text>屏幕</el-text>
           
          </el-form-item>
        </el-col>
        <el-col :span="16"> 
           <el-form-item  prop="digitalscreen">
             <el-text>{{ digitalinfo.digitalscreen }}</el-text>
          </el-form-item>
        </el-col>
      </el-row>
       <el-row :gutter="20"> 
          <el-col :span="5"> 
           <el-form-item >
            <el-text>屏幕特性</el-text>
           
          </el-form-item>
        </el-col>
        <el-col :span="16"> 
           <el-form-item  prop="digitalscreencharacteristics">
             <el-text>{{ digitalinfo.digitalscreencharacteristics }}</el-text>
          </el-form-item>
        </el-col>

           <el-col :span="5"> 
           <el-form-item >
            <el-text>尺寸和重量</el-text>
           
          </el-form-item>
        </el-col>
        <el-col :span="16"> 
           <el-form-item  prop="digitalDimensionsAndWeight">
             <el-text>{{ digitalinfo.digitalDimensionsAndWeight }}</el-text>
          </el-form-item>
        </el-col>

         <el-col :span="5"> 
           <el-form-item >
            <el-text>充电规格</el-text>
           
          </el-form-item>
        </el-col>
        <el-col :span="16"> 
           <el-form-item  prop="digitalcharge">
             <el-text>{{ digitalinfo.digitalcharge }}</el-text>
          </el-form-item>
        </el-col>
        

      </el-row>

    </el-form>

  </el-dialog>
  </div>
  
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
import { ref, onMounted,onBeforeUnmount,watch } from 'vue'
import { useRouter } from 'vue-router'
import one from '@/assets/1.png'
import loginUser from '@/router/index';
import { getUserinfoByIdApi, updateUserinfoApi } from '@/views/api/userinfo'
import { getDigitalList,getDigitalById,getDigitalBrandByTypeId,getDigitalType } from'@/views/api/digital.js'
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


// 已登录时否，获取用户信息
onMounted(() => {
  search1()
  typeListLoad()
  window.addEventListener('pagehide', handlePageHide);

  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if (loginUser && loginUser.username) {
    loginName.value = loginUser.username;
    id.value = loginUser.id;
    getUserInfo(id.value);
  }
})

const loginName = ref('');
const router = useRouter();
// 获取用户信息的方法
const getUserInfo = async (id) => {
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

const search1 = async () => {
  const result = await getDigitalList(
    searchDigita.value.digitalname,
    searchDigita.value.brandid,
    searchDigita.value.typeid,
    searchDigita.value.begin,
    searchDigita.value.end,
    searchDigita.value.max,
    searchDigita.value.min,
  );
  if(result.code){
    digitalList.value = result.data;
  }
 
}

// 搜索digital对象清理
const clear1 = () => {
  searchDigita.value = { digitalname: '' ,brandid: '',typeid: '',release_time: [],begin: '',end: '',max: '',min: '' }
}
// 搜索userinfo对象
const searchForm = ref({ username: '', gender: '', email:'', phone:'',authorType:'',createTime: '', updateTime: '' })

const id = ref('');

// 数码列表数据
const digitalList = ref([{}])

const searchDigita = ref({ digitalname: '' ,brandid: '',typeid: '',release_time: [],begin: '',end: '',max: '',min: ''})
// 发布时间监听
watch(() => { return searchDigita.value.release_time }, (newVal, oldVal) => {
  if (newVal.length == 2) {
    searchDigita.value.begin = newVal[0];
    searchDigita.value.end = newVal[1];
  } else {
    searchDigita.value.begin = '';
    searchDigita.value.end = '';
  }
})



// 退出登录
const logout = async () => {
   if (!userinfo.username) {
    // 未登录，直接跳转登录页
    router.push('/login');
    return;
  }
   // 已登录，弹出确认框
  ElMessageBox.confirm('确认退出登录吗?', '提示',
    { confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning', }
  ).then(async () => {
    ElMessage.success('退出成功');
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



onBeforeUnmount(() => {
  window.removeEventListener('pagehide', handlePageHide);
})


const digitalinfodialog = ref(false)
const digitalinfo  = ref({
  digitalId: '',
  digitalname: '',
  brandname: '',
  typename: '',
  digitalbattery: '',
  digitalprice: '',
  digitalscreen: '',
  digitalimg: '',
  digitalsoc: '',
  releaseTime: '',
  digitalcamera:''
})

const select = async (digitalId) =>{
  const result = await getDigitalById(digitalId);
  if (result.code) {
    digitalinfo.value = result.data;
    digitalinfodialog.value = true;
  }
}

const brandListLoad =async (typeid) => {
  const result = await getDigitalBrandByTypeId(typeid);
  if (result.code) {
    brandList.value = result.data;
  }
}
const typeListLoad = async () => {
  const result = await getDigitalType();
  if (result.code) {
    typeList.value = result.data;
  }
}

const typeList = ref([]);
const brandList = ref([]);

// 添加级联选择器所需的数据
const options = ref([
  {
    value: 'frontend',
    label: '前端开发',
    children: [
      {
        value: 'vue',
        label: 'Vue'
      },
      {
        value: 'react',
        label: 'React'
      }
    ]
  },
  {
    value: 'backend',
    label: '后端开发',
    children: [
      {
        value: 'java',
        label: 'Java'
      },
      {
        value: 'python',
        label: 'Python'
      }
    ]
  }
]);

const props2 = ref({
  expandTrigger: 'hover'
});






</script>
<style  scoped>
*{
  transition: all 1.5s ease;
}
.home {
  min-height: 1000vh;
  background-color: #ffffff;
  border-radius: 10px;
  margin: 5px;
  border: 1px solid #fffefe;
}

.dark-mode .home {
  background-color: #000000;
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
  background-color: none;
  padding: 8px 16px;
  border-radius: 8px;
  position: absolute;
  top: 18%;
  left: 50%;
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
  right: 24.5%;
  transition: all 1.3s ease;
}
/* 添加自定义切换动画速度 */
.change :deep(.el-switch__core) {
  transition-duration: 0.6s;
  border: 1px solid rgba(255, 255, 255, 0.5);
}

.change :deep(.el-switch__action) {
  transition-duration: 0.6s; 
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
}
.dark-mode .quit {
  color: #ffffff;
  /* background-color: #000000; */
}
.quit:hover{
  color: rgb(0, 0, 0) !important; /* 选中文字颜色 */
  background-color: #ff0000 !important; /* 可选：背景色 */
  border-radius: 10px;
  font-weight: bold;
  transition: all 0.5s ease;
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
}
.dark-mode .operate{
  background-color: #000000;
  border: 1px solid #333;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 300px;
  /* min-height: 400px; */
}

.type{
  position: fixed;
  left: 8%;
  transform: translateX(-50%);
  width: 200px;
  z-index: 9999;
  margin: 170px 0 0 1%;
  
}
.dark-mode .type{
  background-color: #000000;
}

.type-1{
  width: 200px;
}
:deep(.type-1){
  height: 50px;

  border-radius: 300px;

}

.custom-radius .input-i {
  border: 1px solid #676767;
}

.el-form-1{
  position: fixed;
  left: 8%;
  transform: translateX(-50%);
  width: 260px;
  z-index: 9999;
  margin: 200px 0 0 5%;
  background-color: none;
  padding: 10px;
  border: 1px solid #000000;
  border-radius: 25px;

  /* border-radius: 10px; */

}
.dark-mode .el-form-1{
  border: 1px solid rgb(255, 255, 255);
}


.el-input:deep(.el-input__wrapper) {
  border-radius: 25px;
  background-color: #ffffff;
  outline: none;
  border: 0;
    transition: all 1.5s ease;

}
.dark-mode .el-input:deep(.el-input__wrapper) { 
  background-color: #020202;
   transition: all 1.5s ease;


}
.el-select:deep(.el-select__wrapper) {
  border-radius: 25px;
  background-color: #ffffff;
  outline: none;
  border: 0;
  transition: all 1.5s ease;

}
.dark-mode .el-select:deep(.el-select__wrapper) { 
  background-color: #000000;
  transition: all 1.5s ease

}




 /* :deep(.el-select-dropdown__item ){
  text-align: center;
}  */


.el-date-picker:deep(.el-range-editor.el-input__wrapper){
  border-radius: 25px;
  background-color: #fefefe !important;
  transition: all 1.5s ease

}

.dark-mode .el-date-picker:deep(.el-range-editor.el-input__wrapper) {
  background-color: #000000 !important;
  transition: all 1.5s ease

}

.digitalTable:deep(.el-table td.el-table__cell, .el-table th.el-table__cell.is-leaf){
  background-color: #ffffff;
  transition: all 1.5s ease

}
.dark-mode .digitalTable:deep(.el-table td.el-table__cell, .el-table th.el-table__cell.is-leaf){
  background-color: #060606;
  transition: all 1.5s ease

}
.digitalTable:deep(.el-table--border th.el-table__cell ){
  background-color: #ffffff;
  transition: all 1.5s ease;
}
.dark-mode .digitalTable:deep(.el-table--border th.el-table__cell ){
  background-color: #000000;
  transition: all 1.5s ease;
}

:deep(.el-table tr){
  border-radius: 25px 25px 0 0;
  background-color: #000000;
}
 .digitalinfo:deep(.el-dialog){
  background-color: #ffffff;
  transition: all 1.5s ease;
  border-radius: 25px ;
  border: 1px solid rgb(0, 0, 0);

}
.dark-mode .digitalinfo:deep(.el-dialog){
  background-color: #000000;
  transition: all 1.5s ease;
  border-radius: 25px ;
  border: 1px solid rgb(255, 255, 255);

}

.dark-mode.digitalimg{
  background-color: #000000;
  transition: all 1.5s ease;

}

</style> 

