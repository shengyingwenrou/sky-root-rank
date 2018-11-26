<template>
    <div class="project-area">
        <div class="project-item padding-top-20 padding-bottom-20">
            <!--
            <text class="font-size-30 color-757575">剩余时长：</text>
             <img class="icon" :src="leftTimeIcon">
            -->
            <text class="font-size-30 color-757575">剩余时长：</text>
            <text class="font-size-30">{{remainDay||'0天0时0分'}}</text>
        </div>

        <div class="project-item padding-bottom-20">
            <!--
            <text class="font-size-30 color-757575">上网地点：</text>
             <img class="icon" :src="positionIcon">
            -->

            <text class="font-size-30 color-757575">上网地点：</text>
            <text class="font-size-30 width-400">{{tenantName||'暂时无法定位'}}</text>
            <text class="project-item-position-button font-size-30" @click="jumpWithParams('RechargeChangeProject')">更换地点</text>
        </div>
    </div>
</template>


<script>
    import {getImagePath} from '../../config/Config';
  export default {

      data(){
          return {
              positionIcon: '', // 返回icon
              leftTimeIcon: '', // 推荐icon
          }
      },

      created() {
//          this.positionIcon = getImagePath('position_choosed', '.png');
//          this.leftTimeIcon = getImagePath('left_time', '.png');
      },

      props: {
          remainDay: {
              type: String,
              default: ''
          },
          tenantName: {
              type: String,
              default: ''
          }
      }
  };
</script>

<style scoped>
    .font-size-30 {
        font-size: 30px;
    }

    .icon{
        width: 40px;
        height: 40px;
    }

    .project-area {
        background-color: #ffffff;
        width: 750px;
        padding-top: 10px;
        min-height: 150px;
    }


    .width-400{
        width:420px;
    }
    .project-item {
        padding-left: 20px;
        padding-top: 6px;
        flex-direction: row;
        justify-content: flex-start;
        color: #757575;
    }

    .padding-top-20{
        padding-top: 20px;
    }

    .color-757575{
        color: #757575;
    }
    .padding-bottom-20{
        padding-bottom: 20px;
    }

    .project-item-position-button {
        color: #b8b8b8;
        text-decoration: underline;
        font-size: 32px;
        line-height:32px;
    }
</style>