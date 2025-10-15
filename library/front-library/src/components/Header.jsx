import React from "react";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { authStore } from "../store/authStore";
import { NavLink, useNavigate } from "react-router";
import { NavDropdown } from "react-bootstrap";

function Header(props) {
  const { isAuthenticated, clearAuth, getUserRole } = authStore();
  const userName = authStore((state) => state.userName);
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
          <Navbar.Brand href="/">logo</Navbar.Brand>
          <Form className="d-flex me-auto my-2 w-50">
            <Form.Control
              type="search"
              placeholder="Search"
              className="me-2"
              aria-label="Search"
            />
            <Button variant="outline-success">Search</Button>
          </Form>
          <Nav>
            <Nav.Link href="#home">장바구니</Nav.Link>
            {isAuthenticated() && getUserRole() === "ROLE_ADMIN" && (
              <Nav.Link as={NavLink} to="/admin">
                관리자 페이지
              </Nav.Link>
            )}
            <Navbar.Collapse className="justify-content-end">
              {isAuthenticated() ? (
                <>
                  <NavDropdown
                    title={`${userName} 님`}
                    id="navbarScrollDropdown"
                  >
                    <NavDropdown.Item href="/mypage">회원정보</NavDropdown.Item>
                    <NavDropdown.Item onClick={handleLogout}>
                      로그아웃
                    </NavDropdown.Item>
                  </NavDropdown>
                </>
              ) : (
                <Nav.Link as={NavLink} to="/login">
                  로그인
                </Nav.Link>
              )}
            </Navbar.Collapse>
          </Nav>
        </Container>
      </Navbar>
      <Container>
        <Nav defaultActiveKey="/home" as="ul">
          <Nav.Item as="li">
            <Nav.Link href="/home">베스트</Nav.Link>
          </Nav.Item>
          <Nav.Item as="li">
            <Nav.Link eventKey="link-1">신상품</Nav.Link>
          </Nav.Item>
          <Nav.Item as="li">
            <Nav.Link eventKey="link-2">국내도서</Nav.Link>
          </Nav.Item>
          <Nav.Item as="li">
            <Nav.Link eventKey="link-2">외국도서</Nav.Link>
          </Nav.Item>
          <Nav.Item as="li">
            <Nav.Link eventKey="link-2">이벤트</Nav.Link>
          </Nav.Item>
        </Nav>
      </Container>
    </header>
  );
}

export default Header;
