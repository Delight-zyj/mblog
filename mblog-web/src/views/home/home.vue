<template>
    <div>
        <img src="../../assets/logo.png" alt="logo" class=" logo invertible">
        <button class="btn1">首页</button>
        <button class="btn">页面2</button>
        <button class="btn">页面3</button>
        <button class="btn">页面4</button>
        <el-button @click="toggleDark">
            切换深色/浅色模式
        </el-button>
    </div>
</template>

<script>
// export default {
//     data() {
//         return {

//         }
//     },
//     mounted(){
//         this.test();
//     },
//     methods: {
//         test() {
//              console.log(this.sysconf.theme);
//         }
//     }
// }
// export default {
//     data() {
//         return {
//             darkMode: false
//         };
//     },
//     methods: {
//         toggleDark() {
//             this.darkMode = !this.darkMode;
//             document.documentElement.style.filter = this.darkMode
//                 ? 'invert(1) hue-rotate(180deg)'
//                 : 'none';
//         }
//     }
// };

// 设置黑暗/白天模式切换
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
/* 默认浅色样式 */
body {
    background-color: #ffffff;
    color: #333333;
}

/* 暗色模式下的样式 */
.dark-mode body {
    background-color: #121212;
    color: #e6e6e6;
}

/* 对某些图标或 UI 元素进行反色处理（可选） */
.dark-mode .invertible {
    filter: invert(1) hue-rotate(180deg);
}

.logo {
    width: 67px;
    height: 67px;
    margin: 30px 0 30px 50px;
    /* filter: invert(1) hue-rotate(180deg); */
}
.btn1{
    margin-left: 45px ;
    /* margin-top: 30px; */
    padding-bottom: 30px;
    margin-bottom: 30px;
}
.btn {
    color: #ffffff;
    padding: 45px 30px 45px 30px;
    display: flex;
    /* 启用 Flexbox */
    flex-direction: row;
    /* 默认值，可以让子元素水平排列 */
    /* padding-left: 300px; */
}

.dark-mode .btn {
    color: #080808;
    padding-left: 300px;
}
</style>
