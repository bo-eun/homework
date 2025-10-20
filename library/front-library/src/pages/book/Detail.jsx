import React, { useEffect, useState } from "react";
import { Container, Tab, Table, Tabs } from "react-bootstrap";
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "../../assets/css/detail.css";
import CommonModal from "../../components/CommonModal";
import ReviewForm from "../../components/ReviewForm";
import Counter from "../../components/button/Counter";
import { useQuery } from "@tanstack/react-query";
import { bookAPI } from "../../service/bookService";
import { useParams } from "react-router";

function Detail(props) {
  const [show, setShow] = useState(false);
  const [key, setKey] = useState("info");

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [detail, setDetail] = useState({});
  const [authorDetail, sesAuthorDetail] = useState({});

  const { bookId } = useParams();
  const { data, isLoading } = useQuery({
    queryKey: ["detail"],
    queryFn: () => bookAPI.bookAndAuthorDetail(bookId),
  });


  useEffect(() => {
    if (data) {
      console.log(data);
      setDetail(data);
    }

  }, [data]);

  if (isLoading) {
    return (
      <div className="d-flex align-items-center justify-content-center vh-100">
        로딩 중...
      </div>
    );
  }
  return (
    <>
      <div className="detail_cont">
        <Container>
          <div className="detail_box d-lg-grid d-block">
            <div className="left_box d-flex align-items-center">
              <div className="img_box">
                <img
                  src={`/static/images/${detail?.bookImages?.[0]?.storedName}`}
                />
              </div>
            </div>
            <div className="right_box">
              <div className="info_box">
                <p className="title mt-lg-0 mt-3">{detail?.bookName}</p>
                <p className="writer">
                  {detail?.authorName} | {detail?.publishingHouseName}
                </p>
                <p className="stars">★★★★★(50)</p>
              </div>
              <div className="price_box border-top pt-3 mt-3">
                <div className="option_box d-flex justify-content-between">
                  <Counter />
                  <strong>{detail?.price} 원</strong>
                </div>
                <div className="btn_box mt-3 d-flex gap-2">
                  <button
                    type="button"
                    className="btn btn-outline-dark btn-lg w-100"
                  >
                    장바구니
                  </button>
                  <button type="button" className="btn btn-dark btn-lg w-100">
                    바로구매
                  </button>
                </div>
              </div>
            </div>
          </div>
        </Container>
        <Container>
          <div className="detail_info_cont mt-5 d-lg-grid d-block">
            <div>
              <Tabs
                id="controlled-tab-example"
                activeKey={key}
                onSelect={(k) => setKey(k)}
                className="mb-3"
              >
                <Tab eventKey="info" title="상품정보">
                  <div className="">
                    <div className="detail_info_box">
                      <h3>상품정보</h3>
                      <Table bordered className="detail_table">
                        <colgroup>
                          <col width={"170px"} />
                        </colgroup>
                        <tbody>
                          <tr>
                            <th>쪽수</th>
                            <td>{detail?.stock}</td>
                          </tr>
                          <tr>
                            <th>출판사</th>
                            <td>{detail?.publishingHouseName}</td>
                          </tr>
                          <tr>
                            <th>저자</th>
                            <td>{detail?.authorName}</td>
                          </tr>
                          <tr>
                            <th>가격</th>
                            <td>{detail?.price}원</td>
                          </tr>
                        </tbody>
                      </Table>
                    </div>
                  </div>
                </Tab>
                <Tab eventKey="intro" title="도서소개">
                  <div className="detail_info_box w-100">
                    <h3>도서 소개</h3>
                    <div className="">{detail?.shortIntro}</div>

                    <div className="my-3">{detail?.intro}</div>

                    <h3>작가 정보</h3>
                    <div className="author_info_box">
                      <p className="name">{detail?.authorName}</p>
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
                        },
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
                </Tab>
                <Tab eventKey="review" title="리뷰">
                  <div className="review_box detail_info_box">
                    <h3 className="d-flex justify-content-between">
                      도서리뷰(10)
                      <button
                        type="button"
                        onClick={handleShow}
                        className="btn btn-light btn-lg"
                      >
                        리뷰쓰기
                      </button>
                    </h3>
                    <ul>
                      <li>
                        <div className="user_ifno">
                          <div className="star">★★★★★</div>
                          <div className="user_id">아이디 | 날짜</div>
                        </div>
                        <div className="text">
                          댓글내용!댓글내용!댓글내용!댓글내용!댓글내용!
                        </div>
                      </li>
                      <li>
                        <div className="user_ifno">
                          <div className="star">★★★★★</div>
                          <div className="user_id">아이디 | 날짜</div>
                        </div>
                        <div className="text">
                          댓글내용!댓글내용!댓글내용!댓글내용!댓글내용!
                        </div>
                      </li>
                      <li>
                        <div className="user_ifno">
                          <div className="star">★★★★★</div>
                          <div className="user_id">아이디 | 날짜</div>
                        </div>
                        <div className="text">
                          댓글내용!댓글내용!댓글내용!댓글내용!댓글내용!
                        </div>
                      </li>
                    </ul>
                  </div>
                </Tab>
                <Tab eventKey="policy" title="교환/환불/반품">
                  <h3>교환</h3>
                  <div className="text_cont pb-3">교환~~~~~~~~~~~~~~~~~~~~</div>
                  <h3>환불</h3>
                  <div className="text_cont pb-3">교환~~~~~~~~~~~~~~~~~~~~</div>
                  <h3>반품</h3>
                  <div className="text_cont pb-5">교환~~~~~~~~~~~~~~~~~~~~</div>
                </Tab>
              </Tabs>
            </div>

            <div className="float_menu_cont d-lg-block d-none">
              <div className="float_menu">
                <div className="img_box">
                  <img src="" alt="" />
                </div>
                <p className="title">책이름</p>
                <p className="author">저자</p>
                <div className="price">가격</div>
              </div>
            </div>
          </div>
        </Container>
      </div>
      <CommonModal show={show} handleClose={handleClose} title="리뷰작성">
        <ReviewForm />
      </CommonModal>
    </>
  );
}

export default Detail;
