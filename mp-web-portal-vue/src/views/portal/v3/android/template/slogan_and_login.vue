<template>

  <div class="lf">

    <div class="common_template">
      <!-- 欢迎语部分 http://magicwifi.com.cn/magicwifi/static/portal/v2/images/portal_03.png  -->

      <div class="lf">
        <h5>deviceMac:{{baseDeviceMac}}</h5>
        <h5>terminalMac:{{baseTerminalMac}}</h5>
      </div>

      <div class="lf logo">
        <div class="logo_div">
          <img src="@/assets/images/portal_v3/portal_03.png">
        </div>
      </div>

      <div class="logo_name lf">WiFi精灵</div>

      <div class="slogan lf">
        <div class="lf">宿舍用WiFi出门用流量</div>
        <div name="second lf">不限应用不限流量不限速</div>
      </div>

      <div class="lf height_30"></div>

      <div class="lf">
        <div class="user_area">
          <div class="lf">
            <div class="auto width89">
              <div name="user_tel" class="lf">
                <input  placeholder="请输入手机号" style="IME-MODE: disabled;" onkeyup="this.value=this.value.replace(/\D/g,'')"
                        onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="11"
                        id="loginTelephone" name="telephone" autocomplete="off"
                        type="tel" pattern="[0-9]+" value="13048873956">
              </div>
            </div>
            <div name="lf height_10"></div>
            <div class="auto width89">
              <div name="user_pwd" class="lf">
                <div class="lf user_code">
                  <input  placeholder="请输入短信验证码" style="IME-MODE: disabled;"
                          onkeyup="this.value=this.value.replace(/\D/g,'')"
                          onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="4"
                          id="loginAuthCode" name="authCode" autocomplete="off"
                          type="password" pattern="[0-9]+"
                          required="" class="input-common" value="1234">
                </div>
                <div class="user_get_code rf">
                  <button v-on:click="authCode();">获取验证码</button>
                </div>
              </div>
            </div>
          </div>
          <div class="lf height_30"></div>
          <div class="lf width100">
            <div class="auto width89 ">
              <div class="lf connect" v-on:click="login();">
                  <button name='connect' type="button" class="formSubmitBtn mourse_way back_c_0091f7
                        width100 margin_top_20 android_new_login_button">开始上网(新用户免费1天)</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import {mapState,mapGetters} from 'vuex'
  import router from '@/router/index.js'
  import store from '@/store/index.js'
  import request from '@/api/request.js'
  import toastUtil from '@/assets/js/common/checkTelphoneAndAuthCode.js'


  require('@/assets/css/common/toast.css')
  require('@/assets/css/portal_v3/index_v2.css')

  export default {
    components: {
    },
    name: 'androidIndex',

    mounted(){
      this.init();
    },
    computed: {
      ...mapState({
                    count: 'numb',
                    count1: 'count'
                  }),
      ...mapGetters([
                      'fruit',
                      'fruitTodos',
                      'baseDeviceMac',
                      'configPortalInfo',
                      'baseTerminalMac'
                    ]),
      doneTodosCount1 () {
        return store.state.todos.filter(todo => todo.done).length
      },
      //通过方法访问
      getTodos:function(){
        return store.state.todos;
      }
    },

    methods:{
      /** 初始化基础参数信息 **/
      init(){
        let deviceMac = router.history.current.query.deviceMac;
        let terminalMac = router.history.current.query.terminalMac;
        let config={
          deviceMac:deviceMac,
          terminalMac:terminalMac
        }
        store.commit('SET_DEVICE_CONFIG', config);
        var params={'command':'102'}
        request.postRequest('/api/portal_v2/terminal/config/conf', params)
          .then(function (respone) {
            if (respone.data.response_header.status === '00') {
              store.commit('SET_DEVICE_CONFIG', respone.data.response.deviceInfo);
              store.commit('SET_PORTAL_CONFIG', respone.data.response.portalInfo);
            }
            console.log(respone.data);
          })
          .catch(function (error) {
            console.log(error);
          });
      },

      /** 获取手机验证码 **/
      authCode(){
        let loginTelephone=$('#loginTelephone').val();
        let checkTelResult = toastUtil.checkTelphone(loginTelephone);
        if(!checkTelResult){
          return;
        }
        let params = {'command': '103', 'telephone': loginTelephone};
        request.postRequest('/api/portal_v2/terminal/account/authCode', params)
          .then(function (respone) {
            if (respone.data.response_header.status === '00') {
              toastUtil.toastMessage('验证码已经发送，请注意查收');
            } else {
              let errorCode = respone.data.response_header.status;
              let message = respone.data.response_header.error_info;
              toastUtil.toastMessage( errorCode + '<br>' + message);
            }
            console.log(respone.data);
          })
          .catch(function (error) {
            console.log(error);
          });
      },

      /** 异步登录 **/
      login(){

        let token= store.state.user.base_token;
        // token为空重新异步登录
        if(!token) {
          let telephone = $('#loginTelephone').val();
          let authCode = $('#loginAuthCode').val();
          let checkTelResult = toastUtil.checkTelphone(telephone);
          let checkAuthCodeResult = toastUtil.checkAuthCode(authCode);
          if (!checkTelResult || !checkAuthCodeResult) {
            return;
          }
          let params = {'command': '103', 'telephone': telephone, 'authCode': authCode}
          request.postRequest('/api/portal_v2/terminal/account/login', params)
            .then(function (respone) {
              if (respone.data.response_header.status === '00') {
                debugger
                store.commit('SET_USER_LOGIN_INFO', respone.data.response.info);
                router.push({path: '/portal/v3/android/connect'});
              } else {
                let errorCode = respone.data.response_header.status;
                let message = respone.data.response_header.error_info;
                toastUtil.toastMessage(errorCode + '<br>' + message);
              }
              console.log(respone.data);
            })
            .catch(function (error) {
              console.log(error);
            });
        }else{
          // token不为空直接checkToken 验证有效性
          let params = {'command': '104', 'token': token}
          request.postRequest('/api/portal_v2/terminal/account/checkToken', params)
            .then(function (respone) {
              if (respone.data.response_header.status === '00') {
                router.push({path: '/portal/v3/android/connect'});
              } else {
                let errorCode = respone.data.response_header.status;
                let message = respone.data.response_header.error_info;
                store.commit('SET_USER_LOGIN_INFO', respone.data.response.info);
                toastUtil.toastMessage(errorCode + '<br>' + message);
              }
              console.log(respone.data);
            })
            .catch(function (error) {
              console.log(error);
            });
        }
      },
      /** 联网跳转 **/
      connect (){
        router.push({name: '/portal/v3/android/connect',params:{id:'1'}});
      }
    },

    data() {
      return {
        isJiuweiSpecially: true,
        gwType: 0,
        repsstr: '123'
      }
    }
  }

</script>
