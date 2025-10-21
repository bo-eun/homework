import { create } from "zustand";
import { persist } from "zustand/middleware";
import { immer } from "zustand/middleware/immer";

export const orderStore = create(
  persist(
    immer((set, get) => ({
      // 장바구니 or 주문할 상품 정보
      orderItems: [],

      // 장바구니 or 주문할 상품 설정
      setOrderItems: (items) => set({ orderItems: items }),
      getOrderItems: () => get().orderItems,
      clearOrderItems: () => set({ orderItems: [] }),
    }))
  ),
  // 현재 주문 정보 로컬스토리지에 저장
  {
    name: "auth-info",
    partialize: (state) => ({
      orderItems: state.orderItems,
    }),
  }
);
