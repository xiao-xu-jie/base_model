import { http } from "@/utils/http";

/** 获取角色列表 */
export const getRoleList = (params?: object) => {
  return http.request<any>("get", "/api/role/selectPage", { params });
};

/** 获取角色所有角色 */
export const getAllRole = () => {
  return http.request<any>("get", "/api/role/list");
};
/** 新增角色角色 */
export const addRole = (data?: object) => {
  return http.request<any>("post", "/api/role/add", { data });
};

/** 更新角色角色 */
export const updateRole = (data?: object) => {
  return http.request<any>("put", "/api/role/update", { data });
};
/** 删除角色 */
export const deleteRole = (data?: object) => {
  return http.request<any>("delete", "/api/role/delete", { data });
};
/** 获取角色所有路由 */
export const getRoleRouters = (params?: object) => {
  return http.request<any>("get", "/api/role/getRoutersByRoleId", {
    params
  });
};
/** 获取所有路由 */
export const getRouters = () => {
  return http.request<any>("get", "/api/role/getAllRouters");
};
