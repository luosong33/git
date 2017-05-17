<template>
  <el-row class="jm-grid-box-border-radius jm-grid-box-bg-white jm-grid-border-1px jm-padding-15X20 jm-margin-top20 jm-margin-bottom20 primary-timer-filter">
    <el-col :span="12">
      <div class="grid-content">
        <span>时间段选择：</span>
        <el-button :type="currentMonth"
                   @click="selectDatePeriod(0)">本月</el-button>
        <el-button :type="PreviousMonth"
                   @click="selectDatePeriod(-1)">上月</el-button>
  
      </div>
    </el-col>
    <el-col :span="12">
      <div class="grid-content clearfix">
        <el-date-picker class="right"
                        :editable="false"
                        :clearable="false"
                        v-model="global_time_filter_value"
                        type="daterange"
                        :picker-options="globalPickerOptions"
                        placeholder="选择时间范围"
                        @change="dateChanged"
                        align="right">
        </el-date-picker>
      </div>
    </el-col>
  </el-row>
</template>
<script>
import util from '@/service/util'

// 当前选中的按钮：本月，上月
let activedButtonPeriod = 0;

export default {
  name: 'rangeDatePicker2',
  created() {
    // 初始化开始时间为本月
    const start = new Date();
    start.setDate(1)
    this.global_time_filter_value[0] = start;

    activedButtonPeriod = 0;
  },
  data() {
    return {
      currentMonth: 'primary',
      PreviousMonth: '',
      global_time_filter_value: [new Date(), new Date()],
      globalPickerOptions: {
        shortcuts: [{
          text: '本月',
          onClick(picker) {
            setPickerDate(picker, 0)
          }
        }, {
          text: '上月',
          onClick(picker) {
            setPickerDate(picker, -1)
          }
        }]
      },
    }
  },
  methods: {
    dateChanged() {
      var period = activedButtonPeriod
      this.updateFilterButtonState(period);
      var startDate = this.global_time_filter_value[0].format(),
        endDate = this.global_time_filter_value[1].format();

      this.$emit('selectedRangeDate', { startDate, endDate });
    },
    selectDatePeriod(index) {
      activedButtonPeriod = index

      this.global_time_filter_value = getStartEnd(index)

    },
    updateFilterButtonState(index) {

      switch (index) {
        case 0:
          this.currentMonth = 'primary'
          this.PreviousMonth = ''
          break;
        case -1:
          this.currentMonth = ''
          this.PreviousMonth = 'primary'
          break;

      }
    }
  },
  mounted() {
    window.addEventListener('resize', fixSearchBar, false);
    window.addEventListener('scroll', fixSearchBar, false);

    var searchFilter = document.querySelector('.primary-timer-filter');

    function fixSearchBar() {

      if (document.body.scrollTop > 113) {
        searchFilter.style.position = 'fixed'
        searchFilter.style.top = '40px'
        searchFilter.style.left = '192px'
        searchFilter.style.right = '20px'
        searchFilter.style.zIndex = 40000
      } else {
        searchFilter.style.position = 'static'
        searchFilter.style.top = ''
        searchFilter.style.left = ''
        searchFilter.style.right = ''

      }


    }
  }
}

function setPickerDate(picker, index) {
  activedButtonPeriod = index;
  picker.$emit('pick', getStartEnd(index));
}

function getStartEnd(index) {
  const _date = new Date();
  let end = new Date();
  let start = new Date();

  if (index === 0) {
    start.setDate(1)
  } else {
    let _month = _date.getMonth()
    _date.setMonth(_month - 1)
    start = util.firstDayOfMonth(_date)
    end = util.lastDayOfMonth(_date)
  }

  return [start, end];

}
</script>

<style lang="scss">
.primary-timer-filter {
  .el-button {
    padding-top: 8px;
    padding-bottom: 8px;
  }
}
</style>
