import React, { useEffect, useState } from "react";
import { Link } from "react-router";
import "../assets/css/list.css";
import CartAddButton from "./button/CartAddButton";

function BookList({ books }) {


  return (
    <>
      {books.map((book) => {
        return (
          <li className="d-flex gap-4 py-3 border-bottom">
            <div className="img_box">
              <img src={`/static/images/${book.bookImages[0]?.storedName}`} />
            </div>
            <div className="info_box d-flex align-items-center justify-content-between h-100 gap-4 w-100">
              <div className="d-flex flex-column gap-2">
                <Link to={`/book/detail/${book.bookId}`} className="title pt-1">
                  {book.bookName}
                </Link>
                <p className="writer">
                  {book.authorNames} 저자 | {book.publishingHouseName}
                </p>
                <p className="pt-2 pb-4">{book.shortIntro}</p>
                <p className="price pb-4">{book.price}원</p>
              </div>

              <div className="btn_box">
                <CartAddButton bookId={book.bookId} quantity={1} styles="btn btn-outline-dark w-100 mt-2" />
                <button type="button" className="btn btn-dark w-100 mt-2">
                  바로구매
                </button>
              </div>
            </div>
          </li>
        );
      })}
    </>
  );
}

export default BookList;
