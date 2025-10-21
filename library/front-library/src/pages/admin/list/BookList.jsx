import { useQuery } from '@tanstack/react-query';
import React, { useEffect, useState } from 'react';
import { bookAPI } from '../../../service/bookService';
import { Container } from 'react-bootstrap';

function BookList(props) {
    const [page, setPage] = useState(0);
    const [books, setBooks] = useState([]);


    const { data, isLoading, error } = useQuery({
        queryKey: ['book', page],
        queryFn: () => bookAPI.list(page),
    });

    useEffect(() => {
        if (data) {
            console.log(data);
            setBooks(data.content);
        }
    }, [data])

    return (
        <Container>
            {books.map(book => {
                return <div className="d-flex">
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
            })}
        </Container>
    );
}

export default BookList;