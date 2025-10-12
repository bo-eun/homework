import React from "react";
import Container from "react-bootstrap/Container";
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "../assets/css/main.css";

function Main(props) {
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
          <div className="slide_cont1 recomend_books"></div>
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
