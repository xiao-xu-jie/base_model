<script setup lang="ts">
import { onMounted, ref } from "vue";
import { getUserRoles } from "@/api/user";
import { Plus } from "@element-plus/icons-vue";
import { getToken } from "@/utils/auth";
import { getAllRole } from "@/api/roles";

// 声明 props 类型
export interface FormProps {
  formInline: {
    id: string;
    avatar: string;
    name: string;
    username: string;
    password: string;
    sex: string;
    phone: string;
    email: string;
    roles: string[];
  };
}

const loading = ref(false);
// 声明 props 默认值
// 推荐阅读：https://cn.vuejs.org/guide/typescript/composition-api.html#typing-component-props
const props = withDefaults(defineProps<FormProps>(), {
  formInline: () => ({
    id: "",
    avatar: "",
    name: "",
    username: "",
    password: "",
    sex: "",
    phone: "",
    email: "",
    roles: []
  })
});

onMounted(() => {
  console.log("mounted");
  getRoles();
});
// vue 规定所有的 prop 都遵循着单向绑定原则，直接修改 prop 时，Vue 会抛出警告。此处的写法仅仅是为了消除警告。
// 因为对一个 reactive 对象执行 ref，返回 Ref 对象的 value 值仍为传入的 reactive 对象，
// 即 newFormInline === props.formInline 为 true，所以此处代码的实际效果，仍是直接修改 props.formInline。
// 但该写法仅适用于 props.formInline 是一个对象类型的情况，原始类型需抛出事件
// 推荐阅读：https://cn.vuejs.org/guide/components/props.html#one-way-data-flow
const newFormInline = ref(props.formInline);
const getRoles = async () => {
  loading.value = true;
  let res = newFormInline.value.id
    ? await getUserRoles(newFormInline.value)
    : null;
  newFormInline.value.roles = res?.data ?? [];
  res = await getAllRole();
  roles.value = res.data.map(item => ({
    value: item.id,
    label: item.name
  }));
  loading.value = false;
  console.log("roles===>>>: ", roles.value);
};
const roles = ref([]);
const header = { Authorization: "Bearer " + getToken().accessToken };
const handleAvatarSuccess = (res: any) => {
  console.log("res===>>>: ", res);
  newFormInline.value.avatar = res.data;
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
    <el-form-item label="头像">
      <el-upload
        class="avatar-uploader"
        action="/api/common/upload"
        :show-file-list="false"
        :headers="header"
        :on-success="handleAvatarSuccess"
      >
        <img
          v-if="newFormInline.avatar"
          :src="newFormInline.avatar"
          class="avatar"
        />
        <el-icon v-else class="avatar-uploader-icon">
          <Plus />
        </el-icon>
      </el-upload>
    </el-form-item>
    <el-form-item label="姓名">
      <el-input
        v-model="newFormInline.name"
        class="!w-[220px]"
        placeholder="请输入姓名"
      />
    </el-form-item>
    <el-form-item label="用户名">
      <el-input
        v-model="newFormInline.username"
        :disabled="!!newFormInline.id"
        class="!w-[220px]"
        placeholder="请输入用户名"
      />
    </el-form-item>
    <el-form-item label="手机号">
      <el-input
        v-model="newFormInline.phone"
        class="!w-[220px]"
        placeholder="请输入手机号"
      />
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input
        v-model="newFormInline.email"
        class="!w-[220px]"
        placeholder="请输入邮箱"
      />
    </el-form-item>
    <el-form-item label="密码">
      <el-input
        v-model="newFormInline.password"
        class="!w-[220px]"
        placeholder="请输入密码"
      />
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="newFormInline.sex">
        <el-radio :value="1" label="男" />
        <el-radio :value="0" label="女" />
      </el-radio-group>
    </el-form-item>
    <el-form-item label="角色">
      <el-select
        v-model="newFormInline.roles"
        multiple
        collapse-tags
        collapse-tags-tooltip
        placeholder="请选择角色"
        style="width: 240px"
      >
        <el-option
          v-for="item in roles"
          :key="item.value"
          :label="item.label"
          :value="item.value"
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
