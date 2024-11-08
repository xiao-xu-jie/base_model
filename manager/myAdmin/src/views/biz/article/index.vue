<script setup lang="tsx">
import { createVNode, onMounted, ref } from "vue";
import { message } from "@/utils/message";
import { addDialog } from "@/components/ReDialog";
import {
  addArticle,
  deleteArticle,
  getArticleList,
  updateArticle
} from "@/api/biz/article";
import { PureTableBar } from "@/components/RePureTableBar";
import { useRenderIcon } from "@/components/ReIcon/src/hooks";
import Refresh from "@iconify-icons/ep/refresh";
import forms from "./form.vue";
import AddFill from "@iconify-icons/ri/add-fill";
import { EditorBase } from "./components";

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
  const res = await deleteArticle(ids);
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
    title: `${title}公告`,
    fullscreenIcon: true,
    sureBtnLoading: true,
    props: {
      formInline: {
        id: item?.id ?? "",
        articleTitle: item?.articleTitle ?? "",
        articleDesc: item?.articleDesc ?? "",
        articleShowImg: item?.articleShowImg ?? "",
        articleSource: item?.articleSource ?? "",
        articleExtra: item?.articleExtra ?? "",
        isShow: item?.isShow ?? 1
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
            ? await updateArticle(options.props.formInline)
            : await addArticle(options.props.formInline);
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
  articleTitle: null,
  articleDesc: null,
  articleSource: null
});
const loadData = async (flag = 1) => {
  loading.value = true;
  let res;
  try {
    res = await getArticleList({
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
    label: "标题",
    prop: "articleTitle"
  },
  {
    label: "描述信息",
    prop: "articleDesc",
    slot: "articleDesc"
  },
  {
    label: "内容",
    prop: "articleContent",
    slot: "articleContent"
  },
  {
    label: "封面图片",
    prop: "articleShowImg",
    slot: "articleShowImg"
  },
  {
    label: "发布人",
    prop: "articleSource"
  },
  {
    label: "附加信息",
    prop: "articleExtra"
  },
  {
    label: "显示状态",
    prop: "isShow",
    slot: "isShow"
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

const showContent = (id, content) => {
  let text = null;
  addDialog({
    title: "内容编辑",
    sureBtnLoading: true,
    props: {
      formInline: {
        id: id ?? "",
        content: content ?? ""
      }
    },
    contentRenderer: () =>
      createVNode(EditorBase, {
        id,
        content,
        onChange: e => {
          text = e;
        }
      }),
    beforeSure: async (done, { options, closeLoading }) => {
      let res;
      options.props.formInline.articleContent = text;
      try {
        res = await updateArticle(options.props.formInline);
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
};
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
        <el-form-item label="发布人员" prop="articleSource">
          <el-input
            v-model="searchForm.articleSource"
            clearable
            placeholder="请输入发布人员"
          />
        </el-form-item>
        <el-form-item label="标题" prop="articleTitle">
          <el-input
            v-model="searchForm.articleTitle"
            clearable
            placeholder="请输入标题"
          />
        </el-form-item>
        <el-form-item label="描述" prop="articleDesc">
          <el-input
            v-model="searchForm.articleDesc"
            clearable
            placeholder="请输入描述"
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
    <PureTableBar :columns="columns" title="公告管理" @refresh="loadData">
      <template #buttons>
        <el-button
          type="primary"
          :icon="useRenderIcon(AddFill)"
          @click="onFullscreenIconClick('新增', {})"
        >
          新增公告
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
          <template #articleShowImg="{ row }">
            <el-image
              style="width: 50px; height: 50px"
              :src="row.articleShowImg"
              fit="cover"
            />
          </template>
          <template #isShow="{ row }">
            <el-tag :type="row.isShow == 1 ? 'success' : 'danger'">
              {{ row.isShow == 1 ? "显示" : "隐藏" }}
            </el-tag>
          </template>
          <template #articleDesc="{ row }">
            {{ !!row?.articleDesc ? row.articleDesc : "暂无描述" }}
          </template>
          <template #articleContent="{ row }">
            <el-button
              type="text"
              size="small"
              @click="showContent(row.id, row.articleContent)"
              >查看详情
            </el-button>
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
              title="是否确认删除用户?"
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
