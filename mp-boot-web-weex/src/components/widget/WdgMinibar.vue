<!-- 顶部标题栏 -->
<template>

    <div>

    <div class="fix-top">
        <wdg-status-bar></wdg-status-bar>
        <div class="wdg-minibar">
            <div class="left" @click="leftClicked" aria-label="返回" :accessible="true">
                <image class="left-button" :src="leftIcon"></image>
            </div>
            <text class="middle-title">{{title}}</text>
            <div class="right"></div>
        </div>
    </div>

    <div class="fix-top-space">    </div>

    </div>
</template>

<style scoped>
    .wdg-minibar {
        width: 750px;
        height: 120px;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        background-color: #22BBBF;
        background-image: linear-gradient(to right,#36c6ad,#12a7d8);
    }

    .fix-top{
        position: fixed;
        z-index: 100;
        background-color: #22BBBF;
        background-image: linear-gradient(to right,#36c6ad,#12a7d8);
        top:0px;
    }

    .fix-top-space{
        width:750px;
        height:150px;
        background-color: #f3f3f3;
    }

    .left {
        width: 180px;
        padding-left: 32px;
        height:100px;
    }

    .middle-title {
        height: 60px;
        font-size: 32px;
        color: #ffffff;
        margin-top: 32px;
        line-height: 34px;
    }

    .right {
        width: 180px;
        padding-right: 32px;
        align-items: flex-end;
    }

    .left-button {
        width: 48px;
        height: 41px;
        top:34px;
    }
</style>

<script>
  import WdgStatusBar from './WdgStatusBar';
  import {getImagePath} from '../../config/Config';

  export default {
    components: {WdgStatusBar},
    props: {
      title: {
        type: String,
        default: '标题'
      }
    },
    data() {
      return {
        leftIcon: ''
      }
    },
    created() {
      this.leftIcon = getImagePath('goback', '.png');
    },
    methods: {
      leftClicked() {
        this.$emit('wdgMinibarLeftClicked', {});
      }
    }
  }
</script>