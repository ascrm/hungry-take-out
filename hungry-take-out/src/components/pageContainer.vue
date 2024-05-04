<script setup>
defineProps({
  title: {
    require: true,
    type: String
  }
})
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>{{ title }}</span>

        <slot name="button"></slot>
      </div>
    </template>

    <slot></slot>
  </el-card>
</template>

<style lang="scss" scoped>
.el-card {
  min-height: 100%; //设置元素最小高度，超出高度会有滚动条
  box-sizing: border-box;

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
}
</style>
