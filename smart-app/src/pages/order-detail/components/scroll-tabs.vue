<template>
  <scroll-view class="scroll-view" scroll-x="true" :show-scrollbar="false">
    <view class="item" :class="active == item.value ? 'active' : ''" v-for="item in tabsList" :key="item.value" @click="change(item.value)">
      {{ item.lable }}
    </view>
  </scroll-view>
</template>

<script setup>
  import { ref, watch } from 'vue';
  const props = defineProps({
    modelValue: {
      type: Number,
      default: 0,
    },
    tabsList: {
      type: Array,
      default: [],
    },
  });

  const active = ref(0);

  watch(
    () => props.modelValue,
    (newValue) => {
      active.value = newValue;
    }
  );

  const emit = defineEmits(['update:modelValue']);

  const change = (value) => {
    active.value = value;
    emit('update:modelValue', value);
  };
</script>

<style lang="scss" scoped>
  .item {
    padding: 0 24rpx;
    height: 72rpx;
    font-size: 30rpx;
    color: #777;
    background: #fff;
    display: inline-block;
    border-radius: 8rpx;
    line-height: 72rpx;
    margin-left: 24rpx;
    margin: 24rpx 0 24rpx 24rpx;
  }
  .active {
    color: #ffffff;
    background: #1a9aff;
  }

  .scroll-view ::v-deep ::-webkit-scrollbar {
    height: 0 !important;
    width: 0 !important;
    background: transparent;
  }

  .smart-form-submit {
    border-top: #eee 1px solid;
    height: 80px;
    display: flex;
    flex-direction: row;
    align-items: center;
    position: absolute;
    bottom: 0;
    background-color: white;
    width: 100%;

    .smart-form-submit-btn {
      margin: 10px;
      height: 2.5;
      flex: 1;
    }
  }
</style>
