<template>
    <div id="year_emission_bar_chart" style="height: 50%;width: 50%;background-color:bisque;">
    </div>

    <div id="project_reduction_pie_chart" style="height: 50%;width: 50%;background-color:bisque;">
    </div>

</template>


<!-- js代码 -->
<script lang="ts" setup>
import * as echarts from 'echarts';
import { ref, onMounted } from "vue";
import axios from 'axios';


let baseUrl = "localhost/8080";
//4.1.1 展示不同合规年分的排放要求
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


//4.1.2 饼图 展示不同的减排项目的预期减排量占比
// ProjectName->name, ExpectedEmissionsReduction->value
const project_reductions = ref([
    { "name": "绿色能源转换项目", "value": 150.0 },
    { "name": "森林碳汇增强计划", "value": 200.0 },
    { "name": "工业能效提升项目", "value": 180.0 },
    { "name": "城市交通电动化改造", "value": 220.0 },
    { "name": "清洁能源技术研发", "value": 160.0 },
    { "name": "农业温室气体减排", "value": 140.0 },
    { "name": "建筑节能改造工程", "value": 210.0 },
    { "name": "废弃物循环利用项目", "value": 170.0 },
    { "name": "碳捕集与封存技术应用", "value": 190.0 },
    { "name": "绿色供应链优化", "value": 230.0 }
])
const data_project_reduction_name = ref([])

//初始化数据：axios
function init_project_reductions() {
    // let len = project_reductions.value.length
    // for (let i = 0; i < len; i++) {
    //     data_project_reduction_name.value[i] = project_reductions.value[i].name
    //     console.log(data_project_reduction_name.value[i])
    // }

      let api = baseUrl + "/getProjectReduction";
      //2.使用axios 进行get请求
      axios.get(api)
        .then((res) => {
          //请求成功的回调函数
          //把数据传给project_reductions数组
          project_reductions.value = res.data.result

          //获取数据的总条数
        //   total = msg.value.length
          //获取当前页的数据
        //   getPageData()
          // console.log(res)
        }).catch((err) => {
          //请求失败的回调函数
          console.log(err)
        })

}
function draw_project_reduction_pie_chart() {

    var chartDom = document.getElementById('project_reduction_pie_chart');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        title: {
            text: 'Weather Statistics',
            subtext: 'Fake Data',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            bottom: 10,
            left: 'center',
            data: data_project_reduction_name.value
        },
        series: [
            {
                type: 'pie',
                radius: '65%',
                center: ['50%', '50%'],
                selectedMode: 'single',
                data: project_reductions.value,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    option && myChart.setOption(option);



}
onMounted(() => {
    // 1.1 展示不同合规年分的排放要求 首先数据初始化
    draw_year_emission_bar_chart()

    // 1.2 展示不同减排项目的预期减排量占比
    init_project_reductions()
    draw_project_reduction_pie_chart()



})

</script>


<!-- 样式 -->
<style></style>