<template>
<div>

  <el-breadcrumb separator=">">
    <span class="el-breadcrumb__item"><span>我的位置：</span></span>
    <el-breadcrumb-item :to="{ path: '/home' }">聚贸通平台</el-breadcrumb-item>
    <el-breadcrumb-item>用户行为</el-breadcrumb-item>
    <el-breadcrumb-item>流量分析</el-breadcrumb-item>
  </el-breadcrumb>

  <el-row class="jm-grid-box-border-radius jm-grid-box-bg-white jm-grid-border-1px jm-padding-15X20 jm-margin-top20 jm-margin-bottom20 primary-timer-filter">
    <el-col :span="12">
      <div class="grid-content">
        <span>时间段选择：</span>
        <el-button :type="latest7Days" @click="selectDatePeriod(7)">最近7天</el-button>
        <el-button :type="latest15Days" @click="selectDatePeriod(15)">最近15天</el-button>
        <el-button :type="latest30Days" @click="selectDatePeriod(30)">最近30天</el-button>
      </div>
    </el-col>
    <el-col :span="12">
      <div class="grid-content clearfix">
        <el-date-picker ref="dateRangePicker" class="right" :editable="false" v-model="global_time_filter_value" type="daterange" :picker-options="globalPickerOptions" placeholder="选择时间范围"
          align="right">
        </el-date-picker>
      </div>
    </el-col>
  </el-row>
  <!-- end of time filter-->

  <el-row>
    <el-col :span="24">
      <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
        <el-tabs v-model="zhibiao_activeTabName" type="card" @tab-click="handle_zhibiao_Click">
          <el-tab-pane label="浏览量" name="lll">

            <div class="box chart sixteen-nine">
              <div class="content " ref='lllChart'>

              </div>
            </div>

          </el-tab-pane>
          <el-tab-pane label="独立访客" name="dlfk">

            <div class="box chart sixteen-nine">
              <div class="content " ref='dlfkChart'>

              </div>
            </div>

          </el-tab-pane>
          <el-tab-pane label="平均停留时间" name="pjtlsj">

            <div class="box chart sixteen-nine">
              <div class="content " ref='pjtlsjChart'>

              </div>
            </div>

          </el-tab-pane>
          <el-tab-pane label="访问深度" name="fwsd">

            <div class="box chart sixteen-nine">
              <div class="content " ref='fwsdChart'>

              </div>
            </div>

          </el-tab-pane>
          <el-tab-pane label="新访客" name="xfk">

            <div class="box chart sixteen-nine">
              <div class="content " ref='xfkChart'>

              </div>
            </div>

          </el-tab-pane>
          <el-tab-pane label="跳出率" name="tcl">

            <div class="box chart sixteen-nine">
              <div class="content" ref='tclChart'>

              </div>
            </div>

          </el-tab-pane>
        </el-tabs>
      </div>
    </el-col>
    
  </el-row>
  <!-- end of zhibiao-->

  <el-row :gutter="20">
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
        <header class="panel-header jm-grid-border-bottom-1px">页面流量排名</header>

        <div class="box sixteen-nine">
          <div class="content" ref='ymllpmChart'>

          </div>
        </div>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
        <header class="panel-header jm-grid-border-bottom-1px">页面跳出率排名</header>

        <div class="box sixteen-nine">
          <div class="content" ref='ymtclpmChart'>

          </div>
        </div>

      </div>
    </el-col>
  </el-row>
  <!-- end of 页面流量排名 & 页面跳出率排名 -->

  <el-row :gutter="20">
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
        <header class="panel-header jm-grid-border-bottom-1px">新老访客
          <el-tooltip class="item" content="鼠标放到I,弹出注释标签，标签中的内容开发中，产品经理撰写提供。" placement="top-end">
            <span class="jm-tooltip-icon"></span>
          </el-tooltip>
        </header>

        <div class="box sixteen-nine">
          <section class="new-old-visitor content">
          <ul class="clearfix">
            <li class="left jm-width-50-p"><span class="lable">新访客：</span><span class="new-percentage">85.42%</span></li>
            <li class="left jm-width-50-p"><span class="lable">新访客：</span><span class="old-percentage">14.58%</span></li>
          </ul>

          <div class="new-old-visitor-detail">

            <el-table :data="tableData" :show-header="false" border style="width: 100%">
              <el-table-column prop="name" label="type">
              </el-table-column>
              <el-table-column prop="new" label="new">
              </el-table-column>
              <el-table-column prop="old" label="old">
              </el-table-column>
            </el-table>
          </div>

        </section>
        </div>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="24" :lg="12">
      <div class="grid-content jm-grid-box-bg-white jm-grid-border-1px">
        <header class="panel-header jm-grid-border-bottom-1px">地域分布
          <el-tooltip class="item" content="鼠标放到I,弹出注释标签，标签中的内容开发中，产品经理撰写提供。" placement="top-end">
            <span class="jm-tooltip-icon"></span>
          </el-tooltip>
        </header>

        <div class="box sixteen-nine">
          <div class="content" ref='dyfbChart'>

          </div>
        </div>

      </div>
    </el-col>
  </el-row>
  <!-- end of 用户流向 & 地域分布 -->

  <el-row class="jm-grid-border-1px">
    <header class="panel-header jm-grid-box-bg-white jm-grid-border-bottom-1px">访问来源
      <el-tooltip class="item" content="标签中的内容开发中，产品经理撰写提供。" placement="top-end">
        <span class="jm-tooltip-icon"></span>
      </el-tooltip>
    </header>
    <el-col :xs="24" :sm="24" :md="24" :lg="16">
      <div class="grid-content jm-grid-box-bg-white">

        <div class="box chart row-group-last">
          <div class="content" ref='fwlyLineChart'>

          </div>

        </div>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="24" :lg="8">
      <div class="grid-content jm-grid-box-bg-white">

        <div class="box  chart row-group-last">
          <div class="content" ref='fwlyPieChart'>

          </div>
        </div>

      </div>
    </el-col>
  </el-row>

</div>

</template>
<script>

  import echarts from 'echarts';
  // https://segmentfault.com/q/1010000008182097
  import 'echarts/map/js/china'

  export default {
    name: 'home',
    methods: {
        selectDatePeriod(period) {

            switch (period) {
              case 7:
                this.latest7Days = 'primary'
                this.latest15Days = ''
                this.latest30Days = ''
                break;
              case 15:
                this.latest7Days = ''
                this.latest15Days = 'primary'
                this.latest30Days = ''
                break;
              case 30:
                this.latest7Days = ''
                this.latest15Days = ''
                this.latest30Days = 'primary'
                break;
            }

          const end = new Date();
          const start = new Date();
          start.setTime(start.getTime() - 3600 * 1000 * 24 * period);

          this.global_time_filter_value = [start, end]
        },
      handle_zhibiao_Click(tab, event) {
        var vm = this;

        var chartId = `${tab.name}Chart`;


        setTimeout(function () {
          vm.loadTabContentChart(chartId)
        }, 200);

      },

      loadTabContentChart(chartId) {
        var vm = this;
        vm[chartId] = echarts.init(vm.$refs[chartId]);
        // 绘制图表
        var option = {
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['邮件营销']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
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
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
          }],
          yAxis: [{
            type: 'value',
            axisLine: {
              lineStyle: {
                color: '#158bca'
              }
            }
          }],
          series: [{
            name: '邮件营销',
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
            data: [120, 132, 101, 134, 90, 230, 210]
          }

          ]
        };
        vm[chartId].setOption(option)
      }

    },
    mounted() {
      var vm = this;

      vm.loadTabContentChart('lllChart')

      loadymllpmChart()
      loadymtclpmChart()
      //loadyhlxChart()
      loaddyfbChart()
      loadfwlyLineChart()
      loadfwlyPieChart()


      // 页面流量排名
      function loadymllpmChart() {
        // 基于准备好的dom，初始化echarts实例
        vm.ymllpmChart = echarts.init(vm.$refs.ymllpmChart);

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
            data: ['巴西', '印尼', '美国', '印度', '中国', '世界人口(万)']
          },
          series: [{
            name: '2012年',
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: '#5eb2ed',
                barBorderRadius: 2
              }
            },
            data: [19325, 23438, 31000, 121594, 134141, 681807]
          }]
        };

        vm.ymllpmChart.setOption(option)
      }

      // 页面跳出率排名
      function loadymtclpmChart() {
        // 基于准备好的dom，初始化echarts实例
        vm.ymtclpmChart = echarts.init(vm.$refs.ymtclpmChart);

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
            data: ['巴西', '印尼', '美国', '印度', '中国', '世界人口(万)']
          },
          series: [{
            name: '2012年',
            type: 'bar',
            barWidth: '20px',
            itemStyle: {
              normal: {
                color: '#44cd8a',
                barBorderRadius: 2
              }
            },
            data: [19325, 23438, 31000, 121594, 134141, 681807]
          }]
        };

        vm.ymtclpmChart.setOption(option)
      }

      // 用户流向
      // function loadyhlxChart() {
      //   // 基于准备好的dom，初始化echarts实例
      //   vm.yhlxChart = echarts.init(vm.$refs.yhlxChart);

      //   // 绘制图表
      //   var option = {
      //     tooltip: {
      //       trigger: 'item',
      //       formatter: "{a} <br/>{b}: {c} ({d}%)"
      //     },
      //     legend: {
      //       orient: 'vertical',
      //       top: '5%',
      //       left: '3%',
      //       data: ['直接访问', '邮件营销', '联盟广告']
      //     },
      //     series: [{
      //       name: '用户流向',
      //       type: 'pie',
      //       radius: ['50%', '70%'],
      //       center: ['55%', '50%'],
      //       avoidLabelOverlap: true,
      //       label: {
      //         normal: {
      //           show: true,
      //           formatter: '{d}%\n{b}',
      //           position: 'outside',
      //           textStyle: {
      //             fontSize: '20'
      //           }
      //         },
      //         emphasis: {
      //           show: true,
      //           textStyle: {
      //             fontSize: '22',
      //             fontWeight: 'bold'
      //           }
      //         }
      //       },
      //       labelLine: {
      //         normal: {
      //           show: true
      //         }
      //       },
      //       color: ['#44cd8a', '#5eb2ec', '#b6a2dc'],
      //       data: [{
      //         value: 335,
      //         name: '直接访问'
      //       },
      //       {
      //         value: 310,
      //         name: '邮件营销'
      //       },
      //       {
      //         value: 234,
      //         name: '联盟广告'
      //       }
      //       ]
      //     }]
      //   };

      //   vm.yhlxChart.setOption(option)
      // }

      function randomData() {
        return Math.round(Math.random() * 1000);
      }
      // 地域分布
      function loaddyfbChart() {

        // 基于准备好的dom，初始化echarts实例
        vm.dyfbChart = echarts.init(vm.$refs.dyfbChart);

        // 绘制图表
        var option = {
          tooltip: {
            trigger: 'item'
          },
          visualMap: {
            orient: 'horizontal',
            inverse: 'true',
            min: 0,
            max: 2500,
            left: '15px',
            top: '15px',
            text: ['高', '低'], // 文本，默认为数值文本
            calculable: true
          },
          series: [{
            name: 'iphone3',
            type: 'map',
            mapType: 'china',
            roam: false,
            label: {
              normal: {
                show: true
              },
              emphasis: {
                show: true
              }
            },
            data: [{
              name: '北京',
              value: randomData()
            },
            {
              name: '天津',
              value: randomData()
            },
            {
              name: '上海',
              value: randomData()
            },
            {
              name: '重庆',
              value: randomData()
            },
            {
              name: '河北',
              value: randomData()
            },
            {
              name: '河南',
              value: randomData()
            },
            {
              name: '云南',
              value: randomData()
            },
            {
              name: '辽宁',
              value: randomData()
            },
            {
              name: '黑龙江',
              value: randomData()
            },
            {
              name: '湖南',
              value: randomData()
            },
            {
              name: '安徽',
              value: randomData()
            },
            {
              name: '山东',
              value: randomData()
            },
            {
              name: '新疆',
              value: randomData()
            },
            {
              name: '江苏',
              value: randomData()
            },
            {
              name: '浙江',
              value: randomData()
            },
            {
              name: '江西',
              value: randomData()
            },
            {
              name: '湖北',
              value: randomData()
            },
            {
              name: '广西',
              value: randomData()
            },
            {
              name: '甘肃',
              value: randomData()
            },
            {
              name: '山西',
              value: randomData()
            },
            {
              name: '内蒙古',
              value: randomData()
            },
            {
              name: '陕西',
              value: randomData()
            },
            {
              name: '吉林',
              value: randomData()
            },
            {
              name: '福建',
              value: randomData()
            },
            {
              name: '贵州',
              value: randomData()
            },
            {
              name: '广东',
              value: randomData()
            },
            {
              name: '青海',
              value: randomData()
            },
            {
              name: '西藏',
              value: randomData()
            },
            {
              name: '四川',
              value: randomData()
            },
            {
              name: '宁夏',
              value: randomData()
            },
            {
              name: '海南',
              value: randomData()
            },
            {
              name: '台湾',
              value: randomData()
            },
            {
              name: '香港',
              value: randomData()
            },
            {
              name: '澳门',
              value: randomData()
            }
            ]
          },
          {
            name: 'iphone4',
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
            data: [{
              name: '北京',
              value: randomData()
            },
            {
              name: '天津',
              value: randomData()
            },
            {
              name: '上海',
              value: randomData()
            },
            {
              name: '重庆',
              value: randomData()
            },
            {
              name: '河北',
              value: randomData()
            },
            {
              name: '安徽',
              value: randomData()
            },
            {
              name: '新疆',
              value: randomData()
            },
            {
              name: '浙江',
              value: randomData()
            },
            {
              name: '江西',
              value: randomData()
            },
            {
              name: '山西',
              value: randomData()
            },
            {
              name: '内蒙古',
              value: randomData()
            },
            {
              name: '吉林',
              value: randomData()
            },
            {
              name: '福建',
              value: randomData()
            },
            {
              name: '广东',
              value: randomData()
            },
            {
              name: '西藏',
              value: randomData()
            },
            {
              name: '四川',
              value: randomData()
            },
            {
              name: '宁夏',
              value: randomData()
            },
            {
              name: '香港',
              value: randomData()
            },
            {
              name: '澳门',
              value: randomData()
            }
            ]
          },
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
            data: [{
              name: '北京',
              value: randomData()
            },
            {
              name: '天津',
              value: randomData()
            },
            {
              name: '上海',
              value: randomData()
            },
            {
              name: '广东',
              value: randomData()
            },
            {
              name: '台湾',
              value: randomData()
            },
            {
              name: '香港',
              value: randomData()
            },
            {
              name: '澳门',
              value: randomData()
            }
            ]
          }
          ]
        };

        vm.dyfbChart.setOption(option)
      }

      // 访问来源
      function loadfwlyLineChart() {
        // 基于准备好的dom，初始化echarts实例
        vm.fwlyLineChart = echarts.init(vm.$refs.fwlyLineChart);

        // 绘制图表
        var option = {
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            top: '2%',
            data: ['邮件营销', '联盟广告', '视频广告']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
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
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
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
            name: '邮件营销',
            type: 'line',
            smooth: true,
            itemStyle: { normal: { color: '#44cd8a' } },
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '联盟广告',
            type: 'line',
            smooth: true,
            itemStyle: { normal: { color: '#5eb2ec' } },
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: '视频广告',
            type: 'line',
            smooth: true,
            itemStyle: { normal: { color: '#b6a2dc' } },
            data: [150, 232, 201, 154, 190, 330, 410]
          }
          ]
        };

        vm.fwlyLineChart.setOption(option)
      }

      // 访问来源
      function loadfwlyPieChart() {
        // 基于准备好的dom，初始化echarts实例
        vm.fwlyPieChart = echarts.init(vm.$refs.fwlyPieChart);

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
            data: ['直接访问', '邮件营销', '联盟广告']
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
            }
            ]
          }]
        };

        vm.fwlyPieChart.setOption(option)
      }

      window.addEventListener('resize', chartsResize, false);

      function chartsResize() {

        var tabContentChart = `${vm.zhibiao_activeTabName}Chart`
        if (vm[tabContentChart]) {
          vm[tabContentChart].resize()
        }

        vm.ymllpmChart.resize()
        vm.ymtclpmChart.resize()
        //vm.yhlxChart.resize()
        vm.dyfbChart.resize()
        vm.fwlyLineChart.resize()
        vm.fwlyPieChart.resize()

      }
    },
    data() {
      return {
        latest7Days: 'primary',
        latest15Days: '',
        latest30Days: '',
        activeMenuIndex: '1-1',
        lllChart: null,
        dlfkChart: null,
        pjtlsjChart: null,
        fwsdChart: null,
        xfkChart: null,
        tclChart: null,
        ymllpmChart: null,
        ymtclpmChart: null,
        //yhlxChart: null,
        dyfbChart: null,
        fwlyLineChart: null,
        fwlyPieChart: null,
        zhibiao_activeTabName: 'lll',
        global_time_filter_value: [new Date(), new Date()],
        globalPickerOptions: {
          shortcuts: [{
            text: '最近7天',
            onClick(picker) {
              setPickerDate(picker, 7)
            }
          }, {
            text: '最近15天',
            onClick(picker) {
              setPickerDate(picker, 15)
            }
          }, {
            text: '最近30天',
            onClick(picker) {
              setPickerDate(picker, 30)
            }
          }]
        },
        tableData: [{
          new: '965',
          name: '浏览量',
          old: '183'
        }, {
          new: '791',
          name: '访客数',
          old: '135'
        }, {
          new: '92.01%',
          name: '跳出率',
          old: '84.62%'
        }, {
          new: '00:03:26',
          name: '平均访问时长',
          old: '00:02:40'
        }, {
          new: '1.11',
          name: '平均访问页数',
          old: '1.19'
        }]
      }
    }
  }

  function setPickerDate(picker, period) {
    const end = new Date();
    const start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * period);
    picker.$emit('pick', [start, end]);
  }

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">

  .primary-timer-filter {
    .el-button {
      padding-top: 8px;
      padding-bottom: 8px;
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

</style>
