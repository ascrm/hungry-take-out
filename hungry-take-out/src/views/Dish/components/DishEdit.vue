<script setup>
import { ref } from 'vue'
import { dishEditService, dishAddService, dishQueryById } from '@/api/dish'
import { UploadFileService } from '@/api/common'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'

const visibleDrawer = ref(false)
//默认数据
const defaultForm = {
  id: '',
  name: '',
  category: '',
  price: '',
  image: '',
  status: '',
  description: ''
}
// 初始化数据
const formModel = ref({ ...defaultForm })

const rules = {
  name: [
    { required: true, message: '名称不能为空', trigger: 'blur' },
    { pattern: /^[0-9\u4e00-\u9fa5]+$/, message: '输入有误', trigger: 'blur' }
  ],
  price: [
    { required: true, message: '价格不能为空', trigger: 'blur' },
    { pattern: /^\d+(\.\d+)?$/, message: '输入有误', trigger: 'blur' }
  ]
}

// 图片上传
const imgUrl = ref('')
const onUploadFile = async (uploadFile) => {
  imgUrl.value = URL.createObjectURL(uploadFile.raw) // 预览图片

  console.log('数据类型：')
  console.log(uploadFile)
  console.log(typeof uploadFile)
  console.log(uploadFile.raw)
  console.log(typeof uploadFile.raw)

  const fd = new FormData()
  fd.append('file', uploadFile.raw)
  const res = await UploadFileService(fd)

  formModel.value.image = res.data.data
  ElMessage.success('上传成功')
}

// 提交
const emit = defineEmits(['success'])
const onPublish = async (status) => {
  formModel.value.status = status
  formModel.value.description = formModel.value.description
    .replace(/<p>/g, '')
    .replace(/<\/p>/g, '')
    .replace(/<br>/g, '')

  if (formModel.value.id) {
    // 编辑操作
    await dishEditService(formModel.value)
    ElMessage.success('修改成功')
    visibleDrawer.value = false
    emit('success', 'edit')
  } else {
    // 添加操作
    await dishAddService(formModel.value)
    ElMessage.success('添加成功')
    visibleDrawer.value = false
    emit('success', 'add')
  }
}

const editorRef = ref()
//控制显示抽屉
const open = async (row) => {
  visibleDrawer.value = true

  if (row.id) {
    const res = await dishQueryById(row.id)
    formModel.value = res.data.data
    imgUrl.value = formModel.value.image
  } else {
    formModel.value = { ...defaultForm } // 基于默认的数据，重置form数据
    // 这里重置了表单的数据，但是图片上传img地址，富文本编辑器内容 => 需要手动重置
    imgUrl.value = ''
    editorRef.value.setHTML('')
  }
}

defineExpose({ open })
</script>

<template>
  <el-drawer v-model="visibleDrawer" :title="formModel.id ? '修改菜品' : '添加菜品'" direction="rtl" size="50%">
    <!-- 发表文章表单 -->
    <el-form :rules="rules" :model="formModel" ref=" formRef" label-width="70px">
      <el-form-item label="名称：" prop="name">
        <el-input placeholder="请输入名称" v-model="formModel.name"></el-input>
      </el-form-item>
      <el-form-item label="分类：" prop="category">
        <el-select v-model="formModel.category">
          <el-option label="主食类" value="主食类"></el-option>
          <el-option label="特色类" value="特色类"></el-option>
          <el-option label="中餐类" value="中餐类"></el-option>
          <el-option label="素菜类" value="素菜类"></el-option>
          <el-option label="小吃类" value="小吃类"></el-option>
          <el-option label="汤菜类" value="汤菜类"></el-option>
          <el-option label="酒水饮料" value="酒水饮料"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="价格：" prop="price">
        <el-input placeholder="请输入价格" v-model="formModel.price"></el-input>
      </el-form-item>
      <el-form-item label="图片：">
        <el-upload class="avatar-uploader" :show-file-list="false" :auto-upload="false" :on-change="onUploadFile">
          <img v-if="imgUrl" :src="imgUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon">
            <Plus />
          </el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="描述：" prop="description">
        <div class="editor">
          <quill-editor ref="editorRef" content-type="html" theme="snow" v-model:content="formModel.description">
          </quill-editor>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button @click="onPublish(1)" type="primary">起售</el-button>
        <el-button @click="onPublish(0)" type="danger">停售</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style lang="scss" scoped>
.avatar-uploader {
  :deep() {
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }

    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }

    .el-upload:hover {
      border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      text-align: center;
    }
  }
}

.editor {
  width: 100%;

  :deep(.ql-editor) {
    min-height: 200px;
  }
}
</style>
