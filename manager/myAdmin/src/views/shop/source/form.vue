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
    stationName: string;
    stationUrl: string;
    uid: string;
    secret: string;
    stationStatus: number;
  };
}

const loading = ref(false);
// 声明 props 默认值
// 推荐阅读：https://cn.vuejs.org/guide/typescript/composition-api.html#typing-component-props
const props = withDefaults(defineProps<FormProps>(), {
  formInline: () => ({
    id: "",
    stationName: "",
    stationUrl: "",
    uid: "",
    secret: "",
    stationStatus: 1
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
    :model="newFormInline"
    label-width="auto"
  >
    <el-form-item v-if="newFormInline.id" label="ID">
      <el-input v-model="newFormInline.id" disabled class="!w-[220px]" />
    </el-form-item>
    <el-form-item label="货源名称">
      <el-input
        v-model="newFormInline.stationName"
        class="!w-[220px]"
        placeholder="请输入货源名称"
      />
    </el-form-item>
    <el-form-item label="地址">
      <el-input
        v-model="newFormInline.stationUrl"
        class="!w-[220px]"
        placeholder="请输入货源地址"
      />
    </el-form-item>
    <el-form-item label="UID">
      <el-input
        v-model="newFormInline.uid"
        class="!w-[220px]"
        placeholder="请输入对接uid"
      />
    </el-form-item>
    <el-form-item label="secret">
      <el-input
        v-model="newFormInline.secret"
        class="!w-[220px]"
        placeholder="请输入对接secret"
      />
    </el-form-item>
    <el-form-item label="状态">
      <el-radio-group v-model="newFormInline.stationStatus">
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
