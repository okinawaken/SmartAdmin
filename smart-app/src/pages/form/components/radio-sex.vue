<template>
	<view class="sex-box">
		<view class="sex-item" :class="modelValue?'active':''" @click="modelValue=true">
			<uni-icons type="circle" v-if="!modelValue" color="#ccc" size="30"></uni-icons>
			<uni-icons v-else type="checkbox-filled" color="#1A9AFF" size="30"></uni-icons>
			<view>
				男
			</view>
		</view>
		<view class="sex-item" :class="!modelValue?'active':''" @click="modelValue=false">
			<uni-icons type="circle" v-if="modelValue" color="#ccc" size="30"></uni-icons>
			<uni-icons v-else type="checkbox-filled" color="#1A9AFF" size="30"></uni-icons>
			<view>
				女
			</view>
		</view>
	</view>
</template>

<script setup>
import { watch } from 'vue';
const emits = defineEmits(['update:modelValue'])
const props = defineProps({
	modelValue:{
		type:Boolean,
		default:true
	}
})

watch(()=>props.modelValue,(newValue,oldValue)=>{
	emits('update:modelValue',newValue)
})
</script>

<style lang="scss" scoped>
.sex-box {
	width: 100%;
	display: flex;
	justify-content: flex-end;

	.sex-item {
		display: flex;
		width: 112rpx;
		height: 64rpx;
		background-color: #f3f3f3;
		border: 1rpx solid #ededed;
		align-items: center;
		margin-left: 40rpx;
		justify-content: center;
		border-radius: 8rpx;
		font-size: 32rpx;
	}

	.active {
		background: #f1f9ff;
		border: 1rpx solid #1a9aff;
		color: #1A9AFF;
	}
}
</style>
