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
    quotationMaxPrice: number;
    quotationAvgPrice: number;
    quotationMinPrice: number;
    quotationStatus: string;
    dataDate: string;
  };
}

const loading = ref(false);
// 声明 props 默认值
// 推荐阅读：https://cn.vuejs.org/guide/typescript/composition-api.html#typing-component-props
const props = withDefaults(defineProps<FormProps>(), {
  formInline: () => ({
    id: "",
    quotationMaxPrice: 0,
    quotationAvgPrice: 0,
    quotationMinPrice: 0,
    quotationStatus: "1",
    dataDate: ""
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
const shortcuts = [
  {
    text: "今天",
    value: new Date()
  },
  {
    text: "昨天",
    value: () => {
      const date = new Date();
      date.setTime(date.getTime() - 3600 * 1000 * 24);
      return date;
    }
  },
  {
    text: "一周前",
    value: () => {
      const date = new Date();
      date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
      return date;
    }
  }
];
const disabledDate = (time: Date) => {
  return time.getTime() > Date.now();
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
    <el-form-item label="最高价">
      <el-input
        v-model.number="newFormInline.quotationMaxPrice"
        class="!w-[220px]"
        placeholder="请输入最高价"
      />
    </el-form-item>

    <el-form-item label="参考价">
      <el-input
        v-model.number="newFormInline.quotationAvgPrice"
        class="!w-[220px]"
        placeholder="请输入参考价"
      />
    </el-form-item>
    <el-form-item label="最低价">
      <el-input
        v-model.number="newFormInline.quotationMinPrice"
        class="!w-[220px]"
        placeholder="请输入最低价"
      />
    </el-form-item>
    <el-form-item label="日期" prop="dataDate">
      <el-date-picker
        v-model="newFormInline.dataDate"
        type="date"
        class="!w-[160px]"
        placeholder="请选择"
        :disabled-date="disabledDate"
        :shortcuts="shortcuts"
        format="YYYY-MM-DD"
        value-format="YYYY-MM-DD"
        :popper-options="{
          placement: 'bottom-start'
        }"
      />
    </el-form-item>
    <el-form-item label="状态">
      <el-select
        v-model="newFormInline.quotationStatus"
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
