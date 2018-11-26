

//TODO 通过样式或者id触发调用的
/*$(function(){

      $(".down_load_button_index").click(function(){
          openWakeUpClient();
      })

      $(".down_load_bottom").click(function(){
          openWakeUpClient();
          downloadLogger(4);
      })

      $(".down_load_bottom_t").click(function(){
          openWakeUpClient();
          downloadLogger(2);
      })

      $("#bw_bean").click(function(){
          openWakeUpClient();
          downloadLogger(22);
      })

      $("#bw_welfare").click(function(){
          openWakeUpClient();
          downloadLogger(23);
      })

})*/

// 唤起客户端
export function openWakeUpClient (){
    let config = {
       scheme_IOS: 'cundong://',
       scheme_Adr: 'cundong://boot',
       timeout: 600
    };

    let ifr = document.createElement('iframe');
    ifr.src = config.scheme_Adr;
    ifr.style.display = 'none';
    document.body.appendChild(ifr);
    setTimeout(function(){
        document.body.removeChild(ifr);
    }, config.timeout);
}



