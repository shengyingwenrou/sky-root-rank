import axios from 'axios'

/**
 * 获取项目名称
 */
export function getProjectSetting(success, fail) {
  let setting = {
    projectName: 'WiFi精灵',
    projectIconImageUrl: 'http://magicwifi.com.cn/magicwifi/static/portal/v2/images/portal_03.png',
    projectBannerImageUrl: 'http://magicwifi.com.cn/magicwifi/static/portal/v2/images/icon_lwq_pic3.jpg'

  };
  //请求数据
  axios.post('/getProjectSetting', {
    deviceMac: ''
  }).then(function (response) {
      console.log(response);
      success(setting);
    }).catch(function (error) {
      console.log(error);
      //setting.projectName = 'error';
      fail(setting);
    });
}
