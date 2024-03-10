<template>
	<view class="view">
		<view class="view-title">
			<view class="title-left-bg">
				
			</view>
			<view class="title-text">
				货物信息1
			</view>
			<view class="sub-title">
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				货物类型：
			</view>
			<view class="item-right">
				其他
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				货物名称：
			</view>
			<view class="item-right">
				其他
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				货物单位：
			</view>
			<view class="item-right">
				吨
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				货物毛重/吨：
			</view>
			<view class="item-right">
				64
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				货物数量：
			</view>
			<view class="item-right">
				2
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				备注：
			</view>
			<view class="item-right">
				这是备注信息～
			</view>
		</view>
	</view>
</template>

<script setup>
	const props = defineProps({
		List:{
			type:Array,
			default:[]
		}
	})
</script>

<style lang="scss" scoped>
	.view{
		width: 94%;
		margin: 0 auto;
		background: #fff;
		border-radius: 16rpx;
		box-sizing: border-box;
		padding: 0 30rpx;
		padding-bottom: 24rpx;
	}
	
	.view-title{
		height: 84rpx;
		display: flex;
		align-items: center;
		margin-bottom: 16rpx;
		.title-left-bg{
			height: 28rpx;
			width: 6rpx;
			background: #1A9AFF;
			border-radius: 4rpx;
			margin-right: 14rpx;
		}
		.title-text{
			font-size: 32rpx;
			color: #323333;
			font-weight: bold;
		}
		.sub-title{
			font-size: 28rpx;
			color: #ccc;
			flex: 1;
			text-align: right;
		}
	}
	
	.view-item{
		display: flex;
		height: 40rpx;
		align-items: center;
		margin-bottom: 16rpx;
		&:last-child{
			margin-bottom: 0;
		}
		.item-left{
			width: 180rpx;
			margin-right: 20rpx;
			font-size: 28rpx;
			color: #777777;
		}
		.item-right{
			font-size: 30rpx;
			color: #323333;
			font-weight: bold;
			font-size: 28rpx;
		}
	}
</style>

