import { create } from "zustand";
import { persist } from "zustand/middleware";
import { immer } from "zustand/middleware/immer";

export const authStore = create(
  persist(
    immer((set, get) => ({
      // 로컬스토리지에 저장할 내용들
      token: null,
      userId: null,
      userName: null,
      userRole: null,

      // 로그인 여부 판단 함수
      // 토큰 있으면 true, 없으면 false
      // 자기 내부 요소 부를 때 get() 메서드 사용
      // !! 부정의 부정. 강제적으로 boolean으로 형변환해줌
      isAuthenticated: () => !!get().token,

      // 권한 가져오기
      getUserRole: () => get().userRole,

      // 로그인 후 정보를 저장하는 함수
      setLogin: ({ token, userId, userName, userRole }) =>
        set((state) => {
          state.token = token;
          state.userId = userId;
          state.userName = userName;
          state.userRole = userRole;
        }),
      // 토큰만 갱신
      setToken: (token) =>
        set((state) => {
          state.token = token;
        }),

      // 토큰 주기
      getToken: () => get().token,

      // 로그아웃 시 정보 지우기
      clearAuth: () =>
        set((state) => {
          state.token = null;
          state.userId = null;
          state.userName = null;
          state.userRole = null;
        }),
    })),
    // 새로고침 시 기존 상태 없어지기 때문에
    // 원하는 내용을 localStorage에 저장
    {
      name: "auth-info",
      partialize: (state) => ({
        token: state.token,
        userId: state.userId,
        userName: state.userName,
        userRole: state.userRole,
      }),
    }
  )
);
