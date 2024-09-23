import { http } from "@/utils/http";

type Result = {
  success: boolean;
  message: string;
  data: Array<any>;
};

export const getAsyncRoutes = () => {
  return http.request<Result>("get", "/api/common/getSyncRouter");
};

/** 获取路由列表 */
export const getRoutersList = (params?: object) => {
  return http.request<any>("get", "/api/routers/selectPage", { params });
};

/** 获取路由所有路由 */
export const getRouters = (params?: object) => {
  return http.request<any>("get", "/api/routers/getRoutersRouters", { params });
};
/** 新增路由路由 */
export const addRouters = (data?: object) => {
  return http.request<any>("post", "/api/routers/add", { data });
};

/** 更新路由路由 */
export const updateRouters = (data?: object) => {
  return http.request<any>("put", "/api/routers/update", { data });
};
/** 删除路由 */
export const deleteRouters = (data?: object) => {
  return http.request<any>("delete", "/api/routers/delete", { data });
};

/** 获取路由子节点 */
export const getRoutersChildren = (params?: object) => {
  return http.request<any>("get", "/api/routers/getRoutersChildren", {
    params
  });
};

/** 获取顶部菜单 */
export const getTopRouters = (params?: object) => {
  return http.request<any>("get", "/api/routers/getTopRouters", { params });
};
