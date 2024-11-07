import { http } from "@/utils/http";

/** 获取社区类型表 */
export const getSliderList = (params?: object) => {
  return http.request<any>("get", "/api/bizSlider/selectPage", {
    params
  });
};

/** 新增用户用户 */
export const addSlider = (data?: object) => {
  return http.request<any>("post", "/api/bizSlider/add", { data });
};

/** 更新用户用户 */
export const updateSlider = (data?: object) => {
  return http.request<any>("put", "/api/bizSlider/update", { data });
};
/** 删除用户 */
export const deleteSlider = (data?: object) => {
  return http.request<any>("delete", "/api/bizSlider/delete", {
    data
  });
};
