import Vue from 'vue'
import VueRouter from 'vue-router'
const _import = require('./_import_' + process.env.NODE_ENV);

Vue.use(VueRouter);

const androidPath = 'android';
const iosPath = 'ios';
const pcPath = 'pc';

export const routerMap = [
  /* 入口 route */
  {path: '/', redirect: '/index'},
  {path: '/portal/index', redirect: '/index'},
  {path: '/index', name: 'index', component: _import('portal/v3/android/index')},

  /* portal android route */
  {path: '/portal/v3/' + androidPath + '/index', name: 'android_index', component: _import('portal/v3/' + androidPath + '/index')},

  {path: '/portal/v3/' + androidPath + '/connect', name: 'android_connect', component: _import('portal/v3/' + androidPath + '/connect')},
  {path: '/portal/v3/' + androidPath + '/success', name: 'android_success', component: _import('portal/v3/' + androidPath + '/success'),meta: { requiresAuth: true }},

  /* portal ios route */
  {path: '/portal/v3/' + iosPath + '/index', name: 'ios_index', component: _import('portal/v3/' + iosPath + '/index')},
  {path: '/portal/v3/' + iosPath + '/connect', name: 'ios_connect', component: _import('portal/v3/' + iosPath + '/connect'),meta: { requiresAuth: true }},
  {path: '/portal/v3/' + iosPath + '/success', name: 'ios_success', component: _import('portal/v3/' + iosPath + '/success'),meta: { requiresAuth: true }},

  /* portal pc route */
  {path: '/portal/v3/' + pcPath + '/index', name: 'pc_index', component: _import('portal/v3/' + pcPath + '/index')},
  {path: '/portal/v3/' + pcPath + '/connect', name: 'pc_connect', component: _import('portal/v3/' + pcPath + '/connect'),meta: { requiresAuth: true }},
  {path: '/portal/v3/' + pcPath + '/success', name: 'pc_success', component: _import('portal/v3/' + pcPath + '/success'),meta: { requiresAuth: true }},

  {path: '/error/404', name: '404', component: _import('error/404')},
  {path: '*', redirect: '/error/404'}
];

var router =  new VueRouter({
     routes:routerMap
})

export default router;

