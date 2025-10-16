import React from "react";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";

function Footer(props) {
  return (
    <footer className="bg-body-tertiary pt-3 pb-4">
      <Container>
        <Navbar.Brand href="/">잇북</Navbar.Brand>
        <ul className="d-flex gap-3 mt-3 mb-4">
          <li>
            <a href="#">회사소개</a>
          </li>
          <li className="border"></li>
          <li>
            <a href="#">이용약관</a>
          </li>
          <li className="border"></li>
          <li>
            <a href="#">개인정보처리방침</a>
          </li>
          <li className="border"></li>
          <li>
            <a href="#">협력사여러분</a>
          </li>
        </ul>

        <dl>
          <dt>대표이사 : </dt>
          <dd>허정도 | 서울특별시 종로구 종로 1 |&nbsp;</dd>
          <dt>사업자등록번호 : </dt>
          <dd>102-81-11670</dd>
        </dl>
        <dl>
          <dt>대표전화 : </dt>
          <dd>1544-1900(발신자 부담전화) | </dd>
          <dt>FAX : </dt>
          <dd>0502-987-5711(지역번호 공통) |&nbsp;</dd>
          <dt>서울특별시 통신판매업신고번호 : </dt>
          <dd>제 653호</dd>
        </dl>
      </Container>
    </footer>
  );
}

export default Footer;
