<template>
  <view>
    <y-tabs v-model="active" sticky :offsetTop="0" color="#007aff">
      <y-tab class="y-tab-virtual" v-for="item in tabsList" :key="item.value" :title="item.title"> </y-tab>
    </y-tabs>

    <ListUI1 v-if="active === 0" />
    <ListUI2 v-if="active === 1" />
    <ListUI3 v-if="active === 2" />
    <ListUI4 v-if="active === 3" />
  </view>
</template>

<script setup>
  import { ref } from 'vue';
  import ListUI1 from './components/list-ui1.vue';
  import ListUI2 from './components/list-ui2.vue';
  import ListUI3 from './components/list-ui3.vue';
  import ListUI4 from './components/list-ui4.vue';

  const active = ref(0);

  const tabsList = [
    {
      title: '线索列表',
      value: 0,
    },
    {
      title: '我的订单',
      value: 1,
    },
    {
      title: '我的运单',
      value: 2,
    },
    {
      title: '付款单',
      value: 3,
    },
  ];
</script>

<style lang="scss" scoped>
  page {
    background: #f5f6f8;
  }
  .input {
    width: 526rpx;
    height: 72rpx;
    background: #f7f8f9;
    border-radius: 4px;
    margin: 8rpx 0;
    display: flex;
    align-items: center;
    icon {
      margin-left: 20rpx;
    }
    input {
      margin-left: 8rpx;
      font-size: 28rpx;
    }
    .placeolder-input {
      color: #ccc;
    }
  }
</style>
