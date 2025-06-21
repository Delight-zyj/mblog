<template>
    <el-button>我是ELement</el-button>
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
