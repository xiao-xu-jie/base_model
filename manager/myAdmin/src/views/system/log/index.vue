<script setup lang="tsx">
import { createVNode, onMounted, ref } from "vue";
import { message } from "@/utils/message";
import { addDialog } from "@/components/ReDialog";
import { PureTableBar } from "@/components/RePureTableBar";
import { useRenderIcon } from "@/components/ReIcon/src/hooks";
import Refresh from "@iconify-icons/ep/refresh";
import forms from "./form.vue";
import { getOperLogList } from "@/api/log";

const formRef = ref();
const tableRef = ref();
const selectedNum = ref(0);

/** 当CheckBox选择项发生变化时会触发该事件 */
function handleSelectionChange(val) {
  selectedNum.value = val.length;
}

/** 批量删除 */
async function onbatchDel(id = null) {}

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
    title: `${title}用户`,
    fullscreenIcon: true,
    sureBtnLoading: true,
    props: {
      formInline: {
        id: item?.id ?? "",
        avatar: item?.avatar ?? "",
        name: item?.name ?? "",
        username: item?.username ?? "",
        phone: item?.phone ?? "",
        email: item?.email ?? "",
        password: item?.password ?? "",
        sex: item?.sex ?? "",
        roles: item?.roles ?? []
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
  console.log("formRef.value===>>>: ", formRef.value);
  if (!formRef.value) return;
  formRef.value.resetFields();
};
const searchForm = ref({
  logDesc: null,
  requestPath: null,
  requestType: null,
  operateIp: null,
  operateUser: null,
  searchDate: null
});
const loadData = async (flag = 1) => {
  loading.value = true;
  let res;
  try {
    res = await getOperLogList({
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
    label: "ID",
    prop: "id",
    width: 100,
    fixed: "left"
  },
  {
    label: "日志描述",
    prop: "logDesc",
    slot: "logDesc",
    fixed: "left"
  },

  {
    label: "调用函数",
    prop: "methodName",
    width: 130
  },
  {
    label: "请求参数",
    prop: "requestParams",
    slot: "requestParams",
    width: 130
  },
  {
    label: "请求方式",
    prop: "requestType",
    slot: "requestType"
  },
  {
    label: "请求路径",
    prop: "requestPath",
    slot: "requestPath"
  },
  {
    label: "返回值",
    prop: "responseBody",
    slot: "responseBody",
    width: 130
  },
  {
    label: "错误信息",
    prop: "errorLog",
    slot: "errorLog",
    width: 130
  },
  {
    label: "请求IP",
    prop: "operateIp"
  },
  {
    label: "执行耗时（ms）",
    prop: "costTime",
    slot: "costTime"
  },
  {
    label: "创建时间",
    prop: "createTime"
  },
  {
    label: "操作用户",
    width: "150",
    prop: "operateUser",
    fixed: "right",
    slot: "operateUser"
  }
];

const shortcuts1 = [
  {
    text: "上周",
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
      return [start, end];
    }
  },
  {
    text: "上个月",
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
      return [start, end];
    }
  },
  {
    text: "三个月前",
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
      return [start, end];
    }
  }
];

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

const requestTypeOptions = [
  {
    label: "GET",
    value: "GET"
  },
  {
    label: "POST",
    value: "POST"
  },
  {
    label: "PUT",
    value: "PUT"
  },
  {
    label: "DELETE",
    value: "DELETE"
  }
];
const requestTypeTag = {
  GET: "success",
  POST: "primary",
  PUT: "warning",
  DELETE: "danger"
};
</script>

<template>
  <div>
    <div class="header">
      <el-form
        ref="formRef"
        class="search-form"
        :inline="true"
        :model="searchForm"
      >
        <el-form-item label="日志描述" prop="logDesc">
          <el-input
            v-model="searchForm.logDesc"
            clearable
            placeholder="请输入日志描述"
          />
        </el-form-item>
        <el-form-item label="请求地址" prop="requestPath">
          <el-input
            v-model="searchForm.requestPath"
            clearable
            placeholder="请输入请求地址"
          />
        </el-form-item>
        <el-form-item label="请求IP" prop="operateIp">
          <el-input
            v-model="searchForm.operateIp"
            clearable
            placeholder="请输入IP"
          />
        </el-form-item>
        <el-form-item label="操作用户" prop="operateUser">
          <el-input
            v-model="searchForm.operateUser"
            clearable
            placeholder="请输入用户ID"
          />
        </el-form-item>
        <el-form-item label="请求方式" prop="requestType">
          <el-select
            v-model="searchForm.requestType"
            style="width: 150px"
            clearable
            placeholder="请选择请求方式"
          >
            <el-option
              v-for="item in requestTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间" prop="searchDate">
          <el-date-picker
            v-model="searchForm.searchDate"
            type="datetimerange"
            range-separator="至"
            :shortcuts="shortcuts1"
            value-format="YYYY-MM-DD HH:mm:ss"
            start-placeholder="开始日期时间"
            end-placeholder="结束日期时间"
            :popper-options="{
              placement: 'bottom-start'
            }"
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
    <PureTableBar :columns="columns" title="日志管理" @refresh="loadData">
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
          <template #requestType="{ row }">
            <el-tag
              size="small"
              effect="dark"
              :type="requestTypeTag[row.requestType]"
            >
              {{ row.requestType }}
            </el-tag>
          </template>
          <template #requestParams="{ row }">
            <el-popover
              placement="bottom"
              title="请求参数"
              trigger="click"
              :width="400 + 'px'"
            >
              <template #reference>
                <el-button type="primary" size="small" class="m-2"
                  >查看
                </el-button>
              </template>

              <json-viewer
                copyable
                :expand-depth="1"
                :value="JSON.parse(row.requestParams)"
              />
            </el-popover>
          </template>
          <template #errorLog="{ row }">
            <el-popover
              v-if="!!row?.errorLog"
              placement="bottom"
              title="错误信息"
              trigger="click"
              :width="400 + 'px'"
            >
              <template #reference>
                <el-button plain type="danger" size="small" class="m-2"
                  >查看
                </el-button>
              </template>

              {{ row?.errorLog ?? "无" }}
            </el-popover>
            <div v-else>无</div>
          </template>
          <template #responseBody="{ row }">
            <el-popover
              placement="bottom"
              title="返回值"
              trigger="click"
              :width="400 + 'px'"
            >
              <template #reference>
                <el-button type="primary" size="small" class="m-2"
                  >查看
                </el-button>
              </template>

              <json-viewer
                copyable
                :width="400 + 'px'"
                :expand-depth="2"
                :value="JSON.parse(row.responseBody)"
              />
            </el-popover>
          </template>
          <template #costTime="{ row }">
            <el-tag :type="row.costTime > 100 ? 'danger' : 'success'">
              {{ row.costTime }}
            </el-tag>
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
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}

.search-form {
  :deep(.el-form-item) {
    margin-bottom: 12px;
  }
}
</style>
