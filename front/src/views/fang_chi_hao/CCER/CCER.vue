<template>
  <div>
    <div>
      <h1>CCER项目数据分析</h1>
    </div>
    <div class="lable">
      <div class="pie1"><pie /></div>
      <div class="pie2"><Line /></div>
    </div>
    <div><h1>项目汇总</h1></div>
    <div class="table">
      <tableshow />
      
    </div>
  </div>
</template>


<script setup>
import tableshow from "@/views/fang_chi_hao/table.vue";
import pie from "../conponents/pie.vue";
import Line from "../conponents/line.vue";
</script>

<style scoped>
.lable {
  overflow: hidden;
  width: "100%";
  height: 300px;
  display: flex;
  margin-bottom: 50px;
}
.pie1 {
  display: flex;
  text-align: center;
  width: 400px;
  height: 300px;
  padding-left: 100px;
}
.pie2 {
  display: flex;
  text-align: center;
  width: 800px;
  height: 300px;
  margin-left: 50px;
}
.table {
}
a{
  margin-left: 20px;
}
</style>