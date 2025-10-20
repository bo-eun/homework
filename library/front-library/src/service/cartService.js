import api from "../api/axiosApi";

export const cartAPI = {
  /* 전체 리스트 */
  list: async (userId) => {
    const response = await api.get(`/api/v1/cart/${userId}`);
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
    console.log(response);
    return response.data.response;
  },

  /* 작가 상세 */
  authorDetail: async (authorId) => {
    const response = await api.get(`/api/v1/admin/author/${authorId}`);
    return response.data.response;
  },

  /* 전체 리스트 */
  userList: async (page) => {
    const response = await api.get(`/api/v1/admin/users?page=${page}`);
    return response.data.response;
  },

  /* 교환/환불/반품 정책 삭제 */
  deletePolicy: async (policyId) => {
    try {
      const response = await api.delete(`/api/v1/admin/policy/${policyId}`);
      return response;
    } catch (error) {
      throw error.response?.data || error;
    }
  },
  /* 교환/환불/반품 정책 가져오기 */
  getPolicy: async () => {
    const response = await api.get(`/api/v1/admin/policy`);
    return response.data.response.content;
  },
};
