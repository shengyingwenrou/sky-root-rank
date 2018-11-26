<template>

        <scroller class="container">
            <div class="normal-div" >
                <img class="popup-ad-back-img" :src="timeDetailIcon" >
            </div>

            <div v-if="popUpAd.type==1" class="outer-div popup-ad-content" @click="linkClick()">
                <div class="normal-div">
                    <text class="text-center title">30天无限流量</text>
                    <text class="text-center orignal_price">原价:{{popUpAd.originalPrice||0}}元</text>

                    <div class="only-desc">
                        <text class="text-center only-desc-line margin-right-10 color-ff554b discount-line"></text>
                        <text class="text-center color-ff554b discount">特惠{{(popUpAd.discount||10)}}折</text>
                        <text class="text-center only-desc-line margin-left-10 color-ff554b discount-line"></text>
                    </div>

                    <div class="only-price">
                        <text class="text-center margin-right-10 color-ff554b font-size-74">仅</text>
                        <text class="text-center color-ff554b font-size-142">{{finalPrice}}</text>
                        <text class="text-center margin-left-10 color-ff554b font-size-74">元</text>
                    </div>
                </div>

                <div class="normal-div">
                    <img  @click="closePopUpAd()" class="popup-ad-close-img" :src="closeIcon" >
                </div>
            </div>

            <div v-else class="outer-div popup-ad-content" @click="linkClick()">

                <div class="normal-div">
                    <img  @click="closePopUpAd()" class="popup-ad-type-close-img" :src="closeIcon" >
                </div>

            </div>

        </scroller>

</template>

<script>

  import {CACHE_TENANT_ID, getImagePath} from "../config/Config";
  import Native from '../util/Native';
  import Dao from '../util/Dao';


  export default {
    components: {},
    data() {
      return {
        closeIcon: '',
        timeDetailIcon:'',
        emptyIcon: '',
        tenantId: 0,
          sellingPrice: 0,//售价（类型为代金券时候用）
          originalPrice: 0,//原价（类型为代金券时候用）
          discount: 0, //折扣（类型为代金券时候用）
          imageUrl: "",  //图片地址
          destination: 0,   //客户端本地跳转目标
          commonParam: false, //是否带公告参数
          linkType: 0,//广告跳转类型(0仅展示/1000url链接/1001客户端本地跳转/1002混编页面/1003客户端
          type: 0, //弹窗广告类型 (0活动  / 1优惠券/2到期弹窗)
          addition: "",//附加参数(url链接地址/混编页面js地址)
          popUpAd:{},
          finalPrice:0,
          devDefaultData:{
              sellingPrice: 0.0,
              originalPrice: 0.0,
              imageUrl: "http://r.magicwifi.com.cn/admin/wifi_wizard/20180810/jpeg/a8/94/a8948d18da5341a69a05d0154e4943ec.jpeg",
              destination: 0,
              commonParam: false,
              discount: 0.0,
              linkType: 0,
              type: 0,
              addition: ""
          }
      }
    },
      created() {
      this.timeDetailIcon = getImagePath('ad_plans_new', '.png');
      this.closeIcon = getImagePath('loading_close', '.png');
      this.init();
    },
    mounted() {
      this.loadPopupAd(this.params);
    },

    methods: {

        loadPopupAd() {
            Dao.getPopupAd(this.params)
                .then((res) => {
                    console.log(JSON.stringify(res));
                    if (res && res.result) {
                        //debugger
                        this.popUpAd = res.data;
                        //this.popUpAd.type=1;
                        console.log(JSON.stringify(this.popUpAd));
                        this.finalPrice=this.getfinalPrice();
                        this.timeDetailIcon=this.popUpAd.imageUrl;
                    }else{
                        // 网络连接失败
                        if (res.code === -1) {
                            // 通知联网失败
                            //event.$emit("informConnectFailed",true);
                        }
                    }
                })
        },

        linkClick(){

            this.closePopUpAd();
            // 跳转类型
            let linkType = this.popUpAd.linkType;
            // 客户端本地跳转
            let destination = this.popUpAd.destination;
            // url 混编地址跳转
            let addition = this.popUpAd.addition;

            if (linkType) {
                /** 0:仅展示  1000:url链接  1001:客户端本地跳转  1002:混编页面  1003:客户端下载  1004:系统下载*/
                if (destination || addition) {
                    Native.jump.open(linkType, destination, addition);
                } else {
                    Native.modal.toast("跳转失败，目标地址为空");
                }
            }
        },
        closePopUpAd(){
            let instanceId = weex.config.instanceId;
            if(instanceId!=undefined){
                Native.jump.closeDialog(instanceId);
            }
        },

        /** 计算优惠后的价格做展示 **/
        getfinalPrice: function () {
            let sellingPrice =this.popUpAd.sellingPrice;
            let discount = this.popUpAd.discount;
            if (typeof(sellingPrice) == "string") {
                if (sellingPrice != null && sellingPrice != '') {
                    sellingPrice = parseFloat(sellingPrice);
                }
            }
            if (typeof(discount) == "string") {
                if (discount != null && discount != '') {
                    discount = parseFloat(discount);
                }
            }
            let finalPrice = sellingPrice * discount / 10;
            finalPrice = finalPrice.toFixed(2);
            return finalPrice;
        },
    }
  };
</script>

<style scoped>



    .container {
        flex: 1;
        background-color: transparent;
        border-top-left-radius: 30px;
        border-top-right-radius: 30px;
        border-bottom-left-radius: 30px;
        border-bottom-right-radius: 30px;
        margin-top: 200px;
    }

    .outer-div{
        width:750px;
        min-height: 800px;
        justify-content: flex-start;
        flex-direction: column;
    }


    .normal-div{
        width:750px;
        min-height: 100px;
        justify-content: center;
        flex-direction: column;
    }

    .popup-ad-content{
        margin-top: 10px;
        margin-bottom: 10px;
        margin-left: 5px;
        position: absolute;
        top: 120px;
    }

    .margin-left-10{
        margin-left: 10px;
    }

    .margin-right-10{
        margin-right: 10px;
    }

    .text-center{
        text-align: center;
    }

    .popup-ad-back-img{
        width: 600px;
        height:742px;
        justify-content: space-between;
        flex-direction: column;
        margin-left: 75px;
    }


    .popup-ad-close-img{
        width: 64px;
        height:64px;
        margin-left: 348px;
        margin-top: 210px;
    }


    .popup-ad-type-close-img{
        width: 64px;
        height:64px;
        margin-left: 348px;
        margin-top: 640px;
    }

    .title{
        font-size: 46px;
        color: #474747;
        height: 90px;
        justify-content: end;
        flex-direction: row;
        line-height: 90px;
    }

    .orignal_price{
        color: #acacac;
        text-decoration: line-through;
        font-size: 34px;
        margin-top: 20px;
    }

    .color-ff554b{
        color: #ff554b;
    }

    .font-size-74{
        font-size:74px;
        margin-top: 50px;
        letter-spacing: 8px;
    }

    .font-size-142{
        font-size:112px;
    }

    .only-price{
        justify-content: center;
        flex-direction: row;
        margin-top: 34px;
    }

    .only-desc{
        justify-content: center;
        flex-direction: row;
    }

    .only-desc-line{
        height: 10px;
        width: 80px;
        border-bottom-color: #ff554b;
        border-bottom-width: 1px;
    }

    .discount{
        font-size: 30px;
        height: 30px;
        line-height: 30px;
        margin-top: 30px;
    }

    .discount-line{
        height: 30px;
        line-height: 30px;
        margin-top: 16px;
    }

</style>