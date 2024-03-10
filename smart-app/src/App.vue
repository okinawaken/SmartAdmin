<script>
  import { useUserStore } from '/@/store/modules/system/user';
  export default {
    onLaunch: function () {
      useUserStore().getLoginInfo();
    },
    onShow: function () {
      console.log('App Show');
    },
    onHide: function () {
      console.log('App Hide');
    },
  };
</script>

<style lang="scss">
  /*每个页面公共css */
  @import url('./theme/index.scss');
</style>
