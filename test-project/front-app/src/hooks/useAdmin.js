import { useMutation, useQueryClient } from "@tanstack/react-query";
import { adminAPI } from "../service/adminService";

export const useAdmin = () => {
  const queryClient = useQueryClient();

  const createUser = useMutation({
    mutationFn: (formData) => adminAPI.create(formData),
    onSuccess: () => {
      console.log("등록 완료");
      queryClient.invalidateQueries({ queryKey: ["admin", 0] });
    },
    onError: (error) => {
      console.log(error);
    },
  });

  const updateUser = useMutation({
    mutationFn: (formData) => adminAPI.update(formData),
    onSuccess: () => {
      console.log("업데이트 완료");
      queryClient.invalidateQueries({ queryKey: ["admin", 0] });
    },
  });

  const deleteUser = useMutation({
    mutationFn: (brdId) => adminAPI.delete(brdId),
    onSuccess: () => {
      console.log("삭제 완료");
      queryClient.invalidateQueries({ queryKey: ["admin", 0] });
    },
  });

  return { updateUser, deleteUser, createUser };
};
