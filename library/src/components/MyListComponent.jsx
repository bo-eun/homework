import React, { useEffect } from 'react';
import { useState } from 'react';

function MyLibraryComponent({ myList, returnBook }) {

    const [checkedList, setCheckedList] = useState([]);

    const checkList = (e) => {
        const value = Number(e.target.value);
        if (e.target.checked) {
            setCheckedList(prev => [...prev, value])
        } else {
            setCheckedList(prev => prev.filter(list => list !== value));
        }
    }

    useEffect(() => {
        setCheckedList([]);
    }, [myList])

    console.log(checkedList);

    return (
        <div>
            <h2>대여한 도서 목록</h2>
            <div className="text-end">
                <button type="button" className='btn btn-outline-primary' onClick={() => returnBook(checkedList)}>반납</button>
            </div>
            {(myList && myList.length == 0) && <div className='text-center py-4'>대여한 도서가 없습니다.</div>}
            <ul className='bg-light my-4'>
                {myList?.map(book => (
                    <li className='p-2' key={`key_borrow${book.id}`}>
                        <input type="checkbox" id={`borrow_${book.id}`} value={book.id} onChange={checkList} />
                        <label htmlFor={`borrow_${book.id}`}>{book.name}</label>
                    </li>
                ))}

            </ul>
        </div>
    );
}

export default MyLibraryComponent;