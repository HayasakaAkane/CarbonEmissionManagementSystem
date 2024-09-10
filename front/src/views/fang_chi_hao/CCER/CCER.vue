<template>
  <div>
    <div>
      <h1>CCER项目数据分析</h1>
    </div>
    <div class="lable">
      <div class="pie"><pie /></div>
      <div class="pie"><Line /></div>
    </div>
    <div><h1>项目汇总</h1></div>
    <div class="table">
      <tableshow />
      <div style="margin-top: 20px; text-align: center; width: 100%;">
        <a href="#" v-for="i in 6" :key="i">{{ i }}</a>
      </div>
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
.pie {
  width: 300px;
  height: 300px;
  margin-left: 200px;
}
.table {
}
a{
  margin-left: 20px;
}
</style>