<template>
	<view>
		<ScrollTabs v-model="active" :tabsList="tabsList"></ScrollTabs>
		<DetailModelBaseInfo v-if="active==0"/>
		<DetailModelPath v-if="active==1"/>
		<DetailModelDuePay v-if="active==2"/>
		<DetailModelReceivable v-if="active==3"/>
		<DetailModelFinal v-if="active==4"/>
		<DetailModelVoucher v-if="active==5"/>
	</view>
</template>

<script setup>
	import { ref } from 'vue';
	import ScrollTabs from './components/scroll-tabs.vue'
	import DetailModelBaseInfo from './components/detail-model-base-info.vue'
	import DetailModelPath from './components/detail-model-path.vue'
	import DetailModelDuePay from './components/detail-model-due-pay.vue'
	import DetailModelReceivable from './components/detail-model-receivable.vue'
	import DetailModelFinal from './components/detail-model-final.vue'
	import DetailModelVoucher from './components/detail-model-voucher.vue'
	
	const tabsList = [{
		lable:'基本路线',
		value:0
	},{
		lable:'路线/货物',
		value:1
	},{
		lable:'应付费用信息',
		value:2
	},{
		lable:'应收费用信息',
		value:3
	},{
		lable:'结算信息',
		value:4
	},{
		lable:'凭证/磅单',
		value:5
	}]
	
	const active = ref(0)
</script>

<style lang="scss" scoped>

page{
	background: #f4f4f4;
}

</style>
