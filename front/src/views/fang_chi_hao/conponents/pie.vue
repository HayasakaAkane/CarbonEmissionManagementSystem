<template>
    <v-chart class="chart" :option="option" />
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
import { ref, provide } from "vue";

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
]);

provide(THEME_KEY, "white");

const option = ref({
  title: {
    text: "CCER项目数量统计",
    left: "center",
    padding: [50,0]
  },
  //鼠标浮动显示
  tooltip: {
    trigger: "item",
    formatter: "{a} <br/>{b} : {c} ({d}%)",
    confine: true
  },
  //图标说明
  // legend: {
  //   orient: "vertical",
  //   left: "left",
  //   data: ["可再生能源", "林业碳汇", "甲烷利用", "能源效率提升 ", "碳捕集与封存"]
  // },
  //图标样式设定
  series: [
    {
      name: "CCER项目",
      type: "pie",
      radius: "55%",
      center: ["50%", "60%"],
      data: [
        { value: 723, name: "可再生能源" },
        { value: 67, name: "林业碳汇" },
        { value: 123, name: "甲烷利用" },
        { value: 87, name: "能源效率提升" },
        { value: 145, name: "碳捕集与封存" }
      ],
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



</script>

<style scoped>

.chart {
  height: 300px;
  width: 300px;
}

</style>