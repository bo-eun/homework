import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useNavigate } from "react-router";
import api from "../api/axiosApi";

export const useOrder = () => {
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  const createMutation = useMutation({
    mutationFn: async (data) => {
      try {
        const response = await api.post("/api/v1/order", data);

        return response.data;
      } catch (error) {
        throw error.response?.data || error;
      }
    },

    onSuccess: () => {
      console.log("주문 완료");
      alert("주문이 완료되었습니다. 주문 정보는 마이페이지에서 확인해주세요.");
      queryClient.invalidateQueries({ queryKey: ["order"] });
      navigate("/mypage");
    },
    onError: (error) => {
      console.error("주문 실패", error);
    },
  });

  return {
    createMutation,
  };
};
