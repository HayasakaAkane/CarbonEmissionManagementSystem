<template>
    <div style="width: 100%; height: 98%;">
        <div style="height: 48%; margin-bottom: 20px;">
            <div style="height: 100%;width: 100%;background-color:#fff;">
                <h1 style="margin-left: 20px;">合规年分排放要求</h1>
                <div id="year_emission_bar_chart" style="height: 90%;width: 100%;background-color:#fff;">
                </div>
            </div>
        </div>

        <div style="height: 48%; display: flex; justify-content: space-between;">
            <div style="height: 100%;width: 49%;background-color:#fff;">
                <h1 style="margin-left: 20px;">减排项目减排占比</h1>
                <div id="project_reduction_pie_chart" style="height: 90%;width: 100%;background-color:#fff;">
                </div>
            </div>

            <div style="height: 100%;width: 50%;background-color:#fff;">
                <h1 style="margin-left: 20px;">排放源碳排放随时间情况</h1>
                <div id="source_emission_times_line" style="height: 90%;width: 100%;background-color:#fff;">
                </div>
            </div>
        </div>

    </div>

</template>


<!-- js代码 -->
<script lang="ts" setup>
import * as echarts from 'echarts';
import { ref, onMounted } from "vue";
import axios from 'axios';

let baseUrl = 'http://localhost:8080'
// 4.1.1 展示不同合规年分的排放要求
// 1.1请求数据
// const complianceYear_emissionRequired = ref([])
// function init_year_emission_bar(){
//   let api = baseUrl + "/getYearReq";
//   //2.使用axios 进行get请求
//   axios.get(api)
//     .then((res) => {
//       //请求成功的回调函数

//       //把数据传给数组
//       complianceYear_emissionRequired.value = res.data.complianceYear_emissionRequired

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
function init_year_emission() {
    let api = baseUrl + "/getYearReq"
    //2.使用axios 进行get请求
    axios.get(api)
        .then((res) => {
            //请求成功的回调函数
            //把数据传给complianceYear_emissionRequired数组
            complianceYear_emissionRequired.value = res.data.data.complianceYearEmissionRequired
            console.log("data_analysis->complianceYear_emissionRequired:",complianceYear_emissionRequired.value)
            // draw_year_emission_bar_chart()

            // draw_year_emission_bar_chart()

        }).catch((err) => {
            console.log(err)
        })
}

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
        grid: {
            // width: "100%", // 宽度
            // height: "0%" // 高度
            top: '10%',
            bottom: '10%'
        },
        xAxis: {
            name: "年份",
            type: 'category',
            axisLabel: { interval: 0, rotate: 30 }
        },
        yAxis: { name: '排放量', },
        series: {

            type: 'bar',
            encode: { x: 'ComplianceYear', y: 'EmissionsRequired' },
            datasetIndex: 1
        }
    };
    option && myChart.setOption(option);
}


// 4.1.2 展示不同减排项目的预期量占比
// 初始化数据：
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
const project_reductions_name = ref([])
function init_project_reductions() {
    let len = project_reductions.value.length
    for (let i = 0; i < len; i++) {
        project_reductions_name.value[i] = project_reductions.value[i].name
    }

    let api = baseUrl + "/getProjectReduction";
    //2.使用axios 进行get请求
    axios.get(api)
        .then((res) => {
            //请求成功的回调函数
            //把数据传给 project_reductions 数组,并把name字段赋值给 ->project_reductions_name数组
            project_reductions.value = res.data.data.projectReductions
            // console.log("projections：",res.data.data.projectReductions)
            // console.log("projections：",project_reductions.value)
            let len = project_reductions.value.length
            for (let i = 0; i < len; i++) {
                project_reductions_name.value[i] = project_reductions.value[i].name
            }


            // draw_project_reduction_pie_chart()
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
        // grid: {
        //     width: "90%", // 宽度
        //     height: "90%" // 高度
        // },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            bottom: 10,
            left: 'center',
            data: project_reductions_name.value
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

// 4.1.3 展示不同排放源随时间的排放
const source_emission_times_year = ref(['2016', '2017', '2018', '2019', '2020', '2021'])
const source_emission_times_name = ref(['A', 'B', 'C', 'D', 'E', 'F'])
const seriesData1 = ref([56.5, 82.1, 88.7, 70.1, 53.4, 85.1]) as any;
const seriesData2 = ref([51.1, 51.4, 55.1, 53.3, 73.8, 68.7]) as any;
const seriesData3 = ref([40.1, 62.2, 69.5, 36.4, 45.2, 32.5]) as any;
const seriesData4 = ref([25.2, 37.1, 41.2, 18, 33.9, 49.1]) as any;
const seriesData5 = ref([20.2, 30.1, 48.2, 10, 30.9, 40.1]) as any;
// const source_emission_times_year = ref([])
// const source_emission_times_name = ref([])
// const seriesData1 = ref([]) as any;
// const seriesData2 = ref([]) as any;
// const seriesData3 = ref([]) as any;
// const seriesData4 = ref([]) as any;
// const seriesData5 = ref([]) as any;

function init_source_emission_times_line() {
    source_emission_times_year.value.unshift('source'),
    seriesData1.value.unshift(source_emission_times_name.value[0]),
    seriesData2.value.unshift(source_emission_times_name.value[1]),
    seriesData3.value.unshift(source_emission_times_name.value[2]),
    seriesData4.value.unshift(source_emission_times_name.value[3]),
    seriesData5.value.unshift(source_emission_times_name.value[4])

    //     console.log(source_emission_times_year.value)
    let api = baseUrl + "/getSourceEmissionTimes";
    //2.使用axios 进行get请求
    axios.get(api)
        .then((res) => {
            //请求成功的回调函数

            //把数据传给数组
            source_emission_times_year.value = res.data.data.years;
            source_emission_times_name.value = res.data.data.names;
            seriesData1.value = res.data.data.data1;
            seriesData2.value = res.data.data.data2;
            seriesData3.value = res.data.data.data3;
            seriesData4.value = res.data.data.data4;
            seriesData5.value = res.data.data.data5;

            //数据初始化
            source_emission_times_year.value.unshift('source'),
            seriesData1.value.unshift(source_emission_times_name.value[0]),
            seriesData2.value.unshift(source_emission_times_name.value[1]),
            seriesData3.value.unshift(source_emission_times_name.value[2]),
            seriesData4.value.unshift(source_emission_times_name.value[3]),
            seriesData5.value.unshift(source_emission_times_name.value[4]),
            console.log()
            draw_source_emission_times_line()
        }).catch((err) => {
            //请求失败的回调函数
            console.log(err)
        })
}

function draw_source_emission_times_line() {

    var chartDom = document.getElementById('source_emission_times_line');
    var myChart = echarts.init(chartDom);
    var option;

    setTimeout(function () {
        option = {
            legend: {},
            tooltip: {
                trigger: 'axis',
                showContent: false
            },
            dataset: {
                source: [
                    source_emission_times_year.value,
                    seriesData1.value,
                    seriesData2.value,
                    seriesData3.value,
                    seriesData4.value,
                    seriesData5.value,
                ]
            },
            xAxis: { type: 'category' },
            yAxis: { gridIndex: 0 },
            grid: { top: '55%' },
            series: [
                {
                    type: 'line',
                    smooth: true,
                    seriesLayoutBy: 'row',
                    emphasis: { focus: 'series' }
                },
                {
                    type: 'line',
                    smooth: true,
                    seriesLayoutBy: 'row',
                    emphasis: { focus: 'series' }
                },
                {
                    type: 'line',
                    smooth: true,
                    seriesLayoutBy: 'row',
                    emphasis: { focus: 'series' }
                },
                {
                    type: 'line',
                    smooth: true,
                    seriesLayoutBy: 'row',
                    emphasis: { focus: 'series' }
                },
                {
                    type: 'line',
                    smooth: true,
                    seriesLayoutBy: 'row',
                    emphasis: { focus: 'series' }
                },
                {
                    type: 'pie',
                    id: 'pie',
                    radius: '30%',
                    center: ['50%', '25%'],
                    emphasis: {
                        focus: 'self'
                    },
                    label: {
                        formatter: '{b}: {@2012} ({d}%)'
                    },
                    encode: {
                        itemName: 'source',
                        value: source_emission_times_year.value[1],
                        tooltip: source_emission_times_year.value[1]
                    }
                }
            ]
        };
        myChart.on('updateAxisPointer', function (event) {
            const xAxisInfo = event.axesInfo[0];
            if (xAxisInfo) {
                const dimension = xAxisInfo.value + 1;
                myChart.setOption({
                    series: {
                        id: 'pie',
                        label: {
                            formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                        },
                        encode: {
                            value: dimension,
                            tooltip: dimension
                        }
                    }
                });
            }
        });
        myChart.setOption(option);
    });

    option && myChart.setOption(option);

}

onMounted(() => {
    // 1.1 展示不同合规年分的排放要求 首先把这一部分的数据初始化
    //函数：
    init_year_emission()
    draw_year_emission_bar_chart()


    //4.1.2展示不同减排项目的预期减排量
    //初始化数据、画图
    init_project_reductions()
    draw_project_reduction_pie_chart()

    //4.1.3 展示排放源随时间的合规情况
    //初始化数据、画图
    init_source_emission_times_line()
    draw_source_emission_times_line()
})


</script>


<!-- 样式 -->
<style>
/* 定义一个 CSS 类，用于 hover 效果 */
.hover-effect {}

/* 定义 hover 效果 */
.hover-effect:hover {
    background-color: #f0f0f0;
    /* 鼠标悬停时的背景颜色 */
    color: #ff0000;
    /* 鼠标悬停时的文字颜色 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    /* 鼠标悬停时的阴影效果 */
}
</style>