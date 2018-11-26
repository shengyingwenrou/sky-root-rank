// 统一api的uri定义文件

const api = {
  // 各接口名定义

  // 账号相关

  login: '/api/portal_v2/terminal/account/login', // 登录
  login_back: '/api/1.0/vue/login/', // 登录
  logout: '/api/1.0/vue/logout/', // 注销
  checkToken: '/api/1.0/vue/token/check/', // 校验token
  getAuthCode: '/api/1.0/vue/auth_code/get/', // 获取验证码
  getRemainsTime: '/api/1.0/vue/ownedtime/remains/', // 获取剩余时长
  getUserInfo: '/api/1.0/vue/user/get/', // 获取用户信息
  chgUser: '/api/1.0/vue/user/chg/', // 更换用户账号

  // 联网相关
  connect: '/api/1.0/vue/submit_connect/', // 联网授权
  cleanSsoStatus: '/api/1.0/vue/sso/online_status/clean/', // 清理单点在线终端状态
  getSsoOnlineTerminal: '/api/1.0/vue/sso/online_terminal/get/', // 获取单点在线终端

  // 时长相关
  getFlowPackageList: '/api/1.0/vue/flow_package/list/', // 获取流量包列表
  payFlowPackage: '/api/1.0/vue/flow_package/pay/', // 支付流量包
  getOrderStatus: '/api/1.0/vue/order/get/', // 查询支付状态
  getOrderList: '/api/1.0/vue/order/list/', // 获取订单列表

  // 版本相关
  getAppVersion: '/api/1.0/vue/app_version/get/', // 获取app版本信息

  // 公告
  getNotice: '/api/1.0/vue/notice/get/', // 获取公告

  // banner
  getBannerList: '/api/1.0/vue/banner/list/', // 获取banner列表

  // log
  reportLog: '/api/1.0/vue/log/report/' // 上报日志
};

export default api;
