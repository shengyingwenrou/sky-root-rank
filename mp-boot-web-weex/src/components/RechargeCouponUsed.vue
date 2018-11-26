<template>
    <scroller class="back_reset">
        <wdg-minibar title="无效优惠券" @wdgMinibarLeftClicked="toBack()"></wdg-minibar>

        <connect-failed ref="connectFailed" @onReLoad="reLoad"></connect-failed>
        <!-- 优惠券列表 start :class="[isChooseActive(item.isActive)]" -->
        <div class="position-content" v-if="enableCoupons!==undefined && enableCoupons!==null && enableCoupons.length > 0 ">
            <div v-for="(item,index) in enableCoupons">
                <image :class="['coupon_used_active']" :src="couponUsedIcon"></image>
                <div class="coupon-item-info coupon-item-active">
                    <div class="item-col-f">
                        <div class="col-f-t">
                            <div class="col-f-t-r" v-if="item.preferenceType===1">
                                <text class="col-f-t-r-text color_757575">{{item.preferenceAmount}}</text>
                                <text class="color_757575 font-size-30 margin-top-45">元</text>
                            </div>
                            <div class="col-f-t-r" v-else="item.preferenceType===0">
                                <text class="col-f-t-r-text color_757575">{{item.preferenceAmount / 10}}</text>
                                <text class="color_757575 font-size-30 margin-top-45">折</text>
                            </div>
                        </div>
                        <div class="col-f-bt">
                            <text class="col-f-bt-text color_757575">满{{item.limitCondition}}天可用</text>
                        </div>
                    </div>

                    <div class="item-col-sp">

                    </div>

                    <div class="item-col-s">
                        <div >
                            <text class="item-col-s-row-one-text">{{item.name}}</text>
                        </div>

                        <div class="item-col-s-row-20">
                            <text class="item-col-s-row-two-text font-size-30">{{item.describe}}</text>
                        </div>

                        <div class="item-col-s-row-16">
                            <text class="item-col-s-row-three-text font-size-30">{{item.startTime}}-{{item.endTime}}</text>
                        </div>
                    </div>

                    <div class="item-col-fo">
                        <div class="item-col-fo-bottom">
                            <image v-if="item.useStatus === 3" class="usedless-item-img" :src="yhqygqIcon"></image>
                            <image v-if="item.useStatus === 2" class="usedless-item-img" :src="yhqysyIcon"></image>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div v-else="enableCoupons!==undefined && enableCoupons.length">
            <div class="empty-list">
                <div class="empty-list-v">
                    <image class="empty-list-img" :src="quanIcon"></image>
                </div>
                <text class="empty-text">暂无优惠券</text>
            </div>
        </div>
        <!-- 优惠券列表 end -->

        <!-- more 查看更多已经使用或者过期的优惠券 start-->
        <div class="coupon-more" >
            <!--<div class="cpupon-more-item-l" @click="jumpWithParams('RechargeCoupon')">-->
            <div class="coupon-more-item-l" @click="jumpWithParams('RechargeCoupon')">
                <text class="coupon-more-item-r-text">查看您尚未使用的券吧</text>
            </div>
            <div class="coupon-more-item-r">
                <text class="coupon-more-item-l-text">没有更多有效券了</text>
            </div>
        </div>

        <!-- more 查看更多已经使用或者过期的优惠券 end -->
    </scroller>
</template>

<script>
  import WdgMinibar from './widget/WdgMinibar';
  import {getImagePath} from "../config/Config";
  import ConnectFailed from './widget/ConnectFailed';
  import Dao from '../util/Dao';
  import event from '../config/event';

  export default {
    components: {WdgMinibar,ConnectFailed},

    data() {
      return {
        yhqygqIcon: '',
        yhqysyIcon: '',
        quanIcon: '',
        couponUsedIcon: '',
        enableCoupons: []
      };
    },
    created() {
      this.couponUsedIcon = getImagePath('not_choose_c', '.png');
      this.yhqygqIcon = getImagePath('icon_yhq_ygq', '.png');
      this.yhqysyIcon = getImagePath('icon_yhq_ysy', '.png');
      this.quanIcon = getImagePath('quan', '.png');
      this.init();
    },
    mounted() {
      this.loadAccountCardList();
    },
    methods: {
        loadAccountCardList() {
            this.params.type = 0;
            Dao.getAccountCardList(this.params)
                .then((res) => {

                    if (res && res.result) {
                        this.enableCoupons = res.data;
                    } else {
                        // 网络连接失败
                        if (res.code === -1) {
                            // 通知联网失败
                            event.$emit("informConnectFailed", true);
                        }
                    }
                })
        }
    }
  };
</script>

<style scoped>

    .position-content{
        z-index: 1;
    }

    .coupon_used_active {
        flex-direction: column;
        width: 704px;
        height: 180px;
        margin: auto;
        margin-left: 24px;
        justify-content: center;
        margin-top: 26px;
    }

    .back_reset{
        background-color: #e8e8e8;
    }


    .font-size-30 {
        font-size: 30px;
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
        text-align: left;
        font-size: 30px;
        padding-left: 20px;
        font-size: 26px;
    }

    .coupon-more-item-l {
        width: 390px;
    }

    .coupon-more-item-r {
        width: 360px;
    }

    .coupon-more-item-r-text {
        height: 80px;
        line-height: 80px;
        color: #F6623C;
        text-align: right;
        font-size: 26px;
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

    .margin-top-45 {
        margin-top: 52px;
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

    .item-col-s-row-one-text {
        color: #757575;
        font-size: 28px;
        margin-top: 4px;
    }

    .item-col-s-row-two-text {
        height: 30px;
        line-height: 30px;
        font-size:20px;
        color:#757575;
    }

    .item-col-s-row-three-text {
        height: 30px;
        line-height: 30px;
        font-size: 20px;
        color:#757575;
    }

    .item-col-fo {
        width: 210px;
        height: 160px;
        justify-content: right;
        right:0px;
    }


    .item-col-s-row-20{
        margin-top: 20px;
    }

    .item-col-s-row-16{
        margin-top: 16px;
    }

    .item-col-fo-bottom {
        height: 180px;
        width: 178px;
    }

    .usedless-item-img {
        width: 120px;
        height: 120px;
        left: 30px;
        top: 30px;
    }

    .color_757575 {
        color: #757575
    }

</style>


