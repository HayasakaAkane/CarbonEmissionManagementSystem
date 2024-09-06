import './assets/main.css'

import Home from "@/components/Home.vue"
import NotFound from "@/components/NotFound.vue"
import emissionSourceSketch from "@/components/emission_source_sketch.vue"
import { createApp} from 'vue'

import App from './App.vue'
import { createRouter ,createWebHistory} from 'vue-router'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import { Axios } from 'axios'
import reductionProject from './components/reduction_project.vue'
import emissionData from './components/emission_data.vue'


//配置路由规则
const routes = [
    {path:"/home",component:Home}, // 访问/home url跳到Home界面
    {path:"/notfound",component:NotFound},
    {path:"/emissionSourceSketch",component:emissionSourceSketch},
    {path:"/reductionProject",component:reductionProject},
    {path:"/emissionData",component:emissionData}
]

//创建路由器
const router = createRouter({
    history:createWebHistory(),  //路由工作模式
    routes
})

//加载路由器

let app = createApp(App)
app.config.globalProperties.Axios = Axios
app.use(router).use(ElementPlus)
app.mount('#app')
