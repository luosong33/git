<template>
  <header class="top-header clearfix">
    <a href="/index"
       class="jm-logo left">
      <img src="../assets/images/top-header/header-logo.png"
           alt="">
      <span>聚贸大数据BI平台</span>
    </a>
    <span class="welcome left">热烈欢迎，{{currentUserName}}！</span>
    <div class="user-profile right">
      <a class="platform">平台切换： <span class="dropdown">聚贸通<i class="icon-link"></i></span>
      <div class="second-nav clearfix">
        <ul>
          <h6>大宗商品交易平台</h6>
          <li><p><a href="javascript:;" target="_blank">聚贸总站</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸化工</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸有色</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸煤炭</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸钢铁</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸矿产</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸农产品</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸石油</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸工业品</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸机械</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸消费品</a></p>
      </li>
      <li>
        <p><a href="javascript:;"
             target="_blank">聚贸食品</a></p>
      </li>
      </ul>
      <ul>
        <h6>服务平台</h6>
        <li>
          <p><a href="/index">聚贸通</a></p>
        </li>
        <li>
          <p><a href="javascript:;">聚运通</a></p>
        </li>
        <li>
          <p><a href="javascript:;">聚融通</a></p>
        </li>
        <li>
          <p><a href="javascript:;">聚智能</a></p>
        </li>
        <li>
          <p><a href="javascript:;">聚认证</a></p>
        </li>
        <li>
          <p><a href="javascript:;">聚咨询</a></p>
        </li>
        <li>
          <p><a href="javascript:;">大数聚</a></p>
        </li>
      </ul>
  
    </div>
    </a>
    <a href="/user/user"
       class="icon-user"></a>
    <a href="/user/list"
       class="icon-setting"
       v-show="currentUserRoleId === '0'"></a>
    <a class="icon-logout"
       href="/doLogout"></a>
    </div>
  
  </header>
</template>
<script>
export default {
  name: 'header',
  created() {
    this.currentUserName = document.getElementById('currentUserName').value;
    this.currentUserRoleId = document.getElementById('currentUserRoleId').value;

  },
  data() {
    return {
      currentUserName: '',
      currentUserRoleId: ''
    }
  }
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
$topbar-height: 60px !default;
.top-header {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 50000;
  width: 100%;
  height: $topbar-height;
  color: #fff;
  background-color: #0588e6;
  min-width: 320px;
  padding-left: 20px;
}

.top-header .jm-logo {
  color: #fff;
  font-size: 18px;
  line-height: $topbar-height;
}

.top-header .jm-logo img {
  width: 37px;
  height: 30px;
  margin-right: 10px;
  display: inline-block;
  vertical-align: middle;
}

.welcome {
  font-size: 14px;
  padding-left: 20px;
  line-height: $topbar-height;
}

.user-profile {
  line-height: $topbar-height;
  .platform {
    font-size: 14px;
    position: relative;
    top: -2px;
  }
}

.icon-user {
  display: inline-block;
  width: 19px;
  height: 18px;
  margin-left: 20px;
  background: url(../assets/images/top-header/icon-user.png) no-repeat;
}

.icon-setting {
  display: inline-block;
  width: 18px;
  height: 18px;
  margin-left: 20px;
  background: url(../assets/images/top-header/icon-setting.png) no-repeat;
}

.icon-logout {
  display: inline-block;
  width: 18px;
  height: 19px;
  margin-left: 20px;
  margin-right: 40px;
  background: url(../assets/images/top-header/icon-logout.png) no-repeat;
}

.platform {
  position: relative;
  height: 60px;
  line-height: 1.375;
  display: inline-block;
  .dropdown {
    .icon-link {
      display: inline-block;
      width: 12px;
      height: 7px;
      position: relative;
      top: -2px;
      margin-left: 10px;
      background: url(../assets/images/icon-arrow-up.png) no-repeat;
      transition: all 0.5s
    }
  }
  .second-nav {
    position: absolute;
    border: 1px solid #0086e9;
    right: 0;
    top: 40px;
    z-index: 6;
    background: #fff;
    overflow: hidden;
    display: none;
    width: 580px;
    padding: 22px 15px 28px 30px;
    color: #666;
    ul {
      float: left;
      list-style: none;
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      &:first-child {
        width: 325px;
        border-right: 1px solid #dedede;
      }
      &:last-child {
        width: 215px;
        margin-left: 30px;
      }
      li {
        float: left;
        width: 33.3333333%;
        box-sizing: border-box;
        margin-bottom: 15px;
      }
      p {
        margin: 0;
      }
      h6 {
        margin: 0;
        margin-bottom: 18px;
        font-size: 16px;
        font-weight: normal;
      }
      a {
        font-size: 14px;
        color: #666;
        &:hover {
          color: #0588e6;
        }
      }
    }
  }
  &:hover {
    .dropdown {
      .icon-link {
        transform: rotate(180deg);
      }
    }
    .second-nav {
      display: block;
    }
  }
}
</style>
