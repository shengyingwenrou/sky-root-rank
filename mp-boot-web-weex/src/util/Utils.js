/**
 * 工具类
 */
import UrlParser from 'url-parse';

const Utils = {
  UrlParser: UrlParser,
  env: {
    isWeb() {
      const {platform} = weex.config.env;
      return typeof (window) === 'object' && platform.toLowerCase() === 'web';
    },
    isAndroid() {
      const {platform} = weex.config.env;
      return platform.toLowerCase() === 'android';
    },
    isIOS() {
      const {platform} = weex.config.env;
      return platform.toLowerCase() === 'ios';
    },
    /**
     * 是否为 iPhone X
     * @returns {boolean}
     */
    isIPhoneX() {
      const {deviceHeight} = weex.config.env;
      if (Utils.env.isWeb()) {
        return typeof window !== undefined && window.screen && window.screen.width && window.screen.height && (parseInt(window.screen.width, 10) === 375) && (parseInt(window.screen.height, 10) === 812);
      }
      return Utils.env.isIOS() && deviceHeight === 2436;
    },
    /**
     * 获取weex屏幕真实的设置高度，需要减去导航栏高度
     * @returns {Number}
     */
    getPageHeight() {
      const {env} = weex.config;
      const navHeight = Utils.env.isWeb() ? 0 : (Utils.env.isIPhoneX() ? 176 : 132);
      return env.deviceHeight / env.deviceWidth * 750 - navHeight;
    },
    /**
     * 获取weex屏幕真实的设置高度
     * @returns {Number}
     */
    getScreenHeight() {
      const {env} = weex.config;
      return env.deviceHeight / env.deviceWidth * 750;
    }
  },
  animation: {
    /**
     * 返回定义页面转场动画起初的位置
     * @param ref
     * @param transform 运动类型
     * @param status
     * @param callback 回调函数
     */
    pageTransitionAnimation(ref, transform, status, callback) {
      const animation = weex.requireModule('animation');
      animation.transition(ref, {
        styles: {
          transform: transform
        },
        duration: status ? 250 : 300, // ms
        timingFunction: status ? 'ease-in' : 'ease-out',
        delay: 0 // ms
      }, function () {
        callback && callback();
      });
    }
  },
  uiStyle: {
    /**
     * 返回定义页面转场动画起初的位置
     * @param animationType 页面转场动画的类型 push，model
     */
    pageTransitionAnimationStyle(animationType) {
      if (animationType === 'push') {
        return {
          left: '750px',
          top: '0px',
          height: (weex.config.env.deviceHeight / weex.config.env.deviceWidth * 750) + 'px'
        }
      } else if (animationType === 'model') {
        return {
          top: (weex.config.env.deviceHeight / weex.config.env.deviceWidth * 750) + 'px',
          left: '0px',
          height: (weex.config.env.deviceHeight / weex.config.env.deviceWidth * 750) + 'px'
        }
      }
      return {}
    }
  },
  /**
   * 判断对象是否字符串
   */
  isString(obj) {
    return typeof (obj) === 'string';
  },
  /**
   * 判断对象是否非空数组
   */
  isNonEmptyArray(obj = []) {
    return obj && obj.length > 0 && Array.isArray(obj) && typeof obj !== 'undefined';
  },
  isObject(item) {
    return (item && typeof item === 'object' && !Array.isArray(item));
  },
  isEmptyObject(obj) {
    return Object.keys(obj).length === 0 && obj.constructor === Object;
  },
  appendProtocol(url) {
    if (/^\/\//.test(url)) {
      const {
        bundleUrl
      } = weex.config;
      return `http${/^https:/.test(bundleUrl) ? 's' : ''}:${url}`;
    }
    return url;
  },
  encodeURLParams(url) {
    const parsedUrl = new UrlParser(url, true);
    return parsedUrl.toString();
  },
  goToH5Page(jumpUrl, animated = false, callback = null) {
    const Navigator = weex.requireModule('navigator');
    const jumpUrlObj = new Utils.UrlParser(jumpUrl, true);
    const url = Utils.appendProtocol(jumpUrlObj.toString());
    Navigator.push({
      url: Utils.encodeURLParams(url),
      animated: animated.toString()
    }, callback);
  },
  //region cache
  /**
   * 设置缓存
   * @param key 缓存key
   * @param value 缓存值
   */
  setCache(key, value) {
    let storage = weex.requireModule('storage');
    return new Promise(function (resolve) {
      try {
        storage.setItem(key, value);
        resolve(true)
      } catch (e) {
        console.log(e);
        resolve(false);
      }
    });
  },
  /**
   * 删除缓存
   * @param key 缓存key
   */
  removeCache(key) {
    let storage = weex.requireModule('storage');
    storage.removeItem(key)
  },
  /**
   * 获取缓存
   * @param key 缓存key
   */
  getCache(key) {
    let storage = weex.requireModule('storage');
    return new Promise(function (resolve) {
      try {
        storage.getItem(key, e => {
          if (e.result === 'success') {
            resolve(e.data);
          } else {
            resolve(null);
          }
        });
      } catch (e) {
        console.log(e);
        resolve(null);
      }
    });
  },
   /** 小数位精确到两位 **/
   toDecimal: function (x) {
       let f = 0;
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
  //endregion
};

export default Utils;