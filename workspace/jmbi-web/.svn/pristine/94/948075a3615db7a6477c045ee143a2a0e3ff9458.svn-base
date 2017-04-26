import Vue from 'vue'
import Router from 'vue-router'
import UserMsg from '@/views/userMsg'

// split code to improve the performance
const Home = resolve => require.ensure([], () => resolve(require('@/views/Home')), 'home')
const Overview = resolve => require.ensure([], () => resolve(require('@/views/Overview')), 'overview')
const AreaOrder = resolve => require.ensure([], () => resolve(require('@/views/AreaOrder')), 'areaOrder')
const Company = resolve => require.ensure([], () => resolve(require('@/views/Company')), 'company')
const ImportExport = resolve => require.ensure([], () => resolve(require('@/views/ImportExport')), 'importExport')

Vue.use(Router)

export default new Router({
  // mode: 'history',
  routes: [{
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/overview',
      name: 'overview',
      component: Overview
    },
    {
      path: '/order',
      name: 'order',
      component: AreaOrder
    },
    {
      path: '/company',
      name: 'company',
      component: Company
    },
    {
      path: '/importExport',
      name: 'importExport',
      component: ImportExport
    },
    {
      path: '/userMsg',
      name: 'userMsg',
      component: UserMsg
    },
    {
      path: '/',
      redirect: '/home'
    }
  ]
})
