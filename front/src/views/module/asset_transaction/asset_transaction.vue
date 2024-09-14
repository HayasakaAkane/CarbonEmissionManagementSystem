<template>
    <div style="width: 100%; height: 98%;">
        <div style="height: 48%; margin-bottom: 20px;">
            <div style="height: 100%;width: 100%;background-color:#fff;">
                <h1 style="margin-left: 20px;">交易类型统计图</h1>
                <div id="tranction_analysis_bar" style="height: 90%;width: 100%;background-color:#fff;">
                </div>
            </div>
        </div>

        <div style="height: 48%; display: flex; justify-content: space-between;">
            <div style="height: 100%;width: 49%;background-color:#fff;">
                <h1 style="margin-left: 20px;">碳资产交易量堆叠图</h1>
                <div id="asset_stack_bar" style="height: 90%;width: 100%;background-color:#fff;">
                </div>
            </div>

            <div style="height: 100%;width: 50%;background-color:#fff;">
                <h1 style="margin-left: 20px;">资产数量与交易量关系图</h1>
                <div id="asset_transaction_quantity_bar" style="height: 90%;width: 100%;background-color:#fff;">
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

//4.2.1 交易类型柱状统计图
//交易类型
let transaction = ref()
// 交易量数组
let transaction_quantity = ref();
// 交易价格数组
let transaction_price = ref();
//数据初始化：transaction ；transaction_quantity；transaction_price
function init_tranction_analysis() {
    //交易类型
    transaction.value = ["配额交易", "基线交易", "碳税", "碳信用", "碳期权和期货"]
    // 交易量数组（单位：吨二氧化碳当量 tCO2e）
    transaction_quantity.value = [50, 20, 15, 30, 25, 18]
    // 交易价格数组（单位：美元/吨）
    transaction_price.value = [15, 18, 12, 20, 17, 19]


    //以下是请求代码
    // let api = baseUrl + '/getTranctionAnalysis';
    // //2.使用axios 进行get请求
    // axios.get(api)
    //     .then((res) => {
    //         //请求成功的回调函数
    //         //把数据传给数组
    //         transaction.value = res.data.transaction,
    //             transaction_price.value = res.data.transactionPrice,
    //             transaction_quantity.value = res.data.transactionQuantity
    //     }).catch((err) => {
    //         //请求失败的回调函数
    //         console.log(err)
    //     })
}
function draw_tranction_analysis_bar() {

    var chartDom = document.getElementById('tranction_analysis_bar');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        grid: {
            width: "80%", // 宽度
            height: "70%" // 高度
        },
        toolbox: {
            feature: {
                dataView: { show: true, readOnly: false },
                magicType: { show: true, type: ['line', 'bar'] },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        legend: {
            data: ['交易价格', '交易数量']
        },
        xAxis: [
            {
                type: 'category',
                //碳交易类型用于横坐标轴
                data: transaction.value,
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '交易价格',
                min: 0,
                max: Math.max.apply(null, transaction_price.value) + 10,
                // internal: 5,
                axisLabel: {
                    formatter: '{value} W'
                }
            },
            {
                type: 'value',
                name: '交易数量',
                min: 0,
                max: Math.max.apply(null, transaction_quantity.value) + 10,
                // internal: 10,
                axisLabel: {
                    formatter: '{value} K吨'
                }
            }
        ],
        series: [
            {
                name: '交易价格',
                type: 'bar',
                yAxisIndex: 0,
                tooltip: {
                    valueFormatter: function (value) {
                        return value + ' W';
                    }
                },
                data: transaction_price.value
            },
            {
                name: '交易数量',
                type: 'bar',
                yAxisIndex: 1,
                tooltip: {
                    valueFormatter: function (value) {
                        return value + ' K吨';
                    }
                },
                data: transaction_quantity.value
            },
        ]
    };

    option && myChart.setOption(option);


}

//4.2.2 资产交易量随时间的堆叠柱状图
let years = ref([2019, 2020, 2021, 2022, 2023, 2024])
let asset_type = ref(['森林碳汇项目', '可再生能源开发', '工业排放减少计划', '绿色交通转型', '碳捕集与存储技术']);
let asset_transaction_quantity1 = ref([20, 22, 24, 26, 28])
let asset_transaction_quantity2 = ref([50, 48, 46, 44, 42])
let asset_transaction_quantity3 = ref([30, 35, 40, 35, 30])
let asset_transaction_quantity4 = ref([40, 35, 30, 35, 40])
let asset_transaction_quantity5 = ref([25, 25, 25, 25, 25])

function init_asset_time() {
    years = ref([2019, 2020, 2021, 2022, 2023])
    asset_type = ref(['森林碳汇项目', '可再生能源开发', '工业排放减少计划', '绿色交通转型', '碳捕集与存储技术']);
    asset_transaction_quantity1 = ref([21, 23, 25, 27, 29])
    asset_transaction_quantity2 = ref([22, 24, 26, 28, 30])
    asset_transaction_quantity3 = ref([23, 25, 27, 29, 31])
    asset_transaction_quantity4 = ref([24, 26, 28, 30, 32])
    asset_transaction_quantity5 = ref([25, 27, 29, 31, 33])

    // let api = baseUrl + '/getAssetAnalysis';
    // //2.使用axios 进行get请求
    // axios.get(api)
    //     .then((res) => {
    //         //请求成功的回调函数
    //         //把数据传给数组
    //         years.value = res.data.years
    //         asset_type.value = res.data.assetType
    //         asset_transaction_quantity1.value = res.data.assetTransactionQuantity1
    //         asset_transaction_quantity2.value = res.data.assetTransactionQuantity2
    //         asset_transaction_quantity3.value = res.data.assetTransactionQuantity3
    //         asset_transaction_quantity4.value = res.data.assetTransactionQuantity4
    //         asset_transaction_quantity5.value = res.data.assetTransactionQuantity5
    //     }).catch((err) => {
    //         //请求失败的回调函数
    //         console.log(err)
    //     })
}
function draw_asset_stack_bar() {
    var chartDom = document.getElementById('asset_stack_bar');
    var myChart = echarts.init(chartDom);
    var option;
    option = {

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow' // 'shadow' as default; can also be 'line' or 'shadow'
            }
        },
        legend: {},
        grid: {
            width: "100%", // 宽度
            height: "70%" // 高度
        },
        xAxis: {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            data: years.value
        },
        series: [
            {
                name: asset_type.value[0],
                type: 'bar',
                stack: 'total',
                label: {
                    show: true
                },
                emphasis: {
                    focus: 'series'
                },
                data: asset_transaction_quantity1.value
            },
            {
                name: asset_type.value[1],
                type: 'bar',
                stack: 'total',
                label: {
                    show: true
                },
                emphasis: {
                    focus: 'series'
                },
                data: asset_transaction_quantity2.value
            },
            {
                name: asset_type.value[2],
                type: 'bar',
                stack: 'total',
                label: {
                    show: true
                },
                emphasis: {
                    focus: 'series'
                },
                data: asset_transaction_quantity3.value
            },
            {
                name: asset_type.value[3],
                type: 'bar',
                stack: 'total',
                label: {
                    show: true
                },
                emphasis: {
                    focus: 'series'
                },
                data: asset_transaction_quantity4.value
            },
            {
                name: asset_type.value[4],
                type: 'bar',
                stack: 'total',
                label: {
                    show: true
                },
                emphasis: {
                    focus: 'series'
                },
                data: asset_transaction_quantity5.value
            }
        ]
    };

    option && myChart.setOption(option);

}


// 4.2.3 资产数量与交易量关系图
let asset_type_4 = ref(['森林碳汇项目', '可再生能源开发', '工业排放减少计划', '绿色交通转型', '碳捕集与存储技术']);
let asset_quantity = ref([50, 20, 15, 30, 25, 18])
let asset_transaction_quantity = ref([20, 22, 24, 26, 28])

function init_asset_transaction() {
    asset_type_4.value = ['森林碳汇项目', '可再生能源开发', '工业排放减少计划', '绿色交通转型', '碳捕集与存储技术']
    asset_quantity.value = [50, 20, 15, 30, 25, 18]
    asset_transaction_quantity.value = [20, 22, 24, 26, 28]

    // let api = baseUrl + '/getAssetQuantityAnalysis';
    // //2.使用axios 进行get请求
    // axios.get(api)
    //     .then((res) => {
    //         //请求成功的回调函数
    //         //把数据传给tableData数组
    //         asset_type_4.value = res.data.assetType,
    //         asset_quantity.value = res.data.assetQuantity,
    //         asset_transaction_quantity.value = res.data.assetTransactionQuantity,

    //     }).catch((err) => {
    //         //请求失败的回调函数
    //         console.log(err)
    //     })

}
function draw_asset_transaction_quantity_bar() {
    var chartDom = document.getElementById('asset_transaction_quantity_bar');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {},
        grid: {
            width: "70%", // 宽度
            height: "70%" // 高度
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            axisLabel: {
                textStyle: {
                    fontSize: 9 // 设置 Y 轴字体大小为 14
                }
            },
            data: asset_type_4.value
        },
        series: [
            {
                name: '资产数量',
                type: 'bar',
                data: asset_quantity.value
            },
            {
                name: '交易量',
                type: 'bar',
                data: asset_transaction_quantity.value
            }
        ]
    };

    option && myChart.setOption(option);

}

onMounted(() => {

    //4.2.1 绘制交易价格分析柱状图
    init_tranction_analysis()
    draw_tranction_analysis_bar()

    //4.2.2 绘制资产交易量随时间的堆叠柱状图
    init_asset_time()
    draw_asset_stack_bar()


    //4.2.3 资产数量与交易量关系
    init_asset_transaction()
    draw_asset_transaction_quantity_bar()


})


</script>


<!-- 样式 -->
<style></style>