<template>
	<view class="font-item-box">
		<text :class="modelValue==0?'active':''" @click="modelValue=0">标准</text>
		<view></view>
		<text :class="modelValue==1?'active':''" @click="modelValue=1">大号</text>
		<view></view>
		<text :class="modelValue==2?'active':''" @click="modelValue=2">小号</text>
	</view>
</template>

<script setup>
import { watch } from 'vue';
const emits = defineEmits(['update:modelValue'])
const props = defineProps({
	modelValue:{
		type:Number,
		default:0
	}
})

watch(()=>props.modelValue,(newValue,oldValue)=>{
	emits('update:modelValue',newValue)
})
</script>

<style lang="scss" scoped>
	.font-item-box {
		width: 100%;
		display: flex;
		justify-content: flex-end;
		align-items: center;

		text {
			font-size: 30rpx;
			color: #cccccc;

			&.active {
				color: #1A9AFF;
			}
		}

		view {
			width: 4rpx;
			height: 16rpx;
			background-color: #e6e6e6;
			margin: 0 42rpx;
		}
	}
</style>
