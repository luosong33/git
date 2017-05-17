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
      <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">开通店铺趋势图
  
      </header>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingShopPublishChartText === '暂无数据'}" v-loading="loadingShopPublishChart" :element-loading-text="loadingShopPublishChartText">
            <div class="content" ref='shopPublishChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingShopClassifyChartText === '暂无数据'}" v-loading="loadingShopClassifyChart" :element-loading-text="loadingShopClassifyChartText">
            <div class="content" ref='shopClassifyChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="grid-content jm-grid-box-bg-white">
  
          <div class="box chart row-group-last" :class="{'no-data': loadingShopTradeChartText === '暂无数据'}" v-loading="loadingShopTradeChart" :element-loading-text="loadingShopTradeChartText">
            <div class="content" ref='shopTradeChart'>
  
            </div>
  
          </div>
        </div>
      </el-col>
  
    </el-row>
    <!--end of 开通店铺趋势图-->
  
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px shop-tab-list">
          <el-tabs v-model="activeName">
            <el-tab-pane label="商品发布排行" name="first">
              <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="order" label="排名" width="180">
                </el-table-column>
                <el-table-column prop="shopName" label="店铺名称" width="180">
                </el-table-column>
                <el-table-column prop="companyProperty" label="公司性质">
                </el-table-column>
                <el-table-column prop="goodsAmount" label="发布商品数量">
                </el-table-column>
                <el-table-column prop="goodsName" label="商品名称">
                </el-table-column>
  
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="店铺分类排行" name="second">
              <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="order" label="排名" width="180">
                </el-table-column>
                <el-table-column prop="shopName" label="店铺名称" width="180">
                </el-table-column>
                <el-table-column prop="companyProperty" label="公司性质">
                </el-table-column>
                <el-table-column prop="goodsAmount" label="发布商品数量">
                </el-table-column>
                <el-table-column prop="goodsName" label="商品名称">
                </el-table-column>
  
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="店铺成交排行" name="third">
              <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="order" label="排名" width="180">
                </el-table-column>
                <el-table-column prop="shopName" label="店铺名称" width="180">
                </el-table-column>
                <el-table-column prop="companyProperty" label="公司性质">
                </el-table-column>
                <el-table-column prop="goodsAmount" label="发布商品数量">
                </el-table-column>
                <el-table-column prop="goodsName" label="商品名称">
                </el-table-column>
  
              </el-table>
            </el-tab-pane>
  
          </el-tabs>
  
        </div>
      </el-col>
  
    </el-row>
    <!-- end of 基础资料完善统计 & 基础资料完善占比 -->
  
  </div>
</template>

<script>
import {
  shopReleaseRank,
  shopCategoryRank,
  shopDealRank
} from '@/service/api'

import rangeDatePicker from '@/components/RangeDatePicker'


import echarts from 'echarts';


export default {
  name: 'shopOperate',
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

      // 商品发布数排行
      vm.loadingShopPublishChart = true
      vm.loadingShopPublishChartText = '加载中...'
      shopReleaseRank({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingShopPublishChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingShopPublishChart = true
          vm.loadingShopPublishChartText = '暂无数据'
        }
        vm.loadshopPublishChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      // 店铺分类排行
      vm.loadingShopClassifyChart = true
      vm.loadingShopClassifyChartText = '加载中...'
      shopCategoryRank({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingShopClassifyChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingShopClassifyChart = true
          vm.loadingShopClassifyChartText = '暂无数据'
        }

        vm.loadshopClassifyChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });

      // 店铺分类排行
      vm.loadingShopTradeChart = true
      vm.loadingShopTradeChartText = '加载中...'
      shopDealRank({
        platform,
        startDate,
        endDate
      }).then(function (response) {
        vm.loadingShopTradeChart = false
        var chartData = response.data

        if (chartData.seriesData.length === 0) {
          vm.loadingShopTradeChart = true
          vm.loadingShopTradeChartText = '暂无数据'
        }

        vm.loadshopTradeChart(chartData)

      })
        .catch(function (error) {
          console.log(error);
        });



    },
    loadshopPublishChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.shopPublishChart = echarts.init(vm.$refs.shopPublishChart);


      // 绘制图表
      var option = {
        title: {
          text: "商品发布数排行",
          left: "center",
          top: '3%',
          textStyle: {
            fontWeight: 'normal',
            fontSize: 16
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
          right: '10%',
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
            name: '2011年',
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

      vm.shopPublishChart.setOption(option)
    },

    loadshopClassifyChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.shopClassifyChart = echarts.init(vm.$refs.shopClassifyChart);


      // 绘制图表
      var option = {
        title: {
          text: "店铺分类排行",
          left: "center",
          top: '3%',
          textStyle: {
            fontWeight: 'normal',
            fontSize: 16
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
          right: '10%',
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
            name: '2011年',
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

      vm.shopClassifyChart.setOption(option)
    },

    loadshopTradeChart(chartData) {
      var vm = this;
      // 基于准备好的dom，初始化echarts实例
      vm.shopTradeChart = echarts.init(vm.$refs.shopTradeChart);

      // 绘制图表
      var option = {
        title: {
          text: "店铺成交排行",
          left: "center",
          top: '3%',
          textStyle: {
            fontWeight: 'normal',
            fontSize: 16
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
          right: '10%',
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
            name: '2011年',
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

      vm.shopTradeChart.setOption(option)
    },

  },

  mounted() {
    var vm = this;

    vm.loadAllData();



  },
  data() {
    return {
      startDate: new Date().format(),
      endDate: new Date().format(),
      loadingShopPublishChart: false,
      loadingShopPublishChartText: '加载中...',
      loadingShopClassifyChart: false,
      loadingShopClassifyChartText: '加载中...',
      loadingShopTradeChart: false,
      loadingShopTradeChartText: '加载中...',
      loadingTODOChart: false,
      activeName: 'second',
      tableData: [
        {
          order: '1',
          shopName: '精密工业',
          companyProperty: '贸易商',
          goodsAmount: '13',
          goodsName: 'pvc、pp、ldpe'
        },
        {
          order: '2',
          shopName: '精密工业',
          companyProperty: '贸易商',
          goodsAmount: '13',
          goodsName: 'pvc、pp、ldpe'
        }, {
          order: '3',
          shopName: '精密工业',
          companyProperty: '贸易商',
          goodsAmount: '13',
          goodsName: 'pvc、pp、ldpe'
        }, {
          order: '4',
          shopName: '精密工业',
          companyProperty: '贸易商',
          goodsAmount: '13',
          goodsName: 'pvc、pp、ldpe'
        },]
    }
  }
}


</script>

<style>
.shop-tab-list .el-tabs__header {
  margin: 0;
}
</style>
