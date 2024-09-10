<template>
    <div class="emissionSourceSketch" style="margin: 0;padding: 0;height: 100%;width: auto;">

        <el-table :data="tableData" style="width: 100%;height: 50%;">
            <el-table-column prop="sourceId" label="排放源标识" />
            <el-table-column prop="name" label="排放源名称" />
            <el-table-column prop="type" label="排放源类型" />
            <el-table-column prop="location" label="排放源地点" />
            <el-table-column prop="status" label="排放源状态">
                <template #default="scope">
                    <span :style="{ color: getStatusColor(scope.row.status) }">
                        {{ scope.row.status }}
                    </span>
                </template>
            </el-table-column>
            <el-table-column prop="complianceYear" label="合规年份" />
            <el-table-column prop="emissionsRequired" label="所需排放量" />
            <el-table-column prop="latestEmissionDate" label="最新排放日期" />
            <el-table-column prop="latestCO2Emissions" label="排放量" />
        </el-table>


        <div style="height: 45%;width: 55%;" id="main"></div>


        <el-pagination style="align-items: center;justify-content: center;height: 5%;"
            v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 5, 8]"
            layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
            @current-change="handleCurrentChange" />
    </div>
</template>

<script lang="ts" setup>

import { ref, onMounted } from "vue";
import * as echarts from 'echarts';

//1、引入 axios 模块
import axios from 'axios'
const currentPage = ref(1)
const pageSize = ref(10)
const msg = ref([
    {
        "sourceId": 1,
        "name": "蓝海化工厂",
        "type": "工业制造",
        "location": "江苏省南京市",
        "status": "运营中",
        "complianceYear": 2023,
        "emissionsRequired": 3000,
        "latestEmissionDate": "2023-06-15",
        "latestCO2Emissions": 2800
    },
    {
        "sourceId": 2,
        "name": "绿能发电厂",
        "type": "能源生产",
        "location": "浙江省杭州市",
        "status": "运营中",
        "complianceYear": 2023,
        "emissionsRequired": 5000,
        "latestEmissionDate": "2023-07-03",
        "latestCO2Emissions": 4500
    },
    {
        "sourceId": 3,
        "name": "金山石油精炼厂",
        "type": "石油化工",
        "location": "广东省广州市",
        "status": "维护中",
        "complianceYear": 2023,
        "emissionsRequired": 6000,
        "latestEmissionDate": "2023-07-01",
        "latestCO2Emissions": 5600
    },
    {
        "sourceId": 4,
        "name": "蓝湾水泥厂",
        "type": "建筑材料",
        "location": "福建省厦门市",
        "status": "运营中",
        "complianceYear": 2023,
        "emissionsRequired": 4200,
        "latestEmissionDate": "2023-06-20",
        "latestCO2Emissions": 3900
    },
    {
        "sourceId": 5,
        "name": "红树林造纸厂",
        "type": "纸浆和造纸",
        "location": "四川省成都市",
        "status": "关闭",
        "complianceYear": 2022,
        "emissionsRequired": 2500,
        "latestEmissionDate": "2022-12-25",
        "latestCO2Emissions": 2200
    },
    {
        "sourceId": 6,
        "name": "银湖钢铁厂",
        "type": "金属冶炼",
        "location": "湖北省武汉市",
        "status": "运营中",
        "complianceYear": 2023,
        "emissionsRequired": 7800,
        "latestEmissionDate": "2023-07-10",
        "latestCO2Emissions": 7200
    },
    {
        "sourceId": 7,
        "name": "翠竹化肥厂",
        "type": "化工产品",
        "location": "湖南省长沙市",
        "status": "运营中",
        "complianceYear": 2023,
        "emissionsRequired": 4800,
        "latestEmissionDate": "2023-07-05",
        "latestCO2Emissions": 4400
    },
    {
        "sourceId": 8,
        "name": "碧水源水处理厂",
        "type": "水处理",
        "location": "山东省济南市",
        "status": "运营中",
        "complianceYear": 2023,
        "emissionsRequired": 1500,
        "latestEmissionDate": "2023-06-18",
        "latestCO2Emissions": 1200
    },
    {
        "sourceId": 9,
        "name": "金穗食品加工厂",
        "type": "食品加工",
        "location": "河南省郑州市",
        "status": "运营中",
        "complianceYear": 2023,
        "emissionsRequired": 2000,
        "latestEmissionDate": "2023-06-12",
        "latestCO2Emissions": 1800
    },
    {
        "sourceId": 10,
        "name": "玉泉玻璃制造厂",
        "type": "非金属矿物制品",
        "location": "陕西省西安市",
        "status": "运营中",
        "complianceYear": 2023,
        "emissionsRequired": 3600,
        "latestEmissionDate": "2023-07-02",
        "latestCO2Emissions": 3200
    }
])
const tableData = ref([])
tableData.value = msg.value

let total = tableData.value.length

//获取所有的数据
function getData() {
    let api = "http://www.phonegap100.com/appapi.php?a=getPortalList&catid=20&page=1";
    // let api = "http://localhost:5173/getAllEmissionSource"
    //2.使用axios 进行get请求
    axios.get(api)
        .then((res) => {
            //请求成功的回调函数

            //把数据传给tableData数组
            msg.value = res.data.result
            //获取数据的总条数
            total = msg.value.length

            //获取当前页的数据
            getPageData()

            // console.log(res)
        }).catch((err) => {
            //请求失败的回调函数
            console.log(err)
        })
}

//获取全部数据
function getAllData() {
    //清空输入的imputStr
    //请求全部的数据
    getData()
}

//获取当前页的数据
function getPageData() {
    //先把当前页面的数据清空
    tableData.value = []

    //获取当前页面的数据
    for (let i = (currentPage.value - 1) * pageSize.value; i < total; i++) {
        //遍历数据添加到tableData中
        tableData.value.push(msg.value[i])

        console.log(msg.value[i])

        console.log(tableData.value[i])

        //判断是否达到一页的要求
        if (tableData.value.length == pageSize.value) break
    }
    console.log(tableData.value)
}

//分页时修改每一页所展示的数据量
const handleSizeChange = (val: number) => {
    //修改当前每一页的数据条数
    pageSize.value = val
    //数据重新分页
    getPageData()

    console.log(`${val} items per page`)
}

//调整当前页的页码
const handleCurrentChange = (val: number) => {
    //修改当前页的页码
    currentPage.value = val
    //数据重新分页
    getPageData()

    console.log(`current page: ${val}`)
}

//渲染状态颜色
function getStatusColor(status) {
    switch (status) {
        case '关闭':
            return 'red';
        case '运营中':
            return 'green';
        case '维护中':
            return 'brown';
        default:
            return 'black';
    }
}

//柱状图
function drawChart() {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;
    option = {
        title: {
            text: '实际排量vs预期排量',
            // subtext: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['实际排放', '预期排放']
        },
        toolbox: {
            show: true,
            feature: {
                dataView: { show: true, readOnly: false },
                magicType: { show: true, type: ['line', 'bar'] },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                // prettier-ignore
                //改为排放源
                data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: 'Rainfall',
                type: 'bar',
                //改为实际排量
                data: [
                    2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
                ],
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' }]
                }
            },
            {
                name: 'Evaporation',
                type: 'bar',
                //改为预期排量
                data: [
                    2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3
                ],
                markPoint: {
                    data: [
                        { name: 'Max', value: 182.2, xAxis: 7, yAxis: 183 },
                        { name: 'Min', value: 2.3, xAxis: 11, yAxis: 3 }
                    ]
                },
                markLine: {
                    data: [{ type: 'average', name: 'Avg' }]
                }
            }
        ]
    };
    option && myChart.setOption(option);
}


onMounted(() => {
    // getData()
    drawChart()
})





</script>
