import React, { useEffect, useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { bookAPI } from '../../service/bookService';
import { Container } from 'react-bootstrap';


function NewList(props) {
    const [page, setPage] = useState(0);
    const [books, setBooks] = useState([]);

    const { data, isLoading, error } = useQuery({
        queryKey: ["book", page],
        queryFn: () => bookAPI.newList(page),
    });

    useEffect(() => {
        if (data) {
            console.log(data);
            setBooks(data.content);
        }
    }, [data]);

    return (
        <>
            <h2 className='text-center'>신상 도서</h2>
            <Container>
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

        </>
    );
}

export default NewList;