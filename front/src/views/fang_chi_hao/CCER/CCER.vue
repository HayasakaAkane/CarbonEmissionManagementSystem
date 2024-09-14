<template>
  <div>
    <div class="header">
      <h1>CCER项目数据分析</h1>
    </div>
    <div class="charts-container">
      <div class="chart pie1"><pie /></div>
      <div class="chart pie2"><Bar /></div>
    </div>
    <div class="summary">
      <h1>项目汇总</h1>
    </div>
    <div class="table-container">
      <tableshow />
    </div>
  </div>
</template>

<script setup>
import tableshow from "@/views/fang_chi_hao/table.vue";
import pie from "@/views/fang_chi_hao/conponents/pie.vue";
import Bar from "@/views/fang_chi_hao/conponents/bar.vue";
</script>

<style scoped>
/* 整体布局 */
div {
  max-width: 1200px;
  margin: 0 auto;
  font-family: Arial, sans-serif;
  color: #333;
}

.header, .summary {
  margin-bottom: 20px;
}

.header h1, .summary h1 {
  font-size: 26px;
  color: #2c3e50; /* 深蓝色 */
  border-bottom: 2px solid #009879; /* 边框下划线 */
  padding-bottom: 5px;
}

/* 图表区域 */
.charts-container {
  display: flex;
  justify-content: space-between;
}

.chart {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.pie1 {
  margin-right: 20px;
}

.pie2 {
  margin-left: 20px;
}

/* 表格区域 */
.table-container {
  margin-top: 40px;
}

/* 链接样式 */
a {
  color: #009879;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>
