import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useNavigate } from "react-router";
import api from "../api/axiosApi";

export const useAdmin = () => {
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  const createMutation = useMutation({
    mutationFn: async (formData) => {
      try {
        const response = await api.post("/api/v1/admin/book", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        });

        return response.data;
      } catch (error) {
        throw error.response?.data || error;
      }
    },

    onSuccess: () => {
      console.log("등록 완료");
      queryClient.invalidateQueries({ queryKey: ["book"] });
      navigate("/admin");
    },
    onError: (error) => {
      console.error("등록 실패", error);
    },
  });

  const updateMutation = useMutation({
    mutationFn: async (formData) => {
      try {
        const response = await api.put("/api/v1/admin/book", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        });

        return response.data;
      } catch (error) {
        throw error.response?.data || error;
      }
    },
    onSuccess: (data) => {
      console.log("책 수정 완료");
      queryClient.invalidateQueries({ queryKey: ["book"] });
      navigate("/admin");
    },
    onError: (error) => {
      console.error("수정 실패", error);
    },
  });

  const deleteMutation = useMutation({
    mutationFn: async (bookId) => {
      try {
        const response = await api.delete(`/api/v1/admin/book/${bookId}`);

        return response.data;
      } catch (error) {
        throw error.response?.data || error;
      }
    },
    onSuccess: () => {
      console.log("삭제 완료");
      queryClient.invalidateQueries({ queryKey: ["book"] });
      navigate("/admin");
    },
    onError: (error) => {
      console.error("삭제 실패", error);
    },
  });

  const createPolicyMutation = useMutation({
    mutationFn: async (formData) => {
      try {
        const response = await api.post(`/api/v1/admin/policy`, formData);
        return response.data;
      } catch (error) {
        throw error.response?.data || error;
      }
    },
    onSuccess: () => {
      console.log("등록/수정 완료");
      queryClient.invalidateQueries({ queryKey: ["policy"] });
      // navigate("/admin/policy");
    },
    onError: (error) => {
      console.error("등록/수정 실패", error);
    },
  });

  return {
    createMutation,
    updateMutation,
    deleteMutation,
    createPolicyMutation,
  };
};
