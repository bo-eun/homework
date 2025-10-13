import React, { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "../assets/css/main.css";
import { useQuery } from "@tanstack/react-query";
import { bookAPI } from "../service/bookService";

function Main(props) {
  const [page, setPage] = useState(0);
  const [recommendBooks, setRecommendBooks] = useState([]);


  const { data, isLoading, error } = useQuery({
    queryKey: ['book', page],
    queryFn: () => bookAPI.list(page),
  });

  useEffect(() => {
    if (data) {
      console.log(data);
      setRecommendBooks(data.content);
    }
  }, [data])

  return (
    <>
      <div className="main_visual">
        <Swiper
          modules={[Pagination]}
          slidesPerView={1}
          onSlideChange={() => console.log("slide change")}
          onSwiper={(swiper) => console.log(swiper)}
        >
          <SwiperSlide>Slide 1</SwiperSlide>
          <SwiperSlide>Slide 2</SwiperSlide>
          <SwiperSlide>Slide 3</SwiperSlide>
          <SwiperSlide>Slide 4</SwiperSlide>
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
              {recommendBooks.length > 0 && recommendBooks.map((book) => {
                return <SwiperSlide>
                  <div className="d-flex">
                    <div className="img_box">
                      <img />
                    </div>
                    <div className="info_box">
                      <p className="title">{book.bookName}</p>
                      <p className="writer">{book.authorNames.join(', ')}</p>
                      <p className="">{book.shortIntro}</p>
                      <p>{book.intro}</p>
                    </div>

                  </div>
                </SwiperSlide>
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
              <SwiperSlide>Slide 1</SwiperSlide>
              <SwiperSlide>Slide 2</SwiperSlide>
              <SwiperSlide>Slide 3</SwiperSlide>
              <SwiperSlide>Slide 4</SwiperSlide>
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
              <SwiperSlide>Slide 1</SwiperSlide>
              <SwiperSlide>Slide 2</SwiperSlide>
              <SwiperSlide>Slide 3</SwiperSlide>
              <SwiperSlide>Slide 4</SwiperSlide>
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
