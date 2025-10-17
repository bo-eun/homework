import React from 'react';
import { Col, Container, Nav, Row } from 'react-bootstrap';
import "../../assets/css/mypage.css";
import MypageLayout from './MypageLayout';


function Mypage(props) {
    return (
        <div className='mypage_main_section'>
            <div className="my_info_box d-flex align-items-center">
                <div className="text_box">
                    ??님<br />안녕하세요
                </div>
                <ul className='card_list'>
                    <li>
                        <p>찜한 상품</p>
                        <strong>10개</strong>
                    </li>
                    <li>
                        <p>캐시</p>
                        <strong>2,000원</strong>
                    </li>
                </ul>
            </div>


            <div className="order_list">
                <h3>최근 주문목록</h3>
                <div className='order'>
                    <h4>2025.10.11 주문내역</h4>
                    <ul className='book_list'>
                        <li className='d-flex gap-4'>
                            <div className="img_box">
                                <img src="" alt="" />
                            </div>
                            <div className="info_box d-flex flex-column">
                                <p className="title">제목</p>
                                <p className="author">저자</p>
                                <p className="price">가격</p>
                            </div>
                        </li>
                        <li className='d-flex gap-4'>
                            <div className="img_box">
                                <img src="" alt="" />
                            </div>
                            <div className="info_box d-flex flex-column">
                                <p className="title">제목</p>
                                <p className="author">저자</p>
                                <p className="price">가격</p>
                            </div>
                        </li>
                        <li className='d-flex gap-4'>
                            <div className="img_box">
                                <img src="" alt="" />
                            </div>
                            <div className="info_box d-flex flex-column">
                                <p className="title">제목</p>
                                <p className="author">저자</p>
                                <p className="price">가격</p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default Mypage;