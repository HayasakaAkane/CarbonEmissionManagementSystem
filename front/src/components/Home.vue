<template>
  <div class="home" style="height: 100%;">

    <el-header style="width: 100%; text-align:center;font-size:20px;">企业碳排放系统</el-header>

    <el-container style="height: 100%; border: 1px solid #eee;">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246);">
        <el-scrollbar style="height: 100%;">

          <!-- 设置路由属性 = true -->
          <el-menu :router="true">
            <el-sub-menu index="1">
              <template #title>
                碳排放资产管理
              </template>

              <el-sub-menu index="1-3">
                <template #title>排放源管控</template>
                <!-- index属性对应路由路径 /about -->
                <el-menu-item index="/emissionSourceSketch">排放源概览</el-menu-item>
                <el-menu-item index="/emissionData">排放数据展示</el-menu-item>
                <el-menu-item index="/reductionProject">减排信息</el-menu-item>
              </el-sub-menu>


              <el-sub-menu index="1-4">
                <template #title>减排分析</template>
                <el-menu-item index="/notfound">暂无内容</el-menu-item>
              </el-sub-menu>

              <el-sub-menu index="1-5">
                <template #title>年度排放展示</template>
                <el-menu-item index="">Option 4-1</el-menu-item>
              </el-sub-menu>

            </el-sub-menu>

          </el-menu>
        </el-scrollbar>
      </el-aside>

      <!-- 路由出口 -->
      <el-container style="height: 100%;">
        <el-main style="height: 100%;">
          <router-view></router-view>
        </el-main>
      </el-container>

    </el-container>

  </div>
</template>