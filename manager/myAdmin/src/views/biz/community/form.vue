<script setup lang="ts">
import { onMounted, ref } from "vue";
import { getUserRoles } from "@/api/user";
import { Plus } from "@element-plus/icons-vue";
import { getToken } from "@/utils/auth";
import { getAllRole } from "@/api/roles";
import { ElMessage } from "element-plus";

// 声明 props 类型
export interface FormProps {
  formInline: {
    id: string;
    title: string;
    postDesc: string;
    coverImg: string;
    status: string;
  };
}

const loading = ref(false);
// 声明 props 默认值
// 推荐阅读：https://cn.vuejs.org/guide/typescript/composition-api.html#typing-component-props
const props = withDefaults(defineProps<FormProps>(), {
  formInline: () => ({
    id: "",
    title: "",
    postDesc: "",
    coverImg: "",
    status: "1"
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

const roles = ref([]);
const header = { Authorization: "Bearer " + getToken().accessToken };
const handleAvatarSuccess = (res: any) => {
  console.log("res===>>>: ", res);
  ElMessage.success("上传成功");
  newFormInline.value.coverImg = res.data;
};
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
    <el-form-item label="封面图片">
      <el-upload
        class="avatar-uploader"
        action="/api/common/upload"
        :show-file-list="false"
        :headers="header"
        :on-success="handleAvatarSuccess"
      >
        <img
          v-if="newFormInline.coverImg"
          :src="newFormInline.coverImg"
          class="avatar"
        />
        <el-icon v-else class="avatar-uploader-icon">
          <Plus />
        </el-icon>
      </el-upload>
    </el-form-item>
    <el-form-item label="标题">
      <el-input
        v-model="newFormInline.title"
        class="!w-[220px]"
        placeholder="请输入标题"
      />
    </el-form-item>

    <el-form-item label="描述">
      <el-input
        v-model="newFormInline.postDesc"
        type="textarea"
        class="!w-[220px]"
        placeholder="请输入描述"
      />
    </el-form-item>
    <el-form-item label="状态">
      <el-select
        v-model="newFormInline.status"
        class="!w-[220px]"
        placeholder="请选择"
      >
        <el-option label="显示" :value="1" />
        <el-option label="隐藏" :value="0" />
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
