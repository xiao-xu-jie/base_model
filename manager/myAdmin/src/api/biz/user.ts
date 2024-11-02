import { http } from "@/utils/http";

/** 获取用户列表 */
export const getUserList = (params?: object) => {
  return http.request<any>("get", "/api/bizUser/selectPage", { params });
};

/** 获取用户所有用户 */
export const getAllUser = () => {
  return http.request<any>("get", "/api/bizUser/list");
};
/** 获取所有VIP */
export const getAllVip = () => {
  return http.request<any>("get", "/api/bizUser/getVipList");
};
/** 新增用户用户 */
export const addUser = (data?: object) => {
  return http.request<any>("post", "/api/bizUser/add", { data });
};

/** 更新用户用户 */
export const updateUser = (data?: object) => {
  return http.request<any>("put", "/api/bizUser/update", { data });
};
/** 更新用户用户会员 */
export const updateUserVip = (data?: object) => {
  return http.request<any>("put", "/api/bizUser/updateUserVip", { data });
};
/** 删除用户 */
export const deleteUser = (data?: object) => {
  return http.request<any>("delete", "/api/bizUser/delete", { data });
};
