import { http } from "@/utils/http";

/** 获取订单列表 */
export const getbizOrderList = (params?: object) => {
  return http.request<any>("get", "/api/bizOrder/selectPage", {
    params
  });
};

/** 获取所有订单 */
export const getAllbizOrder = () => {
  return http.request<any>("get", "/api/bizOrder/list");
};
/** 新增订单 */
export const addbizOrder = (data?: object) => {
  return http.request<any>("post", "/api/bizOrder/add", { data });
};

/** 更新订单 */
export const updatebizOrder = (data?: object) => {
  return http.request<any>("put", "/api/bizOrder/update", { data });
};
/** 删除订单 */
export const deletebizOrder = (data?: object) => {
  return http.request<any>("delete", "/api/bizOrder/delete", { data });
};
