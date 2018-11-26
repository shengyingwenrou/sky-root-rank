// mixins

import {DEBUG, HOST, VERSION_CODE, CHANNEL} from '../config/Config';

export default {
  data() {
    return {
      params: {
        appId: '00000000000000000000000000000000',
        sessionId: '00000000000000000000000000000000',
        networkAccess: '4G',
        versionCode: 4600,
        terminalMac: '000000000000',
        deviceMac: '000000000000',
        bssid: '000000000000',
        token: ''
      }
    }
  },
  methods: {
    init() {
      if (weex.config.appId) {
        this.params.appId = weex.config.appId;
      }
      if (weex.config.sessionId) {
        this.params.sessionId = weex.config.sessionId;
      }
      if (weex.config.networkAccess) {
        this.params.networkAccess = weex.config.networkAccess;
      }
      if (weex.config.versionCode) {
        this.params.versionCode = weex.config.versionCode;
      }
      if (weex.config.terminalMac) {
        this.params.terminalMac = weex.config.terminalMac;
      }
      if (weex.config.deviceMac) {
        this.params.deviceMac = weex.config.deviceMac;
      }
      if (weex.config.bssid) {
        this.params.bssid = weex.config.bssid;
      }
      if (weex.config.token) {
        this.params.token = weex.config.token;
      }
      else if (DEBUG) {
        this.params.deviceMac = '0013ba1a0455',
        this.params.token = '01287eeed9d542c0b9d6865a6e303adb';
      }
    },
    reset(to) {
      if (WXEnvironment.platform === 'Web') {
        if (this.$router) {
          this.$router.replace(to);
        }
      } else {
        if (this.$router) {
          this.$router.replace(to);
        }
      }
    },
    toast(message, duration = 5) {
      let modal = weex.requireModule('modal');
      modal.toast({message: message, duration: duration});
    },
    toBack() {
      if (WXEnvironment.platform === 'Web') {
        this.$router.back();
      } else {
        let navigator = weex.requireModule('navigator');
        navigator.pop({animated: "true"});
      }
    },
    getBaseUrl() {
      let bundleUrl = weex.config.bundleUrl;
      //console.log("bundleUrl:" + bundleUrl);
      bundleUrl = String(bundleUrl);
      let nativeBase;
      let isAndroidAssets = bundleUrl.indexOf('file://assets/') >= 0;

      let isiOSAssets = bundleUrl.indexOf('file:///') >= 0 && bundleUrl.indexOf('WeexDemo.app') > 0;
      if (isAndroidAssets) {
        nativeBase = 'file://assets/dist/';
      }
      else if (isiOSAssets) {
        nativeBase = bundleUrl.substring(0, bundleUrl.lastIndexOf('/') + 1);
      } else {
        let host = HOST;
        //let matches = /\/\/([^\/]+?)\//.exec(bundleUrl);
        //if (matches && matches.length >= 2) {
        //  host = matches[0] + matches[1];
        //}
        nativeBase = host + 'dist/' + VERSION_CODE + "/" + CHANNEL + "/";
      }
      return nativeBase;
    },
    jumpInter(to) {
      if (this.$router) {
        this.$router.push(to);
      }
    },
    jumpWithParams(to, params = {}, animated = true, callback = null) {
      if (WXEnvironment.platform === 'Web') {
        if (this.$router) {
          this.$router.push({name: to, params: params});
        }
      } else {
        let path = this.getBaseUrl();
        let q = this.createQuery(params);
        let url = path + to + '.js' + q;
        let navigator = weex.requireModule('navigator');
        navigator.push({
          url: url,
          animated: animated.toString()
        }, callback)
      }
    },
    getWeexUrl(to, params = {}) {
      let path = this.getBaseUrl();
      let q = this.createQuery(params);
      return path + to + '.js' + q;
    },
    // object 转 URL 参数
    createQuery(obj) {
      if (obj === null || obj === "" || obj.length === 0) {
        return "";
      }
      let url = '?';
      for (let key in obj) {
        if (obj[key] !== null) {
          url += (key + '=' + encodeURIComponent(obj[key]) + '&');
        }
      }
      return url.substring(0, url.lastIndexOf('&'));
    },
    getQuery() {
      if (WXEnvironment.platform === 'Web') {
        return this.$route.params;
      } else {
        return this.getQueryData(weex.config.bundleUrl);
      }
    },
    // 'xxx.js?name=aa' 转 {name: 'aa'}
    getQueryData(url) {
      url = url.substring(url.indexOf('.js?') + 3);
      let result = {};
      if (url.indexOf("?") != -1) {
        let str = url.substr(1);
        let strs = str.split("&");
        for (let i = 0; i < strs.length; i++) {
          result[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
        }
      }
      return result;
    }
  }
}