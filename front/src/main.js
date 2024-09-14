import './assets/main.css'

import Home from "@/views/module/home/Home.vue"
import NotFound from "@/views/NotFound.vue"
import emissionSourceSketch from "@/views/module/emissionSourceSketch/emission_source_sketch.vue"
import { createApp } from 'vue'


import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import { Axios } from 'axios'

import './api/mock'

import router from '@/router/index'


// // 配置路由规则
// const routes = [
//     {
//         path:"/home",
//         // redirect:"/CCER_3",
//         component:Home
//     }, // 访问/home url跳到Home界面
//     {path:"/notfound",component:NotFound},
//     {path:"/emissionSourceSketch",component:emissionSourceSketch},
//     {path:"/reductionProject",component:reductionProject},
//     {path:"/emissionData",component:emissionData},
//     {path:"/CCER",component:CCER},
//     {path:"/CCER_2",component:CCER_2},
//     {path:"/CCER_3",component:CCER_3},
//     {path:"/data_analysis",component:data_analysis}
// ]

// //创建路由器
// const router = createRouter({
//     history:createWebHistory(),  //路由工作模式
//     routes
// })

//加载路由器

let app = createApp(App)
app.config.globalProperties.Axios = Axios
// app.config.productionTip = false
app.use(router).use(ElementPlus)
app.mount('#app')
