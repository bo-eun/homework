import React, { useEffect, useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { bookAPI } from "../../service/bookService";
import { Container } from "react-bootstrap";
import BookList from "../../components/BookList";

function BestList(props) {
  const [page, setPage] = useState(0);
  const [books, setBooks] = useState([]);

  const { data, isLoading, error } = useQuery({
    queryKey: ["book", page],
    queryFn: () => bookAPI.bestList(page),
  });

  useEffect(() => {
    if (data) {
      console.log(data);
      setBooks(data.content);
    }
  }, [data]);

  return (
    <>
      <h2 className="text-center">베스트 도서</h2>
      <Container>
        <BookList books={books} />
      </Container>
    </>
  );
}

export default BestList;
