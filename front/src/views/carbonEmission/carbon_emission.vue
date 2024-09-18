<template>
  <div class="emissionSourceSketch" style="margin: 0;padding: 0;height: 100%;width: auto;">
    <!-- 工具栏 -->
    <el-card :border="false">
      <el-form :inline="true" style="width: auto;height: auto;">
        <el-row>
          <el-col :span="8" style="text-align: left; height: auto;  align-items: center;">
            <el-form-item label="请输入查询条件：">
              <el-input v-model="inputStr" placeholder="输入条件"></el-input>
            </el-form-item>
            <el-button type="primary" @click="queryEmission()"><el-icon>
                  <Search />
                </el-icon>查询
            </el-button>
          </el-col>
          <el-col :span="16" style="text-align: right;">
            <el-button-group>
              <el-button type="primary" @click="getAllData()"><el-icon>
                  <Document />
                </el-icon>全部</el-button>
              <el-button type="primary" @click="addEmission()"><el-icon>
                  <CirclePlus />
                </el-icon>添加</el-button>
            </el-button-group>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <!-- 展示数据的表格 -->
    <el-card :border="false" style="margin-top: 10px;">
      <div style="margin-bottom: 10px;">
        <el-button @click="deleteEmission" type="danger" size="mini">批量删除</el-button>
      </div>
      <el-table :data="tableData" style="width: 100%;" height="770" @selection-change="handleSelectionChange">
        <el-table-column type="selection" />

        <el-table-column prop="dataOrigin" label="排放地区" :filters="area" :filter-method="filterArea">
        </el-table-column>

        <el-table-column prop="emissionType" label="排放源类型" />
        <el-table-column prop="source" label="排放源" />
        <el-table-column prop="emissionDate" label="排放日期" />
        <el-table-column prop="emissionAmount" label="排放量" />
        <el-table-column prop="unit" label="单位" />
        <el-table-column prop="verificationStatus" label="验证状态" :filters="[
          { text: '已验证', value: 'Verified' },
          { text: '未验证', value: 'Pending' }
        ]" :filter-method="filterVerification">
          <template #default="scope">
            <span :style="{ color: getStatusColor(scope.row.verificationStatus) }">
              {{ getStatus(scope.row.verificationStatus) }}
            </span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="250px" align="center">
          <template v-slot="scope">
            <div style="display: flex;justify-content: space-around;">
              <el-button @click="viewEmission(scope.row)" type="success">查看</el-button>
              <el-button @click="updateEmission(scope.row)" type="primary" :disabled="scope.row.verificationStatus == 'Verified'">编辑</el-button>
              <el-button @click="deleteOneRowEmission(scope.row)" type="danger">删除</el-button>
            </div>
          </template>
        </el-table-column>

      </el-table>
      <!-- 分页器 -->
      <el-row style="width: 100%; align-items: center;justify-content: center;margin-top: 20px;">
        <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[15, 20, 30]"
          layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
          @current-change="handleCurrentChange" />
      </el-row>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="40%" :close-on-click-modal="false"
      @close="closeDialogForm('ruleFormRef')">
      <el-form ref="ruleFormRef" :model="emissionForm" :rules="rules" label-width="auto" style="max-width: 600px">
        

        <el-form-item label="排放地点：" prop="dataOrigin">
          <el-input v-model="emissionForm.dataOrigin" :disabled="isView" suffix-icon="el-icon-edit"></el-input>
        </el-form-item>

        <el-form-item label="排放量" prop="emissionAmount">
          <el-input v-model="emissionForm.emissionAmount" :disabled="isView" suffix-icon="el-icon-edit"></el-input>
        </el-form-item>

        <el-form-item label="验证状态：" prop="verificationStatus">
          <el-select v-model="emissionForm.verificationStatus" :disabled="isView" placeholder="请选择验证状态">
            <el-option label="已验证" value="Verified"></el-option>
            <el-option label="未验证" value="Pending"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="排放类型：" prop="emissionType">
          <el-select v-model="emissionForm.emissionType" :disabled="isView" placeholder="请选择排放类型">
            <el-option label="二氧化碳" value="CO2"></el-option>
            <el-option label="其他气体" value="ghg"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="排放日期：" prop="emissionDate">
          <el-date-picker v-model="emissionForm.emissionDate" type="date" placeholder="Pick a date" style="width: 93%;"
            value-format="YYYY-MM-DD" format="YYYY-MM-DD" :disabled="isView" />
        </el-form-item>

        <el-form-item label="排放源：" prop="source">
          <el-input v-model="emissionForm.source" :disabled="isView" suffix-icon="el-icon-edit"></el-input>
        </el-form-item>

        <el-form-item label="排放单位：" prop="unit">
          <el-select v-model="emissionForm.unit" :disabled="isView" placeholder="请选择排放单位">
            <el-option label="千克" value="千克"></el-option>
            <el-option label="吨" value="吨"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="closeDialogForm('ruleFormRef')">
            取消
          </el-button>
          <el-button type="primary" v-show="!isView" @click="submitEmissionForm">确定</el-button>
        </el-form-item>

      </el-form>

    </el-dialog>
  </div>
</template>

<script lang="ts" setup>

import { ref, onMounted, reactive } from "vue";
import * as echarts from 'echarts';
import { ElMessage, ElMessageBox, FormInstance } from "element-plus";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


//1、引入 axios 模块
import axios from 'axios'
//筛选验证状态
const filterVerification = (value, row) => {
  return row.verificationStatus === value
}
//获取筛选地区的list集合
interface origin {
  text: string
  value: string
}
let area: origin[] = [{text:'北京',value:'北京'},{text:'天津',value:'天津'}]
// 请求获取全部的地区
function getArea() {
  let api = baseUrl + '/getDataOrigin';
  //2.使用axios 进行get请求
  axios.get(api)
    .then((res) => {
      //请求成功的回调函数
      //把数据传给数组
      area = res.data.data
    }).catch((err) => {
      //请求失败的回调函数
      console.log(err)
    })
}
//筛选地区
const filterArea = (value, row) => {
  return row.dataOrigin === value
}
const ruleFormRef = ref<FormInstance>()
const currentPage = ref(1)
const pageSize = ref(15)
//记录请求返回数据的集合
const msg = ref([
  { dataOrigin: "北京", emissionAmount: 500.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-11", source: "北京化工厂", unit: "吨" },
  { dataOrigin: "北京", emissionAmount: 450.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-12", source: "北京电子厂", unit: "吨" },
  { dataOrigin: "北京", emissionAmount: 400.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-13", source: "北京机械厂", unit: "吨" },
  { dataOrigin: "北京", emissionAmount: 550.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-14", source: "北京钢铁厂", unit: "吨" },
  { dataOrigin: "北京", emissionAmount: 600.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-15", source: "北京纺织厂", unit: "吨" },
  { dataOrigin: "北京", emissionAmount: 520.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-16", source: "北京食品厂", unit: "吨" },
  { dataOrigin: "北京", emissionAmount: 530.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-17", source: "北京塑料厂", unit: "吨" },
  { dataOrigin: "北京", emissionAmount: 510.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-18", source: "北京制药厂", unit: "吨" },
  { dataOrigin: "北京", emissionAmount: 540.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-19", source: "北京环保厂", unit: "吨" },
  { dataOrigin: "北京", emissionAmount: 550.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-20", source: "北京建筑材料厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 300.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-11", source: "上海汽车厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 320.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-12", source: "上海化肥厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 310.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-13", source: "上海医药厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 330.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-14", source: "上海精密仪器厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 340.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-15", source: "上海钢铁厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 350.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-16", source: "上海印刷厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 360.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-17", source: "上海电子厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 370.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-18", source: "上海机械厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 380.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-19", source: "上海食品厂", unit: "吨" },
  { dataOrigin: "上海", emissionAmount: 390.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-20", source: "上海纺织厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 450.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-11", source: "广州塑料厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 460.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-12", source: "广州制药厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 470.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-13", source: "广州钢铁厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 480.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-14", source: "广州汽车厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 490.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-15", source: "广州食品厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 500.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-16", source: "广州化肥厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 510.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-17", source: "广州纸厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 520.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-18", source: "广州机械厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 530.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-19", source: "广州环保厂", unit: "吨" },
  { dataOrigin: "广州", emissionAmount: 540.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-20", source: "广州纺织厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 350.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-11", source: "深圳电子厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 360.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-12", source: "深圳塑料厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 370.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-13", source: "深圳医药厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 380.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-14", source: "深圳钢铁厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 390.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-15", source: "深圳汽车厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 400.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-16", source: "深圳环保厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 410.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-17", source: "深圳纸厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 420.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-18", source: "深圳机械厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 430.00, verificationStatus: "Verified", emissionType: "CO2", emissionDate: "2024-09-19", source: "深圳化肥厂", unit: "吨" },
  { dataOrigin: "深圳", emissionAmount: 440.00, verificationStatus: "Pending", emissionType: "CO2", emissionDate: "2024-09-20", source: "深圳食品厂", unit: "吨" }
]);

//用于记录用户所查询的输入信息 inputStr
const inputStr = ref()

//用于暂存当前页的数据
const tableData = ref([])
tableData.value = msg.value

let total = tableData.value.length
let baseUrl = 'http://localhost:8080'
//获取所有的数据
function getData() {
  getPageData()
  let api = baseUrl + '/getAllEmissionRecords';
  //2.使用axios 进行get请求
  axios.get(api)
    .then((res) => {
      //请求成功的回调函数
      //把数据传给tableData数组
      msg.value = res.data.data.result
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
  console.log('getAllData')
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
    // console.log(msg.value[i])
    // console.log(tableData.value[i])
    //判断是否达到一页的要求
    if (tableData.value.length == pageSize.value) break
  }
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

//3.2 实现碳排放信息的查询
function queryEmission() {
  console.log("inputMsg:" + inputStr.value)
  axios
    .post(
      baseUrl + "/queryEmission",
      {
        inputStr: inputStr.value
      }
    )
    .then(function (res) {
      if (res.data.code === "200") {
        //把数据给 msg
        msg.value = res.data.data;
        //获取返回记录的总行数
        total = res.data.data.length;
        //获取当前页的数据
        getPageData()
        //提示成功：
        ElMessage({
          message: '查询数据加载成功',
          type: 'success'
        });
      } else {
        ElMessage.error('响应失败');
      }
    })
    .catch(function (err) {
      console.log(err);
      ElMessage.error("获取后端查询结果出现异常！");
    });
}

//----------实现选择功能--------------//
//记录选择的数据,保存被选择的所有信息
const selectData = ref([])
function handleSelectionChange(data) {
  selectData.value = data
  console.log(selectData.value)
}

//3.3 实现删除一群信息功能
function deleteEmission() {
  //等待确认删除
  ElMessageBox.confirm(
    "确认批量删除" + selectData.value.length + "个排放数据信息?",
    '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    //确认删除的相应事件
    //调用后端的接口
    console.log(selectData.value)
    axios.post(
      baseUrl + '/deleteEmission',
      {
        emissionRecords: selectData.value
      }) //左边对应后端参数一致
      .then(res => {
        if (res.data.code == 200) {
          //获取所有的删除后的信息
          msg.value = res.data.data;
          //获取记录数
          total = res.data.data.length;
          //分页
          getPageData();
          //提示
          ElMessage({
            message: '数据批量删除成功',
            type: 'success'
          });
        } else {
          //失败的提示
          ElMessage.error(res.data.msg);
        }

      })
  }).catch(() => {
    this.$message({
      type: 'info',
      message: '已取消删除'
    });
  });
}
//删除一条排放数据记录
function deleteOneRowEmission(row) {
  console.log("row -> source:" , row)
  //等待确认删除
  ElMessageBox.confirm(
    '确认删除？', '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    //确认删除的相应事件
    //调用后端接口
    axios.post(
      baseUrl + '/deleteOneRowEmission',
      {
        ...row
      }
    ).then(res => {
      if (res.data.code == "200") {
        //获取所有排放信息
        msg.value = res.data.data
        //获取记录数
        total = res.data.data.length
        //分页
        getPageData()
        //提示信息
        ElMessage({
          message: '数据删除成功',
          type: 'success'
        })
      } else {
        ElMessage.error(res.data.msg);
      }
    })
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '已取消删除'
    })
  })
}
//-----------------------------------//


//-----------------------------------//
//通过表单添加数据
//定义一个表单的结构体
const emissionForm = ref({
  dataOrigin: '',
  emissionAmount: '',
  verificationStatus: '',
  emissionType: '',
  emissionDate: '',
  source: '',
  unit: ''
});
//定义表单的校验规则  非空就可以  you show time
const rules = {
  dataOrigin: [
    { required: true, message: '排放地点不能为空', trigger: 'change' },
  ],
  emissionAmount: [
    { required: true, message: '排放数据不能为空', trigger: 'change' },
  ],
  verificationStatus: [
    { required: true, message: '验证状态不能为空', trigger: 'change' },
  ],
  emissionType: [
    { required: true, message: '排放类型不能为空', trigger: 'change' },
  ],
  emissionDate: [
    { type: 'date', required: true, message: '排放日期不能为空', trigger: 'change' },
  ],
  source: [
    { required: true, message: '排放源不能为空', trigger: 'change' },
  ],
  unit: [
    { required: true, message: '排放单位不能为空', trigger: 'change' },
  ],
}
let dialogTitle = ref('') //表单的标题
const dialogVisible = ref(false)  //控制显示表单
let isView = ref(false)
let isEdit = ref(false)
//关闭表单要处理的操作
function closeDialogForm(formName) {
  //重置表单的校验
  ruleFormRef.value.resetFields();
  //清空表单上一次所展示的内容
  emissionForm.value.dataOrigin = ''
  emissionForm.value.emissionAmount = ''
  emissionForm.value.verificationStatus = ''
  emissionForm.value.emissionType = ''
  emissionForm.value.emissionDate = ''
  emissionForm.value.source = ''
  emissionForm.value.unit = ''

  //关闭表单
  dialogVisible.value = false

  //初始化isView和isEdit的值是false
  isEdit.value = false
  isView.value = false

}
//查看一条排放数据明细
function viewEmission(row) {
  console.log("这一行的数据->row:", row)
  //修改标题
  dialogTitle.value = "排放数据明细"
  //修改isView变量
  isView.value = true
  //弹出表单
  dialogVisible.value = true
  //深拷贝表单对象
  Object.assign(emissionForm.value, JSON.parse(JSON.stringify(row)))
}

//添加排放信息——>打开表单 ->提交表单 ->添加到数据库
function addEmission() {
  //修改表单标题
  dialogTitle.value = '添加排放信息'
  dialogVisible.value = true
}
//3.5 、提交排放记录的表单 ---->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>表单校验有错误
function submitEmissionForm() {
  console.log(emissionForm.value, '表单数据')

  ruleFormRef.value?.validate((errors) => {
    // console.log(errors, 'sssss')
    if (errors) {
      console.log(errors, 'eeee')
      console.log('校验成功')
      if (isEdit.value) {
        //修改
        submitUpdateEmission()
      } else {
        //添加
        submitAddEmission()
      }
      dialogVisible.value = false
    } else {
      console.log('表单验证失败')
    }
  });
}
//添加到数据库
function submitAddEmission() {
  console.log("submitAddEmission -> 表单数据：", emissionForm.value.emissionDate)
  axios
    .post(baseUrl + '/addEmission', emissionForm.value)
    .then(res => {
      //执行成功
      if (res.data.code == 200) {
        //获取所有的信息 msg
        msg.value = res.data.data;
        //获取记录的条数
        total = res.data.data.length;
        //获取分页信息
        getPageData();
        //提示
        ElMessage({
          message: '查询数据加载成功',
          type: 'success'
        });
        //关闭窗体
        closeDialogForm('studentForm');
      } else {
        //失败的提示
        ElMessage.error(res.data.msg);
      }
    })
    .catch(err => {
      //执行失败
      console.log(err);
      ElMessage.error("获取后端查询结果出现异常！");
    });
}

//修改排放数据明细
function updateEmission(row) {

  dialogTitle.value = "修改排放数据"
  //修改isEdit变量为true
  isEdit.value = true
  //弹出表单
  dialogVisible.value = true
  //将这一行数据深拷贝给表单
  Object.assign(emissionForm.value, JSON.parse(JSON.stringify(row)))

}
//修改更新到数据库
function submitUpdateEmission() {
  console.log("submitUpdate->", emissionForm.value)
  //执行axios请求
  axios
    .post(baseUrl + '/updateEmission', emissionForm.value)
    .then(res => {
      //执行成功
      if (res.data.code == 200) {
        //获取所有的信息 msg
        msg.value = res.data.data;
        //获取记录的条数
        total = res.data.data.length;
        //获取分页信息
        getPageData();
        //提示
        ElMessage({
          message: '数据修改加载成功',
          type: 'success'
        });
        //关闭窗体
        closeDialogForm('emissionForm');
      } else {
        //失败的提示
        ElMessage.error(res.data.msg);
      }
    })
    .catch(err => {
      //执行失败
      console.log(err);
      ElMessage.error("修改时获取后端查询结果出现异常！");
    });
}


//-----------------------------------//

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



onMounted(() => {
  getArea()
  getData()
  // drawChart()
})





</script>

<style scoped></style>