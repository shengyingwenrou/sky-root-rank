import ApiUrl from './ApiUrl'
import Api, {NETWORK_CONNECT_FAILED, NETWORK_TIMEOUT} from './Api'
import Native from './Native'
import {DEBUG} from "../config/Config";

function handleRes(res) {

  // Native.modal.toast("handleRes："+JSON.stringify(res));

  if (res && res.result && res.data) {

    //if (res.code === NETWORK_CONNECT_FAILED) {
    //    this.jumpWithParams("");
    //    // TODO 跳转到网络异常界面
    //}
    if (res.data.code === 0) { // 接口返回正常
      return {
        result: res.result,
        code: res.data.code,
        msg: res.data.msg,
        data: res.data.data
      }
    } else if (res.data.code === 2028) { // token 失效
      Native.modal.toast("您的登录状态已失效，请重新登录！", 5);
      Native.jump.nativeAndClose(33);
    } else {
      return {
        result: false,
        code: res.data.code,
        msg: res.data.msg
      }
    }
  } else {
    return {
      result: false,
      code: res.code,
      msg: "接口请求失败"
    }
  }
}

/**
 * 获取用户剩余时长
 */
const getAccountRemainTime = async (params) => {
  let url = ApiUrl.accountRemainTimeUrl();
  let res = await Api.netFetch(url, 'POST', params);
  if (DEBUG) {
    console.log('getAccountRemainTime: ', res);
  }
  return handleRes(res);
};

/**
 * 获取用户剩余时长列表
 */
const getAccountRemainTimeList = async (params) => {
  let url = ApiUrl.accountRemainTimeListUrl();
  let res = await Api.netFetch(url, 'POST', params);
  if (DEBUG) {
    console.log('getAccountRemainTimeList: ', res);
  }
  return handleRes(res);
};

/**
 * 获取用户代金券列表
 */
const getAccountCardList = async (params) => {
  let url = ApiUrl.accountCardListUrl();
  let res = await Api.netFetch(url, 'POST', params);
  if (DEBUG) {
    console.log('getAccountCardList: ', res);
  }
  return handleRes(res);
};

/**
 * 获取流量包列表
 */
const getFlowPacketList = async (params) => {
  let url = ApiUrl.flowPacketListUrl();
  let res = await Api.netFetch(url, 'POST', params);
  if (DEBUG) {
    console.log('getFlowPacketList: ', res);
  }
  return handleRes(res);
};

/**
 * 获取订单明细
 */
const getOrderHistory = async (params) => {
  let url = ApiUrl.orderHistoryUrl();
  let res = await Api.netFetch(url, 'POST', params);
  if (DEBUG) {
    console.log('getOrderHistory: ', res);
  }
  return handleRes(res);
};

/**
 * 获取剩余灵豆
 */
const getBeanBalance = async (params) => {
  let url = ApiUrl.beanBalanceUrl();
  let res = await Api.netFetch(url, 'POST', params);
  if (DEBUG) {
    console.log('getBeanBalance: ', res);
  }
  return handleRes(res);
};

/**
 * 现金购买时长
 */
const payByMoney = async (params) => {
  let url = ApiUrl.payByMoneyUrl();
  let res = await Api.netFetch(url, 'POST', params);
  if (DEBUG) {
    console.log('payByMoney: ', res);
  }
  return handleRes(res);
};

/**
 * 灵豆购买时长
 */
const payByBean = async (params) => {
  let url = ApiUrl.payByBeanUrl();
  let res = await Api.netFetch(url, 'POST', params);
  if (DEBUG) {
    console.log('payByBean: ', res);
  }
  return handleRes(res);
};

/**
 * 弹框广告
 */
const getPopupAd = async (params) => {
    let url = ApiUrl.popupAdUrl();
    let res = await Api.netFetch(url, 'POST', params);
    if (DEBUG) {
        console.log('getPopupAd: ', res);
    }
    return handleRes(res);
};




export default {
  getAccountRemainTime,
  getAccountRemainTimeList,
  getAccountCardList,
  getFlowPacketList,
  getOrderHistory,
  getBeanBalance,
  payByMoney,
  payByBean,
  getPopupAd
}