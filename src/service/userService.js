import api from "../api/axiosApi";

export const userAPI = {
  list: async (page) => {
    const response = await api.get(`/api/v1/user?page=${page}`);
    return response.data.response;
  },

  get: async (brdId) => {
    const response = await api.get(`/api/v1/user/${brdId}`);
    return response.data.response;
  },

  create: async (formData) => {
    const response = await api.post(`/api/v1/user`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    return response.data.response;
  },

  update: async (formData) => {
    const response = await api.put(`/api/v1/user`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    return response.data.response;
  },

  delete: async (userId) => {
    const response = await api.delete(`/api/v1/user/${userId}`);
    return response.data.response;
  },
};
