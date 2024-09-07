<template>
  <div class="home" style="height: 100%;width: 100%;">
    <el-container style="height: 100%; width: 100%; padding: 0;">
      <el-header style="width: 100%; height: 5%; ">
        
        <div style="height: 100%;width: 15%;display: flex;text-align: center;align-items: center;">
          企业碳信息管理
        </div>

      </el-header>

      <el-container style="height: 95%; border: 1px solid #eee;">

        <el-aside width="200px" style="background-color: rgb(25, 26, 32);">
          <el-scrollbar style="height: 100%;background-color: rgb(52, 73, 94)">
            <!-- 设置路由属性 = true -->
            <el-menu :router="true">
              <el-sub-menu index="1">
                <template #title>
                  碳排放资产管理
                </template>

                <el-sub-menu index="1">
                  <template #title>排放源管控</template>
                  <!-- index属性对应路由路径 /about -->
                  <el-menu-item index="/emissionSourceSketch">排放源概览</el-menu-item>
                  <el-menu-item index="/emissionData">排放数据展示</el-menu-item>
                  <el-menu-item index="/reductionProject">减排信息</el-menu-item>
                </el-sub-menu>

                <el-sub-menu index="2">
                  <template #title>减排分析</template>
                  <el-menu-item index="/notfound">暂无内容</el-menu-item>
                </el-sub-menu>

                <el-sub-menu index="3">
                  <template #title>年度排放展示</template>
                  <el-menu-item index="">Option 4-1</el-menu-item>
                </el-sub-menu>

              </el-sub-menu>
            </el-menu>
          </el-scrollbar>
        </el-aside>

        <!-- 路由出口 -->
        <el-container>
          <el-main style="padding: 2px;">
            <router-view></router-view>
          </el-main>
        </el-container>

      </el-container>

    </el-container>
  </div>
</template>

<style>
html,
body {
  height: 100%;
  /* 确保body元素占满整个视口高度 */
  margin: 0;
  /* 移除默认的外边距 */
}

.center-text {
  text-align: center;
  /* 水平居中 */
  line-height: 100%;
  /* 行高与容器高度相同 */
  height: 100%;
  /* 父元素高度 */
}

.header-center {
  
  display: flex;
  justify-content: center;
  /* align-items: center; */
}

</style>

<script lang="ts" setup>
import { ref } from 'vue'

const activeIndex = ref('1')
const activeIndex2 = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>
