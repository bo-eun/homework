import React, { useState } from 'react';
import { Container } from 'react-bootstrap';
import CommonModal from '../../../components/CommonModal';
import AuthorForm from '../../../components/admin/AuthorForm';
import PublishingForm from '../../../components/admin/PublishingForm';

function AddBook(props) {
    const [show, setShow] = useState(false);
    const [modalTitle, setModalTitle] = useState("");
    const handleClose = () => setShow(false);
    const handleShow = (title) => {
        setShow(true);
        setModalTitle(title);
    }
    return (
        <>
            <h2 className='title text-center mb-5'>도서 등록</h2>
            <Container>
                <form action="" name="" id="">
                    <div className="row mb-2">
                        <label htmlFor="bookImage" className='form-label col-2'>표지등록</label>
                        <div className="col-2" style={{ height: '200px', background: '#f0f0f0', borderRadius: '10px' }}>

                        </div>
                        <div className="col-8">
                            <input type="file" name="bookImage" id="bookImage" className='form-control' />
                        </div>
                    </div>
                    <div className="row mb-2">
                        <label htmlFor="bookImage" className='form-label col-2'>도서제목</label>
                        <div className="col-10">
                            <input type="text" name="bookName" id="bookName" className='form-control' />
                        </div>
                    </div>
                    <div className="row mb-2">
                        <label htmlFor="publishingName" className='form-label col-2'>출판사</label>
                        <div className="row col-10">
                            <div className="col-10">
                                <select name="publishingName" id="publishingName" className='form-select'>
                                    <option value="1">1</option>
                                </select>
                            </div>
                            <button type="button" className='btn btn-light col-2' onClick={() => handleShow("출판사 등록")}>추가</button>
                        </div>
                    </div>
                    <div className="row mb-2">
                        <label htmlFor="publishingName" className='form-label col-2'>저자명</label>
                        <div className="row col-10">
                            <div className="col-10">
                                <select name="authorName" id="authorName" className='form-select'>
                                    <option value="1">1</option>
                                </select>
                            </div>
                            <button type="button" className='btn btn-light col-2' onClick={() => handleShow('저자 등록')}>추가</button>
                        </div>
                    </div>
                    <div className="row mb-2">
                        <label htmlFor="price" className='form-label col-2'>판매가</label>
                        <div className="col-4">
                            <input type="text" name="price" id="price" className='form-control' />
                        </div>
                        <label htmlFor="stock" className='form-label col-2'>재고</label>
                        <div className="col-4">
                            <input type="text" name="stock" id="stock" className='form-control' />
                        </div>
                    </div>
                    <div className="row mb-2">
                        <label htmlFor="pageCount" className='form-label col-2'>페이지수</label>
                        <div className="col-10">
                            <input type="text" name="pageCount" id="pageCount" className='form-control' />
                        </div>
                    </div>
                    <div className="row mb-2">
                        <label htmlFor="shortIntro" className='form-label col-2'>한줄소개</label>
                        <div className="col-10">
                            <input type="text" name="shortIntro" id="shortIntro" className='form-control' />
                        </div>
                    </div>
                    <div className="row">
                        <label htmlFor="intro" className='form-label col-2'>책소개</label>
                        <div className="col-10">
                            <textarea name="intro" id="intro" className='form-control' />
                        </div>

                    </div>
                    <div className="btn_box d-flex justify-content-center gap-2 my-4">
                        <button type="submit" className='btn btn-dark btn-lg w-25'>등록</button>
                        <button type="submit" className='btn btn-outline-dark btn-lg w-25'>취소</button>
                    </div>
                </form>
            </Container>
            <CommonModal show={show} handleClose={handleClose} title={modalTitle}>
                {modalTitle == '저자 등록' ? <AuthorForm /> : <PublishingForm />}
            </CommonModal>
        </>
    );
}

export default AddBook;