<template>
    <div class="manage">
        <div class="manage-header" style="display:flex;justify-content: flex-start;">
            <!-- 搜索框 -->
            请选择搜索
            <el-select v-model="value" placeholder="请选择" style="margin-left:30px;margin-right: 30px;width: 240px;">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
            </el-select>
            <el-select v-model="value2" placeholder="请选择" style="margin-right: 30px;width: 240px;">
                <el-option v-for="item in options2" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
            </el-select>
            <el-button type="primary" @click="search">查询</el-button>
        </div>

        <div ref="chart" style="width: 1200px; height: 700px;margin-top:40px;margin-bottom:100px"></div>

        <div style="display: flex;align-items: center;">
            请选择年份
            <el-select v-model="selectedYear" placeholder="请选择年份" @change="updateChart"
                style="margin-left:30px;margin-right: 30px;width: 240px;">
                <el-option v-for="year in Object.keys(emissionsData2)" :key="year" :label="year" :value="year" />
            </el-select>年
        </div>
        <div ref="lineChart" style="width:1400px; height: 400px;margin-left: -100px;"></div>

    </div>
</template>

<script>
const emissionsData = {
    2023: [
        { month: '一月', value: 100 },
        { month: '二月', value: 120 },
        { month: '三月', value: 130 },
        { month: '四月', value: 125 },
        { month: '五月', value: 135 },
        { month: '六月', value: 145 },
        { month: '七月', value: 150 },
        { month: '八月', value: 140 },
        { month: '九月', value: 155 },
        { month: '十月', value: 160 },
        { month: '十一月', value: 165 },
        { month: '十二月', value: 170 },
    ],
    2022: [
        { month: '一月', value: 90 },
        { month: '二月', value: 110 },
        { month: '三月', value: 120 },
        { month: '四月', value: 115 },
        { month: '五月', value: 125 },
        { month: '六月', value: 135 },
        { month: '七月', value: 140 },
        { month: '八月', value: 130 },
        { month: '九月', value: 145 },
        { month: '十月', value: 150 },
        { month: '十一月', value: 155 },
        { month: '十二月', value: 160 },
    ],
    2021: [
        { month: '一月', value: 190 },
        { month: '二月', value: 320 },
        { month: '三月', value: 1320 },
        { month: '四月', value: 1315 },
        { month: '五月', value: 525 },
        { month: '六月', value: 735 },
        { month: '七月', value: 940 },
        { month: '八月', value: 130 },
        { month: '九月', value: 1045 },
        { month: '十月', value: 350 },
        { month: '十一月', value: 755 },
        { month: '十二月', value: 960 },
    ],
    2020: [
        { month: '一月', value: 190 },
        { month: '二月', value: 610 },
        { month: '三月', value: 820 },
        { month: '四月', value: 415 },
        { month: '五月', value: 565 },
        { month: '六月', value: 905 },
        { month: '七月', value: 440 },
        { month: '八月', value: 730 },
        { month: '九月', value: 445 },
        { month: '十月', value: 950 },
        { month: '十一月', value: 355 },
        { month: '十二月', value: 260 },
    ],
    // 可以继续添加更多年份的数据
};
import * as echarts from 'echarts'

export default {
    data() {
        return {
            selectedYear: '2023', // 默认选择2023年
            emissionsData2: emissionsData,

            options: [{
                value: '0',
                label: '北京'
            }, {
                value: '1',
                label: '广东'
            }, {
                value: '2',
                label: '上海'
            }, {
                value: '3',
                label: '江苏'
            }, {
                value: '4',
                label: '四川'
            }],
            options2: [{
                value: '0',
                label: '农业'
            }, {
                value: '1',
                label: '新能源'
            }, {
                value: '2',
                label: '电商'
            }, {
                value: '3',
                label: '计算机'
            }, {
                value: '4',
                label: '教育'
            }],
            value: '北京',
            value2: '农业',
            // 模拟的碳排放数据
            List: [
                [
                    { industry: '2015', emissions: 50000 },
                    { industry: '2016', emissions: 75000 },
                    { industry: '2017', emissions: 35000 },
                    { industry: '2018', emissions: 20000 },
                    { industry: '2019', emissions: 10000 },
                    { industry: '2020', emissions: 15000 },
                    { industry: '2021', emissions: 12000 },
                    { industry: '2022', emissions: 55000 },
                    { industry: '2023', emissions: 8000 },
                    { industry: '2024', emissions: 6000 }
                ]
            ],
            emissionData: [
            ],
            searchForm: {
                name: ''
            }
        }
    },
    methods: {
        // 获取列表数据
        initChart() {
            // 初始化ECharts实例
            this.chart = echarts.init(this.$refs.lineChart);
            this.updateChart(); // 初始化时渲染图表
        },
        updateChart() {
            // 获取当前选中的年份数据
            const data = this.emissionsData2[this.selectedYear];
            const months = data.map(item => item.month);
            const values = data.map(item => item.value);

            // 配置ECharts的图表选项
            const option = {
                title: {
                    text: `碳排放市场行情（元） - ${this.selectedYear}`,
                    left: 'center',
                },
                tooltip: {
                    trigger: 'axis',
                },
                xAxis: {
                    type: 'category',
                    
                    data: months,
                    name: '月份',
                },
                yAxis: {
                    type: 'value',
                    name: '碳排放市场行情（元）',
                },
                series: [
                    {
                        name: '碳排放市场行情',
                        type: 'line',
                        data: values,
                        smooth: true, // 平滑的曲线
                        lineStyle: {
                            color: 'rgba(54, 162, 235, 1)',
                            width: 2,
                        },
                        itemStyle: {
                            color: 'rgba(54, 162, 235, 1)',
                        },
                    },
                ],
            };

            // 使用新的选项渲染图表
            this.chart.setOption(option);
        },
        search() {
            this.List[0].forEach(item => {
                item.emissions = Math.floor(Math.random() * 100000); // 设置0到100000之间的随机数
            });

            this.emissionData = this.List[0]
            console.log(this.value);
            console.log(this.value2);
            this.drawChart()
        },
        drawChart() {
            // 初始化 echarts 实例
            const chart = echarts.init(this.$refs.chart);

            // 提取行业和碳排放量
            const industries = this.emissionData.map(item => item.industry);
            const emissions = this.emissionData.map(item => item.emissions);

            // 配置图表
            const option = {
                title: {
                    text: '按行业分类的碳排放统计',
                    left: 'center',
                    textStyle: {
                        color: '#333',
                        fontSize: 24
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    data: industries, // X轴数据
                    axisLabel: {
                        color: '#333'
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#333'
                        }
                    }
                },
                yAxis: {
                    type: 'value',
                    name: '碳排放量 (吨)',
                    axisLabel: {
                        color: '#333'
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#333'
                        }
                    },
                    splitLine: {
                        lineStyle: {
                            color: '#333'
                        }
                    }
                },
                series: [
                    {
                        name: '碳排放量',
                        type: 'bar',
                        data: emissions, // Y轴数据
                        itemStyle: {
                            // 颜色渐变
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                { offset: 0, color: '#83bff6' },
                                { offset: 0.5, color: '#188df0' },
                                { offset: 1, color: '#188df0' }
                            ])
                        },
                        barWidth: '60%',
                        emphasis: {
                            itemStyle: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                    { offset: 0, color: '#2378f7' },
                                    { offset: 0.7, color: '#2378f7' },
                                    { offset: 1, color: '#83bff6' }
                                ])
                            }
                        }
                    }
                ],
                backgroundColor: '#fff' // 背景颜色
            };

            // 使用指定的配置项和数据显示图表
            chart.setOption(option);

            // 响应式图表大小
            window.addEventListener('resize', () => {
                chart.resize();
            });
        }
    },
    mounted() {
        this.search()
        this.initChart()
    }
}
</script>

<style lang="less" scoped>
.manage {
    height: 100%;

    .manage-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .common-table {
        height: 90%;
        position: relative;

        .pager {
            position: absolute;
            right: 20px;
            bottom: 0;
        }
    }
}
</style>