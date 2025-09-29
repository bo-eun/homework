import { useMutation, useQueryClient } from "@tanstack/react-query";
import { authStore } from "../store/authStore";
import api from "../api/axiosApi";
import { useNavigate } from "react-router";

export const useLogin = () => {
  const { setLogin } = authStore();
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  return useMutation({
    mutationFn: async (userInfo) => {
      try {
        const response = await api.post("/api/v1/login", userInfo, {
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
        });

        return response.data;
      } catch (error) {
        throw error.response?.data || error;
      }
    },

    onSuccess: (data) => {
      console.log(data);
      queryClient.invalidateQueries({ queryKey: ["users"] });

      setLogin(data.content);
      navigate("/board");
    },

    onError: (error) => {
      console.error("login 실패", error);
    },
  });
};
