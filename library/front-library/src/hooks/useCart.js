import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useNavigate } from "react-router";
import api from "../api/axiosApi";

export const useCart = () => {
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  const createMutation = useMutation({
    mutationFn: async (data) => {
      try {
        const response = await api.post("/api/v1/cart", data);

        return response.data;
      } catch (error) {
        throw error.response?.data || error;
      }
    },

    onSuccess: () => {
      console.log("장바구니 담기 완료");
      alert("상품이 장바구니에 담겼습니다.");
      queryClient.invalidateQueries({ queryKey: ["cart"] });
    },
    onError: (error) => {
      console.error("장바구니 담기 실패", error);
    },
  });

  const updateMutation = useMutation({
    mutationFn: async (cartId, quantity) => {
      try {
        const response = await api.put(`/api/v1/cart/${cartId}`, quantity);

        return response.data;
      } catch (error) {
        throw error.response?.data || error;
      }
    },
    onSuccess: (data) => {
      console.log("장바구니 수정 완료");
      queryClient.invalidateQueries({ queryKey: ["cart"] });
    },
    onError: (error) => {
      console.error("장바구니 수정 실패", error);
    },
  });

  const deleteMutation = useMutation({
    mutationFn: async (cartId) => {
      try {
        const response = await api.delete(`/api/v1/cart/${cartId}`);

        return response.data;
      } catch (error) {
        throw error.response?.data || error;
      }
    },
    onSuccess: () => {
      console.log("삭제 완료");
      queryClient.invalidateQueries({ queryKey: ["cart"] });
      navigate("/admin");
    },
    onError: (error) => {
      console.error("삭제 실패", error);
    },
  });

  return {
    createMutation,
    updateMutation,
    deleteMutation,
  };
};
