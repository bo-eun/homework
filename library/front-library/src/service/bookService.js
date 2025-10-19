import api from "../api/axiosApi";

export const bookAPI = {
  /* 추천 리스트 */
  recommendList: async (page) => {
    const response = await api.get(`/api/v1/book/recommend`);
    return response.data.response;
  },

  /* 베스트 리스트 */
  bestList: async (page) => {
    const response = await api.get(`/api/v1/book/bestList?page=${page}`);
    return response.data.response;
  },

  /* 신상품 리스트 */
  newList: async (page) => {
    const response = await api.get(`/api/v1/book/newList?page=${page}`);
    return response.data.response;
  },

  /* 국내도서 리스트 */
  domesticList: async (page) => {
    const response = await api.get(`/api/v1/book/domesticList?page=${page}`);
    return response.data.response;
  },

  /* 해외도서 리스트 */
  foreignList: async (page) => {
    const response = await api.get(`/api/v1/book/foreignList?page=${page}`);
    return response.data.response;
  },

  detail: async (bookId) => {
    const response = await api.get(`/api/v1/book/${bookId}`);
    return response.data.response;
  },
};
