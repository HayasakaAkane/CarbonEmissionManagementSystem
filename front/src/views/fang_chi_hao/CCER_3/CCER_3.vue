<template>
    <div>
      <div class="header">
        <h1>项目审核</h1>
      </div>
      <div class="search-filter">
        <input v-model="searchQuery" type="text" placeholder="搜索项目..." />
        <button @click="applyFilters">搜索</button>
      </div>
      <div class="table-container">
        <table>
          <thead>
            <tr>
              <th>项目ID</th>
              <th>项目名称</th>
              <th>项目类型</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="project in filteredProjects" :key="project.id">
              <td>{{ project.id }}</td>
              <td>{{ project.name }}</td>
              <td>{{ project.type }}</td>
              <td>{{ project.status }}</td>
              <td>
                <button @click="viewDetails(project.id)">查看详情</button>
                <button @click="approveProject(project.id)">批准</button>
                <button @click="rejectProject(project.id)">拒绝</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="pagination">
        <button @click="prevPage" :disabled="currentPage === 1">上一页</button>
        <span>{{ currentPage }} / {{ totalPages }}</span>
        <button @click="nextPage" :disabled="currentPage === totalPages">下一页</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue';
  
  const projects = ref([
    // 示例数据
    { id: 1, name: '项目A', type: '类型1', status: '待审核' },
    { id: 2, name: '项目B', type: '类型2', status: '待审核' },
    // 添加更多示例数据
  ]);
  
  const searchQuery = ref('');
  const currentPage = ref(1);
  const itemsPerPage = ref(5);
  
  const filteredProjects = computed(() => {
    return projects.value
      .filter(project => project.name.includes(searchQuery.value))
      .slice((currentPage.value - 1) * itemsPerPage.value, currentPage.value * itemsPerPage.value);
  });
  
  const totalPages = computed(() => Math.ceil(projects.value.length / itemsPerPage.value));
  
  function applyFilters() {
    currentPage.value = 1;
  }
  
  function viewDetails(id) {
    // 实现查看详细信息的逻辑
    alert(`查看项目 ${id} 的详细信息`);
  }
  
  function approveProject(id) {
    // 实现批准项目的逻辑
    alert(`批准项目 ${id}`);
  }
  
  function rejectProject(id) {
    // 实现拒绝项目的逻辑
    alert(`拒绝项目 ${id}`);
  }
  
  function prevPage() {
    if (currentPage.value > 1) {
      currentPage.value--;
    }
  }
  
  function nextPage() {
    if (currentPage.value < totalPages.value) {
      currentPage.value++;
    }
  }
  </script>
  
  <style scoped>
  /* 整体布局 */
  div {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
    font-family: Arial, sans-serif;
    color: #333;
  }
  
  .header {
    margin-bottom: 20px;
  }
  
  .header h1 {
    font-size: 26px;
    color: #2c3e50;
    border-bottom: 2px solid #009879;
    padding-bottom: 5px;
  }
  
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
  
  .table-container {
    margin-bottom: 20px;
  }
  
  table {
    width: 100%;
    border-collapse: collapse;
  }
  
  thead th {
    background-color: #f4f4f4;
    color: #333;
    padding: 10px;
    text-align: left;
  }
  
  tbody td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
  }
  
  tr:nth-child(even) {
    background-color: #f9f9f9;
  }
  
  .pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
  }
  
  .pagination button {
    margin: 0 10px;
  }
  </style>
  