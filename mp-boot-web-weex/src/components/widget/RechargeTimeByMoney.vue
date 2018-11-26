<template>
    <div>
        <scroller>

            <div v-if="hasFlowPacketData">
                <account-remain-time :remain-day="remainDay" :tenant-name="tenantName"></account-remain-time>
                <text class="title">兑换时长</text>
                <div class="plan-area">

                   <list class="list">
                       <cell class="cell" v-for="(item,index) in planList"
                             @click="chooseFlowPacket(index)">
                           <div :class="['plan-item', item.checked? 'plan-item-active': 'plan-item-not-active']">
                               <div class="item">
                                   <text class="item-day-num-text">{{ item.name }}</text>
                                   <text v-if="item.promotionName!==undefined && item.promotionName.length" class="promotion-desc-area promotion-desc">{{ item.promotionName }}
                                   </text>
                                   <text v-else class="promotion-desc-area "></text>
                                   <text class="item-discount-text"> {{ (item.discount/10) | toDecimal}}折</text>
                                   <text class="item-price-text">¥&nbsp;{{ item.salePrice }}</text>
                                   <image class="item-recommend-img" v-if="item.tags && item.tags.length > 0" :src="recommendIcon"></image>
                               </div>

                               <div class="item-second-row">
                                   <text v-if="item.description!==undefined && item.description!=''" class="item-second-row-text txl">{{ item.description }}</text>
                                   <text v-else class="item-second-row-text txl"></text>

                                   <text v-if="item.medalName!==undefined && item.medalName!=''" class="item-second-row-text-r txr">{{item.medalName}}</text>
                                   <text v-else class="item-second-row-text-r txr"></text>
                               </div>
                           </div>
                       </cell>

                    </list>

                    <!-- 优惠券 start -->
                    <div class="coupon-area" @click="jumpWithParams('RechargeCoupon')" v-if="couponDisplay">
                        <div class="coupon-name">
                            <text class="coupon-name-text">优惠券</text>
                        </div>
                        <div class="coupon-discount">
                            <text class="coupon-discount-text">{{cardRecommendMessage}}</text>
                        </div>
                        <div class="coupon-img">
                            <image class="coupon-img-size" :src="blueSelectIcon"></image>
                        </div>
                    </div>
                    <!-- 优惠券 end -->
                </div>
                <!-- 套餐列表信息 start -->

                <!-- 支付方式 start -->
                <text class="title">支付方式</text>

                <list class="list">
                    <cell class="cell" v-for="(item,index) in payWays"
                          @click="choosePayWay(index)">
                        <div class="pay-item">
                            <div class="pay-item-n">
                                <div class="pay-item-small">
                                    <image class="pay-item-small-img" :src="item.icon"></image>
                                </div>
                                <div class="pay-item-desc">
                                    <text class="font-size-30 margin-top-8">{{item.desc}}</text>
                                </div>
                                <image class="pay-item-choose-img" :src="item.statusIcon"></image>
                            </div>
                        </div>
                        <div v-if="index===0" class="pay-item-n-split"></div>

                    </cell>
                </list>
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
                <text class="plan-null-image-text">当前上网地点不支持现金购买时长哦!</text>
                <div class="pay-button-div">
                    <wdg-button text="兑换时长"
                                :btnStyle="tabBtnStyle"
                                :text-style="tabBtnTxtStyle"
                                @wdgButtonClicked="resetParentTab"></wdg-button>
                </div>
            </div>
        </scroller>
        <wdg-loading :show="loadingShow" loading-text="加载中..."></wdg-loading>
    </div>
</template>

<script>
    import WdgStatusBar from './WdgStatusBar';
    import AccountRemainTime from './AccountRemainTime';
    import WdgButton from './WdgButton';
    import WdgTag from './WdgTag';
    import WdgLoading from './WdgLoading';
    import {CACHE_TENANT_ID,KEY_CACHE_TENANT_RECHARGE_TIME_PLAN_BY_MONEY, getImagePath} from '../../config/Config';
    import Dao from '../../util/Dao';
    import Data from '../../config/Data';
    import Native from '../../util/Native';
    import Utils from '../../util/Utils';

    import event from '../../config/event';


    export default {
        components: {WdgStatusBar, AccountRemainTime, WdgButton, WdgTag, WdgLoading},
        data() {
            return {
                gobackIcon: '', // 返回icon
                recommendIcon: '', // 推荐icon
                blueSelectIcon: '', // 加载失败icon
                loadFailedIcon: '',
                loadingShow: false, // 是否显示loading
                needMask: true,
                hasFlowPacketData: true, // 是否有数据false
                planList: [], // 流量包数据列表
                remainDay: '',
                tenantName: '',
                couponDisplay: false, // 控制优惠券部分显示
                btnTitle: '', // 支付按钮标题
                cardRecommendMessage: '', // 优惠券推荐语
                payWays: Data.payWays, // 支付方式静态数据
                isDefaultActive: false,
                wxpay: true, // 是否微信支付
                alipay: false, // 是否支付宝支付
                goodsId: 0, // 流量包id
                accountCardId: 0, // 优惠券id
                tagStyle: { // 标签
                    marginTop: 10,
                    width: 180,
                    height: 40,
                    borderRadius: 25,
                    borderWidth: 1,
                    borderColor: '#F6623C',
                    backgroundColor: '#F6623C',
                    color: '#FFFFFF'
                },
                payBtnStyle: { // 支付按钮样式
                    'width': '710px',
                    'height': '84px',
                    'background-color': '#22BBBF',
                    'border-radius': '6px',
                    'outline': 'none'
                },
                payBtnTxtStyle: { // 支付按钮文字样式
                    'font-size': '32px',
                    'letter-spacing': '1px'
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
                payBtnDisabled: true, // 支付按钮是否禁用
                payBtnSwitch:true
            }
        },
        created() {
            this.recommendIcon = getImagePath('recommend', '.png');
            this.blueSelectIcon = getImagePath('blue_selected', '.png');
            this.loadFailedIcon = getImagePath('load_failed', '.png');
            this.init();
            //this.payBtnSwitch=this.$parent.isPayButtonClick;
        },
        mounted() {
            this.loadAccountRemainTime();
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
            /** 获取父组件的支付button状态 **/
            callParent(){
                let payBtnDisabled=true;
                return payBtnDisabled;
            },

            /** 重设父组件TAB **/
            resetParentTab(event){
                this.$emit('resetTab',false);
            },

            /** 子组件相互通信 **/
            informMessage(){
                this.$emit('informConnectFailed', true);
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
                            finalPrice = Utils.toDecimal(finalPrice);
                            this.btnTitle = '支付¥' + finalPrice + "(已优惠" + this.planList[i].card.discountDescribe + ")";
                        } else {
                            this.accountCardId = 0;
                            this.cardRecommendMessage = this.planList[i].cardRecommendMessage;
                            this.btnTitle = '支付¥ ' + this.planList[i].salePrice;
                        }
                        this.goodsId = this.planList[i].id;
                    }
                }
                this.couponDisplay = !!(this.cardRecommendMessage && this.cardRecommendMessage.length > 0);
            },
            chooseFlowPacket(index) {
                this.payBtnDisabled = false;
                for (let i = 0; i < this.planList.length; i++) {
                    this.planList[i].checked = false;
                    if (i === index) {
                        this.planList[index].checked = true;
                        if (this.planList[index].card) { // 选中的流量包如果有优惠券则控制优惠券区域显示
                            this.accountCardId = this.planList[index].card.accountCardId;
                            this.cardRecommendMessage = this.planList[index].card.discountDescribe;
                            let finalPrice = this.planList[i].salePrice - this.planList[i].card.discountAmount;
                            finalPrice = Utils.toDecimal(finalPrice);
                            this.btnTitle = '支付¥' + finalPrice + "(已优惠" + this.planList[i].card.discountDescribe + ")";
                        } else {
                            this.accountCardId = 0;
                            this.cardRecommendMessage = this.planList[index].cardRecommendMessage;
                            this.btnTitle = '支付¥' + this.planList[i].salePrice;
                        }
                        this.couponDisplay = !!(this.cardRecommendMessage && this.cardRecommendMessage.length > 0);
                        this.goodsId = this.planList[i].id;
                    }
                }
            },
            choosePayWay(index) {
                for (let i = 0; i < this.payWays.length; i++) {
                    this.payWays[i].statusIcon = getImagePath('not_choose', '.png');
                    if (i === index) {
                        this.payWays[index].statusIcon = getImagePath('choose', '.png');
                    }
                }
                if (index === 0) {
                    this.wxpay = true;
                    this.alipay = false;
                } else {
                    this.wxpay = false;
                    this.alipay = true;
                }
            },

            async loadFlowPacketList() { // 加载流量包列表
                let tenantIdCache = await Native.data.getCache(CACHE_TENANT_ID);
                if (tenantIdCache) {
                    this.params.tenantId = tenantIdCache;
                }
                this.params.paymentType = 1;
                Dao.getFlowPacketList(this.params)
                    .then((res) => {
                        if (res && res.result) {
                            if(Utils.isNonEmptyArray(res.data)){
                                this.hasFlowPacketData = true;
                                this.planList = res.data;
                                this.payBtnDisabled = false;
                                // 缓存最近拉到的套餐列表
                                // Native.data.setCache(KEY_CACHE_TENANT_RECHARGE_TIME_PLAN_BY_MONEY,JSON.stringify(res.data));
                            }else{
                                this.hasFlowPacketData = false;
                            }
                            // 加载完数据则控制优惠券区域显示
                            this.initCouponAndBtnView();
                        } else {
                            // 网络连接失败
                            if (res.code === -1) {
                                // 加载缓存数据
                                //this.hasFlowPacketData = false;
                                this.payBtnDisabled = true;
                                // 通知联网失败
                                event.$emit("informConnectFailed",true);
                                this.planList = Data.defaultCashPlans;
                                return;
                            }
                            // 没有配置项目套餐状态码 ui展示处理
                            if (res.code === 3004) {
                                this.hasFlowPacketData = false;
                            }
                            // 拉取数据失败 读取最近一次的缓存
                        }
                });
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

                if (WXEnvironment.platform === 'Web') {
                    this.toast("浏览器暂不支持该支付方式");
                    return;
                }
                let tenantIdCache = await Native.data.getCache(CACHE_TENANT_ID);
                if (tenantIdCache) {
                    this.params.tenantId = tenantIdCache;
                }
                let payType = 0;
                // 使用微信支付
                if (this.wxpay && this.wxpay === true) {
                    payType = 5;
                }
                // 使用支付宝支付
                if (this.alipay && this.alipay === true) {
                    payType = 10;
                }
                this.params.payType = payType; // 支付方式
                this.params.goodsId = this.goodsId; // 流量包id
                this.params.accountCardId = this.accountCardId; // 优惠券id
                let that = this;
                Dao.payByMoney(this.params)
                    .then((res) => {
                        if (res && res.result) {
                            // 调用客户端支付模块
                            Native.pay(res.data, payType, function (e) {
                                console.log("支付结果:" + JSON.stringify(e));
                                if (e.result && e.result === "success") {
                                    that.toast("上网时长购买成功！");
                                    that.payBtnDisabled = false;
                                    Native.reload();
                                } else {
                                    that.payBtnDisabled = false;
                                }
                            });
                        } else {
                            that.toast("订单生成失败，请稍后重试！");
                            that.payBtnDisabled = false;
                        }
                    })
            }
        }
    };
</script>

<style scoped>

    .promotion-desc-area {
        height: 34px;
        width: 142px;
        margin-top: 20px;
    }

    .promotion-desc {
        font-size: 24px;
        background: #F6623C;
        background-color: #F6623C;
        border-radius: 40px;
        color: #ffffff;
        line-height: 34px;
        height:34px;
        text-align: center;
    }

    .font-size-30 {
        font-size: 32px;
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

    .plan-null-image-text {
        width: 750px;
        font-size: 30px;
        line-height: 30px;
        text-align: center;
        color: #9e9e9e;
    }

    .txl {
        text-align: left;
    }

    .margin-top-8 {
        margin-top: 11px;
        padding-left: 10px;
    }

    .txr {
        text-align: right;
    }

    .pay-button-div {
        width: 750px;
        height: 120px;
        align-items: center;
        padding-top: 18px;
    }

    .pay-item {
        background-color: #ffffff;
        width: 750px;
    }

    .pay-item-n {
        flex-direction: row;
        justify-content: space-around;
        padding-left: 10px;
        padding-right: 10px;
        padding-top: 24px;
        padding-bottom: 24px;
    }

    .pay-item-n-split {
        flex-direction: row;
        justify-content: space-around;
        border-bottom-width: 1px;
        border-bottom-style: solid;
        border-bottom-color: #dedede;
        width:710px;
        margin-left: 20px;
    }

    .pay-item-small {
        width: 60px;
    }

    .pay-item-small-img {
        width: 60px;
        height: 60px;
    }

    .pay-item-desc {
        width: 600px;
    }

    .pay-item-choose-img {
        width: 36px;
        height: 36px;
        margin-top: 14px;
    }

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
    }

    .item-second-row-text {
        color: #9d9d9d;
        font-size: 28px;
        width: 548px;
        padding-left: 28px;
    }

    .item-second-row-text-r {
        color: #9d9d9d;
        font-size: 28px;
    }

    .item-recommend-img {
        width: 62px;
        height: 64px;
        right: 0px;
        top: 0px;
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
        //justify-content: flex-start;
        //flex-direction: column;
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
        width: 268px;
        margin-top: 24px;
    }

    .item-price-text {
        text-align: left;
        font-size: 36px;
        color: #F6623C;
        width: 150px;
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
</style>

