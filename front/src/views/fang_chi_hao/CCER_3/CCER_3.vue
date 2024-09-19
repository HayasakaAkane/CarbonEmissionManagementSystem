<template>
  <div class="emissionSourceSketch">
    <div class="header">
      <h1>项目审核</h1>
    </div>

    <!-- 搜索和筛选部分 -->
    <div class="search-filter">
      <!-- 搜索输入框，绑定 v-model 为 searchQuery -->
      <input v-model="searchQuery" type="text" placeholder="搜索项目..." />
      <!-- 搜索按钮，点击时调用 applyFilters 函数 -->
      <button @click="applyFilters">搜索</button>
    </div>

    <!-- 展示数据的表格 -->
    <el-card :border="false" style="margin-top: 10px">
      <el-table :data="tableData" style="width: 100%" height="770">
        <el-table-column prop="projectId" label="项目ID" />
        <el-table-column prop="name" label="项目名称" />
        <el-table-column prop="type" label="项目类型" />
        <el-table-column
          prop="status"
          label="状态"
          :filters="[
            { text: '待审核', value: '待审核' },
            { text: '批准', value: '批准' },
            { text: '驳回', value: '驳回' },
          ]"
          :filter-method="filterTag"
        >
          <template #default="scope">
            <span :style="{ color: getStatusColor(scope.row.status) }">
              {{ getStatus(scope.row.status) }}
            </span>
          </template>
        </el-table-column>
        <!-- 操作列，包含查看详情、批准、拒绝按钮 -->
        <el-table-column label="操作" width="350">
          <template #default="scope">
            <!-- 查看详情按钮 -->
            <el-button @click="viewEmission(scope.row)" type="success"
              >查看</el-button
            >
            <!-- 批准按钮，只有在状态为“待审核”时启用 -->
            <el-button
              @click="approveProject(scope.row.projectId)"
              :disabled="scope.row.status != '待审核' ? true : false"
              >批准</el-button
            >
            <!-- 拒绝按钮，只有在状态为“待审核”时启用 -->
            <el-button
              @click="rejectProject(scope.row.projectId)"
              :disabled="scope.row.status != '待审核' ? true : false"
              >拒绝</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页器 -->
      <el-row
        style="
          width: 100%;
          align-items: center;
          justify-content: flex-end;
          height: 5%;
          margin-top: 20px;
        "
      >
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 30]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </el-row>
    </el-card>

    <!-- 弹出对话框表单 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="40%"
      :close-on-click-modal="false"
      @close="closeDialogForm('emissionForm')"
    >
      <el-form :model="form"  ref="formRef" label-width="150px">
        <!-- ccer_project_emission_reductions -->
        <h3>项目信息</h3>
        <el-form-item label="项目名称" prop="projectName">
          <el-input
            v-model="form.projectName"
            placeholder="请输入项目名称"
          ></el-input>
        </el-form-item>

        <el-form-item label="项目类型" prop="projectType">
          <el-select v-model="form.projectType" placeholder="请选择项目类型">
            <el-option label="可再生能源" value="可再生能源"></el-option>
            <el-option label="林业碳汇" value="林业碳汇"></el-option>
            <el-option label="甲烷利用" value="甲烷利用"></el-option>
            <el-option label="能源效率提升" value="能源效率提升"></el-option>
            <el-option label="碳捕集与封存" value="碳捕集与封存"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入地址"></el-input>
        </el-form-item>

        <el-form-item label="注册日期" prop="registrationDate">
          <el-date-picker
            v-model="form.registrationDate"
            type="date"
            format="YYYY-MM-DD"
            placeholder="请选择注册日期"
          ></el-date-picker>
        </el-form-item>

        <el-form-item label="有效期至" prop="validUntil">
          <el-date-picker
            v-model="form.validUntil"
            type="date"
            format="YYYY-MM-DD"
            placeholder="请选择有效期"
          ></el-date-picker>
        </el-form-item>

        <h3>减排信息</h3>

        <el-form-item label="年度" prop="year">
          <el-input
            v-model="form.year"
            type="number"
            placeholder="请输入年度"
          ></el-input>
        </el-form-item>

        <el-form-item label="减排量" prop="reductionAmount">
          <el-input
            v-model="form.reductionAmount"
            type="number"
            placeholder="请输入减排量"
            step="0.01"
          ></el-input>
        </el-form-item>

        <el-form-item label="核查日期" prop="verificationDate">
          <el-date-picker
            v-model="form.verificationDate"
            type="date"
            format="YYYY-MM-DD"
            placeholder="请选择核查日期"
          ></el-date-picker>
        </el-form-item>

        <!-- ccer_project_monitoring_reports -->
        <h3>监测报告</h3>
        <el-form-item label="报告日期" prop="reportDate">
          <el-date-picker
            v-model="form.reportDate"
            type="date"
            format="YYYY-MM-DD"
            placeholder="请选择报告日期"
          ></el-date-picker>
        </el-form-item>

        <el-form-item label="报告详情" prop="reportDetails">
          <el-input
            v-model="form.reportDetails"
            type="textarea"
            placeholder="请输入报告详情"
          ></el-input>
        </el-form-item>

        <!-- ccer_project_registration_info -->
        <h3>注册信息</h3>
        <el-form-item label="注册编号" prop="registrationNumber">
          <el-input
            v-model="form.registrationNumber"
            placeholder="请输入注册编号"
          ></el-input>
        </el-form-item>

        <el-form-item label="颁发机构" prop="issuingAuthority">
          <el-input
            v-model="form.issuingAuthority"
            placeholder="请输入颁发机构"
          ></el-input>
        </el-form-item>

        <el-form-item label="颁发日期" prop="issueDate">
          <el-date-picker
            v-model="form.issueDate"
            type="date"
            format="YYYY-MM-DD"
            placeholder="请选择颁发日期"
          ></el-date-picker>
        </el-form-item>

        <!-- ccer_project_transactions -->
        <h3>交易信息</h3>
        <el-form-item label="买方" prop="buyer" style>
          <el-input v-model="form.buyer" placeholder="请输入买方"></el-input>
        </el-form-item>

        <el-form-item label="卖方" prop="seller">
          <el-input v-model="form.seller" placeholder="请输入卖方"></el-input>
        </el-form-item>

        <el-form-item label="交易日期" prop="transactionDate">
          <el-date-picker
            v-model="form.transactionDate"
            type="date"
            format="YYYY-MM-DD"
            placeholder="请选择交易日期"
          ></el-date-picker>
        </el-form-item>

        <el-form-item label="交易量" prop="quantity">
          <el-input
            v-model="form.quantity"
            type="number"
            placeholder="请输入交易量"
            step="0.01"
          ></el-input>
        </el-form-item>

        <el-form-item label="单价" prop="pricePerUnit">
          <el-input
            v-model="form.pricePerUnit"
            type="number"
            placeholder="请输入单价"
            step="0.01"
          ></el-input>
        </el-form-item>

        <el-form-item label="总金额" prop="totalAmount">
          <el-input
            v-model="form.totalAmount"
            :disabled="true"
            placeholder="总金额由系统计算"
          ></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="dialogVisible = false">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, reactive } from "vue";

//1、引入 axios 模块
import axios from "axios";
const currentPage = ref(1);
const pageSize = ref(10);

const searchQuery = ref(""); // 绑定搜索框的搜索关键字

interface perfcetType {
  projectId: number;
  projectName: string;
  projectType: string;
  status: string;
  location: string;
  registrationDate: string | null;
  validUntil: string | null;
  year: number | null;
  reductionAmount: number | null;
  verificationDate: string | null;
  reportDate: string | null;
  reportDetails: string;
  registrationNumber: string;
  issuingAuthority: string;
  issueDate: string | null;
  buyer: string;
  seller: string;
  transactionDate: string | null;
  quantity: number | null;
  pricePerUnit: number | null;
  totalAmount: number | null;
}

interface formData {
  projectName: string;
  projectType: string;
  location: string;
  registrationDate: string | null;
  validUntil: string | null;
  year: number | null;
  reductionAmount: number | null;
  verificationDate: string | null;
  reportDate: string | null;
  reportDetails: string;
  registrationNumber: string;
  issuingAuthority: string;
  issueDate: string | null;
  buyer: string;
  seller: string;
  transactionDate: string | null;
  quantity: number | null;
  pricePerUnit: number | null;
  totalAmount: number | null;
}

const form = ref<formData>({
  projectName: "",
  projectType: "",
  location: "",
  registrationDate: null,
  validUntil: null,
  year: null,
  reductionAmount: null,
  verificationDate: null,
  reportDate: null,
  reportDetails: "",
  registrationNumber: "",
  issuingAuthority: "",
  issueDate: null,
  buyer: "",
  seller: "",
  transactionDate: null,
  quantity: null,
  pricePerUnit: null,
  totalAmount: null,
});

interface User {
  projectId: number;
  name: string;
  type: string;
  status: string;
}

const filterTag = (value, row) => {
  return row.status === value;
};

//记录请求返回数据的集合
const msg = ref<User[]>([]);

//用于暂存当前页的数据
const tableData = ref([]);
let allData: perfcetType[]=[];
tableData.value = msg.value;

let total = tableData.value.length;
let baseUrl = "http://localhost:8080";
//获取所有的数据
function getData() {
  let api = baseUrl + "/ccer/getProject";
  //2.使用axios 进行get请求
  axios
    .get(api)
    .then((res) => {
      //请求成功的回调函数
      //把数据传给tableData数组
      allData = res.data.data;
      getmsgData();
      //获取数据的总条数
      total = msg.value.length;
      //获取当前页的数据
      getPageData();
    })
    .catch((err) => {
      //请求失败的回调函数
      console.log(err);
    });
}

function getmsgData() {
  console.log(allData[0].projectId);

  let tempArray: User[] = [];
  for (let i in allData) {
    let d: User = {
      projectId: allData[i].projectId,
      name: allData[i].projectName,
      type: allData[i].projectType,
      status: allData[i].status,
    };

    tempArray.push(d);
  }
  msg.value = [...tempArray];


  console.log(msg.value[0]);
}
//获取全部数据
function getAllData() {
  console.log("getAllData");
  //清空输入的imputStr
  //请求全部的数据
  getData();
}

//获取当前页的数据
function getPageData() {
  //先把当前页面的数据清空
  tableData.value = [];
  //获取当前页面的数据
  for (let i = (currentPage.value - 1) * pageSize.value; i < total; i++) {
    //遍历数据添加到tableData中
    tableData.value.push(msg.value[i]);
    //判断是否达到一页的要求
    if (tableData.value.length == pageSize.value) break;
  }
}

//分页时修改每一页所展示的数据量
const handleSizeChange = (val: number) => {
  //修改当前每一页的数据条数
  pageSize.value = val;
  //数据重新分页
  getPageData();
  console.log(`${val} items per page`);
};

//调整当前页的页码
const handleCurrentChange = (val: number) => {
  //修改当前页的页码
  currentPage.value = val;
  //数据重新分页
  getPageData();
  console.log(`current page: ${val}`);
};

//-----------------------------------//
//通过表单添加数据
//定义一个表单的结构体
const emissionForm = reactive({
  dataOrigin: "",
  emissionAmount: "",
  verificationStatus: "",
  emissionType: "",
  emissionDate: "",
  source: "",
  unit: "",
});

let dialogTitle = ref(""); //表单的标题
const dialogVisible = ref(false); //控制显示表单
let isView = ref(false);
let isEdit = ref(false);
//关闭表单要处理的操作
function closeDialogForm(formName) {
  //重置表单的校验

  //清空表单上一次所展示的内容
  emissionForm.dataOrigin = "";
  emissionForm.emissionAmount = "";
  emissionForm.verificationStatus = "";
  emissionForm.emissionType = "";
  emissionForm.emissionDate = "";
  emissionForm.source = "";
  emissionForm.unit = "";

  //关闭表单
  dialogVisible.value = false;

  //初始化isView和isEdit的值是false
  isEdit.value = false;
  isView.value = false;
}

//查看一条排放数据明细
function viewEmission(row) {
  console.log("这一行的数据->row:" + row);
  //修改标题
  dialogTitle.value = "项目详细";
  //修改isView变量
  isView.value = true;
  //弹出表单
  dialogVisible.value = true;


   for(let i in allData){
    if(row.projectId==allData[i].projectId){
      form.value=allData[i]
    }
   }

}

//-----------------------------------//

//渲染状态颜色
function getStatusColor(status) {
  switch (status) {
    case "批准":
      return "green";
    case "驳回":
      return "red";
    default:
      return "black";
  }
}
//用汉字显示状态
function getStatus(status) {
  switch (status) {
    case "批准":
      return "批准";
    case "Pending":
      return "Pending";
    default:
      return "待审核";
  }
}

// 批准项目，改变项目状态为 "批准"
const approveProject = (id: number) => {
  const project = msg.value.find((p) => p.projectId === id);
  if (project) {
    project.status = "批准";
  }
};

// 拒绝项目，改变项目状态为 "驳回"
const rejectProject = (id: number) => {
  const project = msg.value.find((p) => p.projectId === id);
  if (project) {
    project.status = "驳回";
  }
};

// 应用搜索筛选功能，重置页码到第一页
const applyFilters = () => {
  currentPage.value = 1;
};



onMounted(() => {
  getData();
});
</script>



<style scoped>
body,
input,
button,
.el-table,
.el-button,
h1,
span {
  font-size: 20px;
}
div.emissionSourceSketch {
  width: 100%;
  text-align: center;
  align-items: center;
  height: 100vh;
}

/* 整体布局 */
/* div {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
  color: #333;
} */

/* 页头样式 */
.header {
  margin-bottom: 20px;
}

.header h1 {
  font-size: 26px;
  color: #2c3e50;
  border-bottom: 2px solid #009879;
  padding-bottom: 5px;
}

/* 搜索和筛选部分样式 */
.search-filter {
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between;
}

input {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  width: 70%;
}

button {
  padding: 10px 20px;
  margin-left: 10px;
  border: none;
  border-radius: 4px;
  background-color: #009879;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #007b6d;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

/* 表格容器样式 */
.table-container {
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

/* 表头样式 */
thead th {
  background-color: #f4f4f4;
  color: #333;
  padding: 10px;
  text-align: left;
  position: relative;
}

/* 筛选下拉框样式 */
.filter-select {
  width: 120px;
  padding: 5px;
  border-radius: 4px;
  border: 1px solid #ddd;
  margin-top: 5px;
}

/* 表格数据单元格样式 */
tbody td {
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

/* 奇偶行背景色 */
tr:nth-child(even) {
  background-color: #f9f9f9;
}

/* 分页样式 */
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.pagination button {
  margin: 0 10px;
}

/* 状态颜色 */
.status-pending {
  color: gray;
}

.status-approved {
  color: green;
}

.status-rejected {
  color: red;
}

.status-pending {
  color: orange;
}
.status-approved {
  color: green;
}
.status-rejected {
  color: red;
}

.el-form-item {
  margin-bottom: 24px; /* 增大表单项的下边距 */
  line-height: 40px;
}
</style>

