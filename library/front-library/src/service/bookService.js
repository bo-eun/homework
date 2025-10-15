import api from "../api/axiosApi";

export const bookAPI = {
  list: async (page) => {
    const response = await api.get(`/api/v1/book?page=${page}`);
    return response.data.response;
  },

  publishingHouseList: async () => {
    const response = await api.get(`/api/v1/publishingHouse`);
    return response.data.response;
  },

  authorList: async () => {
    const response = await api.get(`/api/v1/author`);
    return response.data.response;
  },
};
