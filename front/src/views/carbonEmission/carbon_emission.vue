<template>
  <div class="emissionSourceSketch" style="margin: 0;padding: 0;height: 100%;width: auto;">
    <!-- 工具栏 -->
    <el-form :inline="true" style="width: auto;height: auto;">
      <el-row>
        <el-col :span="8" style="text-align: left;">
          <el-form-item label="请输入查询条件：">
            <el-input placeholder="输入条件"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="16" style="text-align: right;">
          <el-button-group>
            <el-button type="primary" icon="el-icon-search">查询</el-button>
            <el-button type="primary" icon="el-icon-tickets">全部</el-button>
            <el-button type="primary" icon="el-icon-circle-plus-outline">添加</el-button>
          </el-button-group>
        </el-col>
      </el-row>
    </el-form>
    <!-- 展示数据的表格 -->
    <el-table :data="tableData" style="width: 100%;height: auto;">
      <el-table-column prop="data_origin" label="排放地区" />
      <el-table-column prop="emission_type" label="排放源类型" />
      <el-table-column prop="source" label="排放源" />
      <el-table-column prop="emission_date" label="排放日期" />
      <el-table-column prop="emission_amount" label="排放量" />
      <el-table-column prop="unit" label="单位" />
      <el-table-column prop="verification_status" label="验证状态">
        <template #default="scope">
          <span :style="{ color: getStatusColor(scope.row.verification_status) }">
            {{ getStatus(scope.row.verification_status) }}
          </span>
        </template>
      </el-table-column>

    </el-table>
      <el-pagination  style="position: fixed; bottom: 0; left: 50%; align-items: center;justify-content: center;" v-model:current-page="currentPage"
      v-model:page-size="pageSize" :page-sizes="[10, 20, 30]" layout="total, sizes, prev, pager, next, jumper"
      :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
  
    
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
  { "data_origin": "北京", "emission_amount": 500.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-11", "source": "北京化工厂", "unit": "吨" },
  { "data_origin": "北京", "emission_amount": 450.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-12", "source": "北京电子厂", "unit": "吨" },
  { "data_origin": "北京", "emission_amount": 400.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-13", "source": "北京机械厂", "unit": "吨" },
  { "data_origin": "北京", "emission_amount": 550.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-14", "source": "北京钢铁厂", "unit": "吨" },
  { "data_origin": "北京", "emission_amount": 600.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-15", "source": "北京纺织厂", "unit": "吨" },
  { "data_origin": "北京", "emission_amount": 520.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-16", "source": "北京食品厂", "unit": "吨" },
  { "data_origin": "北京", "emission_amount": 530.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-17", "source": "北京塑料厂", "unit": "吨" },
  { "data_origin": "北京", "emission_amount": 510.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-18", "source": "北京制药厂", "unit": "吨" },
  { "data_origin": "北京", "emission_amount": 540.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-19", "source": "北京环保厂", "unit": "吨" },
  { "data_origin": "北京", "emission_amount": 550.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-20", "source": "北京建筑材料厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 300.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-11", "source": "上海汽车厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 320.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-12", "source": "上海化肥厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 310.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-13", "source": "上海医药厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 330.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-14", "source": "上海精密仪器厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 340.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-15", "source": "上海钢铁厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 350.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-16", "source": "上海印刷厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 360.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-17", "source": "上海电子厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 370.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-18", "source": "上海机械厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 380.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-19", "source": "上海食品厂", "unit": "吨" },
  { "data_origin": "上海", "emission_amount": 390.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-20", "source": "上海纺织厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 450.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-11", "source": "广州塑料厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 460.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-12", "source": "广州制药厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 470.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-13", "source": "广州钢铁厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 480.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-14", "source": "广州汽车厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 490.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-15", "source": "广州食品厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 500.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-16", "source": "广州化肥厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 510.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-17", "source": "广州纸厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 520.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-18", "source": "广州机械厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 530.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-19", "source": "广州环保厂", "unit": "吨" },
  { "data_origin": "广州", "emission_amount": 540.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-20", "source": "广州纺织厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 350.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-11", "source": "深圳电子厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 360.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-12", "source": "深圳塑料厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 370.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-13", "source": "深圳医药厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 380.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-14", "source": "深圳钢铁厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 390.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-15", "source": "深圳汽车厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 400.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-16", "source": "深圳环保厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 410.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-17", "source": "深圳纸厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 420.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-18", "source": "深圳机械厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 430.00, "verification_status": "Verified", "emission_type": "CO2", "emission_date": "2024-09-19", "source": "深圳化肥厂", "unit": "吨" },
  { "data_origin": "深圳", "emission_amount": 440.00, "verification_status": "Pending", "emission_type": "CO2", "emission_date": "2024-09-20", "source": "深圳食品厂", "unit": "吨" }
]
)
const tableData = ref([])
tableData.value = msg.value

let total = tableData.value.length
let baseUrl = 'http://localhost:8080'
//获取所有的数据
function getData() {

  getPageData()


  // // let api = "http://www.phonegap100.com/appapi.php?a=getPortalList&catid=20&page=1";
  // let api = baseUrl + '/getAllEmissionRecords';
  // //2.使用axios 进行get请求
  // axios.get(api)
  //   .then((res) => {
  //     //请求成功的回调函数
  //     //把数据传给tableData数组
  //     console.log(res.data.result)
  //     msg.value = res.data.result
  //     //获取数据的总条数
  //     total = msg.value.length
  //     //获取当前页的数据
  //     getPageData()
  //     // console.log(res)
  //   }).catch((err) => {
  //     //请求失败的回调函数
  //     console.log(err)
  //   })
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
    case 'Verified':
      return 'green';
    case 'Pending':
      return 'red';
    default:
      return 'black';
  }
}
//用汉字显示状态
function getStatus(status) {
  switch (status) {
    case 'Verified':
      return '已验证';
    case 'Pending':
      return '未验证';
    default:
      return '————';
  }

}

//柱状图
function drawChart() {
  var chartDom = document.getElementById('main');
  var myChart = echarts.init(chartDom);
  var option;

  option = {
    title: {
      text: 'Rainfall vs Evaporation',
      subtext: 'Fake Data'
    },
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      data: ['Rainfall', 'Evaporation']
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
  getData()
  // drawChart()
})





</script>