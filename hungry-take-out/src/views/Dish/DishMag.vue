<script setup>
import { ref } from 'vue'
import { Delete, Edit } from '@element-plus/icons-vue'
import { dishPageQueryService, dishDeleteService } from '@/api/dish'
import DishEdit from './components/DishEdit.vue'

//分页查询响应数据
const dishList = ref([])
//总记录数
const total = ref(null)
//加载状态
const loading = ref(false)

//查询参数
const pageParams = ref({
  pageNum: 1,
  pageSize: 10,
  name: '',
  category: '',
  status: ''
})

//获取分页响应数据
const getDishList = async () => {
  const res = await dishPageQueryService(pageParams.value)
  console.log(res.data.data)
  console.log(res.data.data.total)
  dishList.value = res.data.data.data
  total.value = res.data.data.total
  loading.value = false
}
getDishList()

//页大小发生变化
const onSizeChange = (size) => {
  // 只要是每页条数变化了，那么原本正在访问的当前页意义不大了，数据大概率已经不在原来那一页了
  // 重新从第一页渲染即可
  pageParams.value.pageNum = 1
  pageParams.value.pageSize = size
  getDishList()
}

//页码发生变化
const onCurrentChange = (page) => {
  pageParams.value.pageNum = page
  getDishList()
}

// 重置表单
const onReset = () => {
  pageParams.value.pageNum = 1 // 重置页面
  pageParams.value.name = ''
  pageParams.value.category = ''
  pageParams.value.status = ''
  getDishList()
}

//添加和编辑操作
const dishEditRef = ref()

const onAddDish = () => {
  dishEditRef.value.open()
}
const onEditDish = (row) => {
  dishEditRef.value.open(row)
}

// 删除菜品
const onDeleteDish = async (row) => {
  // 提示用户是否要删除
  await ElMessageBox.confirm('此操作将永久删除菜品, 是否继续？', '温馨提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
  await dishDeleteService(row.id)
  ElMessage.success('删除成功')

  getDishList()
}

// 添加或者编辑 成功的回调
const onSuccess = (type) => {
  if (type === 'add') {
    // 如果是添加，最好渲染最后一页
    const lastPage = Math.ceil((total.value + 1) / pageParams.value.pagesize)
    // 更新成最大页码数，再渲染
    pageParams.value.pageNum = lastPage
  }
  pageParams.value.pageNum = 1
  getDishList()
}
</script>

<template>
  <page-container title="菜品列表">
    <template #button>
      <el-button type="primary" plain="true" @click="onAddDish">添加菜品</el-button>
    </template>

    <el-form inline :model="pageParams">
      <el-form-item label="名称：" prop="name">
        <el-input v-model="pageParams.name"></el-input>
      </el-form-item>
      <el-form-item label="类型：" prop="category">
        <el-select v-model="pageParams.category">
          <el-option label="主食类" value="主食类"></el-option>
          <el-option label="特色类" value="特色类"></el-option>
          <el-option label="中餐类" value="中餐类"></el-option>
          <el-option label="素菜类" value="素菜类"></el-option>
          <el-option label="小吃类" value="小吃类"></el-option>
          <el-option label="汤菜类" value="汤菜类"></el-option>
          <el-option label="酒水饮料" value="酒水饮料"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="状态：" prop="status">
        <el-select v-model="pageParams.status">
          <el-option label="起售" value="1"></el-option>
          <el-option label="停售" value="0"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="getDishList">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onReset">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table scrollbar-always-on="true" :data="dishList" v-loading="loading">
      <el-table-column label="编号" prop="id"></el-table-column>
      <el-table-column label="菜名" prop="name"></el-table-column>
      <el-table-column label="类型" prop="category"></el-table-column>
      <el-table-column label="价格" prop="price"></el-table-column>
      <el-table-column label="图片" prop="image">
        <template #default="{ row }">
          <img :src="row.image" alt="图片" style="width: 50px; height: 50px" />
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="status"></el-table-column>
      <el-table-column label="描述" prop="description"></el-table-column>
      <el-table-column label="操作" width="100px">
        <template #default="{ row }">
          <el-button circle type="primary" :icon="Edit" @click="onEditDish(row)"></el-button>
          <el-button circle type="danger" :icon="Delete" @click="onDeleteDish(row)"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination v-model:current-page="pageParams.pageNum" v-model:page-size="pageParams.pageSize"
      :page-sizes="[1, 5, 10, 50]" background layout="jumper, total, sizes, prev, pager, next" :total="total"
      @size-change="onSizeChange" @current-change="onCurrentChange" />
  </page-container>
  <dish-edit ref="dishEditRef" @success="onSuccess"></dish-edit>
</template>

<style lang="scss" scoped>
.el-input {
  width: 200px;
}

.el-select {
  width: 200px;
}
</style>
