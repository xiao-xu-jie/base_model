import { http } from "@/utils/http";

/** 获取货源列表 */
export const getbizSourceStationList = (params?: object) => {
  return http.request<any>("get", "/api/bizSourceStation/selectPage", {
    params
  });
};

/** 获取所有货源 */
export const getAllbizSourceStation = () => {
  return http.request<any>("get", "/api/bizSourceStation/list");
};
/** 新增货源 */
export const addbizSourceStation = (data?: object) => {
  return http.request<any>("post", "/api/bizSourceStation/add", { data });
};

/** 更新货源 */
export const updatebizSourceStation = (data?: object) => {
  return http.request<any>("put", "/api/bizSourceStation/update", { data });
};
/** 删除货源 */
export const deletebizSourceStation = (data?: object) => {
  return http.request<any>("delete", "/api/bizSourceStation/delete", { data });
};
