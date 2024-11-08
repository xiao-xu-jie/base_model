<script setup lang="ts">
import "@wangeditor/editor/dist/css/style.css";
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import {
  onBeforeUnmount,
  ref,
  shallowRef,
  onMounted,
  defineProps,
  defineEmits
} from "vue";

const emit = defineEmits(["change"]);
const props = defineProps({
  content: {
    type: String,
    default: "默认内容"
  },
  id: {
    type: String,
    default: ""
  }
});
defineOptions({
  name: "BaseEditor"
});

const mode = "default";
// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef();

// 内容 HTML
const valueHtml = ref(props.content);

// 模拟 ajax 异步获取内容
onMounted(() => {
  setTimeout(() => {
    console.log("id===>>>: ", props.id);
  }, 1500);
});

const toolbarConfig: any = { excludeKeys: "fullScreen" };
const editorConfig = { placeholder: "请输入内容...", MENU_CONF: {} };

const handleCreated = editor => {
  // 记录 editor 实例，重要！

  editorRef.value = editor;
};

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value;
  if (editor == null) return;
  editor.destroy();
});
const onChange = e => {
  // console.log("html: ", e.getHtml());
  emit("change", e.getHtml());
};
editorConfig.MENU_CONF["uploadImage"] = {
  // 服务端上传地址，根据实际业务改写
  server: "/api/common/upload",
  // form-data 的 fieldName，根据实际业务改写
  fieldName: "file",
  // 选择文件时的类型限制，根据实际业务改写
  allowedFileTypes: ["image/png", "image/jpg", "image/jpeg"],
  // 自定义插入图片
  customInsert(res: any, insertFn) {
    // res.data.url是后端返回的图片地址，根据实际业务改写
    if (res.data) {
      // insertFn插入图片进编辑器
      insertFn(res.data);
    }
  }
};
</script>

<template>
  <div class="wangeditor">
    <Toolbar
      :editor="editorRef"
      :defaultConfig="toolbarConfig"
      :mode="mode"
      style="border-bottom: 1px solid #ccc"
    />
    <Editor
      v-model="valueHtml"
      :defaultConfig="editorConfig"
      :mode="mode"
      style="height: 500px; overflow-y: hidden"
      @onCreated="handleCreated"
      @onChange="onChange"
    />
  </div>
</template>
