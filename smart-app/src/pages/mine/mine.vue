<template>
  <view class="container">
    <!-- 用户 -->
    <MineUserBlue v-if="blueUserFlag" />
    <MineUserWhite v-if="!blueUserFlag" />

    <!---功能菜单--->
    <MineMenu @change-style="onChangeStyle" />

    <!---退出--->
    <view class="logout-btn" @click="logout">
      <view class="label">退出登录</view>
    </view>
  </view>
</template>
<script setup>
  import MineMenu from './components/mine-menu.vue';
  import MineUserBlue from './components/mine-user-blue.vue';
  import MineUserWhite from './components/mine-user-white.vue';
  import { ref } from 'vue';
  const logout = () => {};

  const blueUserFlag = ref(true);
  function onChangeStyle() {
    blueUserFlag.value = !blueUserFlag.value;
  }
</script>
<style lang="scss" scoped>
  .container {
    width: 100%;
    background: #f4f4f4;
    display: flex;
    flex-direction: column;
  }

  .logout-btn {
    margin: 24px 27px 50px;
    height: 44px;
    opacity: 0.5;
    background: #ffffff;
    border-radius: 22px;
    box-shadow: 0px 3px 4px 0px rgba(24, 144, 255, 0.06);
    font-size: 15px;
    line-height: 44px;
    font-weight: 700;
    text-align: center;
    color: #353535;
  }
</style>
