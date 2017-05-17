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
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">新增注册数
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingNewAddedUsersLineChartText === '暂无数据'}" v-loading="loadingNewAddedUsersLineChart" :element-loading-text="loadingNewAddedUsersLineChartText">
            <div class="content" ref='newAddedUsersLineChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart row-group-last" :class="{'no-data': loadingNewAddedUserPieChartText === '暂无数据'}" v-loading="loadingNewAddedUserPieChart" :element-loading-text="loadingNewAddedUserPieChartText">
            <div class="content" ref='newAddedUserPieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">注册用户地域分布
        <el-tooltip class="item" content="网站访客来自哪里，构成比例。" placement="top-end">
          <span class="jm-tooltip-icon"></span>
        </el-tooltip>
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" v-loading="loadingTODOChart" element-loading-text="加载中...">
            <div class="content" ref='registerUserAreaMapChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart row-group-last" v-loading="loadingTODOChart" element-loading-text="加载中...">
            <div class="content" ref='registerUserAreaBarChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 注册用户地域分布-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">注册明细表
        <el-tooltip class="item" content="网站访客来自哪里，构成比例。" placement="top-end">
          <span class="jm-tooltip-icon"></span>
        </el-tooltip>
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box " v-loading="loadingTableData" element-loading-text="加载中...">
            <div class="content">
              <div class="register-table-container">
                <div class="text-right"><span class="margin-right-20">合计：{{tableData.counts}}人 </span><span>占总用户：{{tableData.percent}}</span></div>
                <el-table :data="tableData.tableData" border style="width: 100%">
                  <el-table-column header-align="center" align="center" prop="date" label="日期">
                  </el-table-column>
                  <el-table-column header-align="center" align="center" prop="source" label="来源">
                  </el-table-column>
                  <el-table-column header-align="center" align="center" prop="amount" label="人数">
                  </el-table-column>
                  <el-table-column header-align="center" align="center" prop="rate" label="占比">
                  </el-table-column>
                </el-table>
              </div>
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 注册明细表-->
  
  </div>
</template>

<script>
import {
  newlyIncreased,

  // delete below
  getSources,
  getCompanyNum,
  getCompanyTopAmount,
  pageVisit
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'increasedUser',
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

      vm.loadingNewAddedUsersLineChart = true
      vm.loadingNewAddedUsersLineChartText = '加载中...'
      newlyIncreased({
        platform: '1025',
        startDate,
        endDate,
        dataType: 'lineChart'
      }).then(function (response) {
        vm.loadingNewAddedUsersLineChart = false
        var chartData = response.data

        if (chartData.lineChart.seriesData.length === 0) {
          vm.loadingNewAddedUsersLineChart = true
          vm.loadingNewAddedUsersLineChartText = '暂无数据'
        }
        vm.loadnewAddedUsersLineChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingNewAddedUserPieChart = true
      vm.loadingNewAddedUserPieChartText = '加载中...'
      newlyIncreased({
        platform,
        startDate,
        endDate,
        dataType: 'pieChart'
      }).then(function (response) {
        vm.loadingNewAddedUserPieChart = false
        var chartData = response.data

        if (chartData.pieChart.seriesData.length === 0) {
          vm.loadingNewAddedUserPieChart = true
          vm.loadingNewAddedUserPieChartText = '暂无数据'
        }
        vm.loadnewAddedUserPieChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingTableData = true
      newlyIncreased({
        platform,
        startDate,
        endDate,
        dataType: 'table'
      }).then(function (response) {
        vm.loadingTableData = false
        var data = response.data
        vm.tableData = data

      })
        .catch(function (error) {
          console.log(error);
        });

    },
    // 新增注册数
    loadnewAddedUsersLineChart(chartData) {
      var vm = this;
      var chartId = "newAddedUsersLineChart";
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var lineChart = chartData.lineChart

      // 绘制图表
      var option = {
        color: ["#44cd8a", "#5eb2ec", "#b6a4dd"],
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          top: '2%',
          data: lineChart.legendData
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '5%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          },
          data: lineChart.xAxisData
        },
        yAxis: {
          //name: '次',
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        },
        series: [{
          name: lineChart.legendData[0],
          type: 'line',
          smooth: true,
          itemStyle: {
            normal: {
              color: "#44cd8a"
            }
          },
          data: lineChart.seriesData
        }]
      };

      vm[chartId].setOption(option)
    },

    // 新增注册数
    loadnewAddedUserPieChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.newAddedUserPieChart = echarts.init(vm.$refs.newAddedUserPieChart);

      var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        title: {
          //text: "新增注册数",
          left: 'center',
          top: '5%',
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
          name: "新增注册数",
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data: pieChart.seriesData,

          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.newAddedUserPieChart.setOption(option)
    },
    // 用户登录情况
    loadregisterUserAreaMapChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.registerUserAreaMapChart = echarts.init(vm.$refs.registerUserAreaMapChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
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

      vm.registerUserAreaMapChart.setOption(option)
    },

    // 关键词搜索占比TOP10
    loadregisterUserAreaBarChart(chartData) {
      var vm = this
      // 基于准备好的dom，初始化echarts实例
      vm.registerUserAreaBarChart = echarts.init(vm.$refs.registerUserAreaBarChart);

      var existCountChart = chartData.existCountChart
      console.log(existCountChart)
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

      vm.registerUserAreaBarChart.setOption(option)

      vm.registerUserAreaBarChart.on('click', function (obj) {
        openInNewTab(obj.name);
      })
    },
  },

  mounted() {
    var vm = this;
    var platform = '',
      startDate = '',
      endDate = '';
    // getSources({
    //   platform,
    //   startDate,
    //   endDate
    // }).then(function (response) {
    //   //  vm.loadinguserLoginLineChart = false
    //   var chartData = response.data
    //   vm.loadnewAddedUsersLineChart(chartData)
    //   //vm.loaduserLoginPieChart(chartData)
    // })
    //   .catch(function (error) {
    //     console.log(error);
    //   });

    vm.loadAllData();

    //vm.loadnewAddedUserPieChart();

    vm.loadregisterUserAreaMapChart();

    // vm.loadingregisterUserAreaBarChart = true
    // todo: 
    pageVisit({
      platform,
      startDate,
      endDate
    }).then(function (response) {

      //  vm.loadingregisterUserAreaBarChart = false
      var chartData = response.data
      console.log(chartData)
      vm.loadregisterUserAreaBarChart(chartData)
    })
      .catch(function (error) {
        console.log(error);
      });

  },
  data() {
    return {
      platformId: "102000",
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingNewAddedUsersLineChart: false,
      loadingNewAddedUsersLineChartText: "加载中...",
      loadingNewAddedUserPieChart: false,
      loadingNewAddedUserPieChartText: "加载中...",
      loadingTableData: false,
      loadingTODOChart: false,
      tableData: {}
    }
  }
}


</script>

<style>
.register-table-container {
  padding: 20px;
}

.text-right {
  text-align: right;
}

.margin-right-20 {
  margin-right: 20px;
}
</style>
