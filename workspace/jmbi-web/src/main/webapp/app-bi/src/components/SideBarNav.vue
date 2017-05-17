<template>
  <aside class="jm-sidebar">
    <el-menu :default-active="activeIndex" class="" theme="dark" :router="true">
      <el-submenu index="1" v-if="isVisible('8101')">
        <template slot="title"><i class="icon icon-yhxw"></i>用户行为</template>
        <el-menu-item index="flow-analysis" v-if="isVisible('8102')"><span class="icon-item">流量分析</span></el-menu-item>
        <el-menu-item index="login-analysis" v-if="isVisible('8103')"><span class="icon-item">登录分析</span></el-menu-item>
        <el-menu-item index="user-attension" v-if="isVisible('8104')"><span class="icon-item">用户关注</span></el-menu-item>
      </el-submenu>
  
      <el-submenu index="2" v-if="isVisible('8105')">
        <template slot="title"><i class="icon icon-yyqk"></i>运营情况</template>
        <el-menu-item index="overview" v-if="isVisible('8106')"><span class="icon-item">总览</span></el-menu-item>
        <el-menu-item index="2-1" v-if="isVisible('8107')"><span class="icon-item">注册分析</span></el-menu-item>
  
        <el-menu-item index="increased-user" v-if="isVisible('8107') && isVisible('8108')"><span class="sub-node-item">日新增用户</span></el-menu-item>
        <el-menu-item index="authUser" v-if="isVisible('8107') && isVisible('8109')"><span class="sub-node-item">认证用户</span></el-menu-item>
        <el-menu-item index="signature-pay" v-if="isVisible('8107') && isVisible('8116')"><span class="sub-node-item">签章支付</span></el-menu-item>
  
        <el-menu-item index="2-2" v-if="isVisible('8110')"><span class="icon-item">店铺分析</span></el-menu-item>
  
        <el-menu-item index="shopactived" v-if="isVisible('8110') && isVisible('8111')"><span class="sub-node-item">店铺激活</span></el-menu-item>
        <el-menu-item index="shopoperate" v-if="isVisible('8110') && isVisible('8112')"><span class="sub-node-item">店铺运营</span></el-menu-item>
  
        <el-menu-item index="2-3" v-if="isVisible('8113')"><span class="icon-item">成交分析</span></el-menu-item>
  
        <el-menu-item index="dealingintro" v-if="isVisible('8113') && isVisible('8114')"><span class="sub-node-item">成交概览</span></el-menu-item>
        <el-menu-item index="areaamount" v-if="isVisible('8113') && isVisible('8115')"><span class="sub-node-item">地域金额</span></el-menu-item>
  
      </el-submenu>
  
      <!--<el-submenu index="3">
                          <template slot="title"><i class="icon icon-yjzx"></i>预警中心</template>
                        </el-submenu>
                    
                        <el-submenu index="4">
                          <template slot="title"><i class="icon icon-hdfx"></i>活动分析</template>
                        </el-submenu>
                    
                        <el-submenu index="5">
                          <template slot="title"><i class="icon icon-bibg"></i>BI报告</template>
                        </el-submenu>-->
    </el-menu>
  </aside>
</template>
<script>
export default {
  name: 'sideBarNav',
  created() {
    var visibleMenuList = document.querySelector('#visibleMenuList').value.split(',')
    this.MenuList.forEach(function (item) {
      if (visibleMenuList.indexOf(item.id) !== -1) {
        item.visible = true;
      }
    })

  },
  methods: {
    isVisible(menuId) {
      var item = this.MenuList.find(item => item.id === menuId);
      if (item) {
        return item.visible === true;
      }

      return false;
    }
  },
  computed: {
    activeIndex() {
      return this.$route.name;
    }
  },
  data() {
    return {
      MenuList: [
        { id: '8101', name: '用户行为' },
        { id: '8102', name: '流量分析' },
        { id: '8103', name: '登录分析' },
        { id: '8104', name: '用户关注' },
        { id: '8105', name: '运营分析' },
        { id: '8106', name: '总览' },
        { id: '8107', name: '注册分析' },
        { id: '8108', name: '日新增用户' },
        { id: '8109', name: '认证用户' },
        { id: '8110', name: '店铺分析' },
        { id: '8111', name: '店铺激活' },
        { id: '8112', name: '店铺运营' },
        { id: '8113', name: '成交分析' },
        { id: '8114', name: '成交概览' },
        { id: '8115', name: '地域金额' },
        { id: '8116', name: '签章支付' }
      ]
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
  background-color: #0a245d;
  height: 100%;
  padding-top: 10px;
  position: fixed;
  .icon-item {
    position: relative;
    padding-left: 5px;
    &::after {
      content: '-';
      display: block;
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      left: -10px;
    }
  }
  .sub-node-item {
    padding-left: 20px;
  }

  .el-menu--dark {
    background-color: #0a245d;
  }
  .el-menu--dark .el-menu-item.is-active,
  .el-menu--dark .el-menu-item:hover {
    color: #fff;
    background-color: #1c408e;
  }
  .el-menu--dark .el-submenu .el-menu .el-menu-item:hover {
    background-color: #1c408e;
  }
  .el-submenu__title {
    font-size: 14px;

    .icon {
      display: inline-block;
      width: 20px;
      height: 20px;
      position: relative;
      top: 4px;
      left: -8px;
      background: url(../assets/images/sidebar/menu-icon-group.png) no-repeat;
    }

    .icon-yhxw {
      background-position: 0px 0px
    }
    .icon-yyqk {
      background-position: -28px 0px
    }
    .icon-yjzx {
      background-position: -55px 0px
    }
    .icon-hdfx {
      background-position: -80px 0px
    }
    .icon-bibg {
      background-position: -108px 0px
    }
  }


  .el-submenu {
    border-bottom: 1px solid transparent;
  }
  .el-menu--dark .el-menu-item {
    background-color: #0a245d;
  }

  .el-menu--dark .el-submenu .el-menu {
    background-color: #0a245d;
  }

  .el-menu--dark .el-submenu__title {
    background-color: #061d4f;
  }

  .el-submenu.is-opened>.el-menu {
    margin-top: 15px;
    margin-bottom: 15px;
  }

  .el-menu-item,
  .el-submenu__title {
    height: 35px;
    line-height: 35px;
  }
}
</style>
