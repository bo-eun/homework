import api from "../api/axiosApi";

export const adminAPI = {
  /* 전체 리스트 */
  list: async (page) => {
    const response = await api.get(`/api/v1/admin/book?page=${page}`);
    return response.data.response;
  },

  /* 전체 출판사 */
  publishingHouseList: async () => {
    const response = await api.get(`/api/v1/admin/publishingHouse`);
    return response.data.response;
  },

  /* 전체 작가 */
  authorList: async () => {
    const response = await api.get(`/api/v1/admin/author`);
    return response.data.response;
  },

  /* 전체 리스트 */
  userList: async (page) => {
    const response = await api.get(`/api/v1/admin/users?page=${page}`);
    return response.data.response;
  },
};
