/*global Vue*/
import Router from 'vue-router'
import RechargeChangeProject from '@/components/RechargeChangeProject.vue'
import RechargeCoupon from '@/components/RechargeCoupon.vue'
import RechargeCouponUsed from '@/components/RechargeCouponUsed.vue'
import RechargeRecord from '@/components/RechargeRecord.vue'
import RechargeTime from '@/components/RechargeTime.vue'
import PopUpAd from '@/components/PopUpAd.vue'

Vue.use(Router)

module.exports = new Router({
  routes: [
    //{ path: '/',  name: 'index', redirect: '/api_test' },
    { path: '/',  name: 'index', redirect: '/recharge/index' },
    { path: '/recharge/index', name: 'RechargeTime', component: RechargeTime},
    { path: '/recharge/change_project', name: 'RechargeChangeProject', component: RechargeChangeProject },
    { path: '/recharge/coupon', name: 'RechargeCoupon', component: RechargeCoupon },
    { path: '/recharge/coupon_used', name: 'RechargeCouponUsed', component: RechargeCouponUsed },
    { path: '/recharge/record', name: 'RechargeRecord', component: RechargeRecord },
    { path: '/recharge/popup_ad', name: 'PopUpAd', component: PopUpAd }
  ]
})
