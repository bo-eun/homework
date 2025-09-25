import { useMutation, useQueryClient } from "@tanstack/react-query";
import { authStore } from "../store/authStore";
import api from "../api/axiosApi";
import { useNavigate } from "react-router";

export const useLogin = () => {
  const { setLogin } = authStore();
  // 캐시된 데이터를 직접 조작하거나 변경할 때 사용
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  // 데이터를 변경할 떄 useMutation 사용
  // api 요청 성공 시 onSuccess, 실패 시 onError 함수가 실행된다.
  return useMutation({
    // 사용자 정보 인자로 받아 실행
    mutationFn: async (credentials) => {
      try {
        const response = await api.post("/api/v1/login", credentials, {
          // 로그인은 form방식으로 보내야 함...
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
        });

        return response.data; // onSuccess로 리턴값 전달
      } catch (error) {
        // try catch문이 없어도 실패 시 onError 가 실행되지만,
        // onError에 전달한 에러를 커스텀하기 위해 catch문을 사용한다.
        throw error.response?.data || error;
      }
    },
    // mutationFn이 실행 성공하면 실행되는 함수
    onSuccess: (data) => {
      console.log(data);
      // users캐시가 있을 때 저장된걸 지우고 새 데이터 받아오기
      queryClient.invalidateQueries({ queryKey: ["users"] });
      // 토큰 저장
      setLogin(data.content);
      navigate("/board");
    },
    onError: (error) => {
      console.error("Login 실패", error);
    },
  });
};
