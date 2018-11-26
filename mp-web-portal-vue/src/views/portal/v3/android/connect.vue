<template>

      <div class="lf">
        <div class="lf">
          <svg class="margin_top_up_40" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px"
               y="0px" viewBox="0 0 1000 1000" style="enable-background:new 0 0 1000 1000;" xml:space="preserve">
            <!-- Stroke ring -->
            <circle class="st0 color_stroke" cx="50%" cy="50%" r="5.634rem">
                <animateTransform attributeType="xml" attributeName="transform" type="rotate" from="0 500 500" to="360 500 500" dur="100s" repeatCount="indefinite" />
            </circle>
            <!-- Inner ring -->
            <circle class="st1 color_stroke" cx="50%" cy="50%" r="3.825rem">
                <animateTransform attributeType="xml" attributeName="transform" type="rotate" from="0 500 500" to="360 500 500" dur="40s" repeatCount="indefinite" />
            </circle>
            <!-- Outer ringer -->
            <circle class="st2 color_stroke" cx="50%" cy="50%" r="7.432rem" transform = "rotate(0 500 500)">
                <animateTransform attributeType="xml" attributeName="transform" type="rotate" from="0 500 500" to="-360 500 500" dur="50s" repeatCount="indefinite" />
            </circle>
            <!-- Outer thin ring -->
            <circle class="st3 color_stroke" cx="50%" cy="50%" r="7.92rem" >
            </circle>
            <text id="show_process_percent" class="title"  x="50%" y="50%">{{connectPageConnectProcess}}</text>
          </svg>
        </div>

        <!-- 联网失败重试  -->
        <div class="lf none">
          <div class="re_connect">
            <button v-on:click="connectReTry();" >重试</button>
          </div>
        </div>

        <!-- 联网结果具体提示信息 -->
        <div class="lf">
          <div class="connect_result">
            {{connectpageConnectMessage}}
          </div>
        </div>

      </div>

</template>


<script>

  import router from '@/router/index'
  import {mapState,mapGetters} from 'vuex'
  import store from '@/store/index.js'
  import request from '@/api/request.js'
  require('@/assets/css/portal_v3/connect_v2.css');

  /** 联网百分比进度 **/
  var connectProcessTimer;
  export default {
    name: 'androidConnect',

    data() {
      return {
        msg: '我是联网页'
      }
    },

    computed:{
        ...mapGetters([
                      'connectpageConnectMessage',
                      'connectPageConnectProcess'
        ])
    },
    mounted() {
      this.initProgressBar();
      this.connect();
    },
    methods: {

      /** 首次进入初始化百分比进度 **/
      initProgressBar:function () {
        // 进度1-100
        var connectProcess = 20;
        connectProcessTimer = setInterval(function () {
          if (connectProcess > 98) {
            window.clearInterval(connectProcessTimer);
            return;
          } else {
            store.commit('SET_CONNECT_PAGE_CONNECT_PROCESS',connectProcess);
          }
          connectProcess++;
        }, 100);
      },

      /** 开始联网 **/
      connect: function () {
        let params = {'command': '104'};
        request.postRequest('/api/portal_v2/terminal/device/connect', params)
          .then(function (respone) {
            if (respone.data.response_header.status === '00') {
              store.commit('SET_CONNECT_PAGE_CONNECT_PROCESS',100);
              store.commit('SET_CONNECT_PAGE_CONNECT_MESSAGE','联网成功');
              this.gotoSucess();
            } else {
              window.clearInterval(connectProcessTimer);
              let errorCode = respone.data.response_header.status;
              let message = respone.data.response_header.error_info;
              $('.re_connect').parent().removeClass('none').addClass('show');
              let result =errorCode +message;
              store.commit('SET_CONNECT_PAGE_CONNECT_MESSAGE',result);
            }
            console.log(respone.data);
          }).catch(function (error) {
             $('.re_connect').parent().removeClass('none').addClass('show');
             window.clearInterval(connectProcessTimer);
             store.commit('SET_CONNECT_PAGE_CONNECT_MESSAGE','联网失败，请重试一次(重复3次连续失败请立即联系客服进行反馈</span>)');
             console.log(error);
          });
      },

      /** 去到联网成功页 **/
      gotoSucess:function(){
        this.$router.push({name:'portal/v3/android/sucess'});
      },

      /** 联网失败重试入口开始 **/
      showReTryConnect:function(){
        $('.re_connect').parent().removeClass('none').addClass('show');
      },

      /** 联网失败重试 **/
      connectReTry:function(){
        this.initProgressBar();
        this.connect();
      }
    }
  }

</script>

<style type="text/css">
  body {
    min-width: 320px !important;
    max-width: 720px !important;
    margin: auto !important;
    background: #fff;
  }
</style>

