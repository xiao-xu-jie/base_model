import { http } from "@/utils/http";

/** 获取日志列表 */
export const getOperLogList = (params?: object) => {
  return http.request<any>("get", "/api/operLog/selectPage", { params });
};

/** 新增日志 */
export const addOperLog = (data?: object) => {
  return http.request<any>("post", "/api/operLog/add", { data });
};

/** 更新日志 */
export const updateOperLog = (data?: object) => {
  return http.request<any>("put", "/api/operLog/update", { data });
};
/** 删除日志 */
export const deleteOperLog = (data?: object) => {
  return http.request<any>("delete", "/api/operLog/delete", { data });
};
