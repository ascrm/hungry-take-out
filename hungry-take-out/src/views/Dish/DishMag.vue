<script setup>
import { ref } from 'vue'
import { dishPageQueryService } from '@/api/dish'

//分页查询响应数据
const bookList = ref([])
//总记录数
const total = ref(null)
//加载状态
const loading = ref(false)

//表单数据
const formData = ref({
  bookName: '',
  type: '',
  author: '',
  language: ''
})

//分页查询参数
const pageParams = ref({
  pageNum: 1,
  pageSize: 5
})

const getBookList = async () => {
  const res = await dishPageQueryService(pageParams.value)
  bookList.value = res.data.data.records
  total.value = res.data.data.total

  loading.value = false
}

getBookList()
</script>

<template>
  <page-container title="图书管理">
    <template #button>
      <el-button type="primary" plain="true">添加图书</el-button>
    </template>

    <template #main>
      <div class="search-form">
        <el-form inline :model="formData">
          <el-form-item label="名称：">
            <el-input v-model="formData.bookName"></el-input>
          </el-form-item>
          <el-form-item label="类型：">
            <el-select v-model="formData.type">
              <el-option label="文学类" value="文学类"></el-option>
              <el-option label="数学类" value="数学类"></el-option>
              <el-option label="计算机类" value="计算机类"></el-option>
              <el-option label="科技类" value="科技类"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="作者：">
            <el-input v-model="formData.author"></el-input>
          </el-form-item>
          <el-form-item label="语言：">
            <el-select v-model="formData.language">
              <el-option label="中文" value="中文"></el-option>
              <el-option label="英语" value="英语"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary">搜索</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary">重置</el-button>
          </el-form-item>
        </el-form>
      </div>

      <div>
        <el-table :data="bookList" v-loading="loading">
          <el-table-column label="编号" prop="id"></el-table-column>
          <el-table-column label="名称" prop="bookName"></el-table-column>
          <el-table-column label="类型" prop="type"></el-table-column>
          <el-table-column label="作者" prop="author"></el-table-column>
          <el-table-column label="库存" prop="stock"></el-table-column>
          <el-table-column label="语言" prop="language"></el-table-column>
          <el-table-column label="操作">
            <el-button type="primary">修改</el-button>
            <el-button type="primary">删除</el-button>
          </el-table-column>
        </el-table>
      </div>
    </template>
  </page-container>
</template>

<style lang="scss" scoped>
.search-form {
  .el-input {
    width: 180px;
  }

  .el-select {
    width: 180px;
  }
}
</style>
