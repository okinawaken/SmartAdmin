<template>
  <div>
    <span>{{ dictValueName }}</span>
  </div>
</template>

<script setup>
  import { computed } from 'vue';

  const props = defineProps({
    // 数据
    options: {
      type: Array,
      default: null,
    },
    // 当前的值
    value: [Number, String, Array],
  });
  const dictValueName = computed(() => {
    if (props.value === null || typeof props.value === 'undefined' || props.value === '') return '';
    const valueCodeList = Array.isArray(props.value) ? props.value.map((item) => item.valueCode) : props.value.split(',');
    const valueNameList = props.options.filter((item) => valueCodeList.includes(item.valueCode)).map((item) => item.valueName);

    return valueNameList.join('，');
  });
</script>
