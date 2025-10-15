import { useQuery } from "@tanstack/react-query";
import React, { useEffect, useState } from "react";
import { bookAPI } from "../../../service/bookService";
import { Button, Container, Form } from "react-bootstrap";

function BookList(props) {
  const [page, setPage] = useState(0);
  const [books, setBooks] = useState([]);

  const { data, isLoading, error } = useQuery({
    queryKey: ["book", page],
    queryFn: () => bookAPI.list(page),
  });

  useEffect(() => {
    if (data) {
      console.log(data);
      setBooks(data.content);
    }
  }, [data]);

  return (
    <Container>
      <Form className="d-flex m-auto my-2 w-50">
        <select name="" id="" className="me-2">
          <option value="id">도서명</option>
        </select>
        <Form.Control
          type="search"
          placeholder="Search"
          className="me-2"
          aria-label="Search"
        />
        <Button variant="outline-success" className="w-25">
          검색
        </Button>
      </Form>
      {books.map((book) => {
        return (
          <div className="d-flex">
            <div className="img_box">
              <img />
            </div>
            <div className="info_box">
              <p className="title">{book.bookName}</p>
              <p className="writer">{book.authorNames}</p>
              <p className="">{book.shortIntro}</p>
              <p>{book.intro}</p>
            </div>
          </div>
        );
      })}
    </Container>
  );
}

export default BookList;
