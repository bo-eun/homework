import { useMutation, useQueryClient } from "@tanstack/react-query";
import { bookAPI } from "../service/bookService";

export const useBooks = () => {
  const queryClient = useQueryClient;

  const createBook = useMutation({
    mutationFn: (formData) => bookAPI.create(formData),
    onSuccess: () => {
      console.log("등록 완료");
      queryClient.invalidateQueries({ queryKey: ["book", 0] });
    },
  });

  const updateBook = useMutation({
    mutationFn: (formData) => bookAPI.update(formData),
    onSuccess: () => {
      console.log("업데이트 완료");
      queryClient.invalidateQueries({ queryKey: ["book", 0] });
    },
  });

  const deleteBook = useMutation({
    mutationFn: (brdId) => bookAPI.delete(brdId),
    onSuccess: () => {
      console.log("삭제 완료");
      queryClient.invalidateQueries({ queryKey: ["book", 0] });
    },
  });

  return { createBook, updateBook, deleteBook };
};
