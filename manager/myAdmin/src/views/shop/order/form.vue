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
    name: string;
    code: string;
    roleDesc: string;
    routers: TreeKey[];
  };
}

const loading = ref(false);
// 声明 props 默认值
// 推荐阅读：https://cn.vuejs.org/guide/typescript/composition-api.html#typing-component-props
const props = withDefaults(defineProps<FormProps>(), {
  formInline: () => ({
    id: "",
    name: "",
    code: "",
    roleDesc: "",
    routers: []
  })
});

onMounted(() => {
  loadRouters();
  console.log("mounted");
});
// vue 规定所有的 prop 都遵循着单向绑定原则，直接修改 prop 时，Vue 会抛出警告。此处的写法仅仅是为了消除警告。
// 因为对一个 reactive 对象执行 ref，返回 Ref 对象的 value 值仍为传入的 reactive 对象，
// 即 newFormInline === props.formInline 为 true，所以此处代码的实际效果，仍是直接修改 props.formInline。
// 但该写法仅适用于 props.formInline 是一个对象类型的情况，原始类型需抛出事件
// 推荐阅读：https://cn.vuejs.org/guide/components/props.html#one-way-data-flow
const newFormInline = ref(props.formInline);
const dataProps = ref({
  children: "children",
  value: "id"
});

const loadRouters = async () => {
  loading.value = true;
  const res = await getRouters();
  menusData.value = res.data;
  if (res.success) {
    const ids = await getRoleRouters({ roleId: newFormInline.value.id });
    treeRef.value.setCheckedKeys(ids.data);
  } else {
    message(res.message, { type: "error" });
  }
  loading.value = false;
};
const treeRef = ref<InstanceType<typeof ElTreeV2>>();

const menusData = ref([]);

const filterMethod = (value, data) => {
  if (!value) return true;
  return data.meta.title.indexOf(value) !== -1;
};
const expandedKeys = extractPathList(menusData.value);
const onCheckChange = e => {
  newFormInline.value.routers = treeRef.value.getCheckedKeys();
  console.log("data===>>>: ", newFormInline.value.routers);
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
    <el-form-item label="角色名称">
      <el-input
        v-model="newFormInline.name"
        class="!w-[220px]"
        placeholder="请输入角色名称"
      />
    </el-form-item>
    <el-form-item label="角色Code">
      <el-input
        v-model="newFormInline.code"
        :disabled="!!newFormInline.id"
        class="!w-[220px]"
        placeholder="请输入角色Code"
      />
    </el-form-item>
    <el-form-item label="角色描述">
      <el-input
        v-model="newFormInline.roleDesc"
        class="!w-[220px]"
        placeholder="请输入角色描述"
      />
    </el-form-item>
    <el-form-item label="角色菜单">
      <div style="width: 300px; height: 300px">
        <el-tree-v2
          ref="treeRef"
          :data="menusData"
          :props="dataProps"
          show-checkbox
          :filter-method="filterMethod"
          :default-expanded-keys="expandedKeys"
          @check-change="onCheckChange"
        >
          <template #default="{ data }">
            <span>{{ data.meta.title }}</span>
          </template>
        </el-tree-v2>
      </div>
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
