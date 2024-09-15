<template>
  <div class="form-container">
    <el-form :model="form" :rules="rules" ref="formRef" label-width="150px">
      <!-- ccer_project_emission_reductions -->
      <h3>项目信息</h3>
      <el-form-item label="项目名称" prop="projectName">
        <el-input v-model="form.projectName" placeholder="请输入项目名称"></el-input>
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
        <el-input v-model="form.year" type="number" placeholder="请输入年度"></el-input>
      </el-form-item>

      <el-form-item label="减排量" prop="reductionAmount">
        <el-input v-model="form.reductionAmount" type="number" placeholder="请输入减排量" step="0.01"></el-input>
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
        <el-input v-model="form.reportDetails" type="textarea" placeholder="请输入报告详情"></el-input>
      </el-form-item>

      <!-- ccer_project_registration_info -->
      <h3>注册信息</h3>
      <el-form-item label="注册编号" prop="registrationNumber">
        <el-input v-model="form.registrationNumber" placeholder="请输入注册编号"></el-input>
      </el-form-item>

      <el-form-item label="颁发机构" prop="issuingAuthority">
        <el-input v-model="form.issuingAuthority" placeholder="请输入颁发机构"></el-input>
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
      <el-form-item label="买方" prop="buyer">
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
        <el-input v-model="form.quantity" type="number" placeholder="请输入交易量" step="0.01"></el-input>
      </el-form-item>

      <el-form-item label="单价" prop="pricePerUnit">
        <el-input v-model="form.pricePerUnit" type="number" placeholder="请输入单价" step="0.01"></el-input>
      </el-form-item>

      <el-form-item label="总金额" prop="totalAmount">
        <el-input v-model="form.totalAmount" :disabled="true" placeholder="总金额由系统计算"></el-input>
      </el-form-item>

      <div class="button-container"><el-button type="primary" @click="submitForm" round>提交</el-button></div>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import { FormInstance,ElForm,ElMessage } from 'element-plus';
import CCERProjects from '../API/CCERProjects';

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
  projectName: '',
  projectType: '',
  location: '',
  registrationDate: null,
  validUntil: null,
  year: null,
  reductionAmount: null,
  verificationDate: null,
  reportDate: null,
  reportDetails: '',
  registrationNumber: '',
  issuingAuthority: '',
  issueDate: null,
  buyer: '',
  seller: '',
  transactionDate: null,
  quantity: null,
  pricePerUnit: null,
  totalAmount: null,
});

// 监听交易量和单价变化，自动计算总金额
watch([() => form.value.quantity, () => form.value.pricePerUnit], ([quantity, pricePerUnit]) => {
  if (quantity && pricePerUnit) {
    form.value.totalAmount = quantity * pricePerUnit;
  } else {
    form.value.totalAmount = null;
  }
});

// 表单验证规则
const rules = {
  projectName: [
    { required: true, message: '项目名称必须为中文', trigger: 'blur', pattern: /^[\u4e00-\u9fa5]+$/ }
  ],
  projectType: [
    { required: true, message: '请选择项目类型', trigger: 'change' }
  ],
  location: [
    { required: true, message: '请输入有效的地址', trigger: 'blur' }
  ],
  registrationDate: [
    { required: true, message: '请选择注册日期', trigger: 'change' }
  ],
  validUntil: [
    { required: true, message: '请选择有效期', trigger: 'change' }
  ],
  year: [
    { required: true, message: '请输入有效的年度', trigger: 'blur', pattern: /^\d{4}$/ }
  ],
  reductionAmount: [
    { required: true, message: '请输入有效的减排量', trigger: 'blur',  }
  ],
  verificationDate: [
    { required: true, message: '请选择核查日期', trigger: 'change' }
  ],
  registrationNumber: [
    { required: true, message: '请输入有效的注册编号', trigger: 'blur', pattern: /^[A-Z]{4}\d{6}$/ }
  ],
  quantity: [
    { required: true, message: '请输入有效的交易量', trigger: 'blur' }
  ],
  pricePerUnit: [
    { required: true, message: '请输入有效的单价', trigger: 'blur'}
  ],
  transactionDate: [
    { required: true, message: '请选择交易日期', trigger: 'change' }
  ],
};
  

const formRef = ref<FormInstance>();
const submitForm = () => {

  formRef.value.validate((valid) => {
    if (valid) {
      CCERProjects.putProject(form)
    } else {
      ElMessage.error("表单校验失败，请检查输入！");
      return false;
    }
  });
};

</Script>


<style scoped>
.form-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.el-form {
  font-size: 18px; /* 增大整个表单的字体大小 */
}

.el-form-item {
  margin-bottom: 24px; /* 增大表单项的下边距 */
}

.el-input,
.el-select .el-input__inner,
.el-date-picker .el-input__inner,
.el-button {
  font-size: 18px; /* 增大输入框、选择器、日期选择器和按钮的字体大小 */
}

.el-input {
  height: 40px; /* 增大输入框的高度 */
}

.el-select .el-input__inner {
  height: 40px; /* 增大选择器的高度 */
}

.el-date-picker .el-input__inner {
  height: 40px; /* 增大日期选择器的高度 */
}

.el-button {
  height: 40px; /* 增大按钮的高度 */
  line-height: 40px; /* 确保按钮文字垂直居中 */
}

h3 {
  font-size: 22px; /* 增大标题字体大小 */
  margin-bottom: 16px; /* 增大标题下边距 */
}

.button-container {
  text-align: center; /* 中心对齐按钮 */
  margin-top: 20px; /* 按钮上边距 */
}
</style>

