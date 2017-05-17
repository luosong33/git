<template>
  <div>
    <!--面包屑-->
    <el-breadcrumb separator=">">
      <span class="el-breadcrumb__item"><span>我的位置：</span></span>
      <el-breadcrumb-item :to="{ path: '/home' }">聚贸通平台</el-breadcrumb-item>
      <el-breadcrumb-item>运营情况</el-breadcrumb-item>
      <el-breadcrumb-item>进出口分类统计</el-breadcrumb-item>
    </el-breadcrumb>

    <el-row class="jm-grid-box-bg-white jm-grid-border-1px jm-margin-top20">
<header class="panel-header jm-grid-border-bottom-1px c-header">
  <span>进口</span>
  <div class="c-header-search">
    <ul v-if='cHeaderMore == 0'>
      <li class="active">本周</li>
      <li>本月</li>
      <li>本季</li>
      <li>本年</li>
      <li><span class="c-header-search-btn" v-on:click="cHeaderSearchBtn(1)">高级检索<span class="c-icon c-icon-search"></span></span>
      </li>
    </ul>
    <ul class="c-header-search-list2" v-if='cHeaderMore == 1'>
      <li v-on:click="cHeaderSearchTimeBtn(0)" v-bind:class="{active: cHeaderSearchTime == 0}"><span class="item">周</span></li>
      <li v-on:click="cHeaderSearchTimeBtn(1)" v-bind:class="{active: cHeaderSearchTime == 1}"><span class="item">月</span></li>
      <li v-on:click="cHeaderSearchTimeBtn(2)" v-bind:class="{active: cHeaderSearchTime == 2}"><span class="item">季</span></li>
      <li v-on:click="cHeaderSearchTimeBtn(3)" v-bind:class="{active: cHeaderSearchTime == 3}"><span class="item">年</span></li>
      <li class="c-header-search-line"><span></span></li>
      <li>
        <div class="block">
          <span class="demonstration">统计时间：</span>
          <el-date-picker v-if="cHeaderSearchTime == 0" v-model="cWeekTime" type="week" format="yyyy 第 WW 周" placeholder="选择周" class="company-time-box"></el-date-picker>
          <el-date-picker v-if="cHeaderSearchTime == 1" v-model="cMonthTime" type="month" placeholder="选择月" class="company-time-box"></el-date-picker>
          <el-date-picker v-if="cHeaderSearchTime == 2" v-model="cQuarterTime" type="month" placeholder="选择季" class="company-time-box"></el-date-picker>
          <el-date-picker v-if="cHeaderSearchTime == 3" v-model="cYearTime" type="year" placeholder="选择年" class="company-time-box"></el-date-picker>
        </div>
      </li>
      <li>
        <el-select class="c-header-search-select" v-model="selectVal" placeholder="全国">
          <el-option
            v-for="item in selectOptions"
            :label="item.label"
            :value="item.selectVal">
          </el-option>
        </el-select>
      </li>
      <li>
        <button class="c-header-search-btn1" type="button">统计</button>
      </li>
      <li><span class="c-header-search-btn" v-on:click="cHeaderSearchBtn(0)"><span class="c-icon c-icon-back"></span></span>
      </li>
    </ul>
  </div>
</header>
<div class="clearfix">
  <el-col :xs="24" :sm="24" :md="24" :lg="8">
    <div class="grid-content">
      <div class="box sixteen-nine c-order-box">
        <div class="content" ref='cOrderNumChart'>

        </div>
        <a href="" class="c-order-upload"></a>
      </div>
    </div>
  </el-col>
  <el-col :xs="24" :sm="24" :md="24" :lg="8">
    <div class="grid-content">
      <div class="box sixteen-nine c-order-box">
        <div class="content" ref='cOrderValChart'>

        </div>
        <a href="" class="c-order-upload"></a>
      </div>
    </div>
  </el-col>
  <el-col :xs="24" :sm="24" :md="24" :lg="8">
    <div class="grid-content">
      <div class="box sixteen-nine c-order-box">
        <div class="content" ref='cOrderIncomeChart'>

        </div>
        <a href="" class="c-order-upload"></a>
      </div>

    </div>
  </el-col>
</div>
<header class="panel-header jm-grid-border-bottom-1px c-header c-header1"><span>出口</span></header>
<div class="clearfix">
  <el-col :xs="24" :sm="24" :md="24" :lg="8">
    <div class="grid-content">
      <div class="box sixteen-nine c-order-box">
        <div class="content" ref='cCustomerNumChart'>

        </div>
        <a href="" class="c-order-upload"></a>
      </div>
    </div>
  </el-col>
  <el-col :xs="24" :sm="24" :md="24" :lg="8">
    <div class="grid-content">
      <div class="box sixteen-nine c-order-box">
        <div class="content" ref='cCustomerValChart'>

        </div>
        <a href="" class="c-order-upload"></a>
      </div>
    </div>
  </el-col>
  <el-col :xs="24" :sm="24" :md="24" :lg="8">
    <div class="grid-content">
      <div class="box sixteen-nine c-order-box">
        <div class="content" ref='cCustomerIncomeChart'>

        </div>
        <a href="" class="c-order-upload"></a>
      </div>

    </div>
  </el-col>
</div>
</el-row>
</div>
</template>

<script>
import * as api from '@/service/api'
import util from '@/service/util'
import echarts from 'echarts';
// https://segmentfault.com/q/1010000008182097
import 'echarts/map/js/china'

export default {
  name: 'company',
  components: {},
  methods: {
    cHeaderSearchBtn(index) {
      this.cHeaderMore = index
    },
    cHeaderSearchTimeBtn(index) {
      this.cHeaderSearchTime = index
    }
  },
  mounted() {
    var vm = this;

    // 企业排名
    function companyRank(ref, fnName, inout, unit, color) {
      // 基于准备好的dom，初始化echarts实例
      vm[ref] = echarts.init(vm.$refs[ref]);

      var timeType = 'week' // todo
      var startDate, endDate

      // todo
      if (timeType === 'week') {
        startDate = util.firstDayOfWeek().format()
        endDate = util.lastDayOfWeek().format()
      }

      if (timeType === 'month') {
        startDate = util.firstDayOfMonth().format()
        endDate = util.lastDayOfMonth().format()
      }

      if (timeType === 'quarter') {
        startDate = util.firstDayOfQuarter().format()
        endDate = util.lastDayOfQuarter().format()
      }

      if (timeType === 'year') {
        startDate = util.firstDayOfYear().format()
        endDate = util.lastDayOfYear().format()
      }

      api[fnName]({
          timeType,
          startDate,
          endDate,
          customsCode: 'all',
          inout: inout
        }).then(function (response) {
          var chartData = response.data;
          loadChartData(chartData)
        })
        .catch(function (error) {
          console.log(error);
        });


      function loadChartData(chartData) {

        // 绘制图表
        var option = {
          title: {
            text: chartData.title,
            left: 'center',
            top: 20,
            textStyle: {
              fontSize: 16,
              color: '#767676',
              fontWeight: 'normal'
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          grid: {
            top: '20%',
            left: '5%',
            right: '15%',
            bottom: '10%',
            containLabel: true
          },
          xAxis: {
            type: 'value',
            boundaryGap: false,
            name: unit,
            axisLabel: {
                rotate: 20
            }
          },
          yAxis: {
            type: 'category',
            splitLine: {
              show: true
            },
            axisLabel: {
              formatter: function (value, index) {
                var value = value || ''
                var maxLength = 8
                if (value.length > maxLength) {
                    value = value.substring(0, maxLength) + '...';
                }
                return value;
              },
              rotate: 20
            },
            data: chartData.yAxisData
          },
          series: [{
            name: chartData.title,
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: color,
                barBorderRadius: 2
              }
            },
            data: chartData.seriesData
          }]
        };

        vm[ref].setOption(option)
      }
    }


    companyRank('cOrderNumChart', 'getInOutGoodsAmount', 'in', '票', '#2ec7c9')
    companyRank('cOrderValChart', 'getInOutCustomsAmount', 'in', '美元', '#b6a2de')
    companyRank('cOrderIncomeChart', 'getInOutCountryAmount', 'in', '人民币', '#5ab1ef')

    companyRank('cCustomerNumChart', 'getInOutGoodsAmount', 'out', '票', '#2ec7c9')
    companyRank('cCustomerValChart', 'getInOutCustomsAmount', 'out', '美元', '#b6a2de')
    companyRank('cCustomerIncomeChart', 'getInOutCountryAmount', 'out', '人民币', '#5ab1ef')
  },
  data() {
    return {
      cHeaderMore: 0,
      cHeaderSearchTime: 0,
      cWeekTime: '',
      cMonthTime: '',
      cQuarterTime: '',
      cYearTime: '',
      selectOptions: [{
        selectVal: '选项1',
        label: '黄金糕'
      }, {
        selectVal: '选项2',
        label: '双皮奶'
      }, {
        selectVal: '选项3',
        label: '蚵仔煎'
      }, {
        selectVal: '选项4',
        label: '龙须面'
      }, {
        selectVal: '选项5',
        label: '北京烤鸭'
      }],
      selectVal: ''
    }
  }
}

</script>
