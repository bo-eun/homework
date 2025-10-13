import React from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';

function Header(props) {
    return (
        <header>
            <Navbar expand="lg" className="bg-body-tertiary">
                <Container>
                    <Navbar.Brand href="/admin">관리자</Navbar.Brand>
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