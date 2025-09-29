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

let isRefreshing = false;

// 응답내용 가로채기
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const { response, config } = error;
    console.log(response);
    if (response?.status === 401) {
      console.log("로그인 실패");
      // 로그인 실패 시 기존 localstorage 삭제
      authStore.getState().clearAuth();
    }

    if (response?.status === 406 && !config._retry) {
      // alert("유효하지 않은 토큰입니다. 다시 로그인하세요");
      // authStore.getState().clearAuth();
      // location.href = "/login";

      if (!isRefreshing) {
        isRefreshing = true;
        config._retry = true;
      }

      try {
        const res = await axios.get("/api/v1/refresh", {
          withCredentials: true,
        });
        authStore.getState().setLogin(res.data.response.content);

        const token = authStore.getState().token;
        config.headers.Authorization = `Bearer ${token}`;
        return api(config);
      } catch (error) {
        // refresh 실패
        alert("유효하지 않은 토큰입니다. 다시 로그인하세요");
        authStore.getState().clearAuth();
        location.href = "/login";
      } finally {
        isRefreshing = false;
      }
    }

    return Promise.reject(error);
  }
);

export default api;
