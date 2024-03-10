<template>
	<view class="view">
		<view class="view-title">
			<view class="title-left-bg">

			</view>
			<view class="title-text">
				运单信息
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				运单号
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				订单号
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				客户订单号
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				箱号
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				铅封号
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				柜型
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				最迟提箱时间
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				货主
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				业务时间
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				结算方式
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				结算类型
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				司机
			</view>
			<view class="item-right">
				TDD202304950
			</view>
		</view>
		<view class="view-item">
			<view class="item-left">
				车辆
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
