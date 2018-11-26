<!-- 状态bar -->
<template>
    <div class="status-bar" v-if="show"></div>
</template>

<script>
  import Utils from "../../util/Utils";

  export default {
    data() {
      return {
        show: true
      }
    },
    created() {
      if (Utils.env.isWeb()) {
        this.show = false;
      }
    }
  }
</script>

<style scoped>
    .status-bar {
        height: 32px;
        background-color: #22BBBF;
        background-image: linear-gradient(to right, #36c6ad, #12a7d8);
    }
</style>