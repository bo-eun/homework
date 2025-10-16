import React from 'react';
import { Container, Table } from 'react-bootstrap';
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "../../assets/css/detail.css"

function Detail(props) {
    return (
        <div className='detail_cont'>
            <Container>
                <div className="detail_box d-lg-grid d-block">
                    <div className="left_box d-flex align-items-center">
                        <div className="img_box">
                            <img />
                        </div>
                    </div>
                    <div className="right_box">
                        <div className="info_box">
                            <p className="title mt-lg-0 mt-3">도서명</p>
                            <p className="writer">저자 | 출판사</p>
                            <p className="stars">★★★★★(50)</p>
                        </div>
                        <div className="price_box border-top pt-3 mt-3">
                            <div className="option_box d-flex justify-content-between">
                                <div className="counter">
                                    <button>-</button>
                                    <input type="number" defaultValue="1" className='form-range' />
                                    <button>+</button>
                                </div>
                                <strong>가격</strong>
                            </div>
                            <div className="btn_box mt-3 d-flex gap-2">
                                <button type="button" className='btn btn-outline-dark btn-lg w-100'>장바구니</button>
                                <button type="button" className='btn btn-dark btn-lg w-100'>바로구매</button>
                            </div>
                        </div>
                    </div>
                </div>
            </Container>

            <div className="detail_menu mt-5">
                <Container>
                    <ul className="d-flex">
                        <li>상품정보</li>
                        <li>도서소개</li>
                        <li>리뷰</li>
                        <li>교환/환불/반품</li>
                    </ul>
                </Container>
            </div>
            <Container className='mt-3 mb-5'>
                <div className="detail_info_cont d-lg-grid d-block position-relative">
                    <div className=''>
                        <div className="detail_info_box">
                            <h3>상품정보</h3>
                            <Table bordered className='detail_table'>
                                <tbody>
                                    <tr>
                                        <th>쪽수</th>
                                        <td>2</td>
                                    </tr>
                                    <tr>
                                        <th>출판사</th>
                                        <td>나무나무나무</td>
                                    </tr>
                                    <tr>
                                        <th>저자</th>
                                        <td>김ㅇㅇ</td>
                                    </tr>
                                    <tr>
                                        <th>가격</th>
                                        <td>10,000원</td>
                                    </tr>
                                </tbody>
                            </Table>

                        </div>
                        <div className="detail_info_box w-100">
                            <h3>도서 소개</h3>
                            <div className=''>
                                짧은 문구
                            </div>

                            <div className='mt-3'>
                                긴 문구

                            </div>

                            <h3>작가 정보</h3>
                            <div className="author_info_box">
                                <p className="name">이름</p>
                                <div className="d-flex gap-3 align-items-center">
                                    <div className="img_box"></div>
                                    <div className="info_box"></div>
                                </div>
                            </div>
                            <Swiper
                                modules={[Pagination]}
                                spaceBetween={50}
                                slidesPerView={3}
                                onSlideChange={() => console.log("slide change")}
                                onSwiper={(swiper) => console.log(swiper)}
                                breakpoints={{
                                    1000: {
                                        slidesPerView: 5,
                                    }
                                }}
                                style={{ maxWidth: "800px" }}
                            >
                                <SwiperSlide>
                                    <div className="img_box">
                                        <img />
                                    </div>
                                    <div className="info_box">
                                        <p className="title">책이름!</p>
                                        <p className="writer">저자</p>
                                    </div>
                                </SwiperSlide>
                                <SwiperSlide>
                                    <div className="img_box">
                                        <img />
                                    </div>
                                    <div className="info_box">
                                        <p className="title">책이름!</p>
                                        <p className="writer">저자</p>
                                    </div>
                                </SwiperSlide>
                                <SwiperSlide>
                                    <div className="img_box">
                                        <img />
                                    </div>
                                    <div className="info_box">
                                        <p className="title">책이름!</p>
                                        <p className="writer">저자</p>
                                    </div>
                                </SwiperSlide>
                                <SwiperSlide>
                                    <div className="img_box">
                                        <img />
                                    </div>
                                    <div className="info_box">
                                        <p className="title">책이름!</p>
                                        <p className="writer">저자</p>
                                    </div>
                                </SwiperSlide>
                                <SwiperSlide>
                                    <div className="img_box">
                                        <img />
                                    </div>
                                    <div className="info_box">
                                        <p className="title">책이름!</p>
                                        <p className="writer">저자</p>
                                    </div>
                                </SwiperSlide>
                            </Swiper>
                        </div>
                    </div>
                    <div className="float_menu_cont d-lg-block d-none">
                        <div className="float_menu">
                            <div className="img_box"><img src="" alt="" /></div>
                            <p className="title">책이름</p>
                            <p className="author">저자</p>
                            <div className="price">가격</div>

                        </div>
                    </div>
                </div>

            </Container>
        </div>
    );
}

export default Detail;