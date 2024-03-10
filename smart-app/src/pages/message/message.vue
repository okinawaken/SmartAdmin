<template>
  <view>
    <view class="message" v-for="(item, index) in 10">
      <view class="message-header">
        <view class="header-left">
          <image src="/src/static/images/message/message.png" mode=""></image>
          <view> 系统通知 </view>
        </view>
        <view class="header-time"> 2023-08-20 20:48 </view>
      </view>
      <view class="header-content">
        <view class="content-title"> 报销-付款已完成 </view>
        <view class="content"> 您的费用保修单已完成，付款金额300.00元，请注意查收。 </view>
      </view>
    </view>
  </view>
</template>

<script>
  export default {
    data() {
      return {};
    },
    methods: {},
  };
</script>

<style lang="scss" scoped>
  .message {
    width: 700rpx;
    background: #ffffff;
    border-radius: 12rpx;
    box-shadow: 0px 3px 4px 0px rgba(24, 144, 255, 0.06);
    margin: 24rpx auto 0;
    box-sizing: border-box;
    padding: 30rpx 30rpx 24rpx;
    .message-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 24rpx;
      .header-left {
        align-items: center;
        display: flex;
        image {
          width: 48rpx;
          height: 48rpx;
          margin-right: 14rpx;
        }
        font-size: 34rpx;
        color: #000000;
      }
      .header-time {
        font-size: 28rpx;
        font-weight: 400;
        color: #cccccc;
      }
    }
    .header-content {
      box-sizing: border-box;
      padding: 24rpx;
      background-color: #f7f8f9;
      width: 100%;
      border-radius: 8rpx;
      .content-title {
        color: #323333;
        font-size: 34rpx;
        font-weight: bold;
        margin-bottom: 8rpx;
      }
      .content {
        font-size: 28rpx;
        color: #777777;
      }
    }
  }

  page {
    background-color: #f5f5f5;
  }
</style>
