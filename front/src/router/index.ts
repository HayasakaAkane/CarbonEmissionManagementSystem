import { createRouter, createWebHashHistory } from 'vue-router';
import Layout from "../layout/index.vue";



const routes = [

    {
		path: "/home",
		component: Layout,
		redirect: "/home/home",
		// component: () => import("@/views/module/home/Home.vue"),

		meta: {
			title: "主页",
			hidden: false,
		},
		children: [
			{
				path: "/home/home",
				component: () => import("@/views/home/home.vue"),
				meta: {
					title: "主页",
					hidden: false,
				}
			},
		]
	}, // 访问/home url跳到Home界面

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
                path: "/fang_chi_hao/CCER_2",
                component: () => import("@/views/fang_chi_hao/CCER_2/CCER_2.vue"),
                meta: {
                    title: "about"
                }
            },
            {
                path: "/fang_chi_hao/CCER_3",
                component: () => import("@/views/fang_chi_hao/CCER_3/CCER_3.vue"),
                meta: {
                    title: "排放源概览"
                }
            },
        ]
    },
	{
		path: "/carbonEmission",
		component: Layout,
		redirect: "/carbonEmission/carbon_emission",
		// component: () => import("@/views/module/home/Home.vue"),

		meta: {
			title: "碳排放管理",
			hidden: false,
		},
		children: [
			{
				path: "/carbonEmission/carbon_emission",
				component: () => import("@/views/carbonEmission/carbon_emission.vue"),
				meta: {
					title: "碳排放数据展示",
					hidden: false,
				}
			},
		]
	}, 
	{
		path: "/module",
		component: Layout,
		redirect: "/module/emissionSourceSketch",
		meta: {
			title: "碳资产管理",
			hidden: false,
		},
		children: [

			{
				path: "/module/emissionSourceSketch",
				component: () => import("@/views/module/emissionSourceSketch/emission_source_sketch.vue"),
				meta: {
					title: "排放源概览",
					hidden: false,
				}
			},
			{
				path: "/module/data_analysis",
				component: () => import("@/views/module/data_analysis/data_analysis.vue"),
				meta: {
					title: "排放数据分析",
					hidden: false,
				}
			},

			{
				path: "/module/emissionData",
				component: () => import("@/views/module/emissionData/reduction.vue"),
				meta: {
					title: "减排项目",
					hidden: false,
				}
			},
			{
				path: "/module/asset_transaction",
				component: () => import("@/views/module/asset_transaction/asset_transaction.vue"),
				meta: {
					title: "交易统计",
					hidden: false,
				}
			},
		]
	},

	{
		path: '/',
		redirect: '/login',
		meta: {
			title: 'login',
			hidden: true,
		}
	},

	{

		path: "/login",
		name: 'login',
		component: () => import('@/views/login/index.vue'),
		meta: {
			title: "login",
			requireAuth: false,
			hidden: true
		},

	},


];


const router = createRouter({
    history: createWebHashHistory(),
    routes
});


export default router;