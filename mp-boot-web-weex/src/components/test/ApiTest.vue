<template>
    <scroller>
        <div class="group">
            <text class="title">查询用户剩余时长</text>
            <text class="count">{{getAccountRemainTimeResult}}</text>
        </div>
        <div class="group">
            <text class="title">获取所有项目下的时长</text>
            <text class="count">{{getAccountRemainTimeListResult}}</text>
        </div>
        <div class="group">
            <text class="title">查询用户代金券</text>
            <text class="count">{{getAccountCardListResult}}</text>
        </div>
        <div class="group">
            <text class="title">获取流量包价格列表</text>
            <text class="count">{{getFlowPacketListResult}}</text>
        </div>
        <div class="group">
            <text class="title">获取时长明细</text>
            <text class="count">{{getOrderHistoryResult}}</text>
        </div>
    </scroller>
</template>

<script>
  import dao from '../../util/Dao'

  let params = {
    appId: '7d7a039da25746a59d3de59ba3266388',
    sessionId: '7d7a039da25746a59d3de59ba3266388',
    networkAccess: '4G',
    timestamp: 123467890123,
    nonceStr: '1q2w3e4r5t6y7u8i',
    versionCode: 4600,
    terminalMac: '000000000000',
    deviceMac: '000000000000',
    bssid: '000000000000',
    sign: '7d7a039da25746a59d3de59ba326638812345678',
    token: '7d7a039da25746a59d3de59ba3266388'
  };

  export default {
    data() {
      return {
        getAccountRemainTimeResult: "loading...",
        getAccountRemainTimeListResult: "loading...",
        getAccountCardListResult: "loading...",
        getFlowPacketListResult: "loading...",
        getOrderHistoryResult: "loading..."
      }
    },
    created: function () {
      this.loadAccountRemainTime();
      this.loadAccountRemainTimeList();
      this.loadAccountCardList();
      this.loadFlowPacketList();
      this.loadOrderHistory();
    },
    methods: {
      loadAccountRemainTime() {
        dao.getAccountRemainTime(params)
          .then((res) => {
            if (res && res.result) {
              this.getAccountRemainTimeResult = JSON.stringify(res)
            } else {
              this.getAccountRemainTimeResult = "加载数据失败"
            }
          })
      },
      loadAccountRemainTimeList() {
        dao.getAccountRemainTimeList(params)
          .then((res) => {
            if (res && res.result) {
              this.getAccountRemainTimeListResult = JSON.stringify(res)
            } else {
              this.getAccountRemainTimeListResult = "加载数据失败"
            }
          })
      },
      loadAccountCardList() {
        params.type = 1;
        dao.getAccountCardList(params)
          .then((res) => {
            if (res && res.result) {
              this.getAccountCardListResult = JSON.stringify(res)
            } else {
              this.getAccountCardListResult = "加载数据失败"
            }
          })
      },
      loadFlowPacketList() {
        params.paymentType = 1;
        dao.getFlowPacketList(params)
          .then((res) => {
            if (res && res.result) {
              this.getFlowPacketListResult = JSON.stringify(res)
            } else {
              this.getFlowPacketListResult = "加载数据失败"
            }
          })
      },
      loadOrderHistory() {
        params.orderId = 0;
        dao.getOrderHistory(params)
          .then((res) => {
            if (res && res.result) {
              this.getOrderHistoryResult = JSON.stringify(res)
            } else {
              this.getOrderHistoryResult = "加载数据失败"
            }
          })
      },
    }
  }
</script>

<style scoped>
    .group {
        margin-left: 32px;
        margin-right: 32px;
        margin-bottom: 32px;
    }

    .title {
        font-size: 45px;
        color: #41B883;
    }

    .count {
        margin-top: 6px;
        font-size: 28px;
        color: #888888;
    }
</style>