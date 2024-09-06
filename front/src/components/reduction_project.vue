<template>
    <div class="reductionProject">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="projectName" label="项目名称" width="180" />
        <el-table-column prop="projectType" label="项目类型" width="180" />
        <el-table-column prop="startDate" label="开始日期" width="180" />
        <el-table-column prop="endDate" label="结束日期" width="180" />
        <el-table-column prop="expectedEmissionsReduction" label="预期减排量" width="180" />
        <el-table-column prop="actualEmissionsReduction" label="实际减排量" width="180" />
        <el-table-column prop="projectStatus" label="项目状态" width="180" />
      </el-table>
    </div>
  </template>
  
  <script lang="ts" setup>
  
  import { ref, onMounted } from "vue";
  
  //1、引入 axios 模块
  import axios from 'axios'
  const msg = ref('主页')
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
  