<!-- 加载器 -->
<template>
    <div :class="[showLoading && 'loading-need-mask']" @click="maskClicked">
        <div class="wdg-loading" :style="{ top: topPosition +'px'}" v-if="showLoading">
            <div class="loading-box" :aria-hidden="true">
                <image :src="loadingImage" class="loading-image" resize="contain" quality="original"></image>
                <text v-if="loadingText" class="loading-text">{{loadingText}}</text>
            </div>
        </div>
    </div>
</template>

<script>
  import Utils from '../../util/Utils';

  const BLACK_GIF = 'https://img.alicdn.com/tfs/TB1Ep_9NVXXXXb8XVXXXXXXXXXX-74-74.gif';

  export default {
    props: {
      show: {
        type: Boolean,
        default: false
      },
      loadingText: {
        type: String,
        default: ''
      },
      loadingImage: {
        type: String,
        default: BLACK_GIF
      },
      interval: {
        type: [Number, String],
        default: 0
      }
    },
    data: () => ({
      showLoading: false,
      tid: 0
    }),
    watch: {
      show() {
        this.setShow();
      }
    },
    computed: {
      topPosition() {
        return (Utils.env.getPageHeight() - 200) / 2;
      }
    },
    created() {
      this.setShow();
    },
    methods: {
      maskClicked() {
        this.$emit('wdgLoadingMaskClicked', {});
      },
      setShow() {
        const {interval, show, showLoading} = this;
        const stInterval = parseInt(interval);
        clearTimeout(this.tid);
        if (show) {
          if (showLoading) {
            return;
          }
          if (stInterval === 0) {
            this.showLoading = true;
          } else {
            this.tid = setTimeout(() => {
              this.showLoading = true;
            }, stInterval);
          }
        } else {
          this.showLoading = false;
        }
      }
    }
  }
</script>

<style scoped>
    .loading-need-mask {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, 0);
    }

    .wdg-loading {
        position: fixed;
        left: 287px;
        top: 500px;
        z-index: 9999;
    }

    .loading-box {
        align-items: center;
        justify-content: center;
        border-radius: 20px;
        width: 175px;
        height: 175px;
        background-color: rgba(0, 0, 0, .8);
    }

    .loading-image {
        height: 75px;
        width: 75px;
    }

    .loading-text {
        color: #ffffff;
        font-size: 24px;
        line-height: 30px;
        height: 30px;
        margin-top: 8px;
        text-overflow: ellipsis;
        width: 140px;
        text-align: center;
    }
</style>