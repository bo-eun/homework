import React from 'react';
import { Col, Container, Nav, Row } from 'react-bootstrap';
import "../../assets/css/mypage.css";
import { Outlet, useMatches } from 'react-router';

function MypageLayout() {
    const matches = useMatches();
    // 가장 마지막 일치하는 경로에서 handle.title 가져오기
    const title = matches[matches.length - 1]?.handle?.title || "마이페이지";

    const deleteUser = () => {
        const isDelete = confirm('회원을 탈퇴하시겠습니까?');

        if (isDelete) {
            alert('탈퇴완료!');
        }
    }

    return (
        <div className='mypage_main_section'>
            <h2 className='text-center mb-4'>{title}</h2>

            <Container>
                <Row>
                    {/* 왼쪽 사이드 메뉴 */}
                    <Col xs={2} className="vh-100 p-3">
                        <Nav className="mypage_menu flex-column">
                            <Nav.Link href="/mypage">마이페이지</Nav.Link>
                            <Nav.Link href="/mypage/orderList">주문목록</Nav.Link>
                            <Nav.Link href="/mypage/wishList">찜목록</Nav.Link>
                            <Nav.Link href="/mypage/user/update">회원정보수정</Nav.Link>
                            <Nav.Link onClick={deleteUser}>회원탈퇴</Nav.Link>
                        </Nav>
                    </Col>

                    {/* 메인 콘텐츠 영역 */}
                    <Col xs={10} className="p-4">
                        <Outlet />
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default MypageLayout;