import React from "react";
import Container from "react-bootstrap/esm/Container";
import { Link } from "react-router";

function Login(props) {
  return (
    <Container className="w-25 vh-100">
      <h2>로그인</h2>
      <div className="login_box">
        <form action="" autoComplete="off">
          <div className="mt-5">
            <label htmlFor="id" className="form-label">
              아이디
            </label>
            <input type="text" id="id" name="id" className="form-control" />
          </div>

          <div className="mt-3">
            <label htmlFor="password" className="form-label">
              비밀번호
            </label>
            <input
              type="password"
              id="password"
              name="password"
              className="form-control"
            />
          </div>
          <div className="text-center mt-4">
            <button type="submit" className="btn btn-dark w-100 py-3">
              로그인
            </button>
            <Link className="btn border mt-2 py-3 w-100" to="/join">
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
