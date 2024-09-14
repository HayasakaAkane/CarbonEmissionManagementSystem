<template>
<<<<<<< HEAD
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
        <td><a href="#">详细</a></td>
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
  </div>
</template>

<script lang="ts" setup>
import CCERProjects from "./API/CCERProjects";
import { ref, reactive, computed, onMounted } from "vue";

// 初始化变量
let data = reactive<any[]>([]);
let showData = reactive<any[]>([]);
let index = ref(1);
const pageSizes = [5, 10, 15, 20]; // 允许的每页显示条数选项
let pageSize = ref(pageSizes[0]); // 默认每页显示5条数据
const totalPages = ref(1); // 总页数
=======
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
        <tr v-for="d in data" :key="d.ProjectID">
            <td>{{ d.ProjectID }}</td>
            <td>{{ d.ProjectName }}</td>
            <td>{{ d.ProjectType }}</td>
            <td>{{ d.Location }}</td>
            <td>{{ d.Status }}</td>
            <td>{{ d.RegistrationDate }}</td>
            <td>{{ d.ValidUntil }}</td>
            <td><a href="#">详细</a></td>
        </tr>
    </table>
    <div class="number" style="margin-top: 10px; display: flex; justify-content: center; width: 100%;">
        <a href="#" v-for="i in 6" :key="i" class="pagination">{{ i }}</a>
    </div>
</template>

<script lang="ts" setup>
import CCERProjects from "./API/CCERProjects"
import { reactive, onBeforeMount } from "vue";

let data = reactive<projectOverview>([
    {
        Project: 1,
        ProjectName: "可再生能源",
        ProjectType: "项目A",
        Location: "北京",
        Status: "进行中",
        RegistrationDate: "2021-01-01",
        ValidUntil: "2021-01-01"
    },
    {
        ProjectID: 1,
        ProjectName: "可再生能源",
        ProjectType: "项目A",
        Location: "北京",
        Status: "进行中",
        RegistrationDate: "2021-01-01",
        ValidUntil: "2021-01-01",
    },
    {
        ProjectID: 1,
        ProjectName: "可再生能源",
        ProjectType: "项目A",
        Location: "北京",
        Status: "进行中",
        RegistrationDate: "2021-01-01",
        ValidUntil: "2021-01-01",
    },
    {
        ProjectID: 1,
        ProjectName: "可再生能源",
        ProjectType: "项目A",
        Location: "北京",
        Status: "进行中",
        RegistrationDate: "2021-01-01",
        ValidUntil: "2021-01-01",
    },
]);
>>>>>>> jackson

// 计算当前页码范围
const currentPage = computed(() => index.value);
const paginatedPages = computed(() => {
  const pages = [];
  const maxPagesToShow = 5;
  const startPage = Math.max(1, currentPage.value - Math.floor(maxPagesToShow / 2));
  const endPage = Math.min(totalPages.value, startPage + maxPagesToShow - 1);

<<<<<<< HEAD
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
=======
function GetProject() {
    CCERProjects.getProjects().then((result) => {
        console.log(result)
    }).catch((err) => {
        alert("请求失败")
    });
}

onBeforeMount(() => {
    GetProject()
})
>>>>>>> jackson

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
      const transformedData = transformData(result.data.data);
      Object.assign(data, transformedData);
      totalPages.value = Math.ceil(data.length / pageSize.value); // 计算总页数
      updateShowData(); // 显示第一页数据
    })
    .catch((err) => {
      alert("请求失败");
    });
}

// 在组件挂载时调用
onMounted(() => {
  GetProject();
});
</script>

<style scoped>
/* 整体布局 */
div {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

/* 表格样式 */
table {
<<<<<<< HEAD
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

=======
    width: 100%;
    border-collapse: collapse;
}

table,
th,
td {
    border: 1px solid black;
}

th,
td {
    padding: 8px;
    text-align: center;
}

th {
    background-color: #f2f2f2;
}

a {
    color: rgb(86, 86, 84);
}


.pagination {
    color: #009879;
    padding: 8px 16px;
    text-decoration: none;
    transition: background-color 0.3s;
    margin: 0 4px;
    border: 1px solid #009879;
    border-radius: 4px;
    display: flex;
    justify-content: center;
    margin-top: 10px;
}
</style>
>>>>>>> jackson
