import { useQuery } from "@tanstack/react-query";
import React, { useEffect, useState } from "react";
import { adminAPI } from "../../../service/adminService";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router";
import { useAdmin } from "../../../hooks/useAdmin";

function BookList(props) {
  const [page, setPage] = useState(0);
  const [books, setBooks] = useState([]);

  const navigate = useNavigate();

  const { data, isLoading, error } = useQuery({
    queryKey: ["book", page],
    queryFn: () => adminAPI.list(page),
  });

  const { deleteMutation } = useAdmin();

  const deleteBook = async (bookId) => {
    const isConfirm = confirm("해당 도서를 삭제하시겠습니까?");
    if (isConfirm) {
      try {
        await deleteMutation.mutate(bookId);
      } catch (error) {
        alert("처리 중 오류가 발생했습니다. 다시 시도해 주세요");
      }
    }
  };

  useEffect(() => {
    if (data) {
      console.log(data);
      setBooks(data.content);
    }
  }, [data]);

  return (
    <Container className="book_list_cont">
      <h2 className="text-center mb-5">도서목록</h2>
      <Form className="d-flex m-auto my-2 mb-5 justify-content-center">
        <select name="" id="" className="me-2">
          <option value="id">도서명</option>
        </select>
        <Form.Control
          type="search"
          placeholder="Search"
          className="me-2"
          aria-label="Search"
          style={{ maxWidth: "600px" }}
        />
        <Button variant="outline-success" className="px-5">
          검색
        </Button>
      </Form>
      <div className="pt-5">
        {books.map((book) => {
          return (
            <div className="d-flex gap-4 py-4" key={book.bookId}>
              <div className="img_box">
                <img src={`/static/images/${book.bookImages[0]?.storedName}`} />
              </div>
              <div className="info_box d-flex flex-column">
                <p className="title">{book.bookName}</p>
                <p className="writer">저자 : {book.authorNames}</p>
                <p className="house mt-1">
                  출판사 : {book.publishingHouseName}
                </p>
                <p className="stock mt-1">재고 : {book.stock}</p>
                <p className="price mt-auto">
                  판매가 : <strong>{book.price}</strong>원
                </p>
              </div>
              <div className="btn_box ms-auto">
                <button
                  type="button"
                  className="btn btn-outline-dark"
                  onClick={() => navigate(`/admin/book/update/${book.bookId}`)}
                >
                  수정
                </button>
                <button
                  type="button"
                  className="btn btn-outline-danger ms-1"
                  onClick={() => deleteBook(book.bookId)}
                >
                  삭제
                </button>
              </div>
            </div>
          );
        })}
      </div>
    </Container>
  );
}

export default BookList;
