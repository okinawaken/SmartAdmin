<template>
	<view class="item" v-for="(item,index) in 4" :key="index" :class="type[index]">
		<view class="header">
			Plc控制柜
		</view>
		<view class="footer">
			<view class="footer-left">
				A区气路总阀：
			</view>
			<view class="footer-state" :class="type[index]">
				关闭
			</view>
		</view>
	</view>
</template>

<script setup>
	defineProps({
		list: {
			type: Array,
			default: []
		}
	})
	
	const type = ['info','primary','warning','']
		
</script>

<style lang="scss" scoped>
	.item {
		width: 700rpx;
		margin: 20rpx auto 0;
		background: #ffffff;
		border-radius: 12rpx;
		box-shadow: 0px 3px 4px 0px rgba(24, 144, 255, 0.06);
		padding: 26rpx 40rpx 20rpx;
		box-sizing: border-box;
		border-left: 8rpx solid #4F4D4D;
		&.primary{
			border-color: #007FF2;
		}
		&.warning{
			border-color: #FF6C00;
		}
		&.info{
			border-color: #CCCCCC;
		}

		.header {
			display: flex;
			justify-content: space-between;
			padding-bottom: 20rpx;
			border-bottom: 1rpx solid #ededed;
			margin-bottom: 19rpx;
			font-size: 34rpx;
			color: #444;
		}

		.footer {
			display: flex;
			justify-content: space-between;
			font-size: 28rpx;
			.footer-left{
				color: #777777;
			}
			.footer-state{
				&.primary{
					color: #007FF2;
				}
				&.warning{
					color: #FF6C00;
				}
				&.info{
					color: #CCCCCC;
				}
				color: #4F4D4D;
			}
		}
	}
</style>