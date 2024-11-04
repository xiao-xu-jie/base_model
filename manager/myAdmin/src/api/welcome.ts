import { http } from "@/utils/http";

/** 获取数据 */
export const getIndexData = () => {
  return http.request<any>("get", "/api/welcome/index");
};
