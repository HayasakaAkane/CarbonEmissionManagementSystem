<template>
    <div class="emissionData" style="height: 100%;">
        <el-table :data="tableData" style="width:100%;height: 95%;">
            <el-table-column prop="emissionDate" label="排放日期" />
            <el-table-column prop="co2Emissions" label="Co2排放量"/>
            <el-table-column prop="otherGHGEmissions" label="其他气体排放量"/>
            <el-table-column prop="measurementMethod" label="测量方法" />
            <el-table-column prop="verificationStatus" label="验证状态"/>
        </el-table>

        <el-pagination style="height: 5%;"
        v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[1, 2, 3, 4]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total" 
        @size-change="handleSizeChange" @current-change="handleCurrentChange" />

    </div>

</template>

<script lang="ts" setup>

import { ref, onMounted } from "vue";

//1、引入 axios 模块
import axios from 'axios'

const currentPage = ref(1)
const pageSize = ref(0)



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
// const tableData = ref([]) as any

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
function getAllData(){
    //清空输入的imputStr
    //请求全部的数据
    getData()
}

//获取当前页的数据
function getPageData(){
    //先把当前页面的数据清空
    tableData.value = []

    //获取当前页面的数据
    for(let i = (currentPage.value - 1) * pageSize.value;i < total;i++){
        //遍历数据添加到tableData中
        tableData.value.push(msg.value[i])

        console.log(msg.value[i])

        console.log(tableData.value[i])

        //判断是否达到一页的要求
        if(tableData.value.length == pageSize.value) break 
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
onMounted(() => {
    // getData()
})

</script>

<style scoped>

</style>