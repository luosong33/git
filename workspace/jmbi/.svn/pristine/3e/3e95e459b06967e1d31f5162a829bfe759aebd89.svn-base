<template>
  <el-button class="jm-area-picker">全国<i class="el-icon-arrow-down"></i>

    <el-tabs class="area-list" v-model="activeTab" @tab-click="tabChanged">
      <el-tab-pane label="选择关区" name="guanqu">
        <ul class="clearfix">
          <li v-for="item in guanquList" :key="item"><span>{{item}}</span></li>
        </ul>

      </el-tab-pane>
      <el-tab-pane label="选择口岸" name="kouan">
        <ul class="clearfix">
          <li v-for="item in kouanList" :key="item"><span>{{item}}</span></li>
        </ul>
      </el-tab-pane>
    </el-tabs>

  </el-button>
</template>
<script>
  export default {
    name: 'areaPicker',
    data() {
      return {
        activeTab: 'guanqu',
        guanquList: [
          "南宁关区",
          "天津关区",
          "石家庄关区",
          "太原关区",
          "满洲里关",
          "呼特关区",
          "沈阳关区",
          "大连海关",
          "长春关区",
          "哈尔滨区",
          "上海海关",
          "南京海关",
          "杭州关区",
          "宁波关区",
          "合肥海关",
          "福建关区"
        ],
        kouanList: [
          "防城海关",
          "东兴海关",
          "桂林海关",
          "凭祥海关",
          "广西钦州保税港区",
          "钦州海关",
          "南宁保税物流中心",
          "北海海关",
          "凭祥海关"
        ]
      }
    },
    methods: {
      tabChanged() {

      }
    }
  }

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  .jm-area-picker {
    position: relative;

    i {
      margin-left: 80px;
      transition: all 0.5s;
    }

    &:after {
       display: block;
       content: '';
       position: absolute;
       width: 100%;
       height: 100%;
       top: 50%;
       right: -1px;
       z-index: 19999999;
     }

    &:active {
       color: inherit;
       border-color: inherit;
     }

    &:hover {
       color: rgb(5, 122, 207);
       border-color: rgb(5, 122, 207);
      i {
        transform: rotate(180deg);
      }

      .area-list {
        display: block;
      }
    }

    .area-list {
      display: none;
      position: absolute;
      min-width: 462px;
      right: -1px;
      top: 35px;
      z-index: 10;
      border-radius: 3px;
      border: 1px solid #0086e9;
      color: #666;
      background: #fff;
      z-index: 29999999;

      ul {
        padding-left: 30px;
        margin: 0 0 15px;
      }

      li {
        box-sizing: border-box;
        float: left;
        list-style: none;
        text-align: left;
        width: 33.33333333%;
        margin-bottom: 15px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        padding-right: 30px;

        &:hover {
           color: #0086e9;
         }

      }
    }
  }
</style>
