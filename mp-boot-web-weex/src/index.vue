<template>
    <div class="wrapper" :style="{height: realHeight}">
        <router-view class="r-box">
            <!-- 这里是不被缓存的视图组件 -->
        </router-view>
    </div>
</template>

<script>
  import Utils from "./util/Utils";
  import {getRealScreenHeight} from "./config/Config";

  export default {
    name: "App",
    data() {
      return {
        realHeight: getRealScreenHeight(Utils)
      };
    }
  };
</script>
<style scoped>


    body{
        background-color: #F3F3F3;
    }
    .wrapper {
        width: 750px;
        justify-content: center;
        align-items: center;
        background-color: #F3F3F3;
        //background-color: #ffffff;
    }

    .r-box {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
    }
</style>
