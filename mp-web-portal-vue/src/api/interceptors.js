import axios from 'axios'
import store from '@/store/index.js'
import toast from '@/assets/js/common/toast.js'


axios.defaults.baseURL = process.env.BASE_URL;
axios.defaults.timeout = 50000;

/**
 * 请求拦截器
 */
axios.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    console.log(error);
    Promise.reject(error);
  }
)
axios.interceptors.response.use(
  response => {
    console.log('SUCESS:' + response);
    if(response.data.response_header.status==='00'){
      store.commit('SET_SYS_CONFIG', response.data.response_header);
      return response;
    }else{
      // 服务正常 业务异常的状态码(先默认弹框处理TODO)
      console.log('FAIL: fail');
      return response;
    }
  },
  error => {
    // 服务可能异常 出现的服务状态码(先默认弹框处理TODO)

    console.log(error);
    if (error.response) {
      switch (error.response.status) {
        //登录失效状态码
        case 401:
          router.replace({
              path: 'login',
              query: {redirect: router.currentRoute.fullPath}
          })
          break;
        // 服务超时状态码
        case 504:
          toast.toastAlert(error.config.url + '<br>'+'504请求超时');
          //router.replace({
          //})
          break;
        // 通用逻辑表现
        default:
        console.log('ERROR: error');
      }
    }
    toast.toastAlert(error.config.url + '<br>'+error.request.status);
    return Promise.reject(error)
  }
)

//如果不想要这个拦截器也简单，可以删除拦截器
var interceptorTest = axios.interceptors.request.use(function () {
  alert('test interceptorTest '  );
})
axios.interceptors.request.eject(interceptorTest)

//为自定义axios实例添加拦截器
var instance = axios.create();
instance.interceptors.request.use(function () {
  alert(' i am coming');
});
export default axios
