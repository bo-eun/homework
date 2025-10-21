import React, { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "../assets/css/main.css";
import { useQuery } from "@tanstack/react-query";
import { bookAPI } from "../service/bookService";
import { Link, useNavigate } from "react-router";

function Main(props) {
  let slideIndex = 1;

  const [page, setPage] = useState(0);
  const [recommendBooks, setRecommendBooks] = useState([]);
  const [newBooks, setNewBooks] = useState([]);
  const [bestBooks, setBestBooks] = useState([]);

  const navigate = useNavigate();

  // 추천 도서
  const {
    data: recommendBookDarta,
    isLoading: recommendBookLoading
  } = useQuery({
    queryKey: ["recommendBooks"],
    queryFn: () => bookAPI.recommendList(page),
  });

  // 신상 도서
  const { data: newBookData, isLoading: newBookLoading } = useQuery({
    queryKey: ["newBooks"],
    queryFn: () => bookAPI.newList(page),
  });

  // 베스트 도서
  const { data: bestBookData, isLoading: bestBookLoading } = useQuery({
    queryKey: ["newBooks"],
    queryFn: () => bookAPI.bestList(page),
  });

  useEffect(() => {
    if (recommendBookDarta) {
      console.log(recommendBookDarta)
      setRecommendBooks(recommendBookDarta.content);
    }
    if (newBookData) {
      setNewBooks(newBookData.content);
    }
    if (bestBookData) {
      setBestBooks(bestBookData.content);
    }
  }, [recommendBookDarta, newBookData, bestBookData]);

  return (
    <>
      <div className="main_visual">
        <Swiper
          modules={[Pagination]}
          slidesPerView={1}
          onSlideChange={() => console.log("slide change")}
          onSwiper={(swiper) => console.log(swiper)}
          loop={true}
          autoplay={true}
        >
          <SwiperSlide>
            <img src="/images/slide1.jpg" title="slide1" />
          </SwiperSlide>
          <SwiperSlide>
            <img src="/images/slide2.jpg" title="slide1" />
          </SwiperSlide>
        </Swiper>
      </div>
      <Container>
        <section>
          <h2>추천도서</h2>
          <div className="slide_cont1 recomend_books">
            <Swiper
              modules={[Pagination]}
              slidesPerView={1}
              onSlideChange={() => console.log("slide change")}
              onSwiper={(swiper) => console.log(swiper)}
            >
              {recommendBooks.length > 0 &&
                recommendBooks.map((book) => {
                  return (
                    <SwiperSlide>
                      <Link to={`/book/detail/${book.bookId}`}>
                        <div className="d-flex gap-3">
                          <div className="img_box">
                            <img
                              src={`/static/images/${book.bookImages[0]?.storedName}`}
                            />
                          </div>
                          <div className="info_box">
                            <p className="title fw-bold">{book.bookName}</p>
                            <p className="writer">{book.authorNames}</p>
                            <p className="pt-2 fw-bold">{book.shortIntro}</p>
                            <p className="pt-3">{book.intro}</p>
                          </div>
                        </div>
                      </Link>
                    </SwiperSlide>
                  );
                })}
            </Swiper>
          </div>
        </section>
        <section>
          <h2>신상도서</h2>
          <div className="slide_cont2 new_books">
            <Swiper
              modules={[Pagination]}
              spaceBetween={50}
              slidesPerView={5}
              onSlideChange={() => console.log("slide change")}
              onSwiper={(swiper) => console.log(swiper)}
            >
              {newBooks.length > 0 &&
                newBooks.map((book) => {
                  return (
                    <SwiperSlide>
                      <Link to={`/book/detail/${book.bookId}`}>
                        <div className="img_box">
                          <img
                            src={`/static/images/${book.bookImages[0]?.storedName}`}
                          />
                        </div>
                      </Link>
                      <div className="info_box">
                        <Link to={`/book/detail/${book.bookId}`}><p className="title">{book.bookName}</p></Link>
                        <p className="writer">{book.authorNames}</p>
                      </div>
                    </SwiperSlide>
                  );
                })}
            </Swiper>
          </div>
        </section>
        <section>
          <h2>베스트</h2>
          <div className="slide_cont2 best_books">
            <Swiper
              modules={[Pagination]}
              spaceBetween={50}
              slidesPerView={3}
              onSlideChange={() => console.log("slide change")}
              onSwiper={(swiper) => console.log(swiper)}
            >
              {bestBooks.length > 0 &&
                bestBooks.map((book) => {
                  return (
                    <SwiperSlide>
                      <Link to={`/book/detail/${book.bookId}`}>
                        <div className="img_box">
                          <img
                            src={`/static/images/${book.bookImages[0]?.storedName}`}
                          />
                          <span className="rank">{slideIndex++}</span>
                        </div>
                      </Link>
                    </SwiperSlide>
                  );
                })}
            </Swiper>
          </div>
        </section>
        <section>
          <div className="banner"></div>
        </section>
      </Container>
    </>
  );
}

export default Main;
