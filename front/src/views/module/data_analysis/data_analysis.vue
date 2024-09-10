<template>
    <div id="year_emission_bar_chart" style="height: 50%;width: 50%;background-color:bisque;">
    </div>

</template>


<!-- js代码 -->
<script lang="ts" setup>
import * as echarts from 'echarts';
import { ref, onMounted } from "vue";
import axios from 'axios';


let baseUrl = "localhost/8080";
//1.1 展示不同合规年分的排放要求
//1.1请求数据
// const complianceYear_emissionRequired = ref([])
// function getData() {
//   let api = baseUrl + "/getYearReq";
//   //2.使用axios 进行get请求
//   axios.get(api)
//     .then((res) => {
//       //请求成功的回调函数

//       //把数据传给tableData数组
//       complianceYear_emissionRequired.value = res.data.result

//       //获取数据的总条数
//     //   total = msg.value.length

//       //获取当前页的数据
//     //   getPageData()

//       // console.log(res)
//     }).catch((err) => {
//       //请求失败的回调函数
//       console.log(err)
//     })
// }
const complianceYear_emissionRequired = ref([
    { "ComplianceYear": 2023, "EmissionsRequired": 480.0 },
    { "ComplianceYear": 2022, "EmissionsRequired": 490.0 },
    { "ComplianceYear": 2021, "EmissionsRequired": 500.0 },
    { "ComplianceYear": 2020, "EmissionsRequired": 510.0 },
    { "ComplianceYear": 2019, "EmissionsRequired": 520.0 },
    { "ComplianceYear": 2018, "EmissionsRequired": 530.0 },
    { "ComplianceYear": 2017, "EmissionsRequired": 540.0 },
    { "ComplianceYear": 2016, "EmissionsRequired": 550.0 },
    { "ComplianceYear": 2015, "EmissionsRequired": 620.0 },
    { "ComplianceYear": 2014, "EmissionsRequired": 600.0 },
])

function draw_year_emission_bar_chart() {

    var chartDom = document.getElementById('year_emission_bar_chart');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        dataset: [
            {
                //数据字段：ComplianceYear, EmissionsRequired
                dimensions: ['ComplianceYear', 'EmissionsRequired'],
                // dimensions: ['name', 'age', 'profession', 'score', 'date'],
                source: complianceYear_emissionRequired.value
            },
            {
                transform: {
                    type: 'sort',
                    config: { dimension: 'EmissionsRequired', order: 'desc' }
                }
            }
        ],
        xAxis: {
            type: 'category',
            axisLabel: { interval: 0, rotate: 30 }
        },
        yAxis: {},
        series: {
            type: 'bar',
            encode: { x: 'ComplianceYear', y: 'EmissionsRequired' },
            datasetIndex: 1
        }
    };

    option && myChart.setOption(option);
}

onMounted(() => {
    // 1.1 展示不同合规年分的排放要求
    draw_year_emission_bar_chart() 
})

</script>


<!-- 样式 -->
<style></style>