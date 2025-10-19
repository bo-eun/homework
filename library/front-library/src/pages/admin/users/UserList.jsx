import { useQuery } from "@tanstack/react-query";
import React, { useEffect, useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import { adminAPI } from "../../../service/adminService";

function UserList(props) {
  const [page, setPage] = useState(0);
  const [users, setUsers] = useState([]);

  const { data, isLoading, error } = useQuery({
    queryKey: ["users", page],
    queryFn: () => adminAPI.userList(page),
  });

  useEffect(() => {
    console.log(data);
    setUsers(data?.content);
  }, [data]);

  return (
    <Container>
      <h2 className="text-center mb-5">회원관리</h2>
      <Form className="d-flex m-auto my-2 w-50">
        <select name="" id="" className="me-2">
          <option value="id">아이디</option>
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
      <ul className="user_list mt-5 pt-5">
        {users?.map((user) => {
          return (
            <li
              className="d-flex align-items-center border-bottom py-3"
              key={user.userId}
            >
              <div className="w-75">
                <span className="">{user.role}</span>
                <p>{user.userId}</p>
                <p>{user.name}</p>
                <span>{user.createDate}</span>
              </div>
              <div className="btn_box w-25">
                <button type="button" className="btn btn-light w-100">
                  수정
                </button>
                <button type="button" className="btn btn-warning mt-2 w-100">
                  삭제
                </button>
              </div>
            </li>
          );
        })}
      </ul>
    </Container>
  );
}

export default UserList;
