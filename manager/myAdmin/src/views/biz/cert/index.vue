<script setup lang="tsx">
import { createVNode, onMounted, ref } from "vue";
import { message } from "@/utils/message";
import { addDialog } from "@/components/ReDialog";
import { addCert, deleteCert, getCertList, updateCert } from "@/api/biz/cert";
import { PureTableBar } from "@/components/RePureTableBar";
import { useRenderIcon } from "@/components/ReIcon/src/hooks";
import Refresh from "@iconify-icons/ep/refresh";
import forms from "./form.vue";
import AddFill from "@iconify-icons/ri/add-fill";

const formRef = ref();
const tableRef = ref();
const selectedNum = ref(0);

/** 当CheckBox选择项发生变化时会触发该事件 */
function handleSelectionChange(val) {
  selectedNum.value = val.length;
}

/** 批量删除 */
async function onbatchDel(id = null) {
  // 返回当前选中的行
  let ids;
  console.log("id===>>>: ", id);
  if (id == null) {
    ids = tableRef.value
      .getTableRef()
      .getSelectionRows()
      .map(item => item.id);
  } else {
    ids = [id];
  }
  console.log("curSelected===>>>: ", ids[0]);
  const res = await deleteCert(ids);
  if (res.success) {
    message(res.message, { type: "success" });
    tableRef.value.getTableRef().clearSelection();
    loadData();
  } else {
    message(res.message, { type: "error" });
  }
}

/** 取消选择 */
function onSelectionCancel() {
  selectedNum.value = 0;
  // 用于多选表格，清空用户的选择
  tableRef.value.getTableRef().clearSelection();
}

const pagination = ref({
  pageSize: 10,
  currentPage: 1,
  pageSizes: [10, 15, 20],
  total: 0,
  align: "right",
  background: true,
  size: "default"
});

function onFullscreenIconClick(title, item) {
  addDialog({
    title: `审核认证`,
    fullscreenIcon: true,
    sureBtnLoading: true,
    props: {
      formInline: {
        id: item?.id ?? "",
        certStatus: item?.certStatus ?? ""
      }
    },
    fullscreenCallBack: ({ options, index }) =>
      message(options.fullscreen ? "全屏" : "非全屏", { type: "success" }),
    contentRenderer: () =>
      createVNode(forms, {
        formInline: item
      }),
    beforeSure: async (done, { options, closeLoading }) => {
      let res;
      try {
        res =
          title === "编辑"
            ? await updateCert(options.props.formInline)
            : await addCert(options.props.formInline);
      } catch (e) {
        console.log("e===>>>: ", e);
      } finally {
        closeLoading();
      }
      if (res.success) {
        message(res.message, { type: "success" });
        loadData(1);
        done();
      } else {
        message(res.message, { type: "error" });
      }
      // closeLoading() // 关闭确定按钮动画，不关闭弹框
      // done() // 关闭确定按钮动画并关闭弹框
    }
  });
}

const resetForm = () => {
  if (!formRef.value) return;
  formRef.value.resetFields();
};
const searchForm = ref({
  nickName: null,
  phoneNumber: null,
  certStatus: null
});
const loadData = async (flag = 1) => {
  loading.value = true;
  let res;
  try {
    res = await getCertList({
      ...searchForm.value,
      pageNum: flag == 1 ? pagination.value.currentPage : 1,
      pageSize: pagination.value.pageSize
    });
  } catch (e) {
    console.log("e===>>>: ", e);
  } finally {
    loading.value = false;
  }

  dataList.value = res.data.records;
  pagination.value.total = res.data.total;
  console.log("res===>>>: ", res);
};

onMounted(() => {
  loadData(1);
});
const loading = ref(false);
const dataList = ref();
const select = ref("no");
const columns: TableColumnList = [
  {
    label: "勾选列", // 如果需要表格多选，此处label必须设置
    type: "selection",
    fixed: "left",
    reserveSelection: true // 数据刷新后保留选项
  },
  {
    label: "ID",
    prop: "id",
    width: 90
  },
  {
    label: "用户名称",
    prop: "nickName",
    width: 130
  },
  {
    label: "认证手机号",
    prop: "phoneNumber"
  },
  {
    label: "认证类型",
    prop: "certType"
  },
  {
    label: "认证姓名",
    prop: "realName"
  },
  {
    label: "证件号码",
    prop: "idCardNo"
  },
  {
    label: "证件图片",
    prop: "imgs",
    slot: "imgs",
    width: 130
  },
  {
    label: "认证状态",
    prop: "certStatus",
    slot: "certStatus"
  },
  {
    label: "创建时间",
    prop: "createTime"
  },
  {
    label: "操作",
    width: "150",
    fixed: "right",
    slot: "operation"
  }
];

/**
 * {
 *       label: "勾选列", // 如果需要表格多选，此处label必须设置
 *       type: "selection",
 *       fixed: "left",
 *       reserveSelection: true // 数据刷新后保留选项
 *}
 *
 */
function onChange(val) {
  pagination.value.size = val;
}

function onSizeChange(val) {
  pagination.value.pageSize = val;
  loadData(0);
}

function onCurrentChange(val) {
  pagination.value.currentPage = val;
  console.log("val===>>>: ", val);
  loadData(1);
}

function handleClick(row) {
  console.log(
    "%crow===>>>: ",
    "color: MidnightBlue; background: Aquamarine; font-size: 20px;",
    row
  );
}
</script>

<template>
  <div>
    <div class="header">
      <el-form
        ref="formRef"
        :inline="true"
        class="search-form"
        :model="searchForm"
      >
        <el-form-item label="用户名" prop="nickName">
          <el-input
            v-model="searchForm.nickName"
            clearable
            placeholder="请输入用户名"
          />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input
            v-model="searchForm.phoneNumber"
            clearable
            placeholder="请输入手机号"
          />
        </el-form-item>
        <el-form-item label="认证状态" prop="certStatus">
          <el-select
            v-model="searchForm.certStatus"
            class="!w-[220px]"
            clearable
            placeholder="请选择"
          >
            <el-option label="审核通过" :value="1" />
            <el-option label="审核中" :value="0" />
            <el-option label="审核拒绝" :value="2" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button
            :icon="useRenderIcon('ri:search-line')"
            type="primary"
            @click="loadData(1)"
            >查询
          </el-button>
          <el-button :icon="useRenderIcon(Refresh)" @click="resetForm()">
            重置
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <PureTableBar :columns="columns" title="用户管理" @refresh="loadData">
      <template v-slot="{ size, dynamicColumns }">
        <div
          v-if="selectedNum > 0"
          v-motion-fade
          class="bg-[var(--el-fill-color-light)] w-full h-[46px] mb-2 pl-4 flex items-center"
        >
          <div class="flex-auto">
            <span
              style="font-size: var(--el-font-size-base)"
              class="text-[rgba(42,46,54,0.5)] dark:text-[rgba(220,220,242,0.5)]"
            >
              已选 {{ selectedNum }} 项
            </span>
            <el-button type="primary" text @click="onSelectionCancel">
              取消选择
            </el-button>
          </div>
          <el-popconfirm title="是否确认删除?" @confirm="onbatchDel()">
            <template #reference>
              <el-button type="danger" text class="mr-1"> 批量删除</el-button>
            </template>
          </el-popconfirm>
        </div>
        <pure-table
          ref="tableRef"
          border
          row-key="id"
          alignWhole="center"
          showOverflowTooltip
          :loading="loading"
          :data="dataList"
          :size="size"
          :columns="dynamicColumns"
          :pagination="pagination"
          :header-cell-style="{
            background: 'var(--el-fill-color-light)',
            color: 'var(--el-text-color-primary)'
          }"
          @selection-change="handleSelectionChange"
          @page-size-change="onSizeChange"
          @page-current-change="onCurrentChange"
        >
          <template #imgs="{ row }">
            <el-image
              style="width: 100px; height: 100px"
              :src="row.imgs"
              fit="cover"
            />
          </template>
          <template #certStatus="{ row }">
            <el-tag v-if="row.certStatus == 0" type="warning"> 审核中 </el-tag>
            <el-tag v-else-if="row.certStatus == 1" type="success">
              审核通过
            </el-tag>
            <el-tag v-else-if="row.certStatus == 2" type="danger">
              审核拒绝
            </el-tag>
          </template>
          <template #operation="{ row }">
            <el-button
              link
              type="primary"
              size="small"
              @click="onFullscreenIconClick('编辑', row)"
            >
              审核
            </el-button>
          </template>
        </pure-table>
      </template>
    </PureTableBar>
  </div>
</template>

<style scoped lang="scss">
.header {
  padding: 10px;
  background-color: #fff;
}

.search-form {
  :deep(.el-form-item) {
    margin-bottom: 12px;
  }
}
</style>
