<!-- 对话框 -->
<template>
    <div class="container">
        <wdg-overlay v-if="show" :show="true" :hasAnimation="false"></wdg-overlay>
        <div class="dialog-box" v-if="show" :style="{top:top+'px'}">
            <div class="dialog-content">
                <slot name="title">
                    <text class="content-title">{{title}}</text>
                </slot>
                <slot name="content">
                    <text class="content-subtext">{{content}}</text>
                </slot>
            </div>
            <div class="dialog-footer">
                <div class="footer-btn cancel"
                     v-if="!single"
                     @click="secondaryClicked">
                    <text class="btn-text" :style="{ color: secondBtnColor }">{{cancelText}}</text>
                </div>
                <div class="footer-btn confirm" @click="primaryClicked">
                    <text class="btn-text" :style="{ color: mainBtnColor }">{{confirmText}}</text>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
  import WdgOverlay from './WdgOverlay';

  export default {
    components: {WdgOverlay},
    props: {
      show: {
        type: Boolean,
        default: false
      },
      single: {
        type: Boolean,
        default: false
      },
      title: {
        type: String,
        default: ''
      },
      content: {
        type: String,
        default: ''
      },
      top: {
        type: Number,
        default: 400
      },
      cancelText: {
        type: String,
        default: '取消'
      },
      confirmText: {
        type: String,
        default: '确定'
      },
      mainBtnColor: {
        type: String,
        default: '#22bbbf'
      },
      secondBtnColor: {
        type: String,
        default: '#666666'
      }
    },
    methods: {
      secondaryClicked() {
        this.$emit('wdgDialogCancelBtnClicked', {
          type: 'cancel'
        });
      },
      primaryClicked(e) {
        this.$emit('wdgDialogConfirmBtnClicked', {
          type: 'confirm'
        });
      }
    }
  }
</script>

<style scoped>
    .container {
        position: fixed;
        width: 750px;
        /*兼容H5异常*/
        z-index: 99999;
    }

    .dialog-box {
        position: fixed;
        left: 96px;
        width: 558px;
        background-color: #FFFFFF;
    }

    .dialog-content {
        padding-top: 36px;
        padding-bottom: 36px;
        padding-left: 36px;
        padding-right: 36px;
    }

    .content-title {
        color: #333333;
        font-size: 36px;
        text-align: center;
        margin-bottom: 24px;
    }

    .content-subtext {
        color: #666666;
        font-size: 26px;
        line-height: 36px;
        text-align: center;
    }

    .dialog-footer {
        flex-direction: row;
        align-items: center;
        border-top-color: #F3F3F3;
        border-top-width: 1px;
    }

    .footer-btn {
        flex-direction: row;
        align-items: center;
        justify-content: center;
        flex: 1;
        height: 90px;
    }

    .cancel {
        border-right-color: #F3F3F3;
        border-right-width: 1px;
    }

    .btn-text {
        font-size: 36px;
        color: #666666;
    }
</style>