<template>
  <aside class="jm-sidebar">
      <el-menu :default-active="activeIndex" class="" theme="dark" :router="true">
        <el-submenu index="1">
          <template slot="title">用户行为</template>
          <el-menu-item index="home" ><span class="icon-item icon-ll">流量分析</span></el-menu-item>
        </el-submenu>

        <el-submenu index="2">
          <template slot="title">运营情况</template>
          <el-menu-item index="overview" ><span class="icon-item icon-zl">总览</span></el-menu-item>
          <el-menu-item index="order" ><span class="icon-item icon-order">区域订单统计</span></el-menu-item>
          <el-menu-item index="company"><span class="icon-item icon-company">企业分类统计</span></el-menu-item>
          <el-menu-item index="importExport"><span class="icon-item icon-jck">进出口分类统计</span></el-menu-item>
        </el-submenu>

        <!--<el-submenu index="3">
          <template slot="title">活动分析</template>
          <el-menu-item index="3-1"><span class="icon-item icon-activity">活动分析</span></el-menu-item>
        </el-submenu>

        <el-submenu index="4">
          <template slot="title">BI报告</template>
          <el-menu-item index="4-1"><span class="icon-item icon-report">BI报告</span></el-menu-item>
        </el-submenu>-->
      </el-menu>
    </aside>
</template>
<script>
  export default {
    name: 'sideBarNav',
    computed: {
      activeIndex() {
        return this.$route.name;
      }
    }
  }

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  $sidebar-width: 172px !default;
  $topbar-height: 60px !default;
  .jm-sidebar {
    width: $sidebar-width;
    top: $topbar-height;
    left: 0;
    z-index: 1001;
    display: block;
    min-height: 100%;
    color: #8f9aae;
    background-color: #223242;
    height: 100%;
    position: fixed;
    .icon-item {
      position: relative;
      padding-left: 15px;
      &::after {
        content: '';
        display: block;
        width: 22px;
        height: 20px;
        position: absolute;
        top: 50%;
        left: -20px;
        margin-top: -10px;
        background: url(../assets/images/sidebar/menu-icon-group.png) no-repeat;
      }
    }
    .icon-ll::after {
      background-position: 0 -28px;
    }
    .icon-zl::after {
      background-position: -34px -28px;
    }
    .icon-order::after {
      background-position: -66px -28px;
    }
    .icon-company::after {
      background-position: -99px -28px;
    }
    .icon-jck::after {
      background-position: -131px -28px;
    }
    .icon-activity::after {
      background-position: -165px -28px;
    }
    .icon-report::after {
      background-position: -195px -28px;
    }
    .el-menu-item.is-active,
    .el-menu-item:hover {
      color: #52c0e3;
      .icon-ll::after {
        background-position: 0 0;
      }
      .icon-zl::after {
        background-position: -34px 0px;
      }
      .icon-order::after {
        background-position: -66px 0px;
      }
      .icon-company::after {
        background-position: -99px 0px;
      }
      .icon-jck::after {
        background-position: -131px 0px;
      }
      .icon-activity::after {
        background-position: -165px 0px;
      }
      .icon-report::after {
        background-position: -195px 0px;
      }
    }
    .el-submenu__title {
      font-size: 16px;
    }
    .el-submenu {
      border-bottom: 1px solid #0f2133;
    }
    .el-menu--dark .el-menu-item,
    .el-menu--dark .el-submenu__title {
      background-color: #223242;
    }
  }
</style>
