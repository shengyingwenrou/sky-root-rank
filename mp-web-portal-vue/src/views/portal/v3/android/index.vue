<template>
  <div class="lf">
    <slogana-and-login></slogana-and-login>
    <download-and-introduce></download-and-introduce>
  </div>
</template>

<script>

  import SloganaAndLogin from './template/slogan_and_login.vue'
  import DownloadAndIntroduce from './template/download_and_introduce.vue'
  require('@/assets/css/portal_v3/index_v2.css')

  export default {
    components: {
      SloganaAndLogin,
      DownloadAndIntroduce
    },
    name: 'androidIndex',
    mounted(){
    },
    computed: {
    },
    methods:{
    },
    data() {
      return {
        repsstr: '123'
      }
    }
  }
</script>
