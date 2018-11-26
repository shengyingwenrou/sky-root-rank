/**
 * 客户端原生方法封装
 */
import Utils from './Utils';

const Native = {
  /**
   * 刷新当前页面
   */
  reload() {
    if (Utils.env.isWeb()) {
      return;
    }
    let common = weex.requireModule('common');
    common.reloadPage();
  },
  /**
   * 支付
   * @param params 支付参数
   * @param type 支付类型: 5=微信支付,10=支付宝支付
   * @param callback 回调函数
   */
  pay(params, type = 5, callback = null) {
    if (Utils.env.isWeb()) {
      return;
    }
    let pay = weex.requireModule('pay');
    pay.payByOrder(type, params, callback);
  },

    /**
     * 灵豆兑换时长成功 回调客户端
     * @param rechargeResult 兑换结果
     * @param callback 回调函数
     */
    payByBeans(rechargeResult) {
        if (Utils.env.isWeb()) {
            return;
        }
        let pay = weex.requireModule('pay');
        pay.payResult(rechargeResult);
    },

  /**
   * 页面跳转
   */
  jump: {
      /**
       * 跳转到原生界面
       * @param destId 原生界面ID
       * @param callback 回调函数
       */
      native(destId, callback = null) {
          if (Utils.env.isWeb()) {
              return;
          }
          let jump = weex.requireModule('jump');
          let jsonData = "{\"linkType\":1001,\"destination\":" + destId + "}";
          jump.open(jsonData, callback);
      },
      /**
       * 跳转到weex界面
       * @param url 跳转目标js的全地址
       * @param callback 回调函数
       */
      weex(url, callback = null) {
          if (Utils.env.isWeb()) {
              return;
          }
          let jump = weex.requireModule('jump');
          let jsonData = "{\"linkType\":1002,\"addition\":\"" + url + "\"}";
          jump.open(jsonData, callback);
      },
      /**
       * 跳转到原生界面并关闭其它界面
       * @param destId 原生界面ID
       * @param callback 回调函数
       */
      nativeAndClose(destId, callback = null) {
          if (Utils.env.isWeb()) {
              return;
          }
          let jump = weex.requireModule('jump');
          let jsonData = "{\"linkType\":1001,\"destination\":" + destId + "}";
          jump.openAndCloseOther(jsonData, callback);
      },
      /**
       * 跳转到weex界面并关闭其它界面
       * @param url 跳转目标js的全地址
       * @param callback 回调函数
       */
      weexAndClose(url, callback = null) {
          if (Utils.env.isWeb()) {
              return;
          }
          let jump = weex.requireModule('jump');
          let jsonData = "{\"linkType\":1002,\"addition\":\"" + url + "\"}";
          jump.openAndCloseOther(jsonData, callback);
      },

      /**
       * 关闭以弹框形式打开的混编页面
       * @param instanceId 客户端定义的实例ID
       */
      closeDialog(instanceId){
          if (Utils.env.isWeb()) {
              return;
          }
          let jump = weex.requireModule('jump');
          jump.closeDialog(instanceId);
      },

      /**
       * 统一打开 链接|客户端Native|客户端下载|系统下载
       * @param linkType 与客户端同步定义的跳转类型
       * @param target  目标地址
       */
      open(linkType, destination, target, callback = null){
          let jsonData = "{\"linkType\":" + linkType + ",\"destination\":" + destination + ",\"addition\":\"" + target + "\"}";
          let jump = weex.requireModule('jump');
          jump.open(jsonData, callback);
      }
  },

    /**
   * 消息处理
   */
  modal: {
    toast(message, duration = 3) {
      let modal = weex.requireModule('modal');
      modal.toast({message: message, duration: duration});
    },
    alert(message, okTitle = "OK", callback = null) {
      let modal = weex.requireModule('modal');
      modal.alert({message: message, okTitle: okTitle}, callback);
    }
  },
  /**
   * 数据处理
   */
  data: {
    /**
     * 设置缓存
     * @param key 缓存key
     * @param value 缓存值
     */
    setCache(key, value) {
      if (Utils.env.isWeb()) {
        return;
      }
      let storage = weex.requireModule('data');
        return new Promise(function (resolve) {
            try {
                storage.setItem(key, value,res=> {
                    if (res.result == 'success') {
                        // 数据缓存成功
                        resolve(true)
                    }
                });
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
      if (Utils.env.isWeb()) {
        return;
      }
      let storage = weex.requireModule('data');
      storage.removeItem(key)
    },
    /**
     * 获取缓存
     * @param key 缓存key
     */
    getCache(key) {
      if (Utils.env.isWeb()) {
        return;
      }
      let storage = weex.requireModule('data');
      return new Promise(function (resolve) {
        try {
          storage.getItem(key, e => {
            if (e && e.value) {
              resolve(e.value);
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

      /**
       * 根据缓存key获取缓存[异步asynchronization]
       * @param key 缓存key
       */
      asynGetCacheBykey(key) {
          if (Utils.env.isWeb()) {
              return;
          }
          let storage = weex.requireModule('data');
          return new Promise(function (resolve) {
              try {
                  storage.getItem(key, res => {
                      if (res.result == 'success') {
                          // 异步数据返回
                          resolve(res.data)
                      }
                  });
              } catch (e) {
                  console.log(e);
                  resolve(null);
              }
          });
      },



      /**
       * 根据缓存key获取缓存[同步synchronous]
       * @param key 缓存key
       */
      synGetCacheBykey(key) {
          if (Utils.env.isWeb()) {
              return;
          }
          let storage = weex.requireModule('data');
          try {
              storage.getItem(key, res => {
                  if (res.result == 'success') {
                      return res.data;
                  }else{
                    return null;
                  }
              });
          } catch (e) {
              return null;
              console.log(e);
          }
      }
  }
};

export default Native;