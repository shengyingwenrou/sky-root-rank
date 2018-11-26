// api 请求地址

import {API_HOST} from "../config/Config";

const VERSION = 'v3';

const ApiUrl = {
  // 获取用户剩余时长
  accountRemainTimeUrl: (host = API_HOST, version = VERSION) => {
    return `${host}api/${version}/account/remainTime`
  },
  // 获取用户所有时长
  accountRemainTimeListUrl: (host = API_HOST, version = VERSION) => {
    return `${host}api/${version}/account/remainTime/list`
  },
  // 获取用户代金券列表
  accountCardListUrl: (host = API_HOST, version = VERSION) => {
    return `${host}api/${version}/account/card/list`
  },
  // 获取流量包列表
  flowPacketListUrl: (host = API_HOST, version = VERSION) => {
    return `${host}api/${version}/flowPacket/list`
  },
  // 获取订单明细
  orderHistoryUrl: (host = API_HOST, version = VERSION) => {
    return `${host}api/${version}/order/history`
  },
  // 获取账户剩余灵豆
  beanBalanceUrl: (host = API_HOST, version = VERSION) => {
    return `${host}api/${version}/bean/balance`
  },
  // 现金购买时长
  payByMoneyUrl: (host = API_HOST, version = VERSION) => {
    return `${host}api/${version}/order/payByMoney`
  },
  // 灵豆购买时长
  payByBeanUrl: (host = API_HOST, version = VERSION) => {
    return `${host}api/${version}/order/payByBean`
  },

  // 弹窗页面数据
  popupAdUrl: (host = API_HOST, version = VERSION) => {
     return `${host}api/${version}/channel/popupAd`
  }



  //
};

export default ApiUrl