<template>
  <view class="list-view">
    <view class="item">
      <view class="item-header">
        <view class="item-top">
          <view class="item-top-left"> 2023-08-20 19:15 </view>
          <view class="item-top-right">
            <view class="tag" type="warring"> 未付款 </view>
          </view>
        </view>
        <view class="line-border-bottom"> </view>
        <view class="item-title"> 订单标题 </view>
        <view class="item-sub-title"> 订单金额：¥5852.00 </view>
        <view class="item-sub-title"> 客户：1024创新实验室 </view>
      </view>

      <view class="order-end-time"> 剩余 29:35:07 关闭订单 </view>
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
    .item-header {
      padding: 0 30rpx;
    }
    box-sizing: border-box;
    width: 700rpx;
    background: #ffffff;
    border-radius: 12rpx;
    box-shadow: 0px 3px 4px 0px rgba(24, 144, 255, 0.06);
    padding-top: 24rpx;
    overflow: hidden;

    .item-top {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20rpx;
      .item-top-left {
        font-size: 28rpx;
        font-weight: 400;
        color: #777777;
      }
      .item-top-right {
        .tag {
          width: 112rpx;
          height: 40rpx;
          border-radius: 4rpx;
          text-align: center;
          line-height: 40rpx;
          font-size: 24rpx;
          &[type='warring'] {
            color: #ff6c00;
            background: #fff0ed;
          }
          &[type='success'] {
            color: #323333;
            background: #f7f8f9;
          }
        }
      }
    }

    .item-title {
      margin-top: 20rpx;
      margin-bottom: 12rpx;
      font-size: 34rpx;
      font-weight: 700;
      color: #444444;
    }

    .item-sub-title {
      font-size: 28rpx;
      color: #777777;
      margin-bottom: 10rpx;
      &:last-child {
        padding-bottom: 20rpx;
        margin-bottom: 0;
      }
    }

    .order-end-time {
      height: 40rpx;
      background: #ff6c00;
      box-shadow: 0px 3px 4px 0px rgba(24, 144, 255, 0.06);
      color: #fff;
      text-align: center;
      font-size: 24rpx;
      line-height: 40rpx;
    }
  }

  .line-border-bottom {
    border-bottom: 1rpx solid #ededed;
  }
</style>
