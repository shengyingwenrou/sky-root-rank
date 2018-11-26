import {DEBUG} from "../config/Config";
import Sign from '../util/Sign';

export const CONTENT_TYPE_JSON = "application/json";
export const CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

//网络连接失败
export const NETWORK_CONNECT_FAILED = -1;
//网络超时
export const NETWORK_TIMEOUT = 2;
//网络返回数据格式化异常
export const NETWORK_JSON_EXCEPTION = 3;

export const SUCCESS = 200;

class HttpManager {
  constructor() {
    this.optionParams = {
      timeoutMs: 15000,
    };
  };

  async getFetch(url, header, type) {
    return this.netFetch(url, 'GET', null, null, header, type)
  }

  async netFetch(url, method = 'GET', params, json, header, type) {

    // header
    let headers = {};
    if (header) {
      headers = Object.assign({}, headers, header)
    }

    // requestParams
    let requestParams;
    if (method !== 'GET') {
      if (json) {
        requestParams = this.formParamsJson(method, params, headers)
      } else {
        requestParams = this.formParams(method, params, headers)
      }
    } else {
      requestParams = this.formParams(method, params, headers)
    }

    let response = await this.requestWithTimeout(this.optionParams.timeoutMs, this.fetch(url, requestParams, type));

    if (DEBUG) {
      console.log('[mp]请求url: ', url);
      console.log('[mp]请求参数: ', requestParams);
      console.log('[mp]返回参数: ', response);
    }

    if (response && response.status === NETWORK_TIMEOUT) {
      return {
        result: false,
        code: NETWORK_TIMEOUT,
        data: ""
      }
    }

    if (response && response.status === NETWORK_CONNECT_FAILED) {
      return {
        result: false,
        code: NETWORK_CONNECT_FAILED,
        data: ""
      }
    }

    try {
      let responseJson = response.data;

      if (response.status === 200 || response.status === 201 || response.status === 204 || response.status === 202) {
        return {
          result: true,
          code: SUCCESS,
          data: responseJson,
          headers: response.headers
        }
      }
    } catch (e) {
      console.log(e, url);
      return {
        data: response._bodyText,
        result: response.ok,
        code: response.status ? response.status : NETWORK_JSON_EXCEPTION,
        response
      }
    }

    return {
      result: false,
      code: response.status,
      data: "",
    }
  }

  formParamsJson(method, params, headers) {
    params.timestamp = new Date().getTime(); // 时间戳
    params.nonceStr = Sign.randomStr(false, 16); // 随机数
    params.sign = Sign.make(params);
    const body = JSON.stringify(params);
    const req = {
      method: method,
      headers: {
        'Content-Type': CONTENT_TYPE_JSON,
        ...(headers || {})
      },
      body
    };
    return req
  }

  formParams(method, params, headers) {
    params.timestamp = new Date().getTime(); // 时间戳
    params.nonceStr = Sign.randomStr(false, 16); // 随机数
    const str = [];
    for (let p in params) {
      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(params[p]));
    }
    let sign = Sign.make(params);
    str.push("sign=" + sign);
    let body = null;
    if (str.length > 0) {
      body = str.join("&");
    }
    const req = {
      method: method,
      headers: {
        'Content-Type': CONTENT_TYPE_FORM,
        ...(headers || {})
      }
      ,
      body
    };
    return req
  }

  requestWithTimeout(ms, promise) {
    return new Promise((resolve, reject) => {
      const timeoutId = setTimeout(() => {
        resolve({
          status: NETWORK_TIMEOUT,
          message: ""
        })
      }, ms);
      promise.then(
        (res) => {
          clearTimeout(timeoutId);
          resolve(res);
        },
        (err) => {
          clearTimeout(timeoutId);
          resolve(err);
        }
      );
    })
  }

  fetch(path, requestParams, type = 'json') {
    const stream = weex.requireModule('stream');
    return new Promise((resolve, reject) => {
      stream.fetch({
        method: requestParams.method,
        url: path,
        headers: requestParams.headers,
        type: type,
        body: requestParams.method === 'GET' ? "" : requestParams.body
      }, (response) => {
        if (response.status === 200 || response.status === 201 || response.status === 204 || response.status === 202) {
          resolve(response)
        } else {
          reject(response)
        }
      }, () => {
      })
    })

  }
}

export default new HttpManager();