export const DEBUG = true;

//region 签名相关配置
export const APP_SECRET = "32b23116162d447c892f671f667f9da0";
export const RANDOM_CHARS = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                             'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                             'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
//endregion


//region 请求环境配置
// 线上环境
export const HOST = "http://r.magicwifi.com.cn/weex/";
export const API_HOST = "http://magicwifi.com.cn/";

// 测试环境
//export const HOST = "http://192.168.3.129/weex/";
//export const API_HOST = "http://192.168.3.140:8080/";

// 本地环境
//export const HOST = "http://192.168.1.62:8081/";
//export const API_HOST = "http://192.168.1.62:8080/";

// TODO 打包时需更改
//export const VERSION_CODE = 5000; // 版本号
//export const CHANNEL = 'official'; // 渠道号

// online debug 开发专用
export const VERSION_CODE = 6000; // 版本号
export const CHANNEL = 'official'; // 渠道号

//endregion

//region 缓存key值定义
export const CACHE_TENANT_ID = 0; // 项目ID缓存key

/** 现金流量包套餐列表缓存KEY  **/
export const KEY_CACHE_TENANT_RECHARGE_TIME_PLAN_BY_MONEY = 'KEY_CACHE_TENANT_RECHARGE_TIME_PLAN_BY_MONEY';

/** 灵豆兑换流量包套餐列表缓存KEY  **/
export const KEY_CACHE_TENANT_RECHARGE_TIME_PLAN_BY_BEAN = 'KEY_CACHE_TENANT_RECHARGE_TIME_PLAN_BY_BEAN';

/** 网络状态是否良好KEY  true:是  false:否  **/
export const KEY_CACHE_CONNECT_IS_WORK = 'KEY_CACHE_CONNECT_IS_WORK';

//endregion

export const statusHeight = 32;
/**
 * 获取屏幕高度
 */
export function getRealScreenHeight(Utils) {
  if (Utils.env.isWeb()) {
    return Utils.env.getScreenHeight();
  }
  return Utils.env.getScreenHeight() - statusHeight;
}

/**
 * 获取图片
 */
export function getImagePath(name, type = '', abs = '../../') {
  if (WXEnvironment.platform === 'Web') {
    return `${abs}static/img/${name}${type}`
  } else if (WXEnvironment.platform === 'android') {
    //return `local:///${name}`;
    return `${HOST}static/img/${name}${type}`;
  } else {
    return `local:///${name}${type}`;
  }
}