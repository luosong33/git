<template>
  <div>
  
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item"><span>我的位置：</span></span>
      <el-breadcrumb-item :to="{ path: '/home' }">聚贸通平台</el-breadcrumb-item>
      <el-breadcrumb-item>用户行为</el-breadcrumb-item>
      <el-breadcrumb-item>流量分析</el-breadcrumb-item>
    </el-breadcrumb>
  
    <range-date-picker v-on:selectedRangeDate="getRangeDate"></range-date-picker>
  
    <el-row>
      <el-col :span="24">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px zhibiao-activeTabName">
          <el-tabs v-model="zhibiao_activeTabName" type="card" @tab-click="handle_zhibiao_Click" v-loading="loadingIndex" element-loading-text="加载中...">
            <el-tab-pane label="浏览量" name="pv">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='pvChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="独立访客" name="uv">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='uvChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="IP数" name="ip">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='ipChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="平均停留时长" name="averageTime">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='averageTimeChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="平均访问页数" name="avgVisitPages">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content " ref='avgVisitPagesChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
            <el-tab-pane label="跳出率" name="exitRate">
  
              <div class="box chart home-row-group-1 sixteen-nine">
                <div class="content" ref='exitRateChart'>
  
                </div>
              </div>
  
            </el-tab-pane>
          </el-tabs>
  
          <el-tooltip class="item" popper-class="zhibiao-tooltip" content="通过网站统计，可以了解用户在网站的活动情况。可以详细指导用户数量、次数、访问质量如何。通过时间日历控件，查看选定时间段内，用户流量的趋势，从而对运营起到辅助作用。" placement="top-end">
            <span class="jm-tooltip-icon"></span>
          </el-tooltip>
        </div>
      </el-col>
  
    </el-row>
    <!-- end of zhibiao-->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">页面流量排名
            <el-tooltip class="item" content="统计本网站流量次数TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingYmllpmChart" element-loading-text="加载中...">
            <div class="content" ref='ymllpmChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">页面跳出排名
            <el-tooltip class="item" content="统计本网站页面跳出TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingYmtclpmChart" element-loading-text="加载中...">
            <div class="content" ref='ymtclpmChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 页面流量排名 & 页面跳出率排名 -->
  
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">用户访问时间分布
            <el-tooltip class="item" content="统计本网站流量次数TOP5页面。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box chart sixteen-nine yhfwsjfb" v-loading="loadingYhfwsjfbChart" element-loading-text="加载中...">
            <div class="content" ref='yhfwsjfbChart'>
  
            </div>
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!-- end of 用户访问时间分布 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">新老访客
            <el-tooltip class="item" content="根据选定时间段内，老访客、新访客比例统计网站访客情况。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine">
            <div class="content">
              <section class="new-old-visitor" v-loading="loadingNewOldVisitor" element-loading-text="加载中...">
                <ul class="clearfix">
                  <li class="left jm-width-50-p"><span class="lable">新访客：</span><span class="new-percentage">{{tableData.newPercent}}</span></li>
                  <li class="left jm-width-50-p"><span class="lable">老访客：</span><span class="old-percentage">{{tableData.oldPercent}}</span></li>
                </ul>
  
                <div class="new-old-visitor-detail">
  
                  <el-table :data="tableData.tableData" :show-header="false" border style="width: 100%">
                    <el-table-column prop="name" label="type">
                    </el-table-column>
                    <el-table-column prop="newNumber" label="new">
                    </el-table-column>
                    <el-table-column prop="oldNumber" label="old">
                    </el-table-column>
                  </el-table>
                </div>
  
              </section>
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">地域分布
            <el-tooltip class="item" content="按照访问量，统计访客地域分布，深色到浅色代表访客量由多到少。" placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine" v-loading="loadingDyfbChart" element-loading-text="加载中...">
            <div class="content" ref='dyfbChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 用户流向 & 地域分布 -->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">访问来源
        <el-tooltip class="item" content="网站访客来自哪里，构成比例。" placement="top-end">
          <span class="jm-tooltip-icon"></span>
        </el-tooltip>
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" v-loading="loadingFwlyLineChart" element-loading-text="加载中...">
            <div class="content" ref='fwlyLineChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box  chart row-group-last" v-loading="loadingFwlyLineChart" element-loading-text="加载中...">
            <div class="content" ref='fwlyPieChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
  
  </div>
</template>
<script>
import {
  baiduTrend,
  pageVisit,
  getSources,
  getVistors,
  getAreas
} from '@/service/api'
import util from '@/service/util'

import rangeDatePicker from '@/components/RangeDatePicker'

import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

function openInNewTab(url) {
  var a = document.createElement("a");
  a.target = "_blank";
  a.href = url;
  a.click();
}

export default {
  name: 'flowAnalysis',
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

      vm.loadTabContentChart(vm.zhibiao_activeTabName)

      vm.loadingYmllpmChart = true
      vm.loadingYmtclpmChart = true
      pageVisit({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingYmllpmChart = false
        vm.loadingYmtclpmChart = false
        var chartData = response.data
        vm.loadymllpmChart(chartData)
        vm.loadymtclpmChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingNewOldVisitor = true
      getVistors({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingNewOldVisitor = false
        var tableData = response.data
        vm.tableData = tableData
      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingDyfbChart = true
      getAreas({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingDyfbChart = false
        var chartData = response.data
        vm.loaddyfbChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingFwlyLineChart = true
      getSources({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingFwlyLineChart = false
        var chartData = response.data
        vm.loadfwlyLineChart(chartData)
        vm.loadfwlyPieChart(chartData)
      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadyhfwsjfbChart();
    },
    // 用户访问时间分布
    loadyhfwsjfbChart() {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.yhfwsjfbChart = echarts.init(vm.$refs.yhfwsjfbChart);

      // 绘制图表
      var option = {

        grid: {
          left: '3%',
          right: '7%',
          bottom: '3%',
          containLabel: true
        },
        tooltip: {
          // trigger: 'axis',
          showDelay: 0,
          formatter: function (params) {
            if (params.value.length > 1) {
              return params.seriesName + ' :<br/>'
                + params.value[0] + '点 '
                + params.value[1] + '点击量 ';
            }
            else {
              return params.seriesName + ' :<br/>'
                + params.name + ' : '
                + params.value + '点击量 ';
            }
          },
          axisPointer: {
            show: true,
            type: 'cross',
            lineStyle: {
              type: 'dashed',
              width: 1
            }
          }
        },

        xAxis: [
          {
            type: 'value',
            scale: true,
            axisLabel: {
              formatter: '{value} 点'
            },
            splitLine: {
              show: false
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            scale: true,
            axisLabel: {
              formatter: '{value} 点击量'
            },
            splitLine: {
              show: false
            }
          }
        ],
        series: [
          {
            name: '女性',
            type: 'scatter',
            itemStyle: { normal: { color: '#1bb2d8' } },
            data: [[161.2, 51.6], [167.5, 59.0], [159.5, 49.2], [157.0, 63.0], [155.8, 53.6],
            [170.0, 59.0], [159.1, 47.6], [166.0, 69.8], [176.2, 66.8], [160.2, 75.2],
            [172.5, 55.2], [170.9, 54.2], [172.9, 62.5], [153.4, 42.0], [160.0, 50.0],
            [147.2, 49.8], [168.2, 49.2], [175.0, 73.2], [157.0, 47.8], [167.6, 68.8],
            [159.5, 50.6], [175.0, 82.5], [166.8, 57.2], [176.5, 87.8], [170.2, 72.8],
            [174.0, 54.5], [173.0, 59.8], [179.9, 67.3], [170.5, 67.8], [160.0, 47.0],
            [154.4, 46.2], [162.0, 55.0], [176.5, 83.0], [160.0, 54.4], [152.0, 45.8],
            [162.1, 53.6], [170.0, 73.2], [160.2, 52.1], [161.3, 67.9], [166.4, 56.6],
            [168.9, 62.3], [163.8, 58.5], [167.6, 54.5], [160.0, 50.2], [161.3, 60.3],
            [167.6, 58.3], [165.1, 56.2], [160.0, 50.2], [170.0, 72.9], [157.5, 59.8],
            [167.6, 61.0], [160.7, 69.1], [163.2, 55.9], [152.4, 46.5], [157.5, 54.3],
            [168.3, 54.8], [180.3, 60.7], [165.5, 60.0], [165.0, 62.0], [164.5, 60.3],
            [156.0, 52.7], [160.0, 74.3], [163.0, 62.0], [165.7, 73.1], [161.0, 80.0],
            [162.0, 54.7], [166.0, 53.2], [174.0, 75.7], [172.7, 61.1], [167.6, 55.7],
            [151.1, 48.7], [164.5, 52.3], [163.5, 50.0], [152.0, 59.3], [169.0, 62.5],
            [164.0, 55.7], [161.2, 54.8], [155.0, 45.9], [170.0, 70.6], [176.2, 67.2],
            [170.0, 69.4], [162.5, 58.2], [170.3, 64.8], [164.1, 71.6], [169.5, 52.8],
            [163.2, 59.8], [154.5, 49.0], [159.8, 50.0], [173.2, 69.2], [170.0, 55.9],
            [161.4, 63.4], [169.0, 58.2], [166.2, 58.6], [159.4, 45.7], [162.5, 52.2],
            [159.0, 48.6], [162.8, 57.8], [159.0, 55.6], [179.8, 66.8], [162.9, 59.4],
            [161.0, 53.6], [151.1, 73.2], [168.2, 53.4], [168.9, 69.0], [173.2, 58.4],
            [171.8, 56.2], [178.0, 70.6], [164.3, 59.8], [163.0, 72.0], [168.5, 65.2],
            [166.8, 56.6], [172.7, 105.2], [163.5, 51.8], [169.4, 63.4], [167.8, 59.0],
            [159.5, 47.6], [167.6, 63.0], [161.2, 55.2], [160.0, 45.0], [163.2, 54.0],
            [162.2, 50.2], [161.3, 60.2], [149.5, 44.8], [157.5, 58.8], [163.2, 56.4],
            [172.7, 62.0], [155.0, 49.2], [156.5, 67.2], [164.0, 53.8], [160.9, 54.4],
            [162.8, 58.0], [167.0, 59.8], [160.0, 54.8], [160.0, 43.2], [168.9, 60.5],
            [158.2, 46.4], [156.0, 64.4], [160.0, 48.8], [167.1, 62.2], [158.0, 55.5],
            [167.6, 57.8], [156.0, 54.6], [162.1, 59.2], [173.4, 52.7], [159.8, 53.2],
            [170.5, 64.5], [159.2, 51.8], [157.5, 56.0], [161.3, 63.6], [162.6, 63.2],
            [160.0, 59.5], [168.9, 56.8], [165.1, 64.1], [162.6, 50.0], [165.1, 72.3],
            [166.4, 55.0], [160.0, 55.9], [152.4, 60.4], [170.2, 69.1], [162.6, 84.5],
            [170.2, 55.9], [158.8, 55.5], [172.7, 69.5], [167.6, 76.4], [162.6, 61.4],
            [167.6, 65.9], [156.2, 58.6], [175.2, 66.8], [172.1, 56.6], [162.6, 58.6],
            [160.0, 55.9], [165.1, 59.1], [182.9, 81.8], [166.4, 70.7], [165.1, 56.8],
            [177.8, 60.0], [165.1, 58.2], [175.3, 72.7], [154.9, 54.1], [158.8, 49.1],
            [172.7, 75.9], [168.9, 55.0], [161.3, 57.3], [167.6, 55.0], [165.1, 65.5],
            [175.3, 65.5], [157.5, 48.6], [163.8, 58.6], [167.6, 63.6], [165.1, 55.2],
            [165.1, 62.7], [168.9, 56.6], [162.6, 53.9], [164.5, 63.2], [176.5, 73.6],
            [168.9, 62.0], [175.3, 63.6], [159.4, 53.2], [160.0, 53.4], [170.2, 55.0],
            [162.6, 70.5], [167.6, 54.5], [162.6, 54.5], [160.7, 55.9], [160.0, 59.0],
            [157.5, 63.6], [162.6, 54.5], [152.4, 47.3], [170.2, 67.7], [165.1, 80.9],
            [172.7, 70.5], [165.1, 60.9], [170.2, 63.6], [170.2, 54.5], [170.2, 59.1],
            [161.3, 70.5], [167.6, 52.7], [167.6, 62.7], [165.1, 86.3], [162.6, 66.4],
            [152.4, 67.3], [168.9, 63.0], [170.2, 73.6], [175.2, 62.3], [175.2, 57.7],
            [160.0, 55.4], [165.1, 104.1], [174.0, 55.5], [170.2, 77.3], [160.0, 80.5],
            [167.6, 64.5], [167.6, 72.3], [167.6, 61.4], [154.9, 58.2], [162.6, 81.8],
            [175.3, 63.6], [171.4, 53.4], [157.5, 54.5], [165.1, 53.6], [160.0, 60.0],
            [174.0, 73.6], [162.6, 61.4], [174.0, 55.5], [162.6, 63.6], [161.3, 60.9],
            [156.2, 60.0], [149.9, 46.8], [169.5, 57.3], [160.0, 64.1], [175.3, 63.6],
            [169.5, 67.3], [160.0, 75.5], [172.7, 68.2], [162.6, 61.4], [157.5, 76.8],
            [176.5, 71.8], [164.4, 55.5], [160.7, 48.6], [174.0, 66.4], [163.8, 67.3]
            ],

            markPoint: {
              data: [
                { type: 'max', name: '最大值' },
                { type: 'min', name: '最小值' }
              ]
            },
            markLine: {

              data: [
                { type: 'average', name: '平均值' }
              ]
            }
          }
        ]
      };

      vm.yhfwsjfbChart.setOption(option)
    },
    // 访问来源
    loadfwlyLineChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.fwlyLineChart = echarts.init(vm.$refs.fwlyLineChart);

      var lineChart = chartData.lineChart
      var colors = ["#44cd8a", "#5eb2ec", "#b6a4dd"]
      var series = []
      for (let index = 0; index < lineChart.legendData.length; index++) {
        var _index = index > 2 ? 0 : index;
        series.push({
          name: lineChart.legendData[index],
          type: 'line',
          smooth: true,
          itemStyle: {
            normal: {
              color: colors[_index]
            }
          },
          data: lineChart.seriesData[index]
        })
      }

      // 绘制图表
      var option = {
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
          name: '次',
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#158bca'
            }
          }
        },
        series: series
      };

      vm.fwlyLineChart.setOption(option)
    },

    // 访问来源
    loadfwlyPieChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.fwlyPieChart = echarts.init(vm.$refs.fwlyPieChart);

      var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          top: 'middle',
          right: '5%',
          data: pieChart.legendData
        },
        series: [{
          name: '访问来源',
          type: 'pie',
          center: ['40%', '50%'],
          radius: ['40%', '60%'],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: false,
              position: 'center'
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: '30',
                fontWeight: 'bold'
              }
            }
          },
          labelLine: {
            normal: {
              show: false
            }
          },
          color: ['#44cd8a', '#5eb2ec', '#b6a2dc'],
          data: pieChart.seriesData
        }]
      };

      vm.fwlyPieChart.setOption(option)
    },
    // 地域分布
    loaddyfbChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.dyfbChart = echarts.init(vm.$refs.dyfbChart);


      // 绘制图表
      var option = {
        title: {
          text: '访问量：',
          left: '15px',
          top: '19px',
          textStyle: {
            fontSize: 12,
            fontWeight: 'normal'
          }

        },
        tooltip: {
          trigger: 'item'
        },
        visualMap: {
          orient: 'horizontal',
          inverse: 'true',
          min: 0,
          max: chartData.max,
          left: '65px',
          top: '15px',
          text: ['高', '低'], // 文本，默认为数值文本
          inRange: {
            color: ['#e0ffff', '#006edd']
          }
        },
        geo: {
          map: 'china',
          roam: true,
          label: {
            normal: {
              show: true,
              textStyle: {
                color: 'rgba(0,0,0,0.4)'
              }
            }
          },
          itemStyle: {
            normal: {
              borderColor: 'rgba(0, 0, 0, 0.2)'
            },
            emphasis: {
              areaColor: null,
              shadowOffsetX: 0,
              shadowOffsetY: 0,
              shadowBlur: 20,
              borderWidth: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        },
        series: [{
          name: '地域分布',
          type: 'map',
          geoIndex: 0,
          data: chartData.data
        }]
      };

      vm.dyfbChart.setOption(option)
    },
    // 页面流量排名
    loadymllpmChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.ymllpmChart = echarts.init(vm.$refs.ymllpmChart);

      var pvChartData = chartData.pvChart

      // 绘制图表
      var option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: function (params) {
            var name = params[0].name;
            var index = 50;

            if (name.length > index) {
              name = name.substring(0, index) + '<br>' + name.substring(index)
            }
            var color = params[0].color

            return name + '<br><span class="bar-trend-chart-tooltip-icon" style="background-color:' + color + '"></span>' + params[0].seriesName + ': ' + params[0].value
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
              color: '#158bca'
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
              color: '#158bca'
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
          data: pvChartData.yAxisData.reverse()
        },
        series: [{
          name: pvChartData.seriesName,
          type: 'bar',
          barWidth: '20px',
          itemStyle: {
            normal: {
              color: '#5eb2ed',
              barBorderRadius: 2
            }
          },
          data: pvChartData.seriesData.reverse()
        }]
      };

      vm.ymllpmChart.setOption(option)

      vm.ymllpmChart.on('click', function (obj) {
        openInNewTab(obj.name);
      })

    },
    // 页面跳出排名
    loadymtclpmChart(chartData) {
      var vm = this
      // 基于准备好的dom，初始化echarts实例
      vm.ymtclpmChart = echarts.init(vm.$refs.ymtclpmChart);

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
              color: '#158bca'
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
              color: '#158bca'
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
              color: '#44cd8a',
              barBorderRadius: 2
            }
          },
          data: existCountChart.seriesData.reverse()
        }]
      };

      vm.ymtclpmChart.setOption(option)

      vm.ymtclpmChart.on('click', function (obj) {
        openInNewTab(obj.name);
      })
    },

    handle_zhibiao_Click(tab, event) {
      var vm = this;

      var id = tab.name;

      vm.$nextTick(function () {
        // DOM 现在更新了
        // `this` 绑定到当前实例
        vm.loadTabContentChart(id, false)
      })

    },

    loadTabContentChart(id, reload = true) {
      var vm = this;
      var chartId = id + 'Chart';
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      var platform = vm.platformId;

      var startDate = vm.startDate,
        endDate = vm.endDate;

      if (reload || vm.indexList === null) {
        vm.loadingIndex = true
        baiduTrend({
          platform,
          startDate,
          endDate
        }).then(function (response) {
          vm.loadingIndex = false
          var chartData = response.data
          vm.indexList = chartData;
          loadTabChartOption(chartData)

        })
          .catch(function (error) {
            console.log(error);
          });

      } else {
        var chartData = vm.indexList
        loadTabChartOption(chartData)
      }

      function loadTabChartOption(chartData) {

        var xAxisData = chartData.xAxisData

        var loadingChartData = chartData[chartId]

        // 绘制图表
        var option = {
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: [loadingChartData.seriesName]
          },
          grid: {
            left: '3%',
            right: '3%',
            bottom: '10%',
            containLabel: true
          },
          xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            },
            data: xAxisData
          }],
          yAxis: [{
            name: loadingChartData.unit,
            type: 'value',
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            }
          }],
          series: [{
            name: loadingChartData.seriesName,
            type: 'line',
            smooth: true,
            itemStyle: {
              normal: {
                color: '#5eb2ed'
              }
            },
            areaStyle: {
              normal: {
                color: '#acd7f4'
              }
            },
            data: loadingChartData.seriesData
          }

          ]
        };
        vm[chartId].setOption(option)
      }
    }

  },
  mounted() {

    var vm = this;

    vm.loadAllData()

    window.addEventListener('resize', chartsResize, false);

    var resizeTimer;

    function chartsResize() {
      if (resizeTimer) {
        clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function () {
        var tabContentChart = `${vm.zhibiao_activeTabName}Chart`
        if (vm[tabContentChart]) {
          vm[tabContentChart].resize()
        }

        vm.ymllpmChart && vm.ymllpmChart.resize()
        vm.ymtclpmChart && vm.ymtclpmChart.resize()
        vm.dyfbChart && vm.dyfbChart.resize()
        vm.fwlyLineChart && vm.fwlyLineChart.resize()
        vm.fwlyPieChart && vm.fwlyPieChart.resize()
      }, 100);

    }
  },
  data() {
    return {
      loadingIndex: false,
      loadingYmllpmChart: false,
      loadingYmtclpmChart: false,
      loadingNewOldVisitor: false,
      loadingDyfbChart: false,
      loadingFwlyLineChart: false,
      loadingYhfwsjfbChart: false,
      indexList: null,
      platformId: '102000',
      startDate: new Date().format(),
      endDate: new Date().format(),
      zhibiao_activeTabName: 'pv',
      tableData: []
    }
  }
}

</script>

<style lang="scss">
.zhibiao-tooltip {
  width: 300px;
}

.zhibiao-activeTabName {
  position: relative;
  .el-tooltip {
    position: absolute;
    display: block;
    width: 17px;
    height: 17px;
    padding: 0 10px 0 5px;
    top: 22px;
    right: 30px;
    margin-top: -8px;
    cursor: pointer;
    background: url(../assets/images/icon-tooltip.png) no-repeat center;
  }
}

.new-old-visitor *,
.new-old-visitor *:before,
.new-old-visitor *:after {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

.new-old-visitor {
  text-align: center;
  li {
    line-height: 56px;
    list-style: none;
  }
  li+li {
    border-left: 1px solid #d9d9d9;
  }
  .label {
    vertical-align: middle;
    color: #666666;
  }
  .new-percentage {
    vertical-align: middle;
    font-size: 30px;
    color: #23ad44;
  }
  .old-percentage {
    vertical-align: middle;
    font-size: 30px;
    color: #0588e6;
  }
}

.new-old-visitor-detail {
  padding: 0 20px 30px;
}

.bar-trend-chart-tooltip-icon {
  display: inline-block;
  margin-right: 5px;
  border-radius: 10px;
  width: 9px;
  height: 9px;
}

.chart.yhfwsjfb {
  height: 400px;
}
</style>
