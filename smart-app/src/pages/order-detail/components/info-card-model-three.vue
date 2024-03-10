<template>
	<view class="view">
		<view class="view-title">
			<view class="title-left-bg">
				
			</view>
			<view class="title-text">
				凭证/磅单【装货】
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				时间：
			</view>
			<view class="item-right">
				2023-08-08 12:24:05
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				位置：
			</view>
			<view class="item-right">
				河南省许昌市襄城县书岗线
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				经度：
			</view>
			<view class="item-right">
				33.45767
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				纬度：
			</view>
			<view class="item-right">
				113.45585
			</view>
		</view>
		<view class="image-box">
			<image src="/static/logo.png" mode=""></image>
			<image src="/static/logo.png" mode=""></image>
		</view>
	</view>
</template>

<script setup>
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
		margin-bottom: 24rpx;
	}
	
	.view-title{
		height: 84rpx;
		display: flex;
		align-items: center;
		margin-bottom: 16rpx;
		.title-left-bg{
			height: 28rpx;
			width: 6rpx;
			background: #FED453;
			border-radius: 4rpx;
			margin-right: 14rpx;
		}
		.title-text{
			font-size: 32rpx;
			color: #323333;
			font-weight: bold;
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
			width: 90rpx;
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
	
	.image-box{
		display: flex;
		justify-content: space-between;
		image{
			width: 308rpx;
			height: 172rpx;
			border-radius: 8rpx;
		}
		padding-top: 20rpx;
		border-top: 1rpx solid #ededed;
	}
</style>