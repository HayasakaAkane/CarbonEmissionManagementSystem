const MenuData= [
    {
      path: '/',
      name: 'home',
      label: '首页',
      icon: 's-home',
      url: 'Home/Home'
    },
    {
      path: '/mall',
      name: 'mall',
      label: '查看碳排放总量统计数据',
      icon: 'video-play',
      url: 'MallManage/MallManage'
    },
    {
      path: '/user',
      name: 'user',
      label: '查看碳排放总量统计数据',
      icon: 'user',
      url: 'UserManage/UserManage'
    },
    {
      label: '123',
      icon: 'location',
      children: [
        {
          path: '/page1',
          name: 'page1',
          label: '页面1',
          icon: 'setting',
          url: 'Other/PageOne'
        },
        {
          path: '/page2',
          name: 'page2',
          label: '页面2',
          icon: 'setting',
          url: 'Other/PageTwo'
        }
      ]
    }
]

export default MenuData