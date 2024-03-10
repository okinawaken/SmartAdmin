<template>
  <view class="list-view">
    <view class="item">
      <view class="item-top">
        <view class="item-top-info">
          <view class="name"> 彭嘉轩 </view>
          <view class="mobile"> 13011112338 </view>
        </view>
        <view class="item-top-phone"> <image src="/static/images/list/phone.png" mode=""></image> 联系Ta </view>
      </view>
      <view class="item-title"> 最后跟进内容：上午跟客户谈了下 </view>
      <view class="item-bottom"> 2022-08-20 19:15 最后活跃 </view>
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
  .list-view {
    margin: 20rpx 24rpx;
  }
  .item {
    box-sizing: border-box;
    width: 700rpx;
    height: 240rpx;
    background: #ffffff;
    border-radius: 12rpx;
    box-shadow: 0px 3px 4px 0px rgba(24, 144, 255, 0.06);
    padding: 24rpx 30rpx;

    .item-top {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20rpx;
      .item-top-info {
        display: flex;
        align-items: center;
        .name {
          font-size: 34rpx;
          font-weight: 500;
          color: #444444;
          margin-right: 10rpx;
        }
        .mobile {
          font-size: 32rpx;
          font-weight: bold;
          text-align: left;
          color: #444444;
        }
      }
      .item-top-phone {
        color: #1a9aff;
        font-size: 28rpx;
        display: flex;
        align-items: center;
        image {
          width: 40rpx;
          height: 40rpx;
          margin-left: 4rpx;
        }
      }
    }

    .item-title {
      font-size: 28rpx;
      color: #777777;
      padding-bottom: 20rpx;
      border-bottom: 1rpx solid #ededed;
      margin-bottom: 24rpx;
    }

    .item-bottom {
      font-size: 28rpx;
      color: #777777;
    }
  }
</style>
