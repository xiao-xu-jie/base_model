<script setup lang="ts">
import { onMounted, ref } from "vue";
import { extractPathList } from "@/utils/tree";
import { ElTreeV2 } from "element-plus";
import { message } from "@/utils/message";
import { getAllbizCategory } from "@/api/category";
import { getAllbizSourceStation } from "@/api/source";

// 声明 props 类型
export interface FormProps {
  formInline: {
    id: string;
    goodName: string;
    goodDesc: string;
    goodParams: string;
    goodPrice: number;
    platformId: string;
    categoryId: string;
    stationId: string;
  };
}

const loading = ref(false);
// 声明 props 默认值
// 推荐阅读：https://cn.vuejs.org/guide/typescript/composition-api.html#typing-component-props
const props = withDefaults(defineProps<FormProps>(), {
  formInline: () => ({
    id: "",
    goodName: "",
    goodDesc: "",
    goodParams: "",
    goodPrice: 0,
    platformId: "",
    categoryId: "",
    stationId: ""
  })
});
const loadCategory = async () => {
  loading.value = true;
  let res = await getAllbizCategory();
  if (!res.success) {
    loading.value = false;
    message(res.message, { type: "error" });
    return;
  }
  categoryData.value = res.data;
  res = await getAllbizSourceStation();
  if (!res.success) {
    loading.value = false;
    message(res.message, { type: "error" });
    return;
  }
  sourceData.value = res.data;
  loading.value = false;
};
onMounted(() => {
  loadCategory();
  console.log("mounted");
});
// vue 规定所有的 prop 都遵循着单向绑定原则，直接修改 prop 时，Vue 会抛出警告。此处的写法仅仅是为了消除警告。
// 因为对一个 reactive 对象执行 ref，返回 Ref 对象的 value 值仍为传入的 reactive 对象，
// 即 newFormInline === props.formInline 为 true，所以此处代码的实际效果，仍是直接修改 props.formInline。
// 但该写法仅适用于 props.formInline 是一个对象类型的情况，原始类型需抛出事件
// 推荐阅读：https://cn.vuejs.org/guide/components/props.html#one-way-data-flow
const newFormInline = ref(props.formInline);

const categoryData = ref([]);
const sourceData = ref([]);
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
    <el-form-item label="商品名称">
      <el-input
        v-model="newFormInline.goodName"
        class="!w-[220px]"
        placeholder="请输入商品名称"
      />
    </el-form-item>
    <el-form-item label="商品描述">
      <el-input
        v-model="newFormInline.goodDesc"
        class="!w-[220px]"
        placeholder="请输入商品描述"
      />
    </el-form-item>
    <el-form-item label="商品价格">
      <el-input
        v-model.number="newFormInline.goodPrice"
        class="!w-[220px]"
        placeholder="请输入商品价格"
      />
    </el-form-item>
    <el-form-item label="商品参数">
      <el-input
        v-model.number="newFormInline.goodParams"
        class="!w-[220px]"
        placeholder="请输入商品参数"
      />
    </el-form-item>
    <el-form-item label="平台ID">
      <el-input
        v-model="newFormInline.platformId"
        class="!w-[220px]"
        placeholder="请输入所属平台ID"
      />
    </el-form-item>
    <el-form-item label="所属分类">
      <el-select
        v-model="newFormInline.categoryId"
        class="!w-[220px]"
        placeholder="请选择所属分类"
      >
        <el-option
          v-for="item in categoryData"
          :key="item.id"
          :label="item.categoryName"
          :value="item.id"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="所属货源">
      <el-select
        v-model="newFormInline.stationId"
        class="!w-[220px]"
        placeholder="请选择所属货源"
      >
        <el-option
          v-for="item in sourceData"
          :key="item.id"
          :label="item.stationName"
          :value="item.id"
        />
      </el-select>
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
