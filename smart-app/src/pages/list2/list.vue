<template>
  <y-tabs v-model="active" sticky :offsetTop="0" color="#007aff">
    <y-tab class="y-tab-virtual" v-for="item in tabsList" :key="item.value" :title="item.title"> </y-tab>
  </y-tabs>

  <ExpressList v-if="active === 0" />
  <DiscountList v-if="active === 1" />
  <IotList v-if="active === 2" />
  <ServiceList v-if="active === 3" />
  <CourseList v-if="active === 4" />
</template>

<script setup>
  import CourseList from './components/course-list.vue';
  import ExpressList from './components/express-list.vue';
  import DiscountList from './components/discount-list.vue';
  import ServiceList from './components/service-list.vue';
  import IotList from './components/iot-list.vue';

  import { ref } from 'vue';

  const active = ref(0);

  const tabsList = [
    {
      title: '快递',
      value: 0,
    },
    {
      title: '优惠卷',
      value: 1,
    },
    {
      title: '物联网',
      value: 2,
    },
    {
      title: '售后',
      value: 3,
    },

    {
      title: '课程',
      value: 4,
    },
  ];
</script>

<style lang="scss" scoped>
  page {
    background: #f5f6f8;
  }
</style>
