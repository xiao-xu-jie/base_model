import { http } from "@/utils/http";

/** 获取分类列表*/
export const getbizCategoryList = (params?: object) => {
  return http.request<any>("get", "/api/bizCategory/selectPage", { params });
};

/** 获取所有分类 */
export const getAllbizCategory = () => {
  return http.request<any>("get", "/api/bizCategory/list");
};
/** 新增分类 */
export const addbizCategory = (data?: object) => {
  return http.request<any>("post", "/api/bizCategory/add", { data });
};

/** 更新分类 */
export const updatebizCategory = (data?: object) => {
  return http.request<any>("put", "/api/bizCategory/update", { data });
};
/** 删除分类 */
export const deletebizCategory = (data?: object) => {
  return http.request<any>("delete", "/api/bizCategory/delete", { data });
};
