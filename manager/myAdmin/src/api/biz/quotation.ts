import { http } from "@/utils/http";

/** 获取报价列表 */
export const getQuotationList = (params?: object) => {
  return http.request<any>("get", "/api/bizEggQuotation/selectPage", {
    params
  });
};

/** 获取报价所有报价 */
export const getAllQuotation = () => {
  return http.request<any>("get", "/api/bizEggQuotation/list");
};
/** 新增报价报价 */
export const addQuotation = (data?: object) => {
  return http.request<any>("post", "/api/bizEggQuotation/add", { data });
};

/** 更新报价报价 */
export const updateQuotation = (data?: object) => {
  return http.request<any>("put", "/api/bizEggQuotation/update", { data });
};
/** 删除报价 */
export const deleteQuotation = (data?: object) => {
  return http.request<any>("delete", "/api/bizEggQuotation/delete", { data });
};
