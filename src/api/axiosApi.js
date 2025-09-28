import axios from "axios";
import { authStore } from "../store/authStore";

// axios 커스텀, jwt 토큰을 기본적으로 전달하기 위해 커스텀함
const api = axios.create({
  headers: {
    "Content-Type": "application/json",
  },
});

// 리퀘스트 전에 인증토큰 있으면 헤더에 추가
api.interceptors.request.use((config) => {
  const token = authStore.getState().token;
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
