<template>
  <div id="app">
    <top-header v-if="!isLoginPage"></top-header>

    <main class="jm-body" v-if="!isLoginPage">
      <side-bar-nav></side-bar-nav>

      <div class="jm-main">
        <div class="jm-content">
          <router-view></router-view>
        </div>
      </div>
    </main>

    <router-view v-if="isLoginPage"></router-view>

  </div>
</template>

<script>
import topHeader from '@/components/Header'
import sideBarNav from '@/components/SideBarNav'

export default {
  name: 'app',
  data () {
    return {
        isLoginPage: false
    }
  },
  components:{
    topHeader,
    sideBarNav
  },
  watch: {
    '$route' (to, from) {
      // 对路由变化作出响应...
      console.log(this.$route.path)
      console.log(to)
      console.log(from)
    }
  },
  methods: {
    routeChange () {
      let path = this.$route.path
      if (path === '/login') {
        this.isLoginPage = true;
      } else {
        this.isLoginPage = false;
      }
    }
  },
  mounted() {
    
  },
  created () {
    this.routeChange()
  }
}
</script>

<style>

</style>
