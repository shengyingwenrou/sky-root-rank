import toast from '@/assets/js/common/toast.js'

var checkUtil = {
  checkTelphone: function (tel) {
    let len = 11
    if (!tel) {
      toast.toastAlert('请输入手机号码')
      return false
    }
    let pattern = /^1/
    if (!pattern.test(tel)) {
      toast.toastAlert('请输入正确格式的手机号')
      return false
    }
    if (tel.length !== len) {
      toast.toastAlert('请输入11数字格式的手机号')
      return false
    }

    let regExp = /\d{11}/g
    if (!regExp.test(tel)) {
      toast.toastAlert('请输入正确格式的手机号')
      return false
    }
    return true
  },

  toastMessage:function(message){
    toast.toastAlert(message)
  },

  checkAuthCode:function(authCode){
    let len = 4
    if (!authCode) {
      toast.toastAlert('请输入验证码')
      return false
    }
    let regExp = /\d/g
    if (!regExp.test(authCode)) {
      toast.toastAlert('请输入正确的验证码')
      return false
    }
    let pattern = /\d{4}/g
    if (!pattern.test(authCode)) {
      toast.toastAlert('请输入有效数字验证码')
      return false
    }
    if (authCode.length !== len) {
      toast.toastAlert('请输入有效四位验证码')
      return false
    }
    return true
  }

};
export default checkUtil
