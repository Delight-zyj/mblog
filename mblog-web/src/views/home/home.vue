<template>
   <router-link to="/login">
    <el-button>
        我是ELement
    </el-button>
   </router-link>
     <el-icon>
          <Lock />
        </el-icon>
</template>

<script>
export default {
    data() {
        return {

        }
    },
    mounted(){
        this.test();
    },
    methods: {
        test() {
             console.log(this.sysconf.theme);
        }
    }
}

</script>
