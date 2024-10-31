import { http } from "@/utils/http";

/** 获取社区类型表 */
export const getCommunityTypeList = (params?: object) => {
  return http.request<any>("get", "/api/bizCommunityPostType/selectPage", {
    params
  });
};

/** 获取用户所有用户 */
export const getAllCommunityType = () => {
  return http.request<any>("get", "/api/bizCommunityPostType/list");
};
/** 新增用户用户 */
export const addCommunityType = (data?: object) => {
  return http.request<any>("post", "/api/bizCommunityPostType/add", { data });
};

/** 更新用户用户 */
export const updateCommunityType = (data?: object) => {
  return http.request<any>("put", "/api/bizCommunityPostType/update", { data });
};
/** 删除用户 */
export const deleteCommunityType = (data?: object) => {
  return http.request<any>("delete", "/api/bizCommunityPostType/delete", {
    data
  });
};
