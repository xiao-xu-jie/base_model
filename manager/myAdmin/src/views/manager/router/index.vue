<script setup lang="tsx">
import { createVNode, onMounted, ref } from "vue";
import { message } from "@/utils/message";
import { addDialog } from "@/components/ReDialog";
import {
  addRouters,
  deleteRouters,
  getRoutersChildren,
  getRoutersList,
  updateRouters
} from "@/api/routes";
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
  const res = await deleteRouters(ids);
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
    title: `${title}路由`,
    fullscreenIcon: true,
    sureBtnLoading: true,
    props: {
      formInline: {
        id: item?.id ?? "",
        title: item?.title ?? "",
        name: item?.name ?? "",
        icon: item?.icon ?? "",
        path: item?.path ?? "",
        redirect: item?.redirect ?? "",
        component: item?.component ?? "",
        showlink: item?.showlink ?? true,
        rank: item?.rank ?? "",
        parentId: item?.parentId ?? null
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
            ? await updateRouters(options.props.formInline)
            : await addRouters(options.props.formInline);
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
  title: null
});
const loadData = async (flag = 1) => {
  loading.value = true;
  let res;
  try {
    res = await getRoutersList({
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
    label: "标题",
    prop: "title",
    width: 150
  },
  {
    label: "图标",
    prop: "icon",
    width: 90,
    slot: "icon"
  },
  {
    label: "路径",
    prop: "path",
    width: 130
  },
  {
    label: "重定向",
    prop: "redirect",
    width: 130
  },
  {
    label: "组件",
    prop: "component"
  },
  {
    label: "是否显示",
    prop: "showlink",
    slot: "showLink"
  },
  {
    label: "排序（一级有效）",
    prop: "rank"
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
    slot: "operation",
    width: 150
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

const loadChildren = async (row, treeNode, resolve: (date: any[]) => void) => {
  let res = await getRoutersChildren({ id: row.id });
  resolve(res.data);
};
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
        <el-form-item label="标题" prop="title">
          <el-input
            v-model="searchForm.title"
            clearable
            placeholder="请输入标题名"
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
    <PureTableBar :columns="columns" title="路由管理" @refresh="loadData">
      <template #buttons>
        <el-button
          type="primary"
          :icon="useRenderIcon(AddFill)"
          @click="onFullscreenIconClick('新增', {})"
        >
          新增路由
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
          lazy
          :load="loadChildren"
          :columns="dynamicColumns"
          :pagination="pagination"
          :header-cell-style="{
            background: 'var(--el-fill-color-light)',
            color: 'var(--el-text-color-primary)'
          }"
          :tree-props="{
            hasChildren: 'hasChildren',
            children: 'children',
            checkStrictly: false
          }"
          @selection-change="handleSelectionChange"
          @page-size-change="onSizeChange"
          @page-current-change="onCurrentChange"
        >
          <template #icon="{ row }">
            <div
              style="
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100%;
              "
            >
              <div>
                <IconifyIconOnline :icon="row.icon" />
              </div>
            </div>
          </template>
          <template #showLink="{ row }">
            <el-tag
              v-if="row?.showlink == undefined || row.showlink === true"
              type="success"
              size="small"
              >显示
            </el-tag>
            <el-tag v-else type="danger" size="small">隐藏</el-tag>
          </template>

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
              title="删除路由会连带角色授权记录一并情况，是否确认删除?"
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
