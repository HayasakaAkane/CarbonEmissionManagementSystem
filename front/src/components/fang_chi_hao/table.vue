<template>
<table>
  <tr>
    <th>项目ID</th>
    <th>项目名称</th>
    <th>项目类型</th>
    <th>地点</th>
    <th>状态</th>
    <th>注册日期</th>
    <th>有效期至</th>
  </tr>
  <tr v-for="d in data" :key="d.ProjectID">
    <td>{{ d.ProjectID }}</td>
    <td>{{ d.ProjectName }}</td>
    <td>{{ d.ProjectType }}</td>
    <td>{{ d.Location }}</td>
    <td>{{ d.Status }}</td>
    <td>{{ d.RegistrationDate }}</td>
    <td>{{ d.ValidUntil }}</td>
  </tr>
  

</table>  
</template>

<script lang="ts">
import {type projectOverview} from "@/components/fang_chi_hao/utils/projectOverview"


let data:projectOverview[]=[
    {
    Project: 1,
    ProjectName: "可再生能源",
    ProjectType: "项目A",
    Location: "北京",
    Status: "进行中",
    RegistrationDate: new Date("2021-01-01"),
    ValidUntil: new Date("2021-01-01")
    },
    {
    ProjectID: 1,
    ProjectName: "可再生能源",
    ProjectType: "项目A",
    Location: "北京",
    Status: "进行中",
    RegistrationDate: new Date("2021-01-01"),
    ValidUntil: new Date("2021-01-01")
    },
    {
    ProjectID: 1,
    ProjectName: "可再生能源",
    ProjectType: "项目A",
    Location: "北京",
    Status: "进行中",
    RegistrationDate: new Date("2021-01-01"),
    ValidUntil: new Date("2021-01-01")
    }
]


</script>


<style scoped>
table {
    width: 100%;
    border-collapse: collapse;
  }
  table, th, td {
    border: 1px solid black;
  }
  th, td {
    padding: 8px;
    text-align: left;
  }
  th {
    background-color: #f2f2f2;
  }
</style>