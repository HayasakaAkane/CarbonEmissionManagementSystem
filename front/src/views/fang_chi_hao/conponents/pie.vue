<template>
  <div>
    <v-chart style="height: 400px" class="chart" :option="option" />
  </div>
</template>

<script lang="ts" setup>

import { use } from "echarts/core";
import { CanvasRenderer } from "echarts/renderers";
import { PieChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent
} from "echarts/components";
import VChart, { THEME_KEY } from "vue-echarts";
import { ref, provide, reactive, onMounted } from "vue";
import CCERProjects from "../API/CCERProjects";

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
]);

provide(THEME_KEY, "white");

type pieData={
  value: number,
  name:string
}

let data =reactive([])
let Data =reactive<pieData[]>([])

const option = ref({
  title: {
    text: "CCER项目数量统计",
    left: "center",
    padding: [7,0]
  },
  //鼠标浮动显示
  tooltip: {
    trigger: "item",
    formatter: "{a} <br/>{b} : {c} ({d}%)",
    confine: true
  },
 
  //图标样式设定
  series: [
    {
      name: "CCER项目",
      type: "pie",
      radius: "55%",
      center: ["50%", "60%"],
      data: Data,
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: "rgba(0, 0, 0, 0.5)"
        }
      }
    }
  ]
});



function GetProjectAmounts(){
  CCERProjects.getProjectAmounts().then((result)=>{
    Object.assign(data,result.data.data)

    for(let d in data)
       {
         Data.push({value: data[d].projectAmount ,name: data[d].projectType})
      }

  }).catch((err)=>{
    alert("请求失败")
  })
}

onMounted(()=>{
  GetProjectAmounts()
})


</script>

<style scoped>

.chart {
  height: 300px;
  width: 300px;
}

</style>