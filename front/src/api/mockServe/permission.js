import Mock from 'mockjs'
export default {
    getMenu: config => {
        const { username, password } = JSON.parse(config.body)
        // 先判断用户是否存在
        // 判断账号和密码是否对应
        if (username === 'admin' && password === 'admin') {
            return {
                code: 20000,
                data: {
                    menu: [
                        {
                            path: '/home',
                            name: 'home',
                            label: '首页',
                            icon: 's-home',
                            url: 'Home.vue'
                        },
                        {
                            path: '/user',
                            name: 'user',
                            label: '查看碳排放总量统计数据',
                            icon: 'user',
                            url: 'User.vue'
                        },
                        {
                            path: '/score',
                            name: 'score',
                            label: '查看分类数据',
                            icon: 'score',
                            url: 'score.vue'
                        }
                    ],
                    token: Mock.Random.guid(),
                    message: '获取成功'
                }
            }
        } else if (username === 'xiaoxiao' && password === 'xiaoxiao') {
            return {
                code: 20000,
                data: {
                    menu: [
                        {
                            path: '/home',
                            name: 'home',
                            label: '首页',
                            icon: 's-home',
                            url: 'Home.vue'
                        },
                        {
                            path: '/video',
                            name: 'video',
                            label: '查看碳排放总量统计数据',
                            icon: 'video-play',
                            url: 'Mall.vue'
                        }
                    ],
                    token: Mock.Random.guid(),
                    message: '获取成功'
                }
            }
        } else {
            return {
                code: -999,
                data: {
                    message: '密码错误'
                }
            }
        }

    }
}