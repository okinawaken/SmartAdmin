<template>
  <view class="menu-list">
    <uni-list>
      <uni-list-item title="消息通知" link showBadge badgeText="6" badgeType="error">
        <template #header>
          <image class="icon" src="/static/images/mine/mine-message.png" mode=""></image>
        </template>
      </uni-list-item>
      <uni-list-item title="关于我们" link showBadge>
        <template #header>
          <image class="icon" src="/static/images/mine/mine-about-us.png" mode=""></image>
        </template>
      </uni-list-item>
      <uni-list-item title="版本信息" link rightText="V1.0" showBadge>
        <template #header>
          <image class="icon" src="/static/images/mine/mine-version-info.png" mode=""></image>
        </template>
      </uni-list-item>
      <uni-list-item title="意见反馈" link rightText="欢迎吐槽" showBadge>
        <template #header>
          <image class="icon" src="/static/images/mine/mine-feedback.png" mode=""></image>
        </template>
      </uni-list-item>
      <uni-list-item title="隐私条款" link showBadge>
        <template #header>
          <image class="icon" src="/static/images/mine/user-agreement-icon.png" mode=""></image>
        </template>
      </uni-list-item>
      <uni-list-item title="用户协议" link showBadge>
        <template #header>
          <image class="icon" src="/static/images/mine/mine-protocol.png" mode=""></image>
        </template>
      </uni-list-item>
      <uni-list-item title="联系客服" showBadge>
        <template #header>
          <image class="icon" src="/static/images/mine/mine-service.png" mode=""></image>
        </template>
        <template #footer>
          <view style="font-size: 30rpx; color: #1a9aff; line-height: 45rpx"> 400-888-666 </view>
        </template>
      </uni-list-item>
      <uni-list-item title="账号管理" showBadge link>
        <template #header>
          <image class="icon" src="/static/images/mine/mine-account.png" mode=""></image>
        </template>
      </uni-list-item>
    </uni-list>
  </view>
</template>
<script setup></script>
<style scoped lang="scss">
  .menu-list {
    box-sizing: border-box;
    background-color: #fff;
    width: 700rpx;
    margin: 20rpx auto 0;
    border-radius: 6px;
    overflow: hidden;
    box-shadow: 0 3px 4px 0 rgba(24, 144, 255, 0.06);
    padding: 0 30rpx;

    .icon {
      width: 42rpx;
      height: 42rpx;
      margin-right: 20rpx;
    }
  }
</style>
