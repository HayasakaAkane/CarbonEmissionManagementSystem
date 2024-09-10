<template>
  <div class="home" style="height: 100%;width: 100%;">
    
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
