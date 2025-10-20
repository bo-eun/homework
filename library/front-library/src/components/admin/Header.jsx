import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { authStore } from "../../store/authStore";
import { useNavigate } from "react-router";

function Header(props) {
  const { isAuthenticated, clearAuth, getUserRole } = authStore();
  const navigate = useNavigate();

  const handleLogout = () => {
    clearAuth();
    localStorage.removeItem("auth-info");
    navigate("/login");
  };

  return (
    <header>
      <Navbar expand="lg" className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href="/admin">관리자 페이지</Navbar.Brand>
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="/admin">도서목록</Nav.Link>
              <Nav.Link href="/admin/book/add">도서등록</Nav.Link>
              <Nav.Link href="/admin/user">회원관리</Nav.Link>
              <Nav.Link href="/admin/policy">교환/환불/반품 설정</Nav.Link>
            </Nav>
          </Navbar.Collapse>
          <Nav>
            <Nav.Link href="/">홈</Nav.Link>
            <Nav.Link href="#" onClick={handleLogout}>로그아웃</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </header>
  );
}

export default Header;
