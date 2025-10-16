import React from 'react';
import { Container } from 'react-bootstrap';

function WishList(props) {
    return (
        <div className='wishlist_section'>
            <Container>
                <ul className='book_list'>
                    <li className='d-flex gap-4'>
                        <span className='close_btn'>x</span>
                        <div className="img_box">
                            <img src="" alt="" />
                        </div>
                        <div className="info_box d-flex flex-column">
                            <p className="title">제목</p>
                            <p className="author">저자</p>
                            <p className="price">가격</p>
                        </div>
                        <div className="btn_box ms-auto d-flex justify-content-center flex-column">
                            <button type='button' className='btn btn-outline-dark w-100 px-5'>장바구니</button>
                            <button type='button' className='btn btn-outline-dark w-100 px-5 mt-1'>구매하기</button>
                        </div>
                    </li>
                </ul>
            </Container>
        </div>
    );
}

export default WishList;