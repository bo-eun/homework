import { useMutation, useQueryClient } from "@tanstack/react-query";
import { bookAPI } from "../service/bookService";

export const useBooks = () => {
  const queryClient = useQueryClient;

  const createBook = useMutation({});
};
