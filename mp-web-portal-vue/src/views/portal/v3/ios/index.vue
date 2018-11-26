<template>

  <div class="lf">
    <div class="common_template">
      <!-- 欢迎语部分  -->
      <div class="logo">
        <img src="http://magicwifi.com.cn/magicwifi/static/portal/v2/images/portal_03.png">
      </div>

      <div class="logo_name">WiFi精灵</div>

      <div class="slogan">
        <div>宿舍用WiFi出门用流量</div>
        <div name="second">不限应用不限流量不限速</div>
      </div>

      <div class="lf height_30"></div>

      <div class="lf">
        <div class=" user_info">
          <div name="user_tel" class="lf">
            <input  placeholder="请输入手机号" style="IME-MODE: disabled;" onkeyup="this.value=this.value.replace(/\D/g,'')"
                    onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="11"
                    id="loginTelephone" name="telephone" autocomplete="off"
                    type="tel" pattern="[0-9]+" value="13048873956">
          </div>
          <div name="lf height_10"></div>
          <div name="user_pwd" class="lf">
            <div class="lf user_code">
              <input  placeholder="请输入短信验证码" style="IME-MODE: disabled;"
                      onkeyup="this.value=this.value.replace(/\D/g,'')"
                      onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="4"
                      id="loginAuthCode" name="authCode" autocomplete="off"
                      type="password" pattern="[0-9]+"
                      required="" class="input-common" value="abcd">
            </div>
            <div class="user_get_code rf"><button>获取验证码</button></div>
          </div>
        </div>
      </div>

      <div class="lf height_30"></div>
      <div class="lf connect">
        <div name="connect">开始上网(新用户免费1天)</div>
      </div>

      <div class="lf height_10"></div>
      <div class="lf height_10"></div>

      <div class="lf height_52">
        <div class="down_load">
          <div name="first">下载APP-免费上网</div>
          <div name="second">(赚豆换时长)</div>
          <div name="bag_tip"></div>
        </div>
      </div>

      <div class="lf height_40">
        <div class="constract">

          <div name="tel" class="lf">
            <div class="tel_tip lf" ></div>
            <div class="tel_num lf">:<a  class="" href="tel:400-821-7110">400-666-5780</a> </div>
          </div>

          <div name="wechat" class="rf">
            <div class="wechat_name rf">:<a  class="" >magicwifi</a></div>
            <div class="wechat_tip rf"></div>
          </div>

        </div>
      </div>

      <div class="split_p lf"></div>

      <div class="lf height_20"></div>
      <div class="lf intro_part">下载APP 上网更方便</div>
      <div class="lf intro_part_desc">一次下载  永久自动连接</div>
      <div class="lf intro_part_img">
        <div class="auto">
          <img src="http://magicwifi.com.cn/magicwifi/static/portal/v2/images/icon_sy_img_lw.png">
        </div>
      </div>
      <div class="intro_part_dotted lf"></div>

      <div class="lf height_20"></div>
      <div class="lf intro_part">做任务 不花钱也能上网</div>
      <div class="lf intro_part_desc">动动手指免费上网</div>

      <div class="lf intro_part_img">
        <div class="auto">
          <img src="http://magicwifi.com.cn/magicwifi/static/portal/v2/images/icon_sy_img_yy.png">
        </div>
      </div>

      <div class="intro_part_dotted lf"></div>

      <div class="lf intro_part_download">
        <!--<img src="http://magicwifi.com.cn/magicwifi/static/portal/v2/images/icon_sy_az.png"> -->
        <button>免流量下载APP</button>
      </div>

      <div class="lf height_20"></div>
    </div>
  </div>
</template>
<script>
  require('@/assets/css/portal_v3/index_v2.css');
  export default {
    components: {
    },
    name: 'androidIndex',
    data() {
      return {
        isJiuweiSpecially: true,
        gwType: 0
      }
    }
  }
</script>
