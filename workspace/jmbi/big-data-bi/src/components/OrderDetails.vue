<template>
<!--dialog start -->
<section class="jm-dialog-container order-area-chart-dialog">
  <el-dialog class="order-area-order-analysis-dialog" :title="dialogTitle" v-model="showMe" v-on:close="closeMe">
    <div class="order-search-basic-bar jm-text-align-right no-selection-text" v-if="!advanceSearch">
      <span class="button" v-bind:class="{ 'jm--color-primary': currentSelectedPeriod === 'week' }" @click="selectPeriod('week')">本周</span>
      <span class="button" v-bind:class="{ 'jm--color-primary': currentSelectedPeriod === 'month' }" @click="selectPeriod('month')">本月</span>
      <span class="button" v-bind:class="{ 'jm--color-primary': currentSelectedPeriod === 'quarter' }" @click="selectPeriod('quarter')">本季</span>
      <span class="button" v-bind:class="{ 'jm--color-primary': currentSelectedPeriod === 'year' }" @click="selectPeriod('year')">本年</span>
      <span class="button" @click="advanceSearch = true">高级检索<i class="el-icon-search"></i></span>
    </div>
    <div class="order-advance-search-bar" v-if="advanceSearch">

      <div class="order-date-type-btn-list no-selection-text">
        <span class="icon-date-type icon-week" v-bind:class="{ 'is-active': currentSelectedPeriod === 'week' }" @click="selectPeriod('week')"></span>
        <span class="icon-date-type icon-month " v-bind:class="{ 'is-active': currentSelectedPeriod === 'month' }" @click="selectPeriod('month')"></span>
        <span class="icon-date-type icon-quarter " v-bind:class="{ 'is-active': currentSelectedPeriod === 'quarter' }" @click="selectPeriod('quarter')"></span>
        <span class="icon-date-type icon-year " v-bind:class="{ 'is-active': currentSelectedPeriod === 'year' }" @click="selectPeriod('year')"></span>
      </div>

      <div class="order-date-picker-range-list">
        <span class="">统计时间：</span>

        <div class="week-group" v-show=" currentSelectedPeriod === 'week' ">
          <el-date-picker :editable="false" v-model="weekFrom" type="week" format="yyyy 第 WW 周" placeholder="选择周">
          </el-date-picker>

          <span> - </span>

          <el-date-picker :editable="false" v-model="weekTo" type="week" format="yyyy 第 WW 周" placeholder="选择周">
          </el-date-picker>
        </div>

        <div class="month-group" v-show=" currentSelectedPeriod === 'month' ">
         
          <el-date-picker :editable="false" v-model="monthFrom" type="month" placeholder="选择月">
          </el-date-picker>

          <span> - </span>

          <el-date-picker :editable="false" v-model="monthTo" type="month" placeholder="选择月">
          </el-date-picker>
        </div>

        <div class="quarter-group" v-show=" currentSelectedPeriod === 'quarter' ">
         

          <quarter-picker></quarter-picker>

          <span> - </span>

          <quarter-picker></quarter-picker>
        </div>

        <div class="year-group" v-show=" currentSelectedPeriod === 'year' ">
          <el-date-picker :editable="false" v-model="yearFrom" align="right" type="year" placeholder="选择年">
          </el-date-picker>

          <span> - </span>

          <el-date-picker :editable="false" v-model="yearTo" align="right" type="year" placeholder="选择年">
          </el-date-picker>
        </div>

        <area-picker></area-picker>

        <el-button type="primary no-selection-text">统计</el-button>

        <span class="btn-order-search-return no-selection-text" @click="advanceSearch = false"></span>
      </div>


    </div>
    <div class="box sixteen-nine">
      <div class="content" ref='orderTrendChart'>

      </div>
    </div>

  </el-dialog>
</section>
<!--dialog end-->


</template>
<script>
  import areaPicker from '@/components/AreaPicker'
  import quarterPicker from '@/components/QuarterPicker'

  import echarts from 'echarts';

  export default {
    name: 'orderDetails',
    components: {
      areaPicker,
      quarterPicker
    },
    props: {
      'dialogTitle': {
        type: String
      },
      'popupVisible': {
        type: Boolean,
        default: false
      },
      'orderType': {
        // 1: order price, 2: order amount
        type: Number
      }
    },
    updated() {
      var vm = this;
      if (vm.showMe) {
        vm.loadorderTrendChart();
      }
    },
    computed: {
      showMe() {
        return this.popupVisible;
      }
    },
    data() {
      return {
        currentSelectedPeriod: 'week',
        advanceSearch: false,
        orderTrendChart: null,
        weekFrom: '',
        weekTo: '',
        monthFrom: '',
        monthTo: '',
        quarterFrom: '',
        quarterTo: '',
        yearFrom: '',
        yearTo: ''
      }
    },
    methods: {
      
      selectPeriod(period) {
        this.currentSelectedPeriod = period;
      },
      closeMe() {
        this.$emit('closeMe')
      },
      loadorderTrendChart() {
        var vm = this;
        // 基于准备好的dom，初始化echarts实例
        vm.orderTrendChart = echarts.init(vm.$refs.orderTrendChart);

        var option = {
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            bottom: '0',
            data: ['出口', '进口', '总计']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '10%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            },
            data: ['周一', '周二', '周三', '周四', '周五', '周六']
          },
          yAxis: {
            type: 'value',
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            }

          },
          series: [{
              name: '出口',
              type: 'bar',
              barGap: 0,
              itemStyle: {
                normal: {
                  color: '#2ab2d6'
                }
              },
              data: [18203, 23489, 29034, 104970, 131744, 630230]
            },
            {
              name: '进口',
              type: 'bar',
              barGap: 0,
              itemStyle: {
                normal: {
                  color: '#2391cd'
                }
              },
              data: [19325, 23438, 31000, 121594, 134141, 681807]
            },
            {
              name: '总计',
              type: 'line',
              barGap: 0,
              itemStyle: {
                normal: {
                  color: '#44cd8a'
                }
              },
              data: [37325, 43438, 51000, 221594, 334141, 1281807]
            },

          ]
        };

        vm.orderTrendChart.setOption(option)
      }
    }
  }


</script>

<style lang="scss">
  .order-search-basic-bar {
  height: 30px;
  line-height: 30px;
  .button {
    display: inline-block;
    margin-right: 20px;
    cursor: pointer;
    .el-icon-search {
      padding-left: 5px;
    }
  }
}

.order-date-type-btn-list,
.order-date-picker-range-list {
  display: inline-block;
  vertical-align: middle;
}

.order-date-type-btn-list {
  padding-right: 10px;
  margin-right: 10px;
  border-right: 1px solid #b3b3b3;
  .icon-date-type {
    position: relative;
    cursor: pointer;
    top: 2px;
  }
}

.icon-date-type {
  display: inline-block;
  width: 21px;
  height: 21px;
  background: url(../assets/images/icon-date-list.png) no-repeat;
}

.icon-date-type+.icon-date-type {
  margin-left: 13px;
}

.icon-week {
  background-position: 0 0;
}

.icon-week.is-active {
  background-position: 0px -25px;
}

.icon-month {
  background-position: -23px 0px;
}

.icon-month.is-active {
  background-position: -23px -25px;
}

.icon-quarter {
  background-position: -46px 0px;
}

.icon-quarter.is-active {
  background-position: -46px -25px;
}

.icon-year {
  background-position: -69px 0px;
}

.icon-year.is-active {
  background-position: -69px -25px;
}

.week-group,
.month-group,
.quarter-group,
.year-group {
  display: inline-block;
}

.quarter-group {
  .el-input {
    width: 140px; 
  }
}



.order-date-picker-range-list {
  .el-date-editor.el-input {
    width: 140px;
  }
  .el-input__inner {
    height: 30px;
  }
  .el-button {
    margin-left: 6px;
    padding-top: 6px;
    padding-bottom: 6px;
  }
}

.order-area-chart-dialog {
  .el-dialog--small {
    width: 55%;
  }
  .btn-order-search-return {
    display: inline-block;
    margin-left: 26px;
    width: 16px;
    height: 16px;
    position: relative;
    top: 3px;
    cursor: pointer;
    background: url(../assets/images/icon-return.png) no-repeat;
  }
}

.order-advance-search-bar {
  text-align: center;
}
</style>
