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
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">订单笔数地域分布
        <el-tooltip class="item"
                    content="网站访客来自哪里，构成比例。"
                    placement="top-end">
          <span class="jm-tooltip-icon"></span>
        </el-tooltip>
      </header>
  
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last"
               v-loading="loadingTODOChart"
               element-loading-text="加载中...">
            <div class="content"
                 ref='buyerOrderAmountMapChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <div class="box  chart row-group-last"
               v-loading="loadingTODOChart"
               element-loading-text="加载中...">
            <div class="content"
                 ref='buyerOrderAmountBarChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">订单笔数地域分布
        <el-tooltip class="item"
                    content="网站访客来自哪里，构成比例。"
                    placement="top-end">
          <span class="jm-tooltip-icon"></span>
        </el-tooltip>
      </header>
  
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last"
               v-loading="loadingTODOChart"
               element-loading-text="加载中...">
            <div class="content"
                 ref='sellerOrderAmountMapChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white ">
  
          <div class="box  chart row-group-last"
               v-loading="loadingTODOChart"
               element-loading-text="加载中...">
            <div class="content"
                 ref='sellerOrderAmountBarChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
  </div>
</template>

<script>
import {
  getSources,
  getCompanyNum,
  getCompanyTopAmount,
  pageVisit
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker2'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'Increased',
  components: {
    rangeDatePicker
  },
  methods: {
    getRangeDate({ startDate, endDate }) {
      this.startDate = startDate;
      this.endDate = endDate;
      // this.loadAllData();
    },
    // 三证审核地域分布
    loadbuyerOrderAmountMapChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.buyerOrderAmountMapChart = echarts.init(vm.$refs.buyerOrderAmountMapChart);

      function randomData() {
        return Math.round(Math.random() * 1000);
      }
      // 绘制图表
      var option = {
        title: {
          text: 'iphone销量',
          subtext: '纯属虚构',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          show: false,
          orient: 'vertical',
          left: 'left',
          data: ['iphone3', 'iphone4', 'iphone5']
        },
        visualMap: {
          min: 0,
          max: 2500,
          left: 'left',
          top: 'bottom',
          text: ['高', '低'],           // 文本，默认为数值文本
          inRange: {
            color: ['#e0ffff', '#006edd']
          },
          calculable: true
        },

        series: [

          {
            name: 'iphone5',
            type: 'map',
            mapType: 'china',
            label: {
              normal: {
                show: true
              },
              emphasis: {
                show: true
              }
            },
            data: [
              { name: '北京', value: randomData() },
              { name: '天津', value: randomData() },
              { name: '上海', value: randomData() },
              { name: '广东', value: randomData() },
              { name: '台湾', value: randomData() },
              { name: '香港', value: randomData() },
              { name: '澳门', value: randomData() }
            ]
          }
        ]
      };

      vm.buyerOrderAmountMapChart.setOption(option)
    },
    loadsellerOrderAmountMapChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.sellerOrderAmountMapChart = echarts.init(vm.$refs.sellerOrderAmountMapChart);

      function randomData() {
        return Math.round(Math.random() * 1000);
      }
      // 绘制图表
      var option = {
        title: {
          text: 'iphone销量',
          subtext: '纯属虚构',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          show: false,
          orient: 'vertical',
          left: 'left',
          data: ['iphone3', 'iphone4', 'iphone5']
        },
        visualMap: {
          min: 0,
          max: 2500,
          left: 'left',
          top: 'bottom',
          text: ['高', '低'],           // 文本，默认为数值文本
          inRange: {
            color: ['#e0ffff', '#006edd']
          },
          calculable: true
        },

        series: [

          {
            name: 'iphone5',
            type: 'map',
            mapType: 'china',
            label: {
              normal: {
                show: true
              },
              emphasis: {
                show: true
              }
            },
            data: [
              { name: '北京', value: randomData() },
              { name: '天津', value: randomData() },
              { name: '上海', value: randomData() },
              { name: '广东', value: randomData() },
              { name: '台湾', value: randomData() },
              { name: '香港', value: randomData() },
              { name: '澳门', value: randomData() }
            ]
          }
        ]
      };

      vm.sellerOrderAmountMapChart.setOption(option)
    },

    loadbuyerOrderAmountBarChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.buyerOrderAmountBarChart = echarts.init(vm.$refs.buyerOrderAmountBarChart);

      var existCountChart = chartData.existCountChart
      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          top: '8%',
          left: '5%',
          right: '5%',
          bottom: '8%',
          containLabel: true
        },
        xAxis: {
          name: '次',
          type: 'value',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#008acd'
            }
          }
        },
        yAxis: {
          type: 'category',
          splitLine: {
            show: true
          },
          axisLine: {
            lineStyle: {
              color: '#008acd'
            }
          },
          axisLabel: {
            formatter: function (value, index) {
              var value = value || ''
              var maxLength = 15
              if (value.length > maxLength) {
                value = value.substring(0, maxLength) + '...';
              }
              return value;
            },
            rotate: 20
          },
          data: existCountChart.yAxisData.reverse()
        },
        series: [{
          name: existCountChart.seriesName,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: existCountChart.seriesData.reverse()
        }]
      };

      vm.buyerOrderAmountBarChart.setOption(option)
    },

    loadsellerOrderAmountBarChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.sellerOrderAmountBarChart = echarts.init(vm.$refs.sellerOrderAmountBarChart);

      var existCountChart = chartData.existCountChart
      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          top: '8%',
          left: '5%',
          right: '5%',
          bottom: '8%',
          containLabel: true
        },
        xAxis: {
          name: '次',
          type: 'value',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#008acd'
            }
          }
        },
        yAxis: {
          type: 'category',
          splitLine: {
            show: true
          },
          axisLine: {
            lineStyle: {
              color: '#008acd'
            }
          },
          axisLabel: {
            formatter: function (value, index) {
              var value = value || ''
              var maxLength = 15
              if (value.length > maxLength) {
                value = value.substring(0, maxLength) + '...';
              }
              return value;
            },
            rotate: 20
          },
          data: existCountChart.yAxisData.reverse()
        },
        series: [{
          name: existCountChart.seriesName,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#1790cf',
              barBorderRadius: 2
            }
          },
          data: existCountChart.seriesData.reverse()
        }]
      };

      vm.sellerOrderAmountBarChart.setOption(option)
    },

  },

  mounted() {
    var vm = this;
    var platform = '',
      startDate = '',
      endDate = '';

    vm.loadbuyerOrderAmountMapChart();
    vm.loadsellerOrderAmountMapChart();


    pageVisit({
      platform,
      startDate,
      endDate
    }).then(function (response) {

      //  vm.loadingregisterUserAreaBarChart = false
      var chartData = response.data
      console.log(chartData)
      vm.loadbuyerOrderAmountBarChart(chartData)
      vm.loadsellerOrderAmountBarChart(chartData)
    })
      .catch(function (error) {
        console.log(error);
      });

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingTODOChart: false,

    }
  }
}


</script>

<style>

</style>
