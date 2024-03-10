<template>
	<view class="card-content">
		<view @click="modelValue = index" :class="modelValue == index?'active':''"
			v-for="(item,index) in list" :key="index">
			{{item}}
		</view>
	</view>
</template>

<script setup>
import { watch } from 'vue';
const emits = defineEmits(['update:modelValue'])
const props = defineProps({
	modelValue:{
		type:Number,
		default:0
	},
	list:{
		type:Array,
		default:[]
	}
})

watch(()=>props.modelValue,(newValue,oldValue)=>{
	emits('update:modelValue',newValue)
})
</script>

<style lang="scss" scoped>
	.card-content {
		padding: 0 30rpx 24rpx;
		display: flex;
		flex-wrap: wrap;

		view {
			box-sizing: border-box;
			width: 197rpx;
			height: 72rpx;
			background: #f7f8f9;
			border-radius: 8rpx;
			text-align: center;
			line-height: 72rpx;
			margin-right: 24rpx;
			margin-top: 24rpx;
			font-size: 30rpx;
			color: #323333;
			border: 2rpx solid #f7f8f9;

			&:nth-child(3n) {
				margin-right: 0;
			}
		}

		.active {
			background: #eff8ff;
			border: 2rpx solid #2291f9;
		}
	}
</style>