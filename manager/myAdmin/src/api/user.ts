import { http } from "@/utils/http";

export type UserResult = {
  success: boolean;
  message: string;
  data: {
    /** 头像 */
    avatar: string;
    /** 用户名 */
    username: string;
    /** 昵称 */
    nickname: string;
    /** 当前登录用户的角色 */
    roles: Array<string>;
    /** 按钮级别权限 */
    permissions: Array<string>;
    /** `token` */
    accessToken: string;
    /** 用于调用刷新`accessToken`的接口时所需的`token` */
    refreshToken: string;
    /** `accessToken`的过期时间（格式'xxxx/xx/xx xx:xx:xx'） */
    expires: Date;
  };
};

export type RefreshTokenResult = {
  success: boolean;
  data: {
    /** `token` */
    accessToken: string;
    /** 用于调用刷新`accessToken`的接口时所需的`token` */
    refreshToken: string;
    /** `accessToken`的过期时间（格式'xxxx/xx/xx xx:xx:xx'） */
    expires: Date;
  };
};

/** 登录 */
export const getLogin = (data?: object) => {
  return http.request<UserResult>("post", "/api/common/login", { data });
};

/** 刷新`token` */
export const refreshTokenApi = (data?: object) => {
  return http.request<RefreshTokenResult>("post", "/refresh-token", { data });
};

/** 获取用户列表 */
export const getUserList = (params?: object) => {
  return http.request<any>("get", "/api/user/selectPage", { params });
};

/** 获取用户所有角色 */
export const getUserRoles = (params?: object) => {
  return http.request<any>("get", "/api/user/getUserRole", { params });
};
/** 新增用户角色 */
export const addUser = (data?: object) => {
  return http.request<any>("post", "/api/user/add", { data });
};

/** 更新用户角色 */
export const updateUserRoles = (data?: object) => {
  return http.request<any>("put", "/api/user/update", { data });
};
/** 删除用户 */
export const deleteUser = (data?: object) => {
  return http.request<any>("delete", "/api/user/delete", { data });
};
