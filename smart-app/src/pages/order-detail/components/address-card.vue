<template>
  <view class="view">
    <view class="line"> </view>
    <view class="view-item">
      <view class="itme-top">
        <view class="itme-top-icon-text" type="default"> 提 </view>
        <view class="item-top-text"> 湖北省武汉市东西湖区东湖区（东湖区） </view>
      </view>
      <view class="item-bottom"> 河南省洛阳市洛龙区119号 </view>
    </view>
    <view class="view-item">
      <view class="itme-top">
        <view class="itme-top-icon-text" type="warning"> 装 </view>
        <view class="item-top-text"> 广东省东莞 </view>
      </view>
      <view class="item-bottom"> 河南省洛阳市西工区110</view>
    </view>
    <view class="view-item">
      <view class="itme-top">
        <view class="itme-top-icon-text" type="error"> 卸 </view>
        <view class="item-top-text"> 湖北省武汉市东西湖区东湖区（东湖区） </view>
      </view>
      <view class="item-bottom"> 河南省洛阳市洛龙区110号 </view>
    </view>
    <view class="view-item">
      <view class="itme-top">
        <view class="itme-top-icon-text" type="success"> 还 </view>
        <view class="item-top-text"> 湖北省武汉市东西湖区东湖区（东湖区） </view>
      </view>
      <view class="item-bottom"> 河南省洛阳市洛龙区119号 </view>
    </view>
  </view>
</template>

<script setup></script>

<style lang="scss" scoped>
  .view {
    box-sizing: border-box;
    width: 94%;
    border-radius: 16rpx;
    background-color: #fff;
    padding: 30rpx;
    margin: 0 auto 20rpx;
    position: relative;
    .line {
      position: absolute;
      height: 300rpx;
      border-left: 1rpx dashed #444;
      z-index: 0;
      top: 40rpx;
      left: 50rpx;
    }
    .view-item {
      &:last-child {
        margin-bottom: 0;
      }
      position: relative;
      z-index: 1;
      margin-bottom: 16rpx;
      .item-bottom {
        font-size: 28rpx;
        color: #777777;
        margin-left: 58rpx;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
      .itme-top {
        display: flex;
        align-items: center;
      }
      .itme-top-icon-text {
        width: 40rpx;
        height: 40rpx;
        border-radius: 12rpx;
        text-align: center;
        line-height: 40rpx;
        color: #fff;
        font-size: 26rpx;
        margin-right: 18rpx;
        &[type='default'] {
          background: #1a9aff;
        }
        &[type='warning'] {
          background: #f3c35b;
        }
        &[type='error'] {
          background: #ff6c00;
        }
        &[type='success'] {
          background: #6ec98a;
        }
      }
      .item-top-text {
        font-size: 32rpx;
        color: #444;
        font-weight: bold;
        padding-bottom: 5rpx;
        flex: 1;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
    }
  }
</style>
