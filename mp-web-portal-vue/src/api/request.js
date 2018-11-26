import axios from 'axios'
import store from '@/store/index.js'

axios.defaults.baseURL = process.env.BASE_URL;
axios.defaults.timeout = 50000;

const postRequest = {
  postRequest: function (url, params) {
    return axios({
        method: 'post',
        url: `${url}`,
        data: params,
        dataType:'json',
        transformRequest: [function (data) {
            let ret = ''
            for (let it in data) {
               ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            ret += 'timeStamp' + '=' + encodeURIComponent(Date.parse(new Date())/1000) + '&'
            ret += 'deviceMac' + '=' + encodeURIComponent(store.state.portal.base_deviceMac) + '&'
            ret += 'terminalMac' + '=' + encodeURIComponent(store.state.portal.base_terminalMac) + '&'
            ret += 'sign' + '=' + encodeURIComponent('md5Str')
            return ret
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
     }).catch(function(error){
      console.log(error);
    });
  }
}
export default postRequest
