import { http } from "@/utils/http";

/** 获取用户列表 */
export const getCertList = (params?: object) => {
  return http.request<any>("get", "/api/bizCertification/selectPage", {
    params
  });
};

/** 获取用户所有用户 */
export const getAllCert = () => {
  return http.request<any>("get", "/api/bizCertification/list");
};
/** 新增用户用户 */
export const addCert = (data?: object) => {
  return http.request<any>("post", "/api/bizCertification/add", { data });
};

/** 更新用户用户 */
export const updateCert = (data?: object) => {
  return http.request<any>("put", "/api/bizCertification/update", { data });
};
/** 删除用户 */
export const deleteCert = (data?: object) => {
  return http.request<any>("delete", "/api/bizCertification/delete", { data });
};
