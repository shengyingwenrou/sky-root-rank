<template>

    <scroller   class="back_reset" >
        <wdg-minibar title="优惠券" @wdgMinibarLeftClicked="toBack()"></wdg-minibar>
        <connect-failed ref="connectFailed" @onReLoad="reLoad"></connect-failed>
        <!-- 优惠券列表 -->
        <div class="position-content" v-if="enableCoupons!==undefined && enableCoupons!==null && enableCoupons.length > 0">
            <div v-for="(item,index) in enableCoupons">
                <div class="coupon_ouetr">
                    <image v-if="item.isAvailable === 1" :class="['coupon_active']" :src="couponChooseIcon"></image>
                    <image v-else :class="['coupon_active']" :src="couponNotActiveIcon"></image>
                    <div :class="['coupon-item-info', 'coupon-item-active']" @click="changeActive(index)">
                        <div class="item-col-f">
                            <div class="col-f-t">
                                <div class="col-f-t-r" v-if="item.preferenceType===1">
                                    <text :class="['col-f-t-r-text', item.isAvailable === 1 ? 'color_F6623C' : 'color_cbcbcb']">{{item.preferenceAmount}}</text>
                                    <text :class="['margin-top-52','font-size-30', item.isAvailable === 1 ? 'color_F6623C' : 'color_cbcbcb']">元</text>
                                </div>
                                <div class="col-f-t-r" v-else="item.preferenceType===0">
                                    <text :class="['col-f-t-r-text', item.isAvailable === 1 ? 'color_F6623C' : 'color_cbcbcb']">{{item.preferenceAmount / 10}}</text>
                                    <text :class="['margin-top-52','font-size-30',item.isAvailable === 1 ? 'color_F6623C' : 'color_cbcbcb']">折</text>
                                </div>
                            </div>
                            <div class="col-f-bt">
                                <text :class="['col-f-bt-text', item.isAvailable === 1 ? 'color_F6623C' : 'color_cbcbcb']">满{{item.limitCondition}}天可用</text>
                            </div>
                        </div>

                        <div class="item-col-sp">

                        </div>

                        <div class="item-col-s">
                            <div >
                                <text :class="['item-col-s-row-one-text',item.isAvailable === 1 ? '' : 'color_cbcbcb']">{{item.name}}</text>
                            </div>
                            <div class="item-col-s-row-20">
                                <text :class="['item-col-s-row-two-text',  item.isAvailable === 1 ? 'color_757575' : 'color_cbcbcb']">{{item.describe}}</text>
                            </div>

                            <div class="item-col-s-row-16">
                                <text :class="['item-col-s-row-two-text', item.isAvailable === 1 ? 'color_757575' : 'color_cbcbcb']">期限:{{item.startTime}}-{{item.endTime}}</text>
                            </div>
                        </div>
                        <div class="item-col-fo">
                            <wdg-button v-if="item.isAvailable===1" text="立即使用" @wdgButtonClicked="useBtnClicked"
                                        :btnStyle="useBtnStyle"
                                        :text-style="useBtnTxtStyle"></wdg-button>
                        </div>
                    </div>
                    <div class="item-useless-reason" v-if="item.isAvailable !== 1">
                        <image v-if="item.isAvailable !== 1" :class="['coupon_not_active_bottom_img']" :src="couponNotActiveBottomIcon"></image>
                        <div class="coupon_not_active_bottom">
                            <text class="item-useless-reason-desc-text">不可用原因</text>
                            <text class="item-useless-reason-content-text">{{item.unavailableDesc}}</text>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div v-else>
            <div class="empty-list">
                <div class="empty-list-v">
                    <image class="empty-list-img" :src="quanIcon"></image>
                </div>
                <text class="empty-text">暂无优惠券</text>
            </div>
        </div>
        <!-- 优惠券列表 end -->

        <!-- more 查看更多已经使用或者过期的优惠券 start -->
        <div class="coupon-more" >
            <div class="coupon-more-item-l">
                <text class="coupon-more-item-l-text">没有更多有效券了</text>
            </div>
            <!--<div class="cpupon-more-item-r" @click="jumpWithParams('RechargeCouponUsed')">-->
            <div class="coupon-more-item-r" @click="jumpWithParams('RechargeCouponUsed')">
                <text class="coupon-more-item-r-text">查看更多或已使用的券</text>
            </div>
        </div>
        <!-- more 查看更多已经使用或者过期的优惠券 end -->
    </scroller>
</template>

<script>
  import WdgMinibar from './widget/WdgMinibar';
  import WdgButton from './widget/WdgButton.vue';
  import ConnectFailed from './widget/ConnectFailed';
  import {getImagePath} from "../config/Config";
  import Dao from '../util/Dao';
  import Native from "../util/Native";
  import event from '../config/event';

  export default {
    components: {WdgMinibar, WdgButton,ConnectFailed},

    data() {
      return {
        yhqxzIcon: '',
        quanIcon: '',
        couponChooseIcon: '',
        couponNotActiveIcon: '',
        couponNotActiveBottomIcon: '',
        useBtnStyle: { // 支付按钮样式
            'width': '140px',
            'height': '60px',
            'line-height': '60px',
            'background-color': '#FFFFFF',
            'border-radius': '30px',
            'border-width': '1px',
            'border-style': 'solid',
            'border-radius': '10px',
            'border-color': '#F6623C',
            'position': 'absolute',
            'right': '12px',
            'top': '60px'
        },


        couponBackStyle: { // 选中背景图
          'background-image': this.couponChooseIcon
        },
        useBtnTxtStyle: { // 支付按钮文字样式
          'font-size': '24px',
            'color':'#F6623C'
        },
        enableCoupons: []
      };
    },
    created() {
      this.couponNotActiveBottomIcon = getImagePath('not_choose_bottom_part', '.png');
      this.couponNotActiveIcon = getImagePath('not_choose_top_part', '.png');
      this.couponChooseIcon = getImagePath('not_choose_c', '.png');
      this.yhqxzIcon = getImagePath('icon_yhq_xz', '.png');
      this.quanIcon = getImagePath('quan', '.png');
      this.init();
    },
    mounted() {
      this.loadAccountCardList();
    },
    methods: {

        reLoad(){
            /** 子组件网络状态展示取消 **/
            this.$refs.connectFailed.setIsShowConnectFailed(false);
            /** 页面重新reLoad **/
            Native.reload();
        },

      loadAccountCardList() {
        this.params.type = 1;
        Dao.getAccountCardList(this.params)
          .then((res) => {
            if (res && res.result) {
              this.enableCoupons = res.data;
            }else{
                // 网络连接失败
                if (res.code === -1) {
                    // 通知联网失败
                    event.$emit("informConnectFailed",true);
                }
            }
          })
      },
      changeActive(index) {
        for (let i = 0; i < this.enableCoupons.length; i++) {
          this.enableCoupons[i].isActive = false;
          if (i === index) {
            this.enableCoupons[index].isActive = true;
          }
        }
      },
      useBtnClicked() {
        Native.jump.weexAndClose(this.getWeexUrl("index"));
      }
    }
  };
</script>

<style scoped>

    .position-content{
        z-index: 1;
    }

    .img_area{
        width: 740px;
        margin: auto;
        justify-content: center;
    }
    .coupon_ouetr {
        width: 750px;
        justify-content: center;
    }

    .back_reset{
        background-color: #e8e8e8;
    }

    .coupon_active {
        flex-direction: column;
        width: 704px;
        height: 180px;
        margin: auto;
        margin-left: 24px;
        justify-content: center;
        margin-top: 26px;
    }
    .coupon_not_active {
        width: 710px;
        height: 118px;
        margin-left: 20px;
        margin-top: 40px;
    }

    .coupon_not_active_bottom_img {
        width: 704px;
        height: 120px;
        margin-left: 24px;

    }

    .coupon_not_active_bottom {
        width: 710px;
        height: 118px;
        margin-left: 20px;
        position: absolute;
        top: 0;
    }

    .font-size-30 {
        font-size: 30px;
    }

    .margin-top-52 {
        margin-top: 56px;
    }

    .empty-list {
        width: 750px;
        height: 520px;
    }

    .empty-list-v {
        width: 750px;
        height: 134px;
        margin-top: 280px;
    }

    .empty-list-img {
        width: 165px;
        height: 112px;
        margin-left: 292px;
    }

    .empty-text {
        color: #9e9e9e;
        text-align: center;
        font-size: 30px;
    }

    .coupon-more {
        width: 750px;
        height: 100px;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-around;
        margin-top: 50px;
    }

    .coupon-more-item-l-text {
        height: 80px;
        line-height: 80px;
        color: #9e9e9e;
        text-align: right;
        font-size: 26px;
    }

    .coupon-more-item-r-text {
        height: 80px;
        line-height: 80px;
        color: #F6623C;
        text-align: left;
        padding-left: 20px;
        font-size: 26px;
    }

    .coupon-more-item-l {
        width: 320px;
    }

    .coupon-more-item-r {
        width: 430px;
    }

    .item-useless-reason {
        width: 730px;
        height: 138px;
    }

    .item-useless-reason-desc-text {
        color: #F6623C;
        height: 60px;
        line-height: 70px;
        font-size: 24px;
        padding-left: 40px;
    }

    .item-useless-reason-content-text {
        color: #757575;
        font-size: 24px;
        padding-left: 40px;
    }

    .coupon-item-info {
        width: 730px;
        height: 180px;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: flex-start;
        margin-left: 22px;
    }

    .coupon-item-active {
        position: absolute;
        z-index: 100000;
        top: 20px;
    }

    .item-col-f {
        width: 200px;
        height: 180px;
    }

    .col-f-t {
        width: 200px;
        height: 110px;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-around;
        margin-top: 10px;
    }

    .col-f-t-r {
        height: 100px;
        flex-flow: row;
        flex-direction: row;
        justify-content: center;
    }

    .col-f-t-r-text {
        font-size: 70px;
        top: 20px;
    }

    .col-f-bt {
        width: 200px;
        height: 40px;
    }

    .col-f-bt-text {
        font-size: 24px;
        text-align: center
    }

    .item-col-sp {
        width: 30px;
        height: 180px;
    }

    .item-col-sp-top {
        width: 50px;
        height: 30px;
    }

    .item-col-sp-middle {
        width: 50px;
        height: 120px;
    }

    .item-col-sp-bottom {
        width: 50px;
        height: 30px;
    }

    .item-col-s {
        height: 180px;
        justify-content: center;
    }


    .item-col-s-row-20{
        margin-top: 20px;
    }

    .item-col-s-row-16{
        margin-top: 16px;
    }

    .item-col-s-row-one-text {
        color: #000000;
        font-size: 28px;
        margin-top: 4px;
    }

    .item-col-s-row-two-text {
        height: 30px;
        line-height: 30px;
        font-size:20px;
    }

    .item-col-s-row-three-text {
        height: 30px;
        line-height: 30px;
        font-size: 20px;
    }

    .item-col-fo {
        width: 190px;
        height: 160px;
        justify-content: right;
        right:6px;
    }

    /** 通用样式 **/
    .color_F6623C {
        color: #F6623C;
    }

    .color_757575 {
        color: #757575;
    }

    .color_cbcbcb {
        color: #CBCBCB;
    }
</style>
