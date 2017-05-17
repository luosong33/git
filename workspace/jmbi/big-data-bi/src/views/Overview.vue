<template>
<div>

  <el-breadcrumb separator=">" class="jm-margin-bottom20">
    <span class="el-breadcrumb__item"><span>我的位置：</span></span>
    <el-breadcrumb-item :to="{ path: '/home' }">聚贸通平台</el-breadcrumb-item>
    <el-breadcrumb-item>运营情况</el-breadcrumb-item>
    <el-breadcrumb-item>总览</el-breadcrumb-item>
  </el-breadcrumb>

  <el-row :gutter="20">
    <el-col :xs="8" :sm="8" :md="8" :lg="4">
      <div class="grid-content">
        <div class="box-summary summary-bg-1 ">

          <p><span class="number">159,00</span><span class="unit">万美元</span></p>
          <p class="title">货值</p>

        </div>
      </div>
    </el-col>

    <el-col :xs="8" :sm="8" :md="8" :lg="4">
      <div class="grid-content">
        <div class="box-summary summary-bg-2 ">

          <p><span class="number">355</span><span class="unit">票</span></p>
          <p class="title">订单数</p>
        </div>
      </div>
    </el-col>

    <el-col :xs="8" :sm="8" :md="8" :lg="4">
      <div class="grid-content">
        <div class="box-summary summary-bg-3 ">

          <p><span class="number">159</span><span class="unit">家</span></p>
          <p class="title">客户企业数</p>
        </div>
      </div>
    </el-col>

    <el-col :xs="8" :sm="8" :md="8" :lg="4">
      <div class="grid-content">
        <div class="box-summary summary-bg-4 ">

          <p><span class="number">159</span><span class="unit">家</span></p>
          <p class="title">拍档企业数</p>
        </div>
      </div>
    </el-col>

    <el-col :xs="8" :sm="8" :md="8" :lg="4">
      <div class="grid-content">
        <div class="box-summary summary-bg-5 ">

          <p><span class="number">159,00</span><span class="unit">元</span></p>
          <p class="title">拍档收入</p>
        </div>
      </div>
    </el-col>

    <el-col :xs="8" :sm="8" :md="8" :lg="4">
      <div class="grid-content">
        <div class="box-summary summary-bg-6 ">

          <p><span class="number">159,00</span><span class="unit">元</span></p>
          <p class="title">客户支出</p>
        </div>
      </div>
    </el-col>

  </el-row>
  <!-- end of summary -->

  <el-row class="jm-grid-border-1px">
    <header class="panel-header overview-panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">
      <span class="title">平台企业总览</span>


      <div class="overview-filter">统计区域：
        <area-picker></area-picker>

        </div>


    </header>
    <el-col :xs="24" :sm="24" :md="24" :lg="8">
      <div class="grid-content jm-grid-box-bg-white">

        <div class="box chart">
          <div class="content" ref='overviewLevelPieChart'>

          </div>

        </div>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="24" :lg="8">
      <div class="grid-content jm-grid-box-bg-white">

        <div class="box  chart ">
          <div class="content" ref='overviewAreaPieChart'>

          </div>
        </div>

      </div>
    </el-col>

    <el-col :xs="24" :sm="24" :md="24" :lg="8">
      <div class="grid-content jm-grid-box-bg-white">

        <div class="box  chart ">
          <div class="content" ref='overviewCustomerPieChart'>

          </div>
        </div>

      </div>
    </el-col>
  </el-row>

</div>

</template>

<script>
  import areaPicker from '@/components/AreaPicker'

import echarts from 'echarts';

export default {
  name: 'overview',
  components:{
    areaPicker
  },
  methods: {

  },
  data() {
    return {
      overviewLevelPieChart: null,
      overviewAreaPieChart: null,
      overviewCustomerPieChart: null
    }
  },
  mounted() {
    var vm = this;

    // 绘制图表
    var option = {
      title: {
        text: '某站点用户访问来源',
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
        name: '访问来源',
        type: 'pie',
        radius: '55%',
        center: ['50%', '50%'],
        data: [{
            value: 335,
            name: '直接访问'
          },
          {
            value: 310,
            name: '邮件营销'
          },
          {
            value: 234,
            name: '联盟广告'
          },
          {
            value: 135,
            name: '视频广告'
          },
          {
            value: 1548,
            name: '搜索引擎'
          }
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

    loadoverviewLevelPieChart()


    function loadoverviewLevelPieChart() {
      // 基于准备好的dom，初始化echarts实例
      vm.overviewLevelPieChart = echarts.init(vm.$refs.overviewLevelPieChart);



      vm.overviewLevelPieChart.setOption(option)
    }

    loadoverviewAreaPieChart()


    function loadoverviewAreaPieChart() {
      // 基于准备好的dom，初始化echarts实例
      vm.overviewAreaPieChart = echarts.init(vm.$refs.overviewAreaPieChart);



      vm.overviewAreaPieChart.setOption(option)
    }

    loadoverviewCustomerPieChart()


    function loadoverviewCustomerPieChart() {
      // 基于准备好的dom，初始化echarts实例
      vm.overviewCustomerPieChart = echarts.init(vm.$refs.overviewCustomerPieChart);



      vm.overviewCustomerPieChart.setOption(option)
    }

    window.addEventListener('resize', chartsResize, false);

    function chartsResize() {
      vm.overviewLevelPieChart.resize()
      vm.overviewAreaPieChart.resize()
      vm.overviewCustomerPieChart.resize()



    }
  }
}

</script>

<style lang="scss">
  .box-summary {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-end;
    color: #fff;
    width: 100%;
    height: 135px;
    border-radius: 5px;
    .number {
      font-size: 36px;
    }
    .unit {
      font-size: 14px;
      margin-left: 10px;
      margin-right: 30px;
    }
    .title {
      font-size: 18px;
      margin-right: 30px;
    }
  }

  @for $i from 1 through 6 {
    .summary-bg-#{$i} {
      background: url(../assets/images/overview/summary-#{$i}-bg.png) no-repeat;
      background-size: 100% 100%;
    }
  }

  @media (max-width: 1199px) {
    .box-summary {
      margin-bottom: 20px;
    }
  }

  .overview-panel-header {
    display: flex;
    justify-content: flex-end;
    .title {
      align-self: flex-start;
      margin-right: auto;
    }
  }

  .overview-filter {
    font-size: 14px;
    .jm-area-picker {
      padding: 8px 15px;
    }
  }


</style>
