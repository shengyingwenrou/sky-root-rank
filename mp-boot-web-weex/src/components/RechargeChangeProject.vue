<template>
    <scroller>

        <wdg-minibar title="更换上网地点" @wdgMinibarLeftClicked="toBack()"></wdg-minibar>

        <connect-failed  ref="connectFailed"  @onReLoad="reLoad" ></connect-failed>

        <div  class="position-content" v-if="changeProjects!==undefined && changeProjects!==null && changeProjects.length > 0 ">
            <div v-for="(item,index) in changeProjects">
            <wdg-cell
                    :has-arrow="true"
                    :has-top-border="false"
                    :has-bottom-border="true"
                    @wdgCellClicked="cellClicked(index)">
                <div slot="title">
                    <text id="title_time" class="title_time">{{item.remainDay}}</text>
                    <text id="title_name" class="title_name">{{item.tenantName}}</text>
                </div>
            </wdg-cell>
            </div>
        </div>

        <div v-else >
            <EmptyData></EmptyData>
        </div>
    </scroller>
</template>

<script>
  import WdgMinibar from './widget/WdgMinibar';
  import EmptyData from './widget/EmptyData.vue';
  import ConnectFailed from './widget/ConnectFailed';
  import WdgCell from './widget/WdgCell';
  import Dao from '../util/Dao';
  import {getImagePath} from "../config/Config";
  import Native from '../util/Native';
  import event from '../config/event';

  export default {
    components: {WdgMinibar, WdgCell, ConnectFailed,EmptyData},
    data() {
      return {
        changeProjects: [],
        quanIcon: '',
      }
    },
    created() {
      this.quanIcon = getImagePath('quan', '.png');
      this.init();
    },
    mounted() {
      this.loadAccountRemainTimeList();
    },
    methods: {

        reLoad(){
            /** 子组件网络状态展示取消 **/
            this.$refs.connectFailed.setIsShowConnectFailed(false);
            /** 页面重新reLoad **/
            Native.reload();
        },

      loadAccountRemainTimeList() {
        Dao.getAccountRemainTimeList(this.params)
          .then((res) => {
            if (res && res.result) {
              this.changeProjects = res.data;
            }else{
                // 网络连接失败
                if (res.code === -1) {
                    // 通知联网失败
                    event.$emit("informConnectFailed",true);
                }
            }
          })
      },
      cellClicked(index) {
        let project = this.changeProjects[index];
        this.jumpWithParams('RechargeRecord', {
          tenantId: project.tenantId
        });
      }
    }
  };
</script>

<style scoped>


    .position-content{
        z-index: 1;
    }
    .title_time {
        font-size: 35px;
    }

    .title_name {
        font-size: 25px;
        margin-top: 12px;
        color: #9e9e9e;
    }

    .empty-list {
        width: 750px;
        height: 720px;
    }

    .empty-list-v {
        width: 750px;
        height: 134px;
        margin-top: 280px;
    }

    .empty-list-img {
        width: 165px;
        height: 112px;
        margin-left: 292px;
    }

    .empty-text {
        color: #9e9e9e;
        text-align: center;
        font-size: 30px;
    }
</style>