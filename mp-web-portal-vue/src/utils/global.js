// 全局方法
import router from '@/router/index'
import store from '@/store/index'
import http from '@/api/request'
import api from '@/api/api'

export default {
  install(Vue, options) {
    Vue.prototype.$http = http; // axios全局方法
    Vue.prototype.$api = api; // api地址

    /**
     * path 路径
     * params {}
     */
    Vue.prototype.routerLink = function () {
      let params = {};
      let path = arguments[0] ? arguments[0] : null;

      router.push(
        {
          path: path,
          query: params
        }
      )
    };
    /**
     * 刷新
     */
    Vue.prototype.$reset = function () {
      window.location.reload();
    };
    Vue.prototype.$getUrl = function () {
      let url = store.state.sys.baseUrl;
      let http = url.substring(0, url.indexOf('//') + 2);
      url = url.substring(url.indexOf('//') + 2, url.length);
      if (url.indexOf('/') >= 0) {
        url = url.substring(0, url.indexOf('/'));
      }
      return http + url;
    }
  }
}
