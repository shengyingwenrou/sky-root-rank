/**
 * 签名工具
 */
import md5 from 'js-md5';
import {APP_SECRET, RANDOM_CHARS} from '../config/Config';

const Sign = {
  /**
   * 参数签名
   * @param params 待签名参数
   */
  make(params) {
    const keyValues = [];
    let keys = Object.keys(params).sort();
    for (let i = 0; i < keys.length; i++) {
      if (keys[i] === "sign") {
        continue;
      }
      keyValues.push(keys[i] + "=" + params[keys[i]]);
    }
    let strA = null;
    if (keyValues.length > 0) {
      strA = keyValues.join("&");
    }
    let strB = strA + "&key=" + APP_SECRET;
    console.log("sign strB: " + strB);
    return md5(strB);
  },
  /**
   * 随机数
   * @param randomLen 是否随机长度
   * @param min 最小长度
   * @param max 最大长度
   *
   * @returns {string}
   */
  randomStr(randomLen, min, max) {
    let str = "";
    let len = min;
    // 随机产生
    if (randomLen) {
      len = Math.round(Math.random() * (max - min)) + min;
    }
    for (let i = 0; i < len; i++) {
      let pos = Math.round(Math.random() * (RANDOM_CHARS.length - 1));
      str += RANDOM_CHARS[pos];
    }
    return str;
  },
};

export default Sign