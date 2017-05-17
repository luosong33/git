<template>
  <div>
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item"><span>我的位置：</span></span>
      <el-breadcrumb-item :to="{ path: '/home' }">聚贸平台</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>注册分析</el-breadcrumb-item>
      <el-breadcrumb-item>注册用户数</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row class="jm-grid-border-1px">
  
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">开通店铺趋势图
          </header>
  
          <div class="box chart row-group-last" v-loading="loadingOpenedShopChart" element-loading-text="加载中...">
            <div class="content" ref='openedShopChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 开通店铺趋势图-->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">基础资料完善统计
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingBaseInfoStatisticsChart" element-loading-text="加载中...">
            <div class="content" ref='baseInfoStatisticsChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">基础资料完善占比
  
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingBaseInfoStatisticsChart" element-loading-text="加载中...">
            <div class="content" ref='baseInfoProportionChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 基础资料完善统计 & 基础资料完善占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">店铺uv
            <el-tooltip class="item" content="统计本网站流量次数TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingBaseInfoStatisticsChart" element-loading-text="加载中...">
            <div class="content" ref='shopUvChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">店铺逗留时长（分）
            <el-tooltip class="item" content="统计本网站页面跳出TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingTODO" element-loading-text="加载中...">
            <div class="content" ref='shopStayPeriodChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 店铺uv & 店铺逗留时长（分） -->
  
  </div>
</template>

<script>
import {
  shopOpenTrend,
  shopOpenPerfect
} from '@/service/api'


import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';


export default {
  name: 'shopActived',
  components: {
    rangeDatePicker
  },
  methods: {
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.loadAllData();
    },
    loadAllData() {
      var vm = this;

      var platform = vm.platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;

      // 开通店铺趋势图
      vm.loadingOpenedShopChart = true
      shopOpenTrend({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingOpenedShopChart = false
        var chartData = response.data
        vm.loadopenedShopChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      // 基础资料完善统计及占比
      vm.loadingBaseInfoStatisticsChart = true
      shopOpenPerfect({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingBaseInfoStatisticsChart = false
        var chartData = response.data
        vm.loadbaseInfoStatisticsChart(chartData)
        vm.loadbaseInfoProportionChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    // 开通店铺趋势图
    loadopenedShopChart(chartData) {
      var vm = this;
      var chartId = "openedShopChart"
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis',
          // axisPointer: {
          //   type: 'cross',
          //   label: {
          //     backgroundColor: '#6a7985'
          //   }
          // }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: chartData.xAxisData
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '开通店铺趋势图',
            type: 'line',
            smooth: true,
            areaStyle: { normal: {} },
            itemStyle: {
              normal: {
                color: '#abd6f3'
              }
            },
            lineStyle: {
              normal: {
                color: '#5eb2ec'
              }
            },
            data: chartData.seriesData
          }

        ]
      };

      vm[chartId].setOption(option)
    },

    loadbaseInfoStatisticsChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.baseInfoStatisticsChart = echarts.init(vm.$refs.baseInfoStatisticsChart);


      // 绘制图表
      var option = {
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
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        },
        yAxis: {
          type: 'category',
          // splitLine: {
          //   show: true
          // },
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          },
          data: chartData.yAxisData
        },
        series: [
          {
            name: '基础资料完善统计',
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: '#5eb2ed',
                barBorderRadius: 2
              }
            },
            data: chartData.seriesData
          }

        ]
      };

      vm.baseInfoStatisticsChart.setOption(option)
    },

    loadshopUvChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.shopUvChart = echarts.init(vm.$refs.shopUvChart);


      // 绘制图表
      var option = {
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
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        },
        yAxis: {
          type: 'category',
          // splitLine: {
          //   show: true
          // },
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          },
          data: ['巴西', '印尼', '美国', '印度', '中国', '世界人口(万)']
        },
        series: [
          {
            name: '2011年',
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: '#5eb2ed',
                barBorderRadius: 2
              }
            },
            data: [18203, 23489, 29034, 104970, 131744, 630230]
          }

        ]
      };

      vm.shopUvChart.setOption(option)
    },

    loadshopStayPeriodChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.shopStayPeriodChart = echarts.init(vm.$refs.shopStayPeriodChart);


      // 绘制图表
      var option = {
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
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        },
        yAxis: {
          type: 'category',
          // splitLine: {
          //   show: true
          // },
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          },
          data: ['巴西', '印尼', '美国', '印度', '中国', '世界人口(万)']
        },
        series: [
          {
            name: '2011年',
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: '#5eb2ed',
                barBorderRadius: 2
              }
            },
            data: [18203, 23489, 29034, 104970, 131744, 630230]
          }

        ]
      };

      vm.shopStayPeriodChart.setOption(option)
    },

    loadbaseInfoProportionChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.baseInfoProportionChart = echarts.init(vm.$refs.baseInfoProportionChart);

      var newSeriesData = [];

      chartData.seriesData.forEach(function (value, index) {
        newSeriesData.push({
          value: value,
          name: chartData.yAxisData[index]
        })
      });


      // 绘制图表
      var option = {
        color: ['#1790cf', '#3ab882', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          //text: chartData.title,
          left: 'center',
          bottom: '5%',
          textStyle: {
            color: '#666',
            fontWeight: 'normal',
            fontSize: 14
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          //name: chartData.seriesName,
          type: 'pie',
          radius: ['30%', '60%'],
          center: ['50%', '55%'],
          //data: chartData.seriesData,
          data: newSeriesData,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.baseInfoProportionChart.setOption(option)
    },

  },

  mounted() {
    var vm = this;

    vm.loadAllData()

    // vm.loadopenedShopChart();

    // vm.loadbaseInfoStatisticsChart();

    //vm.loadbaseInfoProportionChart();

    vm.loadshopUvChart();

    vm.loadshopStayPeriodChart();

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingOpenedShopChart: false,
      loadingBaseInfoStatisticsChart: false,
      loadingTODOChart: false,
      loadingTODO: false
    }
  }
}


</script>

<style>

</style>
