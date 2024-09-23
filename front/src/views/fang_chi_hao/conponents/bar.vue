<template>
  <v-chart style="width: 800px; height: 400px" class="charts" :option="option" />

</template>

<script lang="ts" setup>

import { CanvasRenderer } from "echarts/renderers";
import { LineChart } from "echarts/charts";
import { use } from "echarts/core";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  ToolboxComponent,
  GridComponent
} from "echarts/components";
import VChart, { THEME_KEY } from "vue-echarts";
import { ref ,provide, reactive, onMounted} from "vue";
import CCERProjects from "../API/CCERProjects";

use([ 
  TitleComponent,
  ToolboxComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  LineChart,
  CanvasRenderer,
]);

provide(THEME_KEY, "white");

type barData={
  value:string,
  itemStyle:{}
}

let data=reactive([])
let valueData=reactive<barData[]>([])
let typeData=[]
let colorData=[
  {
    color: '#67b580'
  },
  {
    color: '#86ba71'
  },
  {
    color: '#feb041'
  },
  {
    color: '#fe895c'
  },
  {
    color: '#ffdc3a'
  },
]


const option = ref({
  title: {
    text: "CCER项目减排量统计",
    left: "center",
    padding: [7,0]
  },
  xAxis: {
    type: 'category',
    data: typeData
  },
  yAxis: {
    type: 'value'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'cross',
      label: {
        backgroundColor: '#283b56'
      }
    }
  },
  series: [
    {
      data: valueData,
      type: 'bar'
    }
  ]
});

function GetProjectReductionAmounts(){
  CCERProjects.getProjectReductionAmounts().then((result)=>{
      Object.assign(data,result.data.data)
      for(let d in data){
        valueData.push({value:data[d].projectReductionAmount,itemStyle:colorData[d]})
        typeData[d]=data[d].projectType     
      }

  }).catch((err)=>{
    alert("请求失败1")
  })
}

onMounted(()=>{
  GetProjectReductionAmounts()
})


</script>

<style  scoped>
.chart {
  height: 500px;
  width: 700px;
}


</style>