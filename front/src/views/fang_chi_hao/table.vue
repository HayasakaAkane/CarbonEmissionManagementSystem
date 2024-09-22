<template>
    <div>
      <!-- 数据条目选择 -->
      <div style="margin-bottom: 10px;">
        <label for="pageSizeSelect">每页显示条数:</label>
        <select id="pageSizeSelect" v-model="pageSize" @change="onPageSizeChange">
          <option v-for="size in pageSizes" :key="size" :value="size">{{ size }}</option>
        </select>
      </div>
  
      <!-- 表格显示 -->
      <table>
        <tr>
          <th>项目ID</th>
          <th>项目名称</th>
          <th>项目类型</th>
          <th>地点</th>
          <th>状态</th>
          <th>注册日期</th>
          <th>有效期至</th>
          <th>备注</th>
        </tr>
        <tr v-for="(d, index) in showData" :key="index">
          <td>{{ d.projectId }}</td>
          <td>{{ d.projectName }}</td>
          <td>{{ d.projectType }}</td>
          <td>{{ d.location }}</td>
          <td>{{ d.status }}</td>
          <td>{{ d.registrationDate }}</td>
          <td>{{ d.validUntil }}</td>
          <td><a @click="viewEmission(d.projectId)">详细</a></td>
        </tr>
      </table>
  
      <!-- 分页控件 -->
      <div class="number" style="margin-top: 10px; display: flex; justify-content: center; width: 100%;">
        <a v-if="currentPage > 1" class="pagination" @click="onClick(currentPage - 1)">上一页</a>
        <a v-if="showFirstEllipsis" class="pagination" @click="onClick(1)">1</a>
        <a v-if="showFirstEllipsis" class="pagination">...</a>
  
        <a
          v-for="page in paginatedPages"
          :key="page"
          class="pagination"
          :class="{ active: page === currentPage }"
          @click="onClick(page)"
        >
          {{ page }}
        </a>
  
        <a v-if="showLastEllipsis" class="pagination">...</a>
        <a v-if="showLastEllipsis" class="pagination" @click="onClick(totalPages)"> {{ totalPages }} </a>
        <a v-if="currentPage < totalPages" class="pagination" @click="onClick(currentPage + 1)">下一页</a>
      </div>


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
  import CCERProjects from "./API/CCERProjects";
  import { ref, reactive, computed, onMounted } from "vue";
  import axios from "axios";
  
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
  
  // 初始化变量
  let data = reactive<any[]>([]);
  let showData = reactive<any[]>([]);
  let index = ref(1);
  const pageSizes = [5, 10, 15, 20]; // 允许的每页显示条数选项
  let pageSize = ref(pageSizes[0]); // 默认每页显示5条数据
  const totalPages = ref(1); // 总页数
  
  // 计算当前页码范围
  const currentPage = computed(() => index.value);
  const paginatedPages = computed(() => {
    const pages = [];
    const maxPagesToShow = 5;
    const startPage = Math.max(1, currentPage.value - Math.floor(maxPagesToShow / 2));
    const endPage = Math.min(totalPages.value, startPage + maxPagesToShow - 1);
  
    for (let i = startPage; i <= endPage; i++) {
      pages.push(i);
    }
  
    return pages;
  });
  
  // 显示省略号
  const showFirstEllipsis = computed(() => {
    return paginatedPages.value[0] > 1;
  });
  
  const showLastEllipsis = computed(() => {
    return paginatedPages.value[paginatedPages.value.length - 1] < totalPages.value;
  });
  
  // 格式化日期字符串为 YYYY-MM-DD 格式
  function formatToYYYYMMDD(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
  
  // 转换数据数组中的日期字段
  function transformData(dataArray: any[]): any[] {
    return dataArray.map(item => ({
      ...item,
      registrationDate: formatToYYYYMMDD(item.registrationDate),
      validUntil: formatToYYYYMMDD(item.validUntil),
    }));
  }
  
  // 点击分页按钮时，更新显示的数据
  function onClick(page: number) {
    if (page < 1 || page > totalPages.value) return; // 防止点击无效页码
    index.value = page;
    updateShowData();
  }
  
  // 更新要显示的当前页数据
  function updateShowData() {
    const start = (index.value - 1) * pageSize.value;
    const end = start + pageSize.value;
    showData.splice(0, showData.length, ...data.slice(start, end)); // 重新填充 showData
  }
  
  // 处理每页显示条数的变化
  function onPageSizeChange() {
    index.value = 1; // 重置到第一页
    totalPages.value = Math.ceil(data.length / pageSize.value); // 重新计算总页数
    updateShowData(); // 更新数据展示
  }
  
  // 请求项目数据
  function GetProject() {
    CCERProjects.getProjects()
      .then((result) => {
        getData()
        const transformedData = transformData(result.data.data);
        Object.assign(data, transformedData);
        totalPages.value = Math.ceil(data.length / pageSize.value); // 计算总页数
        updateShowData(); // 显示第一页数据
      })
      .catch((err) => {
        alert("请求失败");
      });
  }
  let baseUrl = "http://localhost:8080";
  let allData: perfcetType[]=[];
  function getData() {
  let api = baseUrl + "/ccer/getProject";
  //2.使用axios 进行get请求
  axios
    .get(api)
    .then((res) => {
      //请求成功的回调函数
      //把数据传给tableData数组
      allData = res.data.data;
    })
    .catch((err) => {
      //请求失败的回调函数
      console.log(err);
    });
}

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
    if(row==allData[i].projectId){
      form.value=allData[i]
    }
   }
}

  
  // 在组件挂载时调用
  onMounted(() => {
    GetProject();
  });
  </script>
  
  <style scoped>
  /* 整体布局 */
  /* div {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
  } */
  
  /* 表格样式 */
  table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
  }
  
  th,
  td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: center;
  }
  
  th {
    background-color: #f4f4f4;
    color: #333;
    font-weight: bold;
  }
  
  td {
    background-color: #fff;
    color: #666;
  }
  
  tr:nth-child(even) {
    background-color: #f9f9f9;
  }
  
  /* 数据条目选择下拉菜单 */
  label {
    margin-right: 10px;
    font-size: 16px;
    color: #333;
  }
  
  select {
    padding: 8px;
    font-size: 16px;
    border: 1px solid #ddd;
    border-radius: 4px;
    background-color: #fff;
    color: #333;
    cursor: pointer;
  }
  
  select:focus {
    border-color: #009879;
    outline: none;
  }
  
  /* 分页控件 */
  .number {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
  }
  
  .pagination {
    color: #009879;
    padding: 10px 15px;
    text-decoration: none;
    transition: background-color 0.3s, color 0.3s;
    margin: 0 5px;
    border: 1px solid #009879;
    border-radius: 4px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .pagination:hover {
    background-color: #009879;
    color: #fff;
  }
  
  .pagination.active {
    background-color: #009879;
    color: #fff;
    cursor: default;
  }
  
  .pagination.disabled {
    color: #ccc;
    border-color: #ccc;
    cursor: not-allowed;
  }
  
  .pagination.disabled:hover {
    background-color: #fff;
  }
  
  /* 响应式设计 */
  @media (max-width: 768px) {
    .number {
      flex-direction: column;
    }
  
    .pagination {
      margin: 5px 0;
    }
  }
  </style>