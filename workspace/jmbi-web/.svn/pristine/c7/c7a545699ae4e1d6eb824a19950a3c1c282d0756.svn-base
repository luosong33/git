import Vue from 'vue'
import Router from 'vue-router'


// split code to improve the performance
const FlowAnalysis = () => System.import('@/views/FlowAnalysis')
const loginAnalysis = () => System.import('@/views/loginAnalysis')
const UserAttension = () => System.import('@/views/UserAttension')
const Overview = () => System.import('@/views/Overview')
const IncreasedUser = () => System.import('@/views/IncreasedUser')
const AuthUser = () => System.import('@/views/AuthUser')
const SignaturePay = () => System.import('@/views/SignaturePay')
const ShopActived = () => System.import('@/views/ShopActived')
const ShopOperate = () => System.import('@/views/ShopOperate')
const DealingIntro = () => System.import('@/views/DealingIntro')
const AreaAmount = () => System.import('@/views/AreaAmount')
const Company = () => System.import('@/views/Company')
const ImportExport = () => System.import('@/views/ImportExport')

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [{
      path: '/flow-analysis',
      name: 'flow-analysis',
      component: FlowAnalysis,
      meta: {
        menuId: '8102'
      }
    },
    {
      path: '/login-analysis',
      name: 'login-analysis',
      component: loginAnalysis,
      meta: {
        menuId: '8103'
      }
    },
    {
      path: '/user-attension',
      name: 'user-attension',
      component: UserAttension,
      meta: {
        menuId: '8104'
      }
    },
    {
      path: '/overview',
      name: 'overview',
      component: Overview,
      meta: {
        menuId: '8106'
      }
    },
    {
      path: '/increased-user',
      name: 'increased-user',
      component: IncreasedUser,
      meta: {
        menuId: '8108'
      }
    },
    {
      path: '/authUser',
      name: 'authUser',
      component: AuthUser,
      meta: {
        menuId: '8109'
      }
    },
    {
      path: '/signature-pay',
      name: 'signature-pay',
      component: SignaturePay,
      meta: {
        menuId: '8116'
      }
    },
    {
      path: '/shopactived',
      name: 'shopactived',
      component: ShopActived,
      meta: {
        menuId: '8111'
      }
    },
    {
      path: '/shopoperate',
      name: 'shopoperate',
      component: ShopOperate,
      meta: {
        menuId: '8112'
      }
    },
    {
      path: '/dealingintro',
      name: 'dealingintro',
      component: DealingIntro,
      meta: {
        menuId: '8114'
      }
    },
    {
      path: '/areaamount',
      name: 'areaamount',
      component: AreaAmount,
      meta: {
        menuId: '8115'
      }
    }
    // {
    //   path: '/',
    //   redirect: '/flow-analysis'
    // }
  ]
})
