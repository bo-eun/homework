import React from "react";
import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import Container from "react-bootstrap/esm/Container";
import { Link } from "react-router";
import { useLogin } from "../../hooks/useLogin";

const schema = yup.object({
  username: yup.string().required("아이디를 입력하세요"),
  password: yup.string().required("비밀번호를 입력하세요"),
});

function Login(props) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const { loginMutation } = useLogin();

  const goLogin = async (formData) => {
    try {
      // mutation 실행 -> onSuccess(또는 onError) -> 호출한 곳
      await loginMutation.mutate(formData);
      console.log(formData);
    } catch (error) {
      if (error.status === 401) {
        alert("아이디 또는 패스워드가 일치하지 않습니다.");
      } else if (error.status === 400) {
        alert("입력한 정보가 잘못되었습니다.");
      } else {
        alert("처리 중 오류가 발생했습니다. 다시 시도해 주세요");
      }
    }
  };

  return (
    <Container className="vh-100" style={{ maxWidth: "500px" }}>
      <h2 className="text-center">로그인</h2>
      <div className="login_box">
        <form action="" onSubmit={handleSubmit(goLogin)} autoComplete="off">
          <div className="mt-5">
            <label htmlFor="id" className="form-label">
              아이디
            </label>
            <input
              type="text"
              className={`form-control ${errors.username ? "is-invalid" : ""}`}
              id="username"
              {...register("username")}
            />
            {errors.username && (
              <div className="invalid-feedback">{errors.username.message}</div>
            )}
          </div>

          <div className="mt-3">
            <label htmlFor="password" className="form-label">
              비밀번호
            </label>
            <input
              type="password"
              className={`form-control ${errors.password ? "is-invalid" : ""}`}
              id="password"
              {...register("password")}
            />
            {errors.password && (
              <div className="invalid-feedback">{errors.password.message}</div>
            )}
          </div>
          <div className="text-center mt-4">
            <button type="submit" className="btn btn-dark btn-lg w-100 py-3">
              로그인
            </button>
            <Link className="btn border btn-lg mt-2 py-3 w-100" to="/join">
              회원가입
            </Link>
            <div className="d-flex gap-2 justify-content-center mt-3">
              <Link to="/findId">아이디찾기</Link>
              <span className="border"></span>
              <Link to="/findPw">비밀번호찾기</Link>
            </div>
          </div>
        </form>
      </div>
    </Container>
  );
}

export default Login;
