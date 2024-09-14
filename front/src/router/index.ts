import { createRouter, createWebHashHistory } from 'vue-router';
import Layout from "../layout/index.vue";



const routes = [
    {
        path: "/module",
        component: Layout,
        redirect: "/module/home",
        meta: {
            title: "首页",
        },
        children: [
            {
                path: "/module/home",
                component: () => import("@/views/module/home/Home.vue"),
                meta: {
                    title: "主页"
                }
            }, // 访问/home url跳到Home界面

            {
                path: "/module/about",
                component: () => import("@/views/AboutView.vue"),
                meta: {
                    title: "about"
                }
            },
            {
                path: "/module/data_analysis",
                component: () => import("@/views/module/data_analysis/data_analysis.vue"),
                meta: {
                    title: "数据分析"
                }
            },


            // {
            // 	path: "/module/notfound",
            // 	component: () => import("@/views/NotFound.vue"),
            // 	meta:{
            // 		title:"无法访问"
            // 	}
            // },

            {
                path: "/module/emissionSourceSketch",
                component: () => import("@/views/module/emissionSourceSketch/emission_source_sketch.vue"),
                meta: {
                    title: "排放源概览"
                }
            },
            {
                path: "/module/reductionProject",
                component: () => import("@/views/module/reductionProject/reduction_project.vue"),
                meta: {
                    title: "减排资产"
                }
            },
            {
                path: "/module/emissionData",
                component: () => import("@/views/module/emissionData/emission_data.vue"),
                meta: {
                    title: "排放数据"
                }
            },
        ]
    },
    {
        path: "/fang_chi_hao",
        component: Layout,
        redirect: "/fang_chi_hao/CCER",
        meta: {
            title: "CCER项目管理",
        },
        children: [
            {
                path: "/fang_chi_hao/CCER",
                component: () => import("@/views/fang_chi_hao/CCER/CCER.vue"),
                meta: {
                    title: "项目数据分析"
                }
            },

            {
                path: "/fang_chi_hao/CCER_2",
                // component: Layout,
                redirect: "/fang_chi_hao/CCER_3",
                meta: {
                    title: "项目管理"
                },
                children: [
                    {
                        path: "/fang_chi_hao/CCER_3",
                        component: () => import("@/views/fang_chi_hao/CCER_3/CCER_3.vue"),
                        meta: {
                            title: "项目立项"
                        }
                    },
                    {
                        path: "/fang_chi_hao/CCER_4",
                        component: () => import("@/views/fang_chi_hao/CCER_3/CCER_3.vue"),
                        meta: {
                            title: "项目审核"
                        }
                    },
                ]
            },

        ]
    },
    {
        path: "/data_analysis",
        component: Layout,
        redirect: "/data_analysis/emission_data_map",
        meta: {
            title: "碳排放数据统计分析",
        },
        children: [
            {
                path: "/data_analysis/emission_data_map",
                component: () => import("@/views/data_analysis/emission_data_map/emission_data_map.vue"),
                meta: {
                    title: "全国碳排放量"
                }
            },
            {
                path: "/data_analysis/home",
                component: () => import("@/views/data_analysis/home/Home.vue"),
                meta: {
                    title: "主页"
                }
            },
            {
                path: "/data_analysis/user",
                component: () => import("@/views/data_analysis/user/User.vue"),
                meta: {
                    title: "查看碳排放数据总量"
                }
            },
            {
                path: "/data_analysis/score",
                component: () => import("@/views/data_analysis/score/score.vue"),
                meta: {
                    title: "查看分类数据总量"
                }
            },

        ]
    },
    {
        path: '/',
        redirect: '/login',
        meta: {
            title: 'login',
        }
    },
    {

        path: "/login",
        name: 'login',
        component: () => import('@/views/login/index.vue'),
        meta: {
            title: "login",
            requireAuth: false,
        },

    },
    {

        path: "/register",
        name: 'register',
        component: () => import('@/views/login/Register.vue'),
        meta: {
            title: "register",
            requireAuth: false,
        },

    },

];

// router.beforeEach((to, from) => {

// })

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

// router.beforeEach((to, from, next) => {
//     // 验证token，只有存在token的时候，才能跳转到内容页
//     let token = localStorage.getItem("token");
//     if (token || to.path == '/login') {
//         next();
//     }
//     else {
//         next('/login');
//     }

// })

export default router;