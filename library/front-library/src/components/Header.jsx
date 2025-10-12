import React from "react";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";

function Header(props) {
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
            <Nav.Link href="/login">로그인</Nav.Link>
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
