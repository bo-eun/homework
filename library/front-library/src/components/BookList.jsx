import React from "react";
import { Link } from "react-router";

function BookList({ books }) {
  return (
    <>
      {books.map((book) => {
        return (
          <div className="d-flex">
            <div className="img_box">
              <img src={`/static/images/${book.bookImages[0]?.storedName}`} />
            </div>
            <div className="info_box">
              <Link to={`/book/detail/${book.bookId}`} className="title">
                {book.bookName}
              </Link>
              <p className="writer">{book.authorNames}</p>
              <p className="">{book.shortIntro}</p>
              <p>{book.intro}</p>
            </div>
          </div>
        );
      })}
    </>
  );
}

export default BookList;
