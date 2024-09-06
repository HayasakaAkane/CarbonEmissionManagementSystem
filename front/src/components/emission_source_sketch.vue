<template>
  <div class="emissionSourceSketch">
    <el-table :data="msg" style="width: 100%">
      <el-table-column prop="sourceId" label="排放源标识" width="100" />
      <el-table-column prop="name" label="排放源名称" width="180" />
      <el-table-column prop="type" label="排放源类型" width="150" />
      <el-table-column prop="location" label="排放源地点" width="180" />
      <el-table-column prop="status" label="排放源状态" width="100" />
      <el-table-column prop="complianceYear" label="合规年份" width="100" />
      <el-table-column prop="emissionsRequired" label="所需排放量" width="100" />
      <el-table-column prop="latestEmissionDate" label="最新排放日期" width="180" />
      <el-table-column prop="latestCO2Emissions" label="排放量" width="100" />
  
    </el-table>
  </div>
</template>

<script lang="ts" setup>

import { ref, onMounted } from "vue";

//1、引入 axios 模块
import axios from 'axios'
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
const sourceData = ref([]) as any 

function getData() {
  let api = "http://www.phonegap100.com/appapi.php?a=getPortalList&catid=20&page=1";
  // let api = "http://localhost:5173/getAllEmissionSource"
  //2.使用axios 进行get请求
  axios.get(api).then((res) => {
    //请求成功的回调函数
    sourceData.value = res.data.result
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
