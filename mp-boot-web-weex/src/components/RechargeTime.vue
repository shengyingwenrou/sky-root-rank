<template>
    <div class="back-color-f3f3f3">

        <scroller class="back-color-f3f3f3">

            <div class="fix-top">
                <wdg-status-bar></wdg-status-bar>
                <div class="content ">
                    <div class="content-item" @click="toBack()">
                        <image class="item-img" :src="gobackIcon"></image>
                    </div>
                    <div class="content-buy" @click="changeTab(true)">
                        <text class="font-size-40" :class="[isActiveShowRechargeByMoney? 'item-text-active-color': 'item-text-default']">购买时长</text>
                    </div>
                    <div class="content-spilt"></div>
                    <div class="content-buy" @click="changeTab(false)">
                        <text class="font-size-40" :class="[isActiveShowRechargeByMoney? 'item-text-default': 'item-text-active-color']">兑换时长</text>
                    </div>
                    <div class="content-item"></div>
                </div>
            </div>

            <div class="position-content">

                <connect-failed  ref="connectFailed"  @onReLoad="reLoad" @resetTab="switchTab"></connect-failed>

                <div v-if="isActiveShowRechargeByMoney">
                    <RechargeTimeByMoney   @resetTab="switchTab"></RechargeTimeByMoney>
                </div>
                <div v-else>
                    <RechargeTimeByBeans  @resetTab="switchTab"></RechargeTimeByBeans>
                </div>

            </div>
        </scroller>
    </div>
</template>

<script>

  import WdgStatusBar from './widget/WdgStatusBar';
  import RechargeTimeByMoney from './widget/RechargeTimeByMoney';
  import RechargeTimeByBeans from './widget/RechargeTimeByBeans';
  import ConnectFailed from './widget/ConnectFailed';
  import {getImagePath} from '../config/Config';
  import Native from '../util/Native';

  export default {
    components: {WdgStatusBar,RechargeTimeByBeans, RechargeTimeByMoney,ConnectFailed},

    data() {
      return {
        isActiveShowRechargeByMoney:true,
        gobackIcon: '', // 返回icon
        isPayButtonClick:false
      };
    },

    created() {
        this.initJump();
        this.gobackIcon = getImagePath('goback', '.png');
    },

    mounted() {
    },

    filters: {

    },
    methods: {

        reLoad(){
            /** 子组件网络状态展示取消 **/
            this.$refs.connectFailed.setIsShowConnectFailed(false);
            /** 页面重新reLoad **/
            Native.reload();
        },

        /** 切换tab  **/
        switchTab(data){
            this.isActiveShowRechargeByMoney=data;
        },

        /** tab 手动切换后处理缓存的tab标记 **/
        changeTab(tip) {
            this.isActiveShowRechargeByMoney=tip;
            if(this.isActiveShowRechargeByMoney){
                Native.data.setCache("tab", "");
            }else{
                Native.data.setCache("tab", "/recharge/time_by_beans");
            }
        },
        async initJump() {
            let tab = await Native.data.getCache("tab");
            if (tab) {
                this.isActiveShowRechargeByMoney=false;
            }
        },
    }
  };
</script>

<style scoped>


    .back-color-f3f3f3{
        background-color: #f3f3f3;
    }

    .position-content{
        z-index: 1;
        margin-top: 152px;
    }

    .fix-top{
        position: fixed;
        background-color: #22BBBF;
        background-image: linear-gradient(to right,#36c6ad,#12a7d8);
        z-index: 100;
        top:0px;
    }

    /** 顶部导航栏 **/
    .content {
        width: 750px;
        height: 120px;
        align-items: center;
        flex-wrap: nowrap;
        flex-direction: row;
        justify-content: space-around;
        background-color: #22BBBF;
        background-image: linear-gradient(to right, #36c6ad, #12a7d8);
    }

    .font-size-40 {
        font-size: 32px;
    }

    .content-item {
        width: 227px;
        text-align: center;
        height: 92px;
    }

    .content-spilt {
        width: 70px;
        text-align: center;
        height: 60px;
    }

    .content-buy {
        width: 146px;
        text-align: center;
        height: 60px;
        margin-top: 16px;
        justify-content: center;
        flex-direction: column;
    }

    .item-text-active-color {
        color: #ffffff;
        align-items: center;
        height: 42px;
        width:132px;
        border-bottom-width: 2px;
        border-bottom-style: solid;
        border-bottom-color: #ffffff;
        text-align: center;
        margin: auto;
        opacity: 1;
    }

    .item-text-default {
        color: #ffffff;
        align-items: center;
        height: 42px;
        width:132px;
        border-bottom-width: 2px;
        border-bottom-style: solid;
        border-bottom-color: transparent;
        text-align: center;
        margin: auto;
        opacity: 0.7;
    }

    .item-img {
        width: 48px;
        height: 41px;
        left: 30px;
        top: 34px;
    }
</style>

