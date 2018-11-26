<template>
    <div class="wrapper">
            <div class="empty-list">
                <image class="empty-list-img" :src="emptyIcon"></image>
                <text class="empty-text">暂无数据</text>
            </div>
    </div>
</template>

<script>

  import {getImagePath} from '../../config/Config';

  export default {
    components: {},

    data() {
      return {
          emptyIcon: '',
      }
    },
    created() {
        this.emptyIcon = getImagePath('empty_data', '.png');
    },
    mounted() {

    },
    filters: {

    },
    methods: {

    }
  };
</script>

<style scoped>

    .wrapper[data-v-8f0dd1de] {
        width: 10rem;
        justify-content: center;
        align-items: center;
        background-color: #F3F3F3;
    }
    .empty-list {
        width: 750px;
        height: 520px;
        flex-direction: column;
        justify-content: center;
        margin-top: 120px;
    }

    .empty-list-img {
        width: 275px;
        height: 220px;
        margin-left: 242px;
    }

    .empty-text {
        color: #9e9e9e;
        text-align: center;
        font-size: 30px;
        margin-top: 20px;
    }

</style>

