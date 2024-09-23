<script setup lang="tsx">
import { createVNode, onMounted, ref } from "vue";
import { message } from "@/utils/message";
import { addDialog } from "@/components/ReDialog";
import { addRole, deleteRole, getRoleList, updateRole } from "@/api/roles";
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
  if (id == null) {
    ids = tableRef.value
      .getTableRef()
      .getSelectionRows()
      .map(item => item.id);
  } else {
    ids = [id];
  }
  console.log("curSelected===>>>: ", ids);
  const res = await deleteRole(ids);
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
  // 用于多选表格，清空角色的选择
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
    title: `${title}角色`,
    fullscreenIcon: true,
    sureBtnLoading: true,
    props: {
      formInline: {
        id: item?.id ?? "",
        name: item?.name ?? "",
        code: item?.code ?? "",
        roleDesc: item?.roleDesc ?? ""
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
            ? await updateRole(options.props.formInline)
            : await addRole(options.props.formInline);
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
  username: null
});
const loadData = async (flag = 1) => {
  loading.value = true;
  let res;
  try {
    res = await getRoleList({
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
    width: 180
  },
  {
    label: "角色名称",
    prop: "name",
    width: 150
  },
  {
    label: "名称",
    prop: "name",
    width: 130
  },
  {
    label: "角色Code",
    prop: "code",
    width: 130
  },
  {
    label: "角色描述",
    prop: "roleDesc"
  },
  {
    label: "创建时间",
    prop: "createTime"
  },
  {
    label: "更新时间",
    prop: "updateTime"
  },
  {
    label: "操作",
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
        :ref="formRef"
        :inline="true"
        class="search-form"
        :model="searchForm"
      >
        <el-form-item label="角色名" prop="username">
          <el-input
            v-model="searchForm.username"
            clearable
            placeholder="请输入角色名"
          />
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
    <PureTableBar :columns="columns" title="角色管理" @refresh="loadData">
      <template #buttons>
        <el-button
          type="primary"
          :icon="useRenderIcon(AddFill)"
          @click="onFullscreenIconClick('新增', {})"
        >
          新增角色
        </el-button>
      </template>
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
          <el-popconfirm title="是否确认删除?" @confirm="onbatchDel">
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
          <template #operation="{ row }">
            <el-button
              link
              type="primary"
              size="small"
              @click="onFullscreenIconClick('编辑', row)"
            >
              编辑
            </el-button>
            <el-popconfirm
              title="删除角色会连带用户授权记录一并情况，是否确认删除?"
              @confirm="onbatchDel(row.id)"
            >
              <template #reference>
                <el-button type="danger" text class="mr-1"> 删除</el-button>
              </template>
            </el-popconfirm>
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
