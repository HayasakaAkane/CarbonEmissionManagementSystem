<template>
  <div class="form-container">
    <el-form :model="form"  label-width="150px">
      <!-- ccer_project_emission_reductions -->
      <h3>减排信息</h3>
      <el-form-item label="项目ID">
        <el-input v-model="form.project_id" placeholder="请输入项目ID"></el-input>
      </el-form-item>

      <el-form-item label="年度">
        <el-input v-model="form.year" type="number" placeholder="请输入年度"></el-input>
      </el-form-item>

      <el-form-item label="减排量">
        <el-input v-model="form.reduction_amount" type="number" placeholder="请输入减排量"></el-input>
      </el-form-item>

      <el-form-item label="核查日期">
        <el-date-picker v-model="form.verification_date" type="date" placeholder="请选择核查日期"></el-date-picker>
      </el-form-item>

      <!-- ccer_project_monitoring_reports -->
      <h3>监测报告</h3>
      <el-form-item label="报告ID">
        <el-input v-model="form.report_id" placeholder="请输入报告ID"></el-input>
      </el-form-item>

      <el-form-item label="报告日期">
        <el-date-picker v-model="form.report_date" type="date" placeholder="请选择报告日期"></el-date-picker>
      </el-form-item>

      <el-form-item label="报告详情">
        <el-input v-model="form.report_details" type="textarea" placeholder="请输入报告详情"></el-input>
      </el-form-item>

      <!-- ccer_project_registration_info -->
      <h3>注册信息</h3>
      <el-form-item label="注册编号">
        <el-input v-model="form.registration_number" placeholder="请输入注册编号"></el-input>
      </el-form-item>

      <el-form-item label="颁发机构">
        <el-input v-model="form.issuing_authority" placeholder="请输入颁发机构"></el-input>
      </el-form-item>

      <el-form-item label="颁发日期">
        <el-date-picker v-model="form.issue_date" type="date" placeholder="请选择颁发日期"></el-date-picker>
      </el-form-item>

      <!-- ccer_project_transactions -->
      <h3>交易信息</h3>
      <el-form-item label="买方">
        <el-input v-model="form.buyer" placeholder="请输入买方"></el-input>
      </el-form-item>

      <el-form-item label="卖方">
        <el-input v-model="form.seller" placeholder="请输入卖方"></el-input>
      </el-form-item>

      <el-form-item label="交易日期">
        <el-date-picker v-model="form.transaction_date" type="date" placeholder="请选择交易日期"></el-date-picker>
      </el-form-item>

      <el-form-item label="交易量">
        <el-input v-model="form.quantity" type="number" placeholder="请输入交易量"></el-input>
      </el-form-item>

      <el-form-item label="单价">
        <el-input v-model="form.price_per_unit" type="number" placeholder="请输入单价"></el-input>
      </el-form-item>

      <el-form-item label="总金额">
        <el-input v-model="form.total_amount" type="number" placeholder="请输入总金额"></el-input>
      </el-form-item>

      <el-button type="primary" @click="submitForm" round>提交</el-button>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref,watch } from 'vue';

interface FormData {
  project_id: number | null;
  year: number | null;
  reduction_amount: number | null;
  verification_date: string | null;
  report_id: number | null;
  report_date: string | null;
  report_details: string;
  registration_number: string;
  issuing_authority: string;
  issue_date: string | null;
  buyer: string;
  seller: string;
  transaction_date: string | null;
  quantity: number | null;
  price_per_unit: number | null;
  total_amount: number | null;
}

const form = ref<FormData>({
  project_id: null,
  year: null,
  reduction_amount: null,
  verification_date: null,
  report_id: null,
  report_date: null,
  report_details: '',
  registration_number: '',
  issuing_authority: '',
  issue_date: null,
  buyer: '',
  seller: '',
  transaction_date: null,
  quantity: null,
  price_per_unit: null,
  total_amount: null
});

const submitForm = () => {
  console.log('表单数据:', form.value);
  // 这里可以调用 API 提交数据
};

watch(() => form.value.project_id, (newValue) => {
  console.log('Updated form value:', newValue);
}, { deep: true });

</script>

<style scoped>
.form-container {
  width: 100%;
  margin: 50px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  font-size: 16px;
}

.el-input, .el-date-picker, .el-button {
  font-size: 16px;
}

.el-button {
  border-radius: 20px;
}

h3 {
  font-size: 18px;
  margin-bottom: 10px;
}

.el-form-item {
  margin-bottom: 20px;
}
</style>
