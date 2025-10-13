import api from "../api/axiosApi";

export const bookAPI = {
  list: async (page) => {
    const response = await api.get(`/api/v1/books?page=${page}`);
    return response.data.response;
  },
};
