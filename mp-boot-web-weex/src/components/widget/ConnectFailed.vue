<!-- 网络连接失败处理 -->
<template>
    <div v-if="isShowConnectFailed" >
        <div class="content">
           <text class="content-text">{{connectFailedDesc}}</text>
           <text class="content-retry" @click="reTry()">{{reTryDesc}}</text>
        </div>
    </div>
</template>

<script>
    import Native from "../../util/Native";
    import event from '../../config/event';

    export default {
        data() {
            return {
                isShowConnectFailed:false,//控制展示网络状态不良提示
                connectFailedDesc: '网络不给力，请检查网络后重试',
                reTryDesc: '点击重试'
            }
        },
        mounted(){

        },

        created:function () {
            /** 处理网络中断>重试机制 **/
            event.$on('informConnectFailed',function(isConnectFailed){
                  this.isShowConnectFailed = isConnectFailed;
            }.bind(this))
        },

        methods:{
            /** 网络回复后重新加载父组件内容以获取每个组件内容 **/
            reTry(){
                var that=this;
                that.$emit("onReLoad");
            },
            setIsShowConnectFailed(isShowConnectFailed){
                this.isShowConnectFailed = isShowConnectFailed;
            }
        }
    }
</script>

<style scoped>

    .content{
        width:750px;
        height:80px;

        background-color: #ff6671;
        flex-direction: row;
    }

    .content-text{
        color:#ffffff;
        height:80px;
        line-height: 80px;
        padding-left: 20px;
        text-align: left;
        font-size: 30px;
        width:500px;
    }

    .content-retry{
        width:240px;
        font-size: 30px;
        padding-right: 10px;
        color:#ffffff;
        height:80px;
        line-height: 80px;
        text-align: right;
    }

</style>