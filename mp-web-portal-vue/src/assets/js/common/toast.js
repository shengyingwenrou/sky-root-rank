const checkUtil = {
    toastAlert: function  () {
      let _config = {
        message : 'info',
        position : 'middle',
        time : 2000
      };
      let _len = arguments.length;
      if (_len > 0) {
        let arg0 = arguments[0], arg1 = arguments[1], arg2 = arguments[2];
        let positionReg = /(bottom|top|middle)/i;
        let timeReg = /[1-9]\d*/;

        _config.message = arg0;
        if (positionReg.test(arg1)) {
          _config.position = arguments[1];
        } else if(timeReg.test(arg1)) {
          _config.time = arguments[1];
        }
        if (timeReg.test(arg2)) {
          _config.time = arguments[2];
        }
      }
      if ($('.web_alert').length <= 0) {
        $('body').append('<div class=\'web_alert row margin_auto \'>' + _config.message + '</div>')
      } else {
        $('.web_alert').html(_config.message);
      }
      let alertWidth = $('.web_alert').width(), windowWidth = $(window).width(),alertHeight = $('.web_alert').height(), windowHeight = $(window).height();

      $('.web_alert').show();
      $('.web_alert').css('left', (windowWidth - alertWidth)/2 - 10);
      if (_config.position==='bottom' ) {
        $('.web_alert').css('bottom', 50);
        $('.web_alert').css('top', '');
      } else if (_config.position === 'top') {
        $('.web_alert').css('bottom', '');
        $('.web_alert').css('top', 50);
      } else if (_config.position === 'middle') {
        $('.web_alert').css('bottom', (windowHeight - alertHeight)/2 - 30);
      }
      setTimeout(function () {
        $('.web_alert').hide();
      }, _config.time);
    }
};
export default checkUtil
