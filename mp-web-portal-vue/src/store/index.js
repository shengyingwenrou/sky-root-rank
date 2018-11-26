
import Vue from 'vue'
import Vuex from 'vuex'
import sys from './modules/sys'
import user from './modules/user'
import portal from './modules/portal'
import * as  actions from './actions'
import * as  getters from './getters'
import state from './state'
import mutations from './mutations'
import logger from 'vuex/dist/logger'


Vue.use(Vuex);

const debug = process.env.BASE_URL !== 'prodution'

const store = new Vuex.Store({
   modules: {
       sys:sys,
       user:user,
       portal:portal
   },
   actions:actions,
   getters:getters,
   state:state,
   mutations:mutations,
   strict:debug,
   plugins:debug?[logger()]:[],
   computed: {
   }
});

  /**
   * 预研失败TODO
   * 初始化Portal请求前必备初始基础参数信息  网关请求协议查看是否能够获取
  const url ='/address/';
  axios({
        method: 'get',
        url:url
      })
  .then(function(respone){
    debugger;
    console.log(respone);
  }).catch(function(error){
    console.log(error);
  });
**/

export default store
