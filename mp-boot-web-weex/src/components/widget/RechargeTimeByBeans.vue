<template>
    <div>
        <scroller>

            <div v-if="hasFlowPacketData">
                <account-remain-time :remain-day="remainDay" :tenant-name="tenantName"></account-remain-time>
                <!-- 套餐列表信息 start -->
                <text class="title">兑换时长</text>
                <div class="plan-area">
                    <list class="list">
                        <cell class="cell" v-for="(item,index) in planList"
                              @click="chooseFlowPacket(index)">
                            <div :class="['plan-item', item.checked? 'plan-item-active': 'plan-item-not-active']">
                                <div class="item">
                                    <text class="item-day-num-text">{{ item.name }}</text>
                                    <text class="item-discount-text"> {{ (item.discount / 10) | toDecimal}}折</text>
                                    <image class="item-bean-img" :src="beansIcon"></image>
                                    <text class="item-price-text">{{ item.salePrice }}</text>
                                    <image class="item-recommend-img" v-if="item.isRecommend" :src="recommendIcon"></image>
                                </div>

                                <div class="item-second-row">
                                    <text v-if="item.description!==undefined && item.description!=''" class="item-second-row-text txl">{{ item.description }}</text>
                                    <text v-else class="txl item-second-row-text txl"></text>

                                    <text v-if="item.medalName!==undefined && item.medalName!=null" class="item-second-row-text-r txr">{{item.medalName}}</text>
                                    <text v-else class="item-second-row-text-r txr"></text>
                                </div>
                            </div>
                        </cell>
                    </list>
                    <!-- 套餐列表信息 end -->

                    <!-- 优惠券 start -->
                    <div class="coupon-area" @click="jumpWithParams('RechargeCoupon')" v-if="couponDisplay">
                        <!--<div class="coupon-area" @click="jumpInter('/recharge/coupon')" v-if="couponDisplay">-->
                        <div class="coupon-name">
                            <text class="coupon-name-text font-size-30">优惠券</text>
                        </div>
                        <div class="coupon-discount">
                            <text class="coupon-discount-text font-size-30 padding-left-6">{{cardRecommendMessage}}</text>
                        </div>
                        <div class="coupon-img">
                            <image class="coupon-img-size" :src="blueSelectIcon"></image>
                        </div>
                    </div>
                    <!-- 优惠券 end -->

                </div>

                <!-- 支付方式 start -->
                <div class="beans-item">
                    <text class="beans-item-label font-size-30">灵豆余额：</text>
                    <text class="beans-item-content  font-size-30">{{accountBeans}}</text>
                </div>
                <!-- 支付方式 end -->


                <!-- 支付按钮 start -->
                <div class="pay-button-div">
                    <wdg-button :text="btnTitle"
                                :disabled="payBtnDisabled"
                                :btnStyle="payBtnStyle"
                                :text-style="payBtnTxtStyle"
                                @wdgButtonClicked="payBtnClicked"></wdg-button>
                </div>
                <!-- 支付按钮 end -->
            </div>

            <div v-else>
                <div class="plan-null-area">
                        <image class="plan-null-image" :src="loadFailedIcon"></image>
                </div>
                <text class="plan-null-image-text">当前上网地点不支持灵豆兑换时长哦!</text>
                <div class="pay-button-div">
                    <wdg-button text="购买时长"
                                :btnStyle="tabBtnStyle"
                                :text-style="tabBtnTxtStyle"
                                @wdgButtonClicked="resetParentTab"></wdg-button>
                </div>
            </div>
        </scroller>
        <wdg-loading :show="loadingShow" loading-text="加载中..."></wdg-loading>
        <wdg-dialog title="提示"
                    content="灵豆不足，兑换失败"
                    :show="dialogShow"
                    @wdgDialogCancelBtnClicked="dialogCancelBtnClick"
                    @wdgDialogConfirmBtnClicked="dialogConfirmBtnClick"></wdg-dialog>
    </div>
</template>


<script>
  import WdgStatusBar from './WdgStatusBar';
  import AccountRemainTime from './AccountRemainTime';
  import WdgButton from './WdgButton';
  import WdgLoading from './WdgLoading';
  import WdgDialog from './WdgDialog';
  import {CACHE_TENANT_ID, getImagePath,KEY_CACHE_TENANT_RECHARGE_TIME_PLAN_BY_BEAN} from '../../config/Config';
  import Dao from '../../util/Dao';
  import Native from "../../util/Native";
  import Utils from '../../util/Utils';
  import Data from '../../config/Data';
  import event from '../../config/event';

  export default {
    components: {WdgStatusBar, AccountRemainTime, WdgButton, WdgLoading, WdgDialog},
    data() {
      return {
        gobackIcon: '',
        recommendIcon: '',
        blueSelectIcon: '',
        loadFailedIcon: '',
        beansIcon: '',
        loadingShow: false, // 是否显示loading
        needMask: true,
        hasFlowPacketData: true, // 是否有数据
        planList: [],
        remainDay: '',
        tenantName: '',
        couponDisplay: false,
        btnTitle: '',
        cardRecommendMessage: '',
        accountBeans: 0,
        goodsId: 0, // 流量包id
        accountCardId: 0, // 优惠券id
        payBtnStyle: { // 支付按钮样式
            'width': '710px',
            'height': '84px',
            'background-color': '#22BBBF',
            'border-radius': '6px',
            'outline': 'none'
        },
        payBtnTxtStyle: { // 支付按钮文字样式
            'font-size': '30px',
            'color':'#ffffff'
        },
        tabBtnStyle: { // 切换tab按钮样式
            'width': '200px',
            'height': '50px',
            'background-color': '#F3F3F3',
            'border-radius': '4px',
            'border-width':'1px',
            'border-style':'solid',
            'border-color':'#d1d1d1',
            'margin':'20px'
        },
        tabBtnTxtStyle: { // 切换tab按钮文字样式
            'font-size': '30px',
            'color':'#9e9e9e'
        },
        payBtnDisabled: false, // 支付按钮是否禁用
        dialogShow: false
      };
    },
    created() {
      this.beansIcon = getImagePath('beans', '.png');
      this.gobackIcon = getImagePath('goback', '.png');
      this.recommendIcon = getImagePath('recommend', '.png');
      this.blueSelectIcon = getImagePath('blue_selected', '.png');
      this.loadFailedIcon = getImagePath('load_failed', '.png');
      this.payBtnDisabled = true;
      this.init();
    },
    mounted() {
      this.loadAccountRemainTime();
      this.loadBeanBalance();
      this.loadFlowPacketList();
    },
    filters: {
      toDecimal: function (x) {
        let f = 0;
        //判断是否是字符串类型
        if (typeof(x) == "string") {
          if (x != null && x != '') {
            f = parseFloat(x);
          }
        } else {
          f = x;
        }
        f = f.toFixed(2);
        return f;
      }
    },
    methods: {
      /** 重设父组件TAB **/
      resetParentTab(event){
          this.$emit('resetTab',true);
      },
      // 初始化优惠券和按钮显示
      initCouponAndBtnView() {
        // 如果套餐不存在则不显示优惠券信息
        if (!this.planList) {
          return;
        }
        // 如果没有索引则判断套餐中的checked属性来决定优惠券信息显示
        for (let i = 0; i < this.planList.length; i++) {
          if (this.planList[i].checked === true) {
            if (this.planList[i].card) {
              this.accountCardId = this.planList[i].card.accountCardId;
              this.cardRecommendMessage = this.planList[i].card.discountDescribe;
              let finalPrice = this.planList[i].salePrice - this.planList[i].card.discountAmount;
              this.btnTitle = finalPrice + "灵豆兑换(已优惠" + this.planList[i].card.discountDescribe + ")";
            } else {
              this.accountCardId = 0;
              this.cardRecommendMessage = this.planList[i].cardRecommendMessage;
              this.btnTitle = this.planList[i].salePrice +'灵豆兑换';
            }
            this.goodsId = this.planList[i].id;
          }
        }
        this.couponDisplay = !!(this.cardRecommendMessage && this.cardRecommendMessage.length > 0);
      },
      loadBeanBalance() {
        Dao.getBeanBalance(this.params)
          .then((res) => {
            if (res && res.result) {
              this.accountBeans = res.data;
            } else {
              this.accountBeans = 0;
            }
          })
      },
      chooseFlowPacket(index) {
        this.payBtnDisabled = false;
        for (let i = 0; i < this.planList.length; i++) {
          this.planList[i].checked = false;
          if (i === index) {
            this.planList[index].checked = true;
            if (this.planList[index].card) {
              this.accountCardId = this.planList[index].card.accountCardId;
              this.cardRecommendMessage = this.planList[index].card.discountDescribe;
              let finalPrice = this.planList[i].salePrice - this.planList[i].card.discountAmount;
              this.btnTitle = '支付￥' + finalPrice + "(已优惠" + this.planList[i].card.discountDescribe + ")";
            } else {
              this.accountCardId = 0;
              this.cardRecommendMessage = this.planList[index].cardRecommendMessage;
              this.btnTitle = '灵豆兑换' + this.planList[i].salePrice + "灵豆";
            }
            this.couponDisplay = !!(this.cardRecommendMessage && this.cardRecommendMessage.length > 0);
            this.goodsId = this.planList[i].id;
          }
        }
      },
      dialogCancelBtnClick() {
        this.dialogShow = false;
        this.payBtnDisabled = false;
      },
      dialogConfirmBtnClick() {
        this.dialogShow = false;
        this.payBtnDisabled = false;
        // 跳到灵豆充值页面
        Native.jump.native(15);
      },
      async loadFlowPacketList() {
        let tenantIdCache = await Native.data.getCache(CACHE_TENANT_ID);
        if (tenantIdCache) {
          this.params.tenantId = tenantIdCache;
        }
        this.params.paymentType = 2;
        Dao.getFlowPacketList(this.params)
          .then((res) => {
            if (res && res.result) {
                if (Utils.isNonEmptyArray(res.data)) {
                    this.hasFlowPacketData=true;
                    this.planList = res.data;
                    // 缓存最近拉到的灵豆兑换套餐列表
                    Native.data.setCache(KEY_CACHE_TENANT_RECHARGE_TIME_PLAN_BY_BEAN,JSON.stringify(res.data));
                    this.payBtnDisabled = false;
                }
              this.initCouponAndBtnView();
            } else {
                // 没有配置项目套餐状态码处理
                if (res.code === 3004) {
                    this.hasFlowPacketData = false;
                }

                // 网络连接失败
                if (res.code === -1) {
                    // 加载缓存数据
                    //this.hasFlowPacketData = false;
                    this.payBtnDisabled = true;
                    // 通知联网失败
                    event.$emit("informConnectFailed",true);
                    this.planList = Data.defaultBeanPlans;
                    return;
                }
            }
            event.$emit("informConnectFailed",false);
            setTimeout(() => {
              this.loadingShow = false;
            }, 100);
          })
      },
      async loadAccountRemainTime() { // 获取用户剩余时长
        let tenantIdCache = await Native.data.getCache(CACHE_TENANT_ID);
        if (tenantIdCache) {
          this.params.tenantId = tenantIdCache;
        }
        Dao.getAccountRemainTime(this.params)
          .then((res) => {
            if (res && res.result) {
              this.remainDay = res.data.remainDay;
              this.tenantName = res.data.tenantName;
            }
          })
      },
      async payBtnClicked(e) {
        const {disabled} = e;
        if (disabled) {
          return;
        }
        this.payBtnDisabled = true;
        let tenantIdCache = await Native.data.getCache(CACHE_TENANT_ID);
        if (tenantIdCache) {
          this.params.tenantId = tenantIdCache;
        }
        this.params.goodsId = this.goodsId; // 流量包id
        this.params.accountCardId = this.accountCardId; // 优惠券id
        let that = this;
        Dao.payByBean(this.params)
          .then(res => {
            if (res && res.result) {
              // 调用客户端支付模块
                Native.payByBeans(true);
                console.log("灵豆兑换结果:" + JSON.stringify(e));
                that.toast("上网时长兑换成功！");
                that.payBtnDisabled = true;
                // 保存tab位置
                Native.data.setCache("tab", "/recharge/time_by_beans");
                Native.reload();
            } else if (res && res.code === 2025) {
              that.payBtnDisabled = false;
              that.dialogShow = true;
            } else {
              that.toast("上网时长兑换失败，请稍后重试！");
              that.payBtnDisabled = false;
            }
          })
      }
    }
  };
</script>

<style scoped>
    .font-size-30 {
        font-size: 30px;
    }

    .plan-null-area {
        width: 750px;
        height: 490px;
    }

    .plan-null-image {
        margin-left: 280px;
        width: 184px;
        height: 184px;
        margin-top: 270px;
    }

    .padding-left-6 {
        padding-left: 6px;
    }

    .plan-null-image-text {
        width: 750px;
        font-size: 30px;
        line-height: 30px;
        text-align: center;
        color: #9e9e9e;
    }

    .beans-item {
        width: 750px;
        height: 74px;
        padding-top: 20px;
        padding-left: 20px;
        flex-direction: row;
        justify-content: flex-start;
        background-color: #f3f3f3;
    }

    .beans-item-label {
        color: #757575;
    }

    .beans-item-content {
        color: #f6623c;
        line-height: 40px;
    }


    /** 代金券 **/
    .coupon-area {
        height: 54px;
        background-color: #ffffff;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-around;
        margin-top: 14px;
    }

    .coupon-name {
        width: 120px;
        height:54px;
    }

    .coupon-name-text {
        color: #757575;
        padding-left:10px;
        font-size: 28px;
        height: 54px;
        line-height: 54px;
    }

    .coupon-discount {
        width: 560px;
    }

    .coupon-discount-text {
        color: #22BBBF;
        text-align: right;
        padding-left: 2px;
        font-size:30px;
        height:54px;
        line-height: 54px;
    }

    .coupon-img {
        width: 40px;
    }

    .coupon-img-size {
        width: 16px;
        height: 26px;
        margin-top: 15px;
        left: 10px;
    }
    

    .item-second-row {
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: flex-start;
        width:730px;
    }

    .item-second-row-text {
        color: #9d9d9d;
        font-size: 28px;
        width: 548px;
        padding-left: 28px;
    }

    .item-second-row-text-r {
        color: #B8B8B8;
        font-size: 28px;
    }

    .item-bean-img {
        width: 40px;
        height: 34px;
        top:22px;
    }

    .item-recommend-img {
        width: 78px;
        height: 80px;
        right: 0;
        top: -6px;
        position: absolute;
    }

    .plan-area {
        background-color: #ffffff;
        padding-left: 20px;
        padding-right: 10px;
        min-height: 160px;
        flex-direction: column;
        padding-bottom: 20px;
    }

    .item {
        width: 710px;
        height: 70px;
        flex-direction: row;
    }

    .plan-item {
        margin-top: 24px;
        width: 710px;
        height: 132px;
        border-radius: 10px;
        justify-content: flex-start;
        flex: 1;
    }

    .plan-item-not-active {
        border-width: 1px;
        border-style: solid;
        border-color: #d1d1d1;
    }

    .plan-item-active {
        border-width: 2px;
        border-style: solid;
        border-color: #22BBBF;
    }

    .item-day-num-text {
        text-align: left;
        font-size: 34px;
        width: 138px;
        margin-top: 20px;
        padding-left: 28px;
    }

    .item-discount-text {
        color: #9d9d9d;
        text-align: center;
        font-size: 28px;
        width: 410px;
        margin-top: 24px;
    }

    .item-price-text {
        text-align: left;
        font-size: 36px;
        color: #F6623C;
        width: 140px;
        margin-top: 20px;
    }

    .title {
        height: 60px;
        color: #999999;
        padding-left: 20px;
        font-size: 28px;
        line-height: 64px;
        background-color: #f3f3f3;
    }


    .txl {
        text-align: left;
    }

    .pay-button-div {
        width: 750px;
        height: 120px;
        align-items: center;
        padding-top: 18px;
    }
</style>

