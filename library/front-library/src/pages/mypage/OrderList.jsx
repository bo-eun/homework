import React from 'react';

function OrderList(props) {
    return (
        <div className="order_list">
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
    );
}

export default OrderList;