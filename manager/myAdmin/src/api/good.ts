import { http } from "@/utils/http";

/** 获取商品列表 */
export const getbizGoodList = (params?: object) => {
  return http.request<any>("get", "/api/bizGood/selectPage", { params });
};

/** 获取所有商品 */
export const getAllbizGood = () => {
  return http.request<any>("get", "/api/bizGood/list");
};
/** 新增商品 */
export const addbizGood = (data?: object) => {
  return http.request<any>("post", "/api/bizGood/add", { data });
};

/** 更新商品 */
export const updatebizGood = (data?: object) => {
  return http.request<any>("put", "/api/bizGood/update", { data });
};
/** 删除商品 */
export const deletebizGood = (data?: object) => {
  return http.request<any>("delete", "/api/bizGood/delete", { data });
};
