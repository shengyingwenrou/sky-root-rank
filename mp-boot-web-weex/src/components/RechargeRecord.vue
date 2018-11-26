<template>


        <scroller class="container">
            <!--<wdg-minibar title="购买明细" @wdgMinibarLeftClicked="jumpInter('/recharge/change_project')"></wdg-minibar>-->
            <wdg-minibar title="购买明细" @wdgMinibarLeftClicked="toBack()"></wdg-minibar>

            <connect-failed ref="connectFailed" @onReLoad="reLoad"></connect-failed>


            <div class="head position-content">
                <div class="image_div">
                    <image class="image" :src="timeDetailIcon" resize="cover"></image>
                </div>
                <div class="head_project">
                    <text class="head_project_text">{{tenantName}}</text>
                </div>
                <div class="head_time">
                    <text class="head_time_text">{{remainDay}}</text>
                </div>
                <div class="head_btn">
                    <wdg-button text="兑换时长"
                                :btn-style="chargeBtnStyle"
                                :text-style="chargeTextStyle"
                                @wdgButtonClicked="exchangeBtnClicked"></wdg-button>
                </div>
            </div>

            <div v-if="" class="position-content" v-if="orderLists!==undefined && orderLists!==null && orderLists.length > 0">
            <div v-for="item in orderLists">
                <wdg-cell
                        :has-arrow="false"
                        :has-top-border="true"
                        :has-bottom-border="false">
                    <div slot="title">
                        <text class="title_name">{{item.describe}}</text>
                        <text class="title_time">{{item.date}}</text>
                    </div>
                    <text slot="value" class="value">+{{item.timeStr}}</text>
                </wdg-cell>
            </div>
            </div>


        </scroller>

</template>

<script>
  import WdgMinibar from './widget/WdgMinibar';
  import WdgCell from './widget/WdgCell';
  import EmptyData from './widget/EmptyData.vue';
  import WdgButton from './widget/WdgButton';
  import ConnectFailed from './widget/ConnectFailed';
  import {CACHE_TENANT_ID, getImagePath} from "../config/Config";
  import Dao from '../util/Dao';
  import Native from "../util/Native";
  import event from '../config/event';

  export default {
    components: {WdgMinibar, WdgCell, WdgButton,EmptyData,ConnectFailed},
    data() {
      return {
        timeDetailIcon: '',
        emptyIcon: '',
        tenantId: 0,
        tenantName: '',
        remainDay: '',
        orderLists: [],

        chargeBtnStyle: {
          'width': '240px',
          'height': '70px',
          'backgroundColor': '#FFFFFF',
          'borderColor': '#35C5AE',
          'borderWidth': '1px',
          'borderStyle': 'solid',
          'borderRadius': '6px'
        },
        chargeTextStyle: {
          'color': '#35C5AE',
          'fontSize': '30px'
        }
      }
    },
    created() {
      this.timeDetailIcon = getImagePath('time_detail', '.png');
      this.emptyIcon = getImagePath('empty_data', '.png');
      this.init();
      this.initQuery();
    },
    mounted() {
      this.loadAccountRemainTime();
      this.loadOrderHistory();
    },
    methods: {

      reLoad(){
        /** 子组件网络状态展示取消 **/
        this.$refs.connectFailed.setIsShowConnectFailed(false);
        /** 页面重新reLoad **/
        Native.reload();
      },

      initQuery() {
        if (this.getQuery().tenantId) {
          this.tenantId = this.getQuery().tenantId;
        }
      },
      loadAccountRemainTime() {
        this.params.tenantId = this.tenantId;
        Dao.getAccountRemainTime(this.params)
          .then((res) => {
            if (res && res.result) {
              this.remainDay = res.data.remainDay;
              this.tenantName = res.data.tenantName;
            } else {
                // 网络连接失败
                if (res.code === -1) {
                    // 通知联网失败
                    event.$emit("informConnectFailed",true);
                }
            }
          })
      },
      loadOrderHistory() {
        this.params.tenantId = this.tenantId;
        Dao.getOrderHistory(this.params)
          .then((res) => {
            if (res && res.result) {
              this.orderLists = res.data;
            }else{
                // 网络连接失败
                if (res.code === -1) {
                    // 通知联网失败
                    event.$emit("informConnectFailed",true);
                }
            }
          })
      },
      exchangeBtnClicked() {
        Native.data.setCache(CACHE_TENANT_ID, this.tenantId);
        Native.jump.weexAndClose(this.getWeexUrl("index", {
          tenantId: this.tenantId
        }));
      }
    }
  };
</script>

<style scoped>


    .position-content{
        z-index: 1;
        background-color: #ffffff;
    }

    .container {
        flex: 1;
        background-color: #F3F3F3;
    }

    .head {
        justify-content: flex-start;
        align-items: center;
        min-height: 420px;
    }

    .head_project {

    }

    .head_project_text {
        color: #666666;
        font-size: 28px;
        margin-top: 20px;
    }

    .head_time {
        margin-top: 20px;
    }

    .head_time_text {
        color: #212121;
        font-size: 44px;
        font-weight: bold;
    }

    .head_btn {
        margin-top: 20px;
        height: 100px;
        margin-bottom: 25px;
    }

    .title_name {
        font-size: 30px;
    }

    .title_time {
        font-size: 25px;
        margin-top: 12px;
        color: #9e9e9e;
    }

    .value {
        font-size: 32px;
        color: #35c5ae;
    }

    .image_div {
        width: 166px;
        height: 184px;
        margin-top: 15px;
    }

    .image {
        width: 146px;
        height: 146px;
        margin-top: 30px;
    }
</style>