<template>
<div>
  <!--面包屑-->
  <el-breadcrumb separator=">">
    <span class="el-breadcrumb__item"><span>我的位置：</span></span>
    <el-breadcrumb-item :to="{ path: '/home' }">聚贸通平台</el-breadcrumb-item>
    <el-breadcrumb-item>运营情况</el-breadcrumb-item>
    <el-breadcrumb-item>企业分类统计</el-breadcrumb-item>
  </el-breadcrumb>

  <el-row :gutter="20" class="jm-margin-top20 jm-margin-bottom20">
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px c-el-tabs">
        <span class="el-tabs-name">企业新增数</span>
        <el-tabs v-model="cNewAdd_activeTabName" type="board-card" @tab-click="handle_cNewAdd_Click">
          <el-tab-pane label="本周" name="week">
            <div class="box chart row-group-1">
              <div class="content " ref='weekNewAddChart'></div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="本月" name="month">
            <div class="box chart row-group-1">
              <div class="content " ref='monthNewAddChart'></div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="本季" name="quarter">
            <div class="box chart row-group-1">
              <div class="content " ref='quarterNewAddChart'></div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="本年" name="year">
            <div class="box chart row-group-1">
              <div class="content " ref='yearNewAddChart'></div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px c-el-tabs">
        <span class="el-tabs-name">企业活跃数</span>
        <el-tabs v-model="cActive_activeTabName" type="board-card" @tab-click="handle_cActive_Click">
          <el-tab-pane label="本周" name="week">
            <div class="box chart row-group-1">
              <div class="content " ref='weekActivityChart'></div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="本月" name="month">
            <div class="box chart row-group-1">
              <div class="content " ref='monthActivityChart'></div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="本季" name="quarter">
            <div class="box chart row-group-1">
              <div class="content " ref='quarterActivityChart'></div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="本年" name="year">
            <div class="box chart row-group-1">
              <div class="content " ref='yearActivityChart'></div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-col>
  </el-row>
  <el-row class="jm-grid-box-bg-white jm-grid-border-1px">
    <header class="panel-header jm-grid-border-bottom-1px c-header">
      <span>拍档企业</span>
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
              <el-option v-for="item in selectOptions" :label="item.label" :value="item.selectVal">
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
    <header class="panel-header jm-grid-border-bottom-1px c-header c-header1"><span>客户企业</span></header>
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
  import {getCompanyNum,getCompanyTopAmount} from '@/service/api'
  import util from '@/service/util'

  import echarts from 'echarts';
  // https://segmentfault.com/q/1010000008182097
  import 'echarts/map/js/china'

  export default {
    name: 'company',
    components: {},
    methods: {
      handle_cNewAdd_Click(tab, event) {
        var vm = this;

        var chartId = `${tab.name}NewAddChart`;

        var chartConfig = {
          index: 1,
          type: 'bar'
        }
        setTimeout(function () {
          vm.companyNewAddChart(chartId, chartConfig)
        }, 200);


      },
      // 企业新增数 || 企业活跃数
      companyNewAddChart(chartId, chartConfig) {
        var vm = this;
        console.log(chartId)
        // 基于准备好的dom，初始化echarts实例
        vm[chartId] = echarts.init(vm.$refs[chartId]);

        var timeType = vm.cNewAdd_activeTabName
        var startDate, endDate

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

        getCompanyNum({
            timeType,
            startDate,
            endDate,
            chart: chartConfig.index
          }).then(function (response) {
            var chartData = response.data;
            var chartType = chartConfig.type
            loadChartData(chartData, chartType)
          })
          .catch(function (error) {
            console.log(error);
          });

        function loadChartData(chartData, chartType) {

          var series = chartData.series || [];

          var mergedSeries = series.map(function (obj) {
            var rObj = Object.assign({}, obj, {
              type: chartType,
              markPoint: {
                data: [{
                    type: 'max',
                    name: '最大值'
                  },
                  {
                    type: 'min',
                    name: '最小值'
                  }
                ]
              },
              markLine: {
                data: [{
                  type: 'average',
                  name: '平均值'
                }]
              }
            })

            return rObj;
          });

          var legendData = chartData.legendData || [];
          var updatedLegendData = legendData.map((val) => {
            return {
              name: val,
              icon: 'roundRect'
            }
          })

          // 绘制图表
          var option = {
            tooltip: {
              trigger: 'axis'
            },
            legend: {
              data: updatedLegendData,
              right: 20
            },
            color: ['#2ec7c9', '#b6a2de'],
            calculable: true,
            xAxis: [{
              type: 'category',
              data: chartData.xAxisData
            }],
            yAxis: [{
              name: '数量',
              type: 'value'
            }],
            series: mergedSeries
          };

          vm[chartId].setOption(option)
        }

      },

      handle_cActive_Click(tab, event) {
        var vm = this;

        var chartId = `${tab.name}ActivityChart`;

        var chartConfig = {
          index: 2,
          type: 'line'
        }
        setTimeout(function () {
          vm.companyNewAddChart(chartId, chartConfig)
        }, 200);

      },

      cHeaderSearchBtn(index) {
        this.cHeaderMore = index
      },
      cHeaderSearchTimeBtn(index) {
        this.cHeaderSearchTime = index
      }
    },

    mounted() {
      var vm = this;

      vm.companyNewAddChart(vm.cNewAdd_activeTabName + 'NewAddChart', {
        index: 1,
        type: 'bar'
      })

      vm.companyNewAddChart(vm.cActive_activeTabName + 'ActivityChart', {
        index: 2,
        type: 'line'
      })

      // 企业排名
      function companyRank(ref, unit, color, chartIndex) {
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

        getCompanyTopAmount({
            timeType,
            startDate,
            endDate,
            customsCode: 'all',
            chart: chartIndex
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
              name: unit
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

      // 2、拍档企业订单数top
      companyRank('cOrderNumChart', '票', '#2ec7c9', 2)
      // 4、拍档企业收入top
      companyRank('cOrderValChart', '美元', '#b6a2de', 4)
      // 6、拍档企业:订单货值top
      companyRank('cOrderIncomeChart', '人民币', '#5ab1ef', 6)
      // 1、客户企业订单数top
      companyRank('cCustomerNumChart', '票', '#2ec7c9', 1)
      // 3、客户企业收入top
      companyRank('cCustomerValChart', '美元', '#b6a2de', 3)
      // 5、客户企业:订单货值top
      companyRank('cCustomerIncomeChart', '人民币', '#5ab1ef', 5)

      window.addEventListener('resize', chartsResize, false);

      function chartsResize() {

        // 企业新增数
        var cNewContentChart = `${vm.cNewAdd_activeTabName}Chart`
        if (vm[cNewContentChart]) {
          vm[cNewContentChart].resize()
        }

        // 企业活跃数
        var cActiveContentChart = `${vm.cActive_activeTabName}Chart`
        if (vm[cActiveContentChart]) {
          vm[cActiveContentChart].resize()
        }

        // todo: bar resize
        //[].forEach(fun)


      }
    },
    data() {
      return {
        cHeaderMore: 0,
        cHeaderSearchTime: 0,
        cNewAdd_activeTabName: 'week',
        cActive_activeTabName: 'week',
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

<style>
.chart{
  height: 365px;
}
.c-el-tabs{
  position: relative;
}
.el-tabs-name{
  position: absolute;
  top: 12px;
  left: 20px;
  font-size: 16px;
  color: #333333;
}
.c-el-tabs .el-tabs__nav{
  float: right;
}
.c-header{
  color: #333333
}
.c-header1{
  border-top: 1px solid #d9d9d9;
}
.c-header-search{
  float: right
}
.c-header-search ul{
  float: left;
  list-style: none;
  display: inline-block;
  margin: 0;
  padding: 0;
  vertical-align: middle;
}
.c-header-search .c-header-search-list2{
  color: #666666;
}
.c-header-search li{
  float: left;
  color: #444444;
  margin-right: 15px;
  font-size: 14px;
  cursor: pointer;
}
.c-header-search li:hover,
.c-header-search .active{
  color: #0086e9;
}
.c-header-search .hide{
  display: none;
}
.c-header-search-btn{
  float: right;
  vertical-align: middle;
  font-size: 14px;
  cursor: pointer;
  color: #666666;
}
.c-icon{
  display: inline-block;
  width: 14px;
  height: 14px;
  vertical-align: middle;
  margin-left: 5px;
}
.c-icon.c-icon-search{
  background: url("../assets/images/company/search-icon.png");
}
.c-icon.c-icon-back{
  width: 16px;
  height: 16px;
  background: url("../assets/images/company/back-icon.png");
}
/*时间选择*/
.el-date-editor.el-input.company-time-box{
  width: 140px;
  height: 30px;
}
.company-time-box .el-input__inner,
.c-header-search-select .el-input__inner,
.c-header-search-btn1{
  width: 140px;
  height: 30px;
  font-family: 'Microsoft YaHei';
  font-size: 14px;
  border: 1px solid #d9d9d9;
  border-radius: 2px;
}
.c-header-search-select,
.c-header-search-select .el-input__inner{
  height: 30px;
  width: 120px;
}
.c-header-search-list2 .item{
  display: inline-block;
  width: 21px;
  height: 21px;
  line-height: 22px;
  font-size: 12px;
  text-align: center;
  vertical-align: middle;
  background: url("../assets/images/company/search-icon1.png") -34px 0 no-repeat;
}
.c-header-search-list2 .active .item{
  background-position: 0 0;
  color: #0086e9;
}
.c-header-search-list2 .c-header-search-line,
.c-header-search-list2 .c-header-search-line+li{
  cursor: inherit;
  color: #666666;
}
.c-header-search-list2 .c-header-search-line span{
  display: inline-block;
  border-left: 1px solid #b2b2b2;
  height: 22px;
  vertical-align: middle;
}
.c-header-search-btn1{
  width: 60px;
  text-align: center;
  border: 1px solid #0086e9;
  color: #ffffff;
  background-color: #0086e9;
  cursor: pointer;
}
.c-header-search-btn1:hover{
  background-color: #2e7bdc;
}
.c-order-box{
  height: 410px;
  margin-right: 20px;
}
.c-order-upload{
  position: absolute;
  top: 80px;
  right: 20px;
  display: inline-block;
  width: 20px;
  height: 17px;
  background: url("../assets/images/company/upload-icon.png") no-repeat;
}
</style>
