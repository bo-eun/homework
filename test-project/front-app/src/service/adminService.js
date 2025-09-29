import api from "../api/axiosApi";

export const adminAPI = {
  list: async (page, searchText, delYn) => {
    const response = await api.get(
      `/api/v1/admin/user?page=${page}&searchText=${searchText}&delYn=${delYn}`
    );
    return response.data;
  },

  get: async (brdId) => {
    const response = await api.get(`/api/v1/admin/user/${brdId}`);
    return response.data.response;
  },

  create: async (formData) => {
    const response = await api.post(`/api/v1/admin/user`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    return response.data.response;
  },

  update: async (formData) => {
    const response = await api.put(`/api/v1/admin/user`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    return response.data.response;
  },

  delete: async (userId) => {
    const response = await api.delete(`/api/v1/admin/user/${userId}`);
    return response.data.response;
  },
};
