<template>
  <div>
    <div>
      <h1>CCER项目数据分析</h1>
    </div>
    <div class="lable">
    <div class="pie"><pie/></div>
    <div class="pie"><Line/></div> 
  </div>
  <div class="table">
    <tableshow />
  </div>
  </div>
</template>


<script setup>
import tableshow from "@/views/fang_chi_hao/table.vue"
import pie from "../conponents/pie.vue";
import Line from "../conponents/line.vue";


</script>

<style scoped>
.pie{
  width: 300px;
  height: 300px;
  margin-left: 200px;
}
.table{

}
</style>