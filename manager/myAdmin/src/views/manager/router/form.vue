<script setup lang="ts">
import { onMounted, ref } from "vue";
import IconSelect from "@/components/ReIcon/src/Select.vue";
import { getTopRouters } from "@/api/routes";

// 声明 props 类型
export interface FormProps {
  formInline: {
    id: string;
    title: string;
    name: string;
    icon: string;
    path: string;
    redirect: string;
    component: string;
    showlink: boolean;
    rank: string;
    parentId: string;
  };
}

const loading = ref(false);
// 声明 props 默认值
// 推荐阅读：https://cn.vuejs.org/guide/typescript/composition-api.html#typing-component-props
const props = withDefaults(defineProps<FormProps>(), {
  formInline: () => ({
    id: "",
    title: "",
    name: "",
    icon: "",
    path: "",
    redirect: "",
    component: "",
    showlink: true,
    rank: "",
    parentId: ""
  })
});

onMounted(() => {
  console.log("mounted");
  loadTopList();
});
// vue 规定所有的 prop 都遵循着单向绑定原则，直接修改 prop 时，Vue 会抛出警告。此处的写法仅仅是为了消除警告。
// 因为对一个 reactive 对象执行 ref，返回 Ref 对象的 value 值仍为传入的 reactive 对象，
// 即 newFormInline === props.formInline 为 true，所以此处代码的实际效果，仍是直接修改 props.formInline。
// 但该写法仅适用于 props.formInline 是一个对象类型的情况，原始类型需抛出事件
// 推荐阅读：https://cn.vuejs.org/guide/components/props.html#one-way-data-flow
const newFormInline = ref(props.formInline);

const parentList = ref([
  {
    id: "0",
    title: "顶级菜单"
  }
]);
const loadTopList = async () => {
  const res = await getTopRouters();
  console.log("res===>>>: ", res);
  parentList.value = parentList.value.concat(res.data);
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
    <el-form-item label="页面标题">
      <el-input
        v-model="newFormInline.title"
        class="!w-[220px]"
        placeholder="请输入页面标题"
      />
    </el-form-item>
    <el-form-item label="路由名称">
      <el-input
        v-model="newFormInline.name"
        class="!w-[220px]"
        placeholder="请输入路由名称"
      />
    </el-form-item>
    <el-form-item label="icon">
      <IconSelect v-model="newFormInline.icon" class="w-[200px]" />
    </el-form-item>
    <el-form-item label="路径">
      <el-input
        v-model="newFormInline.path"
        class="!w-[220px]"
        placeholder="请输入路径"
      />
    </el-form-item>
    <el-form-item label="重定向">
      <el-input
        v-model="newFormInline.redirect"
        class="!w-[220px]"
        placeholder="请输入重定向路径"
      />
    </el-form-item>
    <el-form-item label="组件">
      <el-input
        v-model="newFormInline.component"
        class="!w-[220px]"
        placeholder="请输入组件"
      />
    </el-form-item>
    <el-form-item label="是否显示">
      <el-radio-group v-model="newFormInline.showlink">
        <el-radio :value="true">显示</el-radio>
        <el-radio :value="false">隐藏</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="排序">
      <el-input
        v-model.number="newFormInline.rank"
        class="!w-[220px]"
        placeholder="请输入排序"
      />
    </el-form-item>
    <el-form-item label="父级菜单">
      <el-select
        v-model="newFormInline.parentId"
        placeholder="请选择父级菜单"
        style="width: 240px"
      >
        <el-option
          v-for="item in parentList"
          :key="item.id"
          :label="item.title"
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
