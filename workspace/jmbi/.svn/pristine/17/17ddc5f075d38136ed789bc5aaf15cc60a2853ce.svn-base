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
  
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="24">
        <div class="grid-content jm-grid-box-bg-white">
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">2016年3月平台运营情况表
            <el-tooltip class="item"
                        content="网站访客来自哪里，构成比例。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box "
               v-loading="loadingTODOChart"
               element-loading-text="加载中...">
            <div class="content">
              <el-table :data="tableData"
                        border
                        style="width: 100%">
                <el-table-column prop="order"
                                 label="订单">
                </el-table-column>
                <el-table-column prop="month"
                                 label="3月">
                </el-table-column>
                <el-table-column prop="companyProperty"
                                 label="环比">
                </el-table-column>
                <el-table-column prop="goodsAmount"
                                 label="本年累计">
                </el-table-column>
  
              </el-table>
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 开通店铺趋势图-->
  
    <el-row class="jm-grid-border-1px">
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">地域成交金额分析
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
                 ref='threeCertAuditMapChart'>
  
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
                 ref='threeCertAuditBarChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!--end of 新增注册数-->
  
    <el-row :gutter="20">
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">交易转化跟踪
            <el-tooltip class="item"
                        content="统计本网站流量次数TOP5页面。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine"
               v-loading="loadingTODO"
               element-loading-text="加载中...">
            <div class="content"
                 ref='tradeTrackChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">付款天数用户占比
            <el-tooltip class="item"
                        content="统计本网站页面跳出TOP5页面。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine"
               v-loading="loadingTODO"
               element-loading-text="加载中...">
            <div class="content"
                 ref='payedDaysUserChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 交易转化跟踪 & 付款天数用户占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">提货方式用户占比
            <el-tooltip class="item"
                        content="统计本网站流量次数TOP5页面。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine"
               v-loading="loadingTODO"
               element-loading-text="加载中...">
            <div class="content"
                 ref='pickUpGoodsChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">交货时间占比
            <el-tooltip class="item"
                        content="统计本网站页面跳出TOP5页面。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine"
               v-loading="loadingTODO"
               element-loading-text="加载中...">
            <div class="content"
                 ref='deliverGoodsChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 交易转化跟踪 & 付款天数用户占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">确认收货货时间
            <el-tooltip class="item"
                        content="统计本网站流量次数TOP5页面。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine"
               v-loading="loadingTODO"
               element-loading-text="加载中...">
            <div class="content"
                 ref='confirmReceivingGoodsChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">结算方式占比
            <el-tooltip class="item"
                        content="统计本网站页面跳出TOP5页面。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine"
               v-loading="loadingTODO"
               element-loading-text="加载中...">
            <div class="content"
                 ref='settlementMethodChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 确认收货货时间 & 结算方式占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">支付方式
            <el-tooltip class="item"
                        content="统计本网站流量次数TOP5页面。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine"
               v-loading="loadingTODO"
               element-loading-text="加载中...">
            <div class="content"
                 ref='methodOfPayChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">合同方式
            <el-tooltip class="item"
                        content="统计本网站页面跳出TOP5页面。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine"
               v-loading="loadingTODO"
               element-loading-text="加载中...">
            <div class="content"
                 ref='formOfTreatyChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 支付方式 & 合同方式 -->
  
    <el-row :gutter="20">
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">支付渠道金额占比
            <el-tooltip class="item"
                        content="统计本网站流量次数TOP5页面。"
                        placement="top-end">
              <span class="jm-tooltip-icon"></span>
            </el-tooltip>
          </header>
  
          <div class="box sixteen-nine"
               v-loading="loadingTODO"
               element-loading-text="加载中...">
            <div class="content"
                 ref='channelOfDisbursementChart'>
  
            </div>
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!-- end of 支付渠道金额占比 -->
  
  </div>
</template>

<script>

import {
  getSources,
  getCompanyNum,
  getCompanyTopAmount,
  pageVisit
} from '@/service/api'

import rangeDatePicker from '@/components/RangeDatePicker2'

import echarts from 'echarts';
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
    loadtradeTrackChart() {
      var vm = this;

      var chartId = 'tradeTrackChart'
      // 基于准备好的dom，初始化echarts实例
      vm[chartId] = echarts.init(vm.$refs[chartId]);

      //var pvChartData = chartData.pvChart

      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c}%"
        },

        legend: {
          data: ['展现', '点击', '访问', '咨询', '订单']
        },
        calculable: true,
        series: [
          {
            name: '漏斗图',
            type: 'funnel',
            left: '10%',
            top: 60,
            //x2: 80,
            bottom: 60,
            width: '80%',
            // height: {totalHeight} - y - y2,
            min: 0,
            max: 100,
            minSize: '0%',
            maxSize: '100%',
            sort: 'descending',
            gap: 2,
            label: {
              normal: {
                show: true,
                position: 'inside'
              },
              emphasis: {
                textStyle: {
                  fontSize: 20
                }
              }
            },
            labelLine: {
              normal: {
                length: 10,
                lineStyle: {
                  width: 1,
                  type: 'solid'
                }
              }
            },
            itemStyle: {
              normal: {
                borderColor: '#fff',
                borderWidth: 1
              }
            },
            data: [
              { value: 60, name: '访问' },
              { value: 40, name: '咨询' },
              { value: 20, name: '订单' },
              { value: 80, name: '点击' },
              { value: 100, name: '展现' }
            ]
          }
        ]
      };

      vm[chartId].setOption(option)

      // vm.relativeCompanyChart.on('click', function (obj) {
      //   openInNewTab(obj.name);
      // })

    },

    // 三证审核地域分布
    loadthreeCertAuditMapChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.threeCertAuditMapChart = echarts.init(vm.$refs.threeCertAuditMapChart);

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

      vm.threeCertAuditMapChart.setOption(option)
    },

    // 三证审核地域分布
    loadthreeCertAuditBarChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.threeCertAuditBarChart = echarts.init(vm.$refs.threeCertAuditBarChart);

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

      vm.threeCertAuditBarChart.setOption(option)
    },

    // 用户登录情况
    loadpayedDaysUserChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.payedDaysUserChart = echarts.init(vm.$refs.payedDaysUserChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
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
          radius: '55%',
          center: ['50%', '50%'],
          //data: chartData.seriesData,
          data: [
            { value: 335, name: '直接访问' },
            { value: 310, name: '邮件营销' },
            { value: 234, name: '联盟广告' },
            { value: 135, name: '视频广告' },
            { value: 1548, name: '搜索引擎' }
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.payedDaysUserChart.setOption(option)
    },

    loaddeliverGoodsChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.deliverGoodsChart = echarts.init(vm.$refs.deliverGoodsChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
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
          radius: '55%',
          center: ['50%', '50%'],
          //data: chartData.seriesData,
          data: [
            { value: 335, name: '直接访问' },
            { value: 310, name: '邮件营销' },
            { value: 234, name: '联盟广告' },
            { value: 135, name: '视频广告' },
            { value: 1548, name: '搜索引擎' }
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.deliverGoodsChart.setOption(option)
    },

    loadpickUpGoodsChart(chartData) {
      var vm = this
      // 基于准备好的dom，初始化echarts实例
      vm.pickUpGoodsChart = echarts.init(vm.$refs.pickUpGoodsChart);

      //var existCountChart = chartData.existCountChart
      //console.log(existCountChart)
      // 绘制图表
      var option = {
        color: ['#1bb2d8', '#1790cf', '#fcb738', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
        // title: {
        //   text: '三证合一比例',
        //   textStyle: {
        //     fontSize: 16,
        //     fontWeight: 'normal'
        //   },
        //   left: 10,
        //   top: 10

        // },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          left: 'center',
          top: '10%',
          data: ['直接访问', '邮件营销']
        },
        series: [{
          //name: chartData.seriesName,
          type: 'pie',
          radius: '50%',
          center: ['50%', '50%'],
          //data: chartData.seriesData,
          data: [
            { value: 335, name: '直接访问' },
            { value: 310, name: '邮件营销' }

          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.pickUpGoodsChart.setOption(option)


    },

    loadconfirmReceivingGoodsChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.confirmReceivingGoodsChart = echarts.init(vm.$refs.confirmReceivingGoodsChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
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
          radius: '55%',
          center: ['50%', '50%'],
          //data: chartData.seriesData,
          data: [
            { value: 335, name: '直接访问' },
            { value: 310, name: '邮件营销' },
            { value: 234, name: '联盟广告' },
            { value: 135, name: '视频广告' },
            { value: 1548, name: '搜索引擎' }
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.confirmReceivingGoodsChart.setOption(option)
    },

    loadsettlementMethodChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.settlementMethodChart = echarts.init(vm.$refs.settlementMethodChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
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
          radius: ['40%', '60%'],
          center: ['50%', '50%'],
          //data: chartData.seriesData,
          data: [
            { value: 335, name: '直接访问' },
            { value: 310, name: '邮件营销' },
            { value: 234, name: '联盟广告' },
            { value: 135, name: '视频广告' },
            { value: 1548, name: '搜索引擎' }
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.settlementMethodChart.setOption(option)
    },

    loadmethodOfPayChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.methodOfPayChart = echarts.init(vm.$refs.methodOfPayChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
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
          radius: ['40%', '60%'],
          center: ['50%', '50%'],
          //data: chartData.seriesData,
          data: [
            { value: 335, name: '直接访问' },
            { value: 310, name: '邮件营销' },
            { value: 234, name: '联盟广告' },
            { value: 135, name: '视频广告' },
            { value: 1548, name: '搜索引擎' }
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.methodOfPayChart.setOption(option)
    },

    loadformOfTreatyChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.formOfTreatyChart = echarts.init(vm.$refs.formOfTreatyChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
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
          radius: ['40%', '60%'],
          center: ['50%', '50%'],
          //data: chartData.seriesData,
          data: [
            { value: 335, name: '直接访问' },
            { value: 310, name: '邮件营销' },
            { value: 234, name: '联盟广告' },
            { value: 135, name: '视频广告' },
            { value: 1548, name: '搜索引擎' }
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.formOfTreatyChart.setOption(option)
    },

    loadchannelOfDisbursementChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.channelOfDisbursementChart = echarts.init(vm.$refs.channelOfDisbursementChart);
      // console.log('用户登录情况', chartData)
      // var pieChart = chartData.pieChart
      // 绘制图表
      var option = {
        color: ['#1c7099', '#1790cf', '#1bb2d8', '#99d2dd', '#88b0bb', '#22b1e6', '#4598ff'],
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
          radius: ['40%', '60%'],
          center: ['50%', '50%'],
          //data: chartData.seriesData,
          data: [
            { value: 335, name: '直接访问' },
            { value: 310, name: '邮件营销' },
            { value: 234, name: '联盟广告' },
            { value: 135, name: '视频广告' },
            { value: 1548, name: '搜索引擎' }
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };

      vm.channelOfDisbursementChart.setOption(option)
    },
  },

  mounted() {
    var vm = this;

    vm.loadthreeCertAuditMapChart();
    var platform = '',
      startDate = '',
      endDate = '';
    pageVisit({
      platform,
      startDate,
      endDate
    }).then(function (response) {

      //  vm.loadingregisterUserAreaBarChart = false
      var chartData = response.data
      console.log(chartData)
      vm.loadthreeCertAuditBarChart(chartData)
    })
      .catch(function (error) {
        console.log(error);
      });

    vm.loadtradeTrackChart();

    vm.loadpayedDaysUserChart();

    vm.loaddeliverGoodsChart();

    vm.loadpickUpGoodsChart();

    vm.loadconfirmReceivingGoodsChart()

    vm.loadsettlementMethodChart();

    vm.loadmethodOfPayChart();

    vm.loadformOfTreatyChart();

    vm.loadchannelOfDisbursementChart();

  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingTODO: false,
      loadingTODOChart: false,
      tableData: [
        {
          order: '1',
          month: '精密工业',
          companyProperty: '贸易商',
          goodsAmount: '13'
        },
        {
          order: '2',
          month: '精密工业',
          companyProperty: '贸易商',
          goodsAmount: '13'
        }, {
          order: '3',
          month: '精密工业',
          companyProperty: '贸易商',
          goodsAmount: '13'
        }, {
          order: '4',
          month: '精密工业',
          companyProperty: '贸易商',
          goodsAmount: '13'
        },]
    }
  }
}


</script>

<style>

</style>
