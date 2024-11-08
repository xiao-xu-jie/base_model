import { http } from "@/utils/http";

/** 获取社区帖子表 */
export const getArticleList = (params?: object) => {
  return http.request<any>("get", "/api/bizArticle/selectPage", {
    params
  });
};
/** 获取所有通知 */
export const getAllArticle = () => {
  return http.request<any>("get", "/api/bizArticle/list");
};
/** 新增通知 */
export const addArticle = (data?: object) => {
  return http.request<any>("post", "/api/bizArticle/add", { data });
};

/** 更新通知 */
export const updateArticle = (data?: object) => {
  return http.request<any>("put", "/api/bizArticle/update", { data });
};
/** 删除通知 */
export const deleteArticle = (data?: object) => {
  return http.request<any>("delete", "/api/bizArticle/delete", {
    data
  });
};
