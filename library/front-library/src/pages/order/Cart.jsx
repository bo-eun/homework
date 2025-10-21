import React, { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { cartAPI } from "../../service/cartService";
import { authStore } from "../../store/authStore";
import { Link, useNavigate } from "react-router";
import Counter from "../../components/button/Counter";
import { useCart } from "../../hooks/useCart";
import { IoMdClose } from "react-icons/io";
import "../../assets/css/order.css"
import { orderStore } from "../../store/orderStore";

function Cart() {
  const userId = authStore((state) => state.userId);

  const [dataList, setDataList] = useState([]);

  const { updateMutation, deleteMutation } = useCart();

  const setOrderItems = orderStore((state) => state.setOrderItems);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await cartAPI.list(userId);
        const cartItems = response.content;

        console.log(cartItems);
        setDataList(cartItems);

        const initialQuantities = {};
        cartItems.forEach((item) => {
          initialQuantities[item.cartId] = item.quantity;
        });
      } catch (error) {
        console.log(error);
      }
    };

    fetchCart();
  }, []);

  const updateQuantity = async (bookData, newQuantity) => {
    try {
      const requestData = { userId, cartId: bookData.cartId, bookId: bookData.bookId, quantity: newQuantity }
      await updateMutation.mutate(requestData);
      setDataList(prevList =>
        prevList.map(item =>
          item.cartId === bookData.cartId
            ? { ...item, quantity: newQuantity }
            : item
        )
      );
    } catch (error) {
      console.log(error);
    }
  }

  const deleteCart = async (cartId) => {
    try {
      const isConfirm = confirm('장바구니에서 상품을 삭제하시겠습니까?');
      if (isConfirm) {
        await deleteMutation.mutate(cartId);
        setDataList(prev => prev.filter(item => item.cartId !== cartId));
      }
    } catch (error) {
      console.log(error);
    }

  }

  const goOrder = () => {
    // 장바구니 정보 로컬스토리지에 저장
    setOrderItems(dataList);
    navigate('/order');
  }

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
            <ul className="cart_list">
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
                          <p className="price pb-3 text-end">{book.quantity * book.price}원</p>
                          <Counter value={book.quantity} setValue={(newQuantity) => updateQuantity(book, newQuantity)} />
                        </div>
                      </div>

                      <button type="button" className="close_btn" onClick={() => deleteCart(book.cartId)}>
                        <IoMdClose style={{ fontSize: "20px" }} />
                      </button>
                    </li>
                  );
                })}
            </ul>
            <div className="btn_box text-center my-5 pb-5">
              <button type="button" className="btn btn-lg btn-dark w-25" onClick={goOrder}>
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
