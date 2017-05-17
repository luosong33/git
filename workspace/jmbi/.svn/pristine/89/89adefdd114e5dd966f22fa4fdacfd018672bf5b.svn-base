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
          <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">2016年3月平台运营情况表
  
          </header>
  
          <div class="box " v-loading="loadingdealOperate" element-loading-text="加载中...">
            <div class="content">
              <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="title" label="订单">
                </el-table-column>
                <el-table-column prop="monthAmount" label="3月">
                </el-table-column>
                <el-table-column prop="yearAmount" label="本年累计">
                </el-table-column>
  
              </el-table>
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 平台运营情况表-->
  
    <!--<el-row class="jm-grid-border-1px">
                                                              <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">地域成交金额分析
                                                                <el-tooltip class="item" content="网站访客来自哪里，构成比例。" placement="top-end">
                                                                  <span class="jm-tooltip-icon"></span>
                                                                </el-tooltip>
                                                              </header>
                                                          
                                                              <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                                <div class="grid-content jm-grid-box-bg-white">
                                                          
                                                                  <div class="box chart row-group-last" v-loading="loadingTODOChart" element-loading-text="加载中...">
                                                                    <div class="content" ref='areaDealingMapChart'>
                                                          
                                                                    </div>
                                                          
                                                                  </div>
                                                                </div>
                                                              </el-col>
                                                              <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                                <div class="grid-content jm-grid-box-bg-white ">
                                                          
                                                                  <div class="box  chart row-group-last" v-loading="loadingTODOChart" element-loading-text="加载中...">
                                                                    <div class="content" ref='threeCertAuditBarChart'>
                                                          
                                                                    </div>
                                                                  </div>
                                                          
                                                                </div>
                                                              </el-col>
                                                            </el-row>-->
    <!--end of 地域成交金额分析-->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">交易转化跟踪
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealTranslateChartText === '暂无数据'}" v-loading="loadingdealTranslateChart" :element-loading-text="loadingdealTranslateChartText">
            <div class="content" ref='tradeTrackChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">付款天数用户占比
  
          </header>
  
          <div class="box sixteen-nine no-data" v-loading="true" element-loading-text="暂无数据">
            <div class="content" ref='payedDaysUserChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 交易转化跟踪 & 付款天数用户占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">提货方式用户占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealPickupChartText === '暂无数据'}" v-loading="loadingdealPickupChart" :element-loading-text="loadingdealPickupChartText">
            <div class="content" ref='pickUpGoodsChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">交货时间占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdeliverGoodsChartText === '暂无数据'}" v-loading="loadingdeliverGoodsChart" :element-loading-text="loadingdeliverGoodsChartText">
            <div class="content" ref='deliverGoodsChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 交易转化跟踪 & 付款天数用户占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">确认收货货时间
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingconfirmReceivingGoodsText === '暂无数据'}" v-loading="loadingconfirmReceivingGoods" :element-loading-text="loadingconfirmReceivingGoodsText">
            <div class="content" ref='confirmReceivingGoodsChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">结算方式占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealSettleText === '暂无数据'}" v-loading="loadingdealSettle" :element-loading-text="loadingdealSettleText">
            <div class="content" ref='settlementMethodChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 确认收货货时间 & 结算方式占比 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">支付方式
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealPayText === '暂无数据'}" v-loading="loadingdealPay" :element-loading-text="loadingdealPayText">
            <div class="content" ref='methodOfPayChart'>
  
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">合同方式
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealContractText === '暂无数据'}" v-loading="loadingdealContract" :element-loading-text="loadingdealContractText">
            <div class="content" ref='formOfTreatyChart'>
  
            </div>
          </div>
  
        </div>
      </el-col>
    </el-row>
    <!-- end of 支付方式 & 合同方式 -->
  
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
          <header class="panel-header jm-grid-border-bottom-1px">支付渠道金额占比
  
          </header>
  
          <div class="box sixteen-nine" :class="{'no-data': loadingdealPayChannelText === '暂无数据'}" v-loading="loadingdealPayChannel" :element-loading-text="loadingdealPayChannelText">
            <div class="content" ref='channelOfDisbursementChart'>
  
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
  dealOperate,
  dealTranslate,
  dealPickup,
  dealDelivery,
  dealReceipt,
  dealSettle,
  dealPay,
  dealContract,
  dealPayChannel
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
      this.loadAllData();
    },
    loadAllData() {
      var vm = this;
      vm.loadTableData();

      var platform = vm.platformId,
        startDate = vm.startDate,
        endDate = vm.endDate;

      vm.loadingdealTranslateChart = true
      vm.loadingdealTranslateChartText = '加载中...'
      dealTranslate({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealTranslateChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealTranslateChart = true
          vm.loadingdealTranslateChartText = '暂无数据'
        }
        vm.loadtradeTrackChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingdealPickupChart = true
      vm.loadingdealPickupChartText = '加载中...'
      dealPickup({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealPickupChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealPickupChart = true
          vm.loadingdealPickupChartText = '暂无数据'
        }
        vm.loadpickUpGoodsChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingdeliverGoodsChart = true
      vm.loadingdeliverGoodsChartText = '加载中...'
      dealDelivery({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdeliverGoodsChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdeliverGoodsChart = true
          vm.loadingdeliverGoodsChartText = '暂无数据'
        }
        vm.loaddeliverGoodsChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingconfirmReceivingGoods = true
      vm.loadingconfirmReceivingGoodsText = '加载中...'
      dealReceipt({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingconfirmReceivingGoods = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingconfirmReceivingGoods = true
          vm.loadingconfirmReceivingGoodsText = '暂无数据'
        }
        vm.loadconfirmReceivingGoodsChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingdealSettle = true
      vm.loadingdealSettleText = '加载中...'
      dealSettle({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealSettle = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealSettle = true
          vm.loadingdealSettleText = '暂无数据'
        }
        vm.loadsettlementMethodChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


      vm.loadingdealPay = true
      vm.loadingdealPayText = '加载中...'
      dealPay({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealPay = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealPay = true
          vm.loadingdealPayText = '暂无数据'
        }
        vm.loadmethodOfPayChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingdealContract = true
      vm.loadingdealContractText = '加载中...'
      dealContract({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealContract = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealContract = true
          vm.loadingdealContractText = '暂无数据'
        }
        vm.loadformOfTreatyChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      vm.loadingdealPayChannel = true
      vm.loadingdealPayChannelText = '加载中...'
      dealPayChannel({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealPayChannel = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingdealPayChannel = true
          vm.loadingdealPayChannelText = '暂无数据'
        }
        vm.loadchannelOfDisbursementChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });


    },
    loadTableData() {
      var vm = this;
      var platform = vm.platformId;
      var startDate = this.startDate;
      var endDate = this.endDate;

      vm.loadingdealOperate = true

      dealOperate({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingdealOperate = false
        var data = response.data
        var monthData = data[0]
        var yearData = data[1]
        var tableData = [{
          title: '订单量',
          monthAmount: monthData.order_count,
          yearAmount: yearData.order_count
        }, {
          title: '金额（亿元）',
          monthAmount: monthData.order_money,
          yearAmount: yearData.order_money
        },
        {
          title: '支付笔数',
          monthAmount: monthData.payment_count,
          yearAmount: yearData.payment_count
        },
        {
          title: '支付金额',
          monthAmount: monthData.payment_money,
          yearAmount: yearData.payment_money
        }];
        vm.tableData = tableData;



      })
        .catch(function (error) {
          console.log(error);
        });
    },
    loadtradeTrackChart(chartData) {
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
          formatter: "{a} <br/>{b} : {c}"
        },

        legend: {
          top: 5,
          data: chartData.legendData//['展现', '点击', '访问', '咨询', '订单']
        },
        calculable: true,
        series: [
          {
            name: chartData.seriesName,
            type: 'funnel',
            left: '10%',
            top: 60,
            //x2: 80,
            bottom: 60,
            width: '80%',
            // height: {totalHeight} - y - y2,
            // min: 0,
            // max: 100,
            // minSize: '0%',
            // maxSize: '100%',
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
            data: chartData.seriesData
            // [
            //   { value: 60, name: '访问' },
            //   { value: 40, name: '咨询' },
            //   { value: 20, name: '订单' },
            //   { value: 80, name: '点击' },
            //   { value: 100, name: '展现' }
            // ]
          }
        ]
      };

      vm[chartId].setOption(option)

      // vm.relativeCompanyChart.on('click', function (obj) {
      //   openInNewTab(obj.name);
      // })

    },

    // 三证审核地域分布
    // loadareaDealingMapChart(chartData) {
    //   var vm = this;
    //   // 基于准备好的dom，初始化echarts实例
    //   vm.areaDealingMapChart = echarts.init(vm.$refs.areaDealingMapChart);

    //   function randomData() {
    //     return Math.round(Math.random() * 1000);
    //   }
    //   // 绘制图表
    //   var option = {
    //     title: {
    //       text: 'iphone销量',
    //       subtext: '纯属虚构',
    //       left: 'center'
    //     },
    //     tooltip: {
    //       trigger: 'item'
    //     },
    //     legend: {
    //       show: false,
    //       orient: 'vertical',
    //       left: 'left',
    //       data: ['iphone3', 'iphone4', 'iphone5']
    //     },
    //     visualMap: {
    //       min: 0,
    //       max: 2500,
    //       left: 'left',
    //       top: 'bottom',
    //       text: ['高', '低'],           // 文本，默认为数值文本
    //       inRange: {
    //         color: ['#e0ffff', '#006edd']
    //       },
    //       calculable: true
    //     },

    //     series: [

    //       {
    //         name: 'iphone5',
    //         type: 'map',
    //         mapType: 'china',
    //         label: {
    //           normal: {
    //             show: true
    //           },
    //           emphasis: {
    //             show: true
    //           }
    //         },
    //         data: [
    //           { name: '北京', value: randomData() },
    //           { name: '天津', value: randomData() },
    //           { name: '上海', value: randomData() },
    //           { name: '广东', value: randomData() },
    //           { name: '台湾', value: randomData() },
    //           { name: '香港', value: randomData() },
    //           { name: '澳门', value: randomData() }
    //         ]
    //       }
    //     ]
    //   };

    //   vm.areaDealingMapChart.setOption(option)
    // },

    // 三证审核地域分布
    // loadthreeCertAuditBarChart(chartData) {
    //   var vm = this;
    //   // 基于准备好的dom，初始化echarts实例
    //   vm.threeCertAuditBarChart = echarts.init(vm.$refs.threeCertAuditBarChart);

    //   var existCountChart = chartData.existCountChart
    //   // 绘制图表
    //   var option = {
    //     tooltip: {
    //       trigger: 'axis',
    //       axisPointer: {
    //         type: 'shadow'
    //       }
    //     },
    //     grid: {
    //       top: '8%',
    //       left: '5%',
    //       right: '5%',
    //       bottom: '8%',
    //       containLabel: true
    //     },
    //     xAxis: {
    //       name: '次',
    //       type: 'value',
    //       boundaryGap: false,
    //       axisLine: {
    //         lineStyle: {
    //           color: '#008acd'
    //         }
    //       }
    //     },
    //     yAxis: {
    //       type: 'category',
    //       splitLine: {
    //         show: true
    //       },
    //       axisLine: {
    //         lineStyle: {
    //           color: '#008acd'
    //         }
    //       },
    //       axisLabel: {
    //         formatter: function (value, index) {
    //           var value = value || ''
    //           var maxLength = 15
    //           if (value.length > maxLength) {
    //             value = value.substring(0, maxLength) + '...';
    //           }
    //           return value;
    //         },
    //         rotate: 20
    //       },
    //       data: existCountChart.yAxisData.reverse()
    //     },
    //     series: [{
    //       name: existCountChart.seriesName,
    //       type: 'bar',
    //       barWidth: '20px',
    //       itemStyle: {
    //         normal: {
    //           color: '#1790cf',
    //           barBorderRadius: 2
    //         }
    //       },
    //       data: existCountChart.seriesData.reverse()
    //     }]
    //   };

    //   vm.threeCertAuditBarChart.setOption(option)
    // },

    // 付款天数用户占比
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
        // title: {
        //   text: chartData.title,
        //   left: 'center',
        //   bottom: '5%',
        //   textStyle: {
        //     color: '#666',
        //     fontWeight: 'normal',
        //     fontSize: 14
        //   }
        // },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
          name: chartData.seriesName,
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data: chartData.seriesData,

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

    // 提货方式用户占比
    loadpickUpGoodsChart(chartData) {
      var vm = this
      // 基于准备好的dom，初始化echarts实例
      vm.pickUpGoodsChart = echarts.init(vm.$refs.pickUpGoodsChart);

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
          data: chartData.legendData//['直接访问', '邮件营销']
        },
        series: [{
          name: chartData.seriesName,
          type: 'pie',
          radius: '50%',
          center: ['50%', '50%'],
          data: chartData.seriesData,
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
          name: chartData.seriesName,
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data: chartData.seriesData,
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
          name: chartData.seriesName,
          type: 'pie',
          radius: ['40%', '60%'],
          center: ['50%', '50%'],
          data: chartData.seriesData,
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
          name: chartData.seriesName,
          type: 'pie',
          radius: ['40%', '60%'],
          center: ['50%', '50%'],
          data: chartData.seriesData,
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
          name: chartData.seriesName,
          type: 'pie',
          radius: ['40%', '60%'],
          center: ['50%', '50%'],
          data: chartData.seriesData,
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
          name: chartData.seriesName,
          type: 'pie',
          radius: ['40%', '60%'],
          center: ['50%', '50%'],
          data: chartData.seriesData,
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

    vm.loadAllData();




    // vm.loadpayedDaysUserChart();















  },
  data() {
    return {
      platformId: '100700',
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingdealTranslateChart: false,
      loadingdealTranslateChartText: '加载中...',
      loadingdealPickupChart: false,
      loadingdealPickupChartText: '加载中...',
      loadingdeliverGoodsChart: false,
      loadingdeliverGoodsChartText: '加载中...',
      loadingconfirmReceivingGoods: false,
      loadingconfirmReceivingGoodsText: '加载中...',
      loadingdealSettle: false,
      loadingdealSettleText: '加载中...',
      loadingdealPay: false,
      loadingdealPayText: '加载中...',
      loadingdealContract: false,
      loadingdealContractText: '加载中...',
      loadingdealPayChannel: false,
      loadingdealPayChannelText: '加载中...',
      loadingdealOperate: false,

      tableData: [
      ]
    }
  }
}


</script>

<style>

</style>
