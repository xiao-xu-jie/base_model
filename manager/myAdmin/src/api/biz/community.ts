import { http } from "@/utils/http";

/** 获取社区帖子表 */
export const getCommunityList = (params?: object) => {
  return http.request<any>("get", "/api/bizCommunityPost/selectPage", {
    params
  });
};
/** 获取用户所有用户 */
export const getAllCommunity = () => {
  return http.request<any>("get", "/api/bizCommunityPost/list");
};
/** 新增用户用户 */
export const addCommunity = (data?: object) => {
  return http.request<any>("post", "/api/bizCommunityPost/add", { data });
};

/** 更新用户用户 */
export const updateCommunity = (data?: object) => {
  return http.request<any>("put", "/api/bizCommunityPost/update", { data });
};
/** 删除用户 */
export const deleteCommunity = (data?: object) => {
  return http.request<any>("delete", "/api/bizCommunityPost/delete", {
    data
  });
};
