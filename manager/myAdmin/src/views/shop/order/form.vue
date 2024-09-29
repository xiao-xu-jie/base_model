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
    orderNo: string;
    goodId: string;
    goodName: string;
    goodDesc: string;
    totalPrice: string;
    phone: string;
    password: string;
    classInfo: string;
    orderStatus: string;
  };
}

const loading = ref(false);
// 声明 props 默认值
// 推荐阅读：https://cn.vuejs.org/guide/typescript/composition-api.html#typing-component-props
const props = withDefaults(defineProps<FormProps>(), {
  formInline: () => ({
    id: "",
    orderNo: "",
    goodId: "",
    goodName: "",
    goodDesc: "",
    totalPrice: "",
    phone: "",
    password: "",
    classInfo: "",
    orderStatus: ""
  })
});

onMounted(() => {
  console.log("mounted");
});
const newFormInline = ref(props.formInline);
const menusData = ref([]);
</script>

<template>
  <el-form
    v-loading="loading"
    style="max-width: 600px"
    label-position="left"
    :disabled="true"
    :model="newFormInline"
    label-width="auto"
  >
    <el-form-item v-if="newFormInline.id" label="ID">
      <el-input v-model="newFormInline.id" disabled class="!w-[220px]" />
    </el-form-item>
    <el-form-item label="订单号">
      <el-input
        v-model="newFormInline.orderNo"
        class="!w-[220px]"
        placeholder="请输入订单号"
      />
    </el-form-item>
    <el-form-item label="商品ID">
      <el-input
        v-model="newFormInline.goodId"
        class="!w-[220px]"
        placeholder="请输入商品ID"
      />
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
    <el-form-item label="总价">
      <el-input
        v-model.number="newFormInline.totalPrice"
        class="!w-[220px]"
        placeholder="请输入totalPrice"
      />
    </el-form-item>
    <el-form-item label="用户手机号">
      <el-input
        v-model.number="newFormInline.phone"
        class="!w-[220px]"
        placeholder="请输入用户手机号"
      />
    </el-form-item>
    <el-form-item label="密码">
      <el-input
        v-model.number="newFormInline.password"
        class="!w-[220px]"
        placeholder="请输入密码"
      />
    </el-form-item>
    <el-form-item label="下单课程">
      <json-viewer
        copyable
        :width="400 + 'px'"
        :expand-depth="3"
        :value="JSON.parse(newFormInline.classInfo)"
      />
    </el-form-item>
    <el-form-item label="订单状态">
      <el-input
        v-model.number="newFormInline.orderStatus"
        class="!w-[220px]"
        placeholder="请输入状态"
      />
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
