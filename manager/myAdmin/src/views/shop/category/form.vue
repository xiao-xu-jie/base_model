<script setup lang="ts">
import { onMounted, ref } from "vue";
import { extractPathList } from "@/utils/tree";
import { ElTreeV2 } from "element-plus";
import { getRoleRouters, getRouters } from "@/api/roles";
import { message } from "@/utils/message";
import { TreeKey } from "element-plus/es/components/tree-v2/src/types";

// 声明 props 类型
export interface FormProps {
  formInline: {
    id: string;
    categoryName: string;
    categoryDesc: string;
    categoryRank: number;
    categoryStatus: number;
  };
}

const loading = ref(false);
// 声明 props 默认值
// 推荐阅读：https://cn.vuejs.org/guide/typescript/composition-api.html#typing-component-props
const props = withDefaults(defineProps<FormProps>(), {
  formInline: () => ({
    id: "",
    categoryName: "",
    categoryDesc: "",
    categoryRank: 99,
    categoryStatus: 1
  })
});

onMounted(() => {
  console.log("mounted");
});
// vue 规定所有的 prop 都遵循着单向绑定原则，直接修改 prop 时，Vue 会抛出警告。此处的写法仅仅是为了消除警告。
// 因为对一个 reactive 对象执行 ref，返回 Ref 对象的 value 值仍为传入的 reactive 对象，
// 即 newFormInline === props.formInline 为 true，所以此处代码的实际效果，仍是直接修改 props.formInline。
// 但该写法仅适用于 props.formInline 是一个对象类型的情况，原始类型需抛出事件
// 推荐阅读：https://cn.vuejs.org/guide/components/props.html#one-way-data-flow
const newFormInline = ref(props.formInline);
const menusData = ref([]);
</script>

<template>
  <el-form
    v-loading="loading"
    style="max-width: 600px"
    label-position="left"
    :model="newFormInline"
    label-width="auto"
  >
    <el-form-item v-if="newFormInline.id" label="ID">
      <el-input v-model="newFormInline.id" disabled class="!w-[220px]" />
    </el-form-item>
    <el-form-item label="分类名称">
      <el-input
        v-model="newFormInline.categoryName"
        class="!w-[220px]"
        placeholder="请输入分类名称"
      />
    </el-form-item>
    <el-form-item label="分类描述">
      <el-input
        v-model="newFormInline.categoryDesc"
        class="!w-[220px]"
        placeholder="请输入分类描述"
      />
    </el-form-item>
    <el-form-item label="分类排序">
      <el-input
        v-model.number="newFormInline.categoryRank"
        class="!w-[220px]"
        placeholder="请输入分类排序"
      />
    </el-form-item>
    <el-form-item label="状态">
      <el-radio-group v-model="newFormInline.categoryStatus">
        <el-radio label="1" :value="1">启用</el-radio>
        <el-radio label="0" :value="0">禁用</el-radio>
      </el-radio-group>
    </el-form-item>
  </el-form>
</template>
<style lang="scss" scoped>
.avatar-uploader .avatar {
  display: block;
  width: 178px;
  height: 178px;
}

.avatar-uploader .el-upload {
  position: relative;
  overflow: hidden;
  cursor: pointer;
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  width: 178px;
  height: 178px;
  font-size: 28px;
  color: #8c939d;
  text-align: center;
}

.avatar {
  display: block;
  width: 144px;
  height: 144px;
}
</style>
