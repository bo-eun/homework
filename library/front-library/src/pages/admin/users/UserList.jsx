import React from 'react';
import { Container, Form, Button } from 'react-bootstrap';

function UserList(props) {
    return (
        <section>
            <h2 className='text-center mb-5'>회원관리</h2>
            <Container>
                <Form className="d-flex m-auto my-2 w-50">
                    <select name="" id="" className='me-2'>
                        <option value="id">아이디</option>
                    </select>
                    <Form.Control
                        type="search"
                        placeholder="Search"
                        className="me-2"
                        aria-label="Search"
                    />
                    <Button variant="outline-success" className='w-25'>검색</Button>
                </Form>
                <ul className='user_list mt-5 pt-5'>
                    <li className='d-flex align-items-center border-bottom py-3'>
                        <div className='w-75'>
                            <p>아이디</p>
                            <p>이름</p>
                            <span>가입날짜</span>
                        </div>
                        <div className='btn_box w-25'>
                            <button type="button" className='btn btn-light w-100'>수정</button>
                            <button type="button" className='btn btn-warning mt-2 w-100'>삭제</button>
                        </div>
                    </li>
                </ul>
            </Container>
        </section>
    );
}

export default UserList;