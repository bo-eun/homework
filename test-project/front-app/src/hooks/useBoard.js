import { useMutation, useQueryClient } from "@tanstack/react-query";
import { boardAPI } from "../service/boardService";

export const useBoard = () => {
  const queryClient = useQueryClient();

  const deleteFile = useMutation({
    mutationFn: (bfId) => boardAPI.deleteFile(bfId),
  });

  const createBoard = useMutation({
    mutationFn: (formData) => boardAPI.create(formData),
    onSuccess: () => {
      console.log("등록 완료");
      queryClient.invalidateQueries({ queryKey: ["board", 0] });
    },
  });

  const updateBoard = useMutation({
    mutationFn: (formData) => boardAPI.update(formData),
    onSuccess: () => {
      console.log("업데이트 완료");
      queryClient.invalidateQueries({ queryKey: ["board", 0] });
    },
  });

  const deleteBoard = useMutation({
    mutationFn: (brdId) => boardAPI.delete(brdId),
    onSuccess: () => {
      console.log("삭제 완료");
      queryClient.invalidateQueries({ queryKey: ["board", 0] });
    },
  });

  return { deleteFile, updateBoard, deleteBoard, createBoard };
};
