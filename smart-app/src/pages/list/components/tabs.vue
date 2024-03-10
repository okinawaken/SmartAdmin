<template>
	<view class="tabs">
		<view class="item" @click="value = item.value" v-for="(item,index) in list" :key="index">
			{{item.title}}
			<view class="tabs-slide" v-if="value === item.value">
			</view>
		</view>
	</view>
</template>

<script setup>
import { ref, watch } from 'vue';
const emits = defineEmits(['update:value'])
const props = defineProps({
	value:{
		type:Number,
		default:0
	},
	list:{
		type:Array,
		default:[]
	}
})

watch(()=>props.value,(newValue,oldValue)=>{
	emits('update:value',newValue)
})
</script>

<style lang="scss" scoped>
.tabs{
		box-sizing: border-box;
		height: 100rpx;
		display: flex;
		justify-content: space-between;
		width: 100%;
		padding: 0 30rpx;
		align-items: center;
		position: relative;
		background-color: #fff;
		.item{
			position: relative;
			height: 100%;
			line-height: 100rpx;
		}
		.tabs-slide{
			position: absolute;
			transition: 0.3s;
			width: 50%;
			background: #1a9aff;
			border-radius: 4rpx;
			height: 8rpx;
			bottom: 0;
			left: 50%;
			transform: translate(-50%);
		}
	}
</style>
