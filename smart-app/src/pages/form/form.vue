<template>
  <view class="">
    <view class="form-card">
      <view class="title"> 常用功能 </view>
      <view class="content">
        <uni-forms :label-width="100" :modelValue="formData" label-position="left">
          <uni-forms-item class="uni-forms-item" label="姓名" name="name">
            <input class="input" type="text" v-model="formData.name" placeholder="请输入姓名" />
          </uni-forms-item>
          <uni-forms-item class="uni-forms-item" label="手机号码" name="name">
            <input class="input" type="text" v-model="formData.name" placeholder="请输入手机号码" />
          </uni-forms-item>
          <uni-forms-item class="uni-forms-item" label="邮箱地址" name="name">
            <input class="input" type="text" v-model="formData.name" placeholder="请输入邮箱地址" />
          </uni-forms-item>
          <uni-forms-item class="uni-forms-item" label="性别" name="name">
            <RadioSex v-model="formData.sex"></RadioSex>
          </uni-forms-item>
          <uni-forms-item class="uni-forms-item" label="出生日期" name="name">
            <view class="item-box">
              <picker ref="datePickerRef" mode="date" @change="bindDateChange">
                <input ref="dateInputRef" class="input" type="text" v-model="date" placeholder="点击选择时间" />
              </picker>
            </view>
          </uni-forms-item>
          <uni-forms-item class="uni-forms-item" label="所在地" name="name">
            <input class="input" disabled type="text" v-model="formData.name" placeholder="点击选择所在地" />
          </uni-forms-item>
        </uni-forms>
      </view>
    </view>
    <view class="form-card">
      <view class="title"> 推送用户 </view>
      <view class="content">
        <uni-forms :label-width="100" :modelValue="formData" label-position="left">
          <uni-forms-item class="uni-forms-item" label="选择用户" name="name">
            <view class="item-box" @click="openSelectPeople">
              <image class="user-select-image" src="/src/static/images/form/add.png" mode=""></image>
            </view>
          </uni-forms-item>
        </uni-forms>
      </view>
    </view>
    <view class="form-card">
      <view class="title"> 兴趣爱好 </view>
      <Interest v-model="formData.interest" :list="interestList"></Interest>
    </view>
    <view class="form-card">
      <view class="title"> 推送用户 </view>
      <view class="content">
        <uni-forms :label-width="100" :modelValue="formData" label-position="left">
          <uni-forms-item class="uni-forms-item" label="亮度调整" name="name">
            <view class="item-box">
              <slider style="width: 100%" value="50" activeColor="#2291F9" backgroundColor="#f5f6f8" block-color="#2291F9" block-size="20" />
            </view>
          </uni-forms-item>
          <uni-forms-item class="uni-forms-item" label="字体大小" name="name">
            <FontSizeSelece v-model="formData.fontType"></FontSizeSelece>
          </uni-forms-item>
        </uni-forms>
      </view>
    </view>
    <view class="form-card">
      <view class="title"> 自我介绍 </view>
      <view class="content">
        <uni-forms :modelValue="formData" label-position="left">
          <view class="textarea">
            <textarea auto-height style="font-size: 30rpx" placeholder="请输入自我介绍" placeholder-class="textarea-placeholder" />
          </view>
          <view class="example-body">
            <uni-file-picker limit="9" title="上传图片">
              <image style="width: 100%; height: 100%" src="/static/images/form/add-image.png" mode=""></image>
            </uni-file-picker>
          </view>
        </uni-forms>
      </view>
    </view>
  </view>
</template>

<script setup>
  import RadioSex from './components/radio-sex.vue';
  import Interest from './components/interest.vue';
  import FontSizeSelece from './components/font-size-select.vue';
  import { reactive, ref } from 'vue';

  const interestList = ['唱歌', '跳舞', 'RAP', '篮球', '音乐', '唱歌', '跳舞', 'RAP', '篮球'];
  const formData = reactive({
    interest: 4,
    fontType: 0,
  });
  const hobby = ref('');
  const date = ref();
  const bindDateChange = (e) => {
    date.value = e.detail.value;
  };

  const openSelectPeople = () => {
    uni.navigateTo({
      url: '/pages/select-people/select-people',
    });
  };
</script>

<style lang="scss" scoped>
  page {
    background: #f5f6f8;
  }

  ::v-deep .uni-forms-item__content {
    display: flex;
    align-items: center;
  }

  ::v-deep .uni-forms-item__label {
    font-size: 32rpx;
    color: #000000;
    padding-top: 28rpx;
  }

  ::v-deep .uni-forms-item {
    margin-bottom: 0 !important;
  }

  ::v-deep .uni-slider-thumb {
    background: #fff !important;
    border: 10rpx solid #1a9aff;
    box-sizing: border-box;
  }

  .uni-forms-item {
    height: 100rpx;
    border-bottom: 1rpx solid #ededed;

    &:last-child {
      border: none;
    }
  }

  .form-card {
    box-sizing: border-box;
    width: 700rpx;
    margin: 20rpx auto 0;
    background: #fff;
    border-radius: 16rpx;

    .title {
      width: 100%;
      height: 84rpx;
      background-image: url('/static/images/list/form-list.png');
      background-size: 100% 84rpx;
      line-height: 84rpx;
      text-indent: 30rpx;
      font-size: 32rpx;
      color: #323333;
      font-weight: bold;
    }

    .content {
      padding: 0 30rpx;
    }

    .input {
      font-size: 30rpx;
      text-align: right;
      width: 100%;
    }
  }

  .item-box {
    width: 100%;
    display: flex;
    justify-content: flex-end;
  }

  .user-select-image {
    width: 40rpx;
    height: 40rpx;
  }

  .textarea {
    background: #fcfcfc;
    border: 0.5px solid #ededed;
    border-radius: 4px;
    width: 100%;
    height: 320rpx;
    margin-top: 24rpx;
    padding: 24rpx 30rpx;
    box-sizing: border-box;
    .textarea-placeholder {
      color: #cccccc;
      font-size: 30rpx;
    }
  }

  .example-body {
    padding-bottom: 24rpx;
  }
</style>
