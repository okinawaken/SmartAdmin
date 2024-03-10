<template>
  <view class="item" v-for="(item, index) in 2" :key="index">
    <view class="header">
      <view class="header-left">
        <image class="header-left-image" src="/static/images/pure-list/maintain.png" mode=""></image>
        <view class="header-left-title"> 报修信息 </view>
      </view>
      <view class="header-right-id"> 保修单还：12876 </view>
    </view>
    <view class="footer">
      <view class="footer-item">
        <view class="label"> 设备： </view>
        <view class="info"> 灭火控制柜（plc） </view>
      </view>
      <view class="footer-item">
        <view class="label"> 描述： </view>
        <view class="info"> 总设备压力过大，导致气压不稳 </view>
      </view>
      <view class="footer-item">
        <view class="label"> 报修人： </view>
        <view class="info"> 闫jj </view>
      </view>
      <view class="footer-item">
        <view class="label"> 受理人： </view>
        <view class="info"> 王jj </view>
      </view>
    </view>
  </view>
</template>

<script setup>
  defineProps({
    list: {
      type: Array,
      default: [],
    },
  });
</script>

<style lang="scss" scoped>
  .item {
    position: relative;
    width: 700rpx;
    margin: 20rpx auto 0;
    background: #ffffff;
    border-radius: 12rpx;
    box-shadow: 0px 3px 4px 0px rgba(24, 144, 255, 0.06);
    padding: 26rpx 40rpx 20rpx;
    box-sizing: border-box;
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding-bottom: 20rpx;
      border-bottom: 1rpx solid #ededed;
      margin-bottom: 16rpx;

      .header-left {
        display: flex;
        align-items: center;
        .header-left-image {
          width: 48rpx;
          height: 48rpx;
          margin-right: 16rpx;
        }
        .header-left-title {
          font-size: 34rpx;
          font-weight: bold;
          color: #444444;
        }
      }
      .header-right-id {
        font-size: 28rpx;
        color: #777777;
      }
    }

    .footer {
      .footer-item {
        display: flex;
        align-items: center;
        margin-bottom: 16rpx;
        .label {
          width: 112rpx;
          font-size: 28rpx;
          color: #777777;
        }
        .info {
          font-size: 28rpx;
          color: #323333;
          font-weight: bold;
        }
      }
    }
  }
</style>
