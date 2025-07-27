import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import sysconf  from './src/config/config.js'

// https://vitejs.dev/config/
const config = defineConfig({
  lintOnSave: false,
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    host:"localhost",//代理人地址
    open:true,//是否打开
    port: 8082,// 重点，通过代理转发到8080,可以不配置
    proxy: {
      //代理信息（转发信息）
      // 这里的dev-api可乱写, 是拼接在url后面的地址 如果接口中没有统一的后缀可自定义
      // 如果有统一后缀, 如api, 直接写api即可, 也不用rewrite了
      ['/api']: {
        target: sysconf.baseUrl,//真实地址 表示要代理到哪里去（后端端口号）
        ws: false,        //如果要代理 websockets，配置这个参数
        secure: false,  // 如果是https接口，需要配置这个参数
        changeOrigin:true,//地址替换
        rewrite: path => path.replace(/^\/api/, ''),
      }
    }
  }
});

export default config;
