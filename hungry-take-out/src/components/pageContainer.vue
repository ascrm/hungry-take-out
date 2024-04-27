<script setup>
defineProps({
  title: {
    require: true,
    type: String
  }
})
</script>

<template>
  <el-card>
    <template #header>
      <div class="header">
        <span>{{ title }}</span>

        <slot name="button"></slot>
      </div>
    </template>

    <slot name="main"></slot>
  </el-card>
</template>

<style lang="scss" scoped>
.el-card {
  height: 100%;
  box-sizing: border-box;

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
}
</style>
