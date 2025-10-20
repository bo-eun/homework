import React, { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { cartAPI } from "../../service/cartService";
import { authStore } from "../../store/authStore";
import { Link } from "react-router";
import Counter from "../../components/button/Counter";

function Cart() {
  const userId = authStore((state) => state.userId);

  const [dataList, setDataList] = useState([]);

  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await cartAPI.list(userId);
        console.log(response.content);
        setDataList(response.content);
      } catch (error) {
        console.log(error);
      }
    };

    fetchCart();
  }, []);

  return (
    <>
      <h2 className="text-center">장바구니</h2>
      <Container>
        {dataList.length <= 0 ? (
          <div className="d-flex justify-content-center align-items-center vh-100">
            장바구니가 비었습니다.
          </div>
        ) : (
          <>
            <ul>
              {dataList.length > 0 &&
                dataList.map((book) => {
                  return (
                    <li
                      key={`${book.cartId}${book.bookId}`}
                      className="d-flex align-items-center gap-4 py-3 border-bottom"
                    >
                      <div className="img_box">
                        <img
                          src={`/static/images/${book.bookImages[0]?.storedName}`}
                        />
                      </div>
                      <div className="info_box d-flex align-items-center justify-content-between h-100 gap-4 w-100">
                        <div className="d-flex flex-column gap-2">
                          <Link
                            to={`/book/detail/${book.bookId}`}
                            className="title pt-1 pb-1"
                          >
                            {book.bookTitle}
                          </Link>
                          <p className="writer">
                            {book.authorName} 저자 | {book.publishingHouseName}
                          </p>
                          <p className="pt-2 pb-4">{book.shortIntro}</p>
                        </div>

                        <div className="btn_box">
                          <p className="price pb-3 text-end">{book.price}원</p>
                          <Counter />
                        </div>
                      </div>
                    </li>
                  );
                })}
            </ul>
            <div className="btn_box text-center my-5 pb-5">
              <button type="button" className="btn btn-lg btn-dark w-25">
                주문하기
              </button>
            </div>
          </>
        )}
      </Container>
    </>
  );
}

export default Cart;
