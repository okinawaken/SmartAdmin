<template>
	<view class="view">
		<view class="view-title">
			<view class="title-left-bg">
				
			</view>
			<view class="title-text">
				费用明细
			</view>
			<view class="sub-title">
				已付费用/预付费用/费用
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				运费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				过磅费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				压夜费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				超重费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				提箱费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				报关费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				其他费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				保税绕园费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				放空费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				修箱费
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				服务费
			</view>
			<view class="item-right">
				TDD202304950
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
	}
	
	.view-title{
		height: 84rpx;
		display: flex;
		align-items: center;
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
		height: 100rpx;
		justify-content: space-between;
		align-items: center;
		color: 323333;
		border-bottom: 1rpx solid #ededed;
		&:last-child{
			border: none;
		}
		.item-left{
			font-size: 32rpx;
		}
		.item-right{
			font-size: 30rpx;
		}
	}
</style>
