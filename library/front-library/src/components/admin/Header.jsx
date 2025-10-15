import React from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';

function Header(props) {
    return (
        <header>
            <Navbar expand="lg" className="bg-body-tertiary">
                <Container>
                    <Navbar.Brand href="/admin">관리자 페이지</Navbar.Brand>
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="/admin/book">도서목록</Nav.Link>
                            <Nav.Link href="/admin/book/add">도서등록</Nav.Link>
                            <Nav.Link href="/admin/user">회원관리</Nav.Link>
                            <Nav.Link href="/admin/policy">교환/환불/반품 설정</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                    <Nav>
                        <Nav.Link href="/">홈</Nav.Link>
                        <Nav.Link href="/logout">로그아웃</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>

        </header>
    );
}

export default Header;