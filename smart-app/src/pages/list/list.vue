<template>
	<view>
		<uni-nav-bar :border="false" fixed>
			<view class="input">
				<icon type="search" size="16"/>
				<input placeholder="请输入搜索关键字" placeholder-class="placeolder-input"/>
			</view>
			<template #right>
				<uni-icons type="list" size="30"></uni-icons>
			</template>
		</uni-nav-bar>
		<Tabs v-model:value="active" :list="tabsList"/>
		<ListModelOne v-if="active==0"></ListModelOne>
		<ListModelTwo v-if="active==1"/>
		<ListModelThree v-if="active==2"/>
		<ListModelFour v-if="active==3"/>
	</view>
</template>

<script setup>
import { ref } from 'vue'
import Tabs from './components/tabs.vue'
import ListModelOne from './components/list-model-one.vue'
import ListModelTwo from './components/list-model-two.vue'
import ListModelThree from './components/list-model-three.vue'
import ListModelFour from './components/list-model-four.vue'

const active = ref(0);

const tabsList = [{
	title:'线索列表',
	value:0
},{
	title:'我的订单',
	value:1
},{
	title:'我的运单',
	value:2
},{
	title:'付款单',
	value:3
}]

</script>

<style lang="scss" scoped>
	page{
		background: #f5f6f8;
	}
	.input{
		width: 526rpx;
		height: 72rpx;
		background: #f7f8f9;
		border-radius: 4px;
		margin: 8rpx 0;
		display: flex;
		align-items: center;
		icon{
			margin-left: 20rpx;
		}
		input{
			margin-left: 8rpx;
			font-size: 28rpx;
		}
		.placeolder-input{
			color: #ccc;
		}
	}
	
</style>
