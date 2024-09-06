<template>
    <div class="emissionData">
      <el-table :data="msg" style="width:100%;">
        <el-table-column prop="emissionDate" label="排放日期" width="180" />
        <el-table-column prop="co2Emissions" label="Co2排放量" width="180" />
        <el-table-column prop="otherGHGEmissions" label="其他气体排放量" width="180" />
        <el-table-column prop="measurementMethod" label="测量方法" width="180" />
        <el-table-column prop="verificationStatus" label="验证状态" width="180" />
 
      </el-table>
    </div>
  </template>
  
  <script lang="ts" setup>
  
  import { ref, onMounted } from "vue";
  
  //1、引入 axios 模块
  import axios from 'axios'
  const msg = ref(
    [
  {
    "emissionDate": "2023-06-01",
    "co2Emissions": 2500,
    "otherGHGEmissions": 150,
    "measurementMethod": "直接测量",
    "verificationStatus": "已验证"
  },
  {
    "emissionDate": "2023-06-15",
    "co2Emissions": 2800,
    "otherGHGEmissions": 160,
    "measurementMethod": "间接测量",
    "verificationStatus": "已验证"
  },
  {
    "emissionDate": "2023-07-01",
    "co2Emissions": 2600,
    "otherGHGEmissions": 155,
    "measurementMethod": "直接测量",
    "verificationStatus": "待验证"
  }
]
  )
  const tableData = ref([]) as any 
  
  function getData() {
    let api = "http://www.phonegap100.com/appapi.php?a=getPortalList&catid=20&page=1";
    // let api = "http://localhost:5173/getAllEmissionSource"
    //2.使用axios 进行get请求
    axios.get(api).then((res) => {
      //请求成功的回调函数
      tableData.value = res.data.result
      console.log(res)
    }).catch((err) => {
      //请求失败的回调函数
      console.log(err)
    })
  }
  onMounted(() => {
    getData()
  })
  
  </script>
  