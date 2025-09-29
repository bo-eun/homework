import React, { useEffect, useState } from "react";
import Pagination from "../../components/Pagination";
import "../../assets/css/boardList.css";
import { useQuery } from "@tanstack/react-query";
import { adminAPI } from "../../service/adminService";
import { Link, useNavigate } from "react-router";

function UserList() {
  const [page, setPage] = useState(0);
  const [userList, setUserList] = useState([]);
  const [totalRows, setTotalRows] = useState(0);
  const [delYn, setDelYn] = useState("ALL");
  const [searchText, setSearchText] = useState("");
  const navigate = useNavigate();

  const { data, isLoading, error, refetch } = useQuery({
    queryKey: ["user", page],
    queryFn: () => adminAPI.list(page, searchText, delYn),
  });

  useEffect(() => {
    if (data) {
      console.log(data);
      setPage(data.page);
      setTotalRows(data.total);
      setUserList(data.content);
    }
  }, [data]);

  const movePage = (pageNum) => {
    setPage(pageNum);
  };

  const goWrite = () => {
    navigate("/admin/add");
  };

  const goDetail = (userId) => {
    navigate(`/admin/${userId}`);
  };

  const search = () => {
    refetch();
  }

  return (
    <>
      <div className="content">
        <main className="container">
          <section className="contents">
            <header className="text-center mb-5">
              <h2>회원 리스트</h2>
            </header>

            <section className="sch-box">
              <ul className="sch-list d-flex mb-4">
                <li className="mx-2">
                  <input
                    type="text"
                    className="form-control d-inline-block"
                    id="searchText"
                    placeholder="아이디 또는 이름"
                    onChange={(e) => setSearchText(e.target.value)}
                  />
                </li>
                <li className="mx-2">
                  <select name="delYn" id="delYn" className="form-select" value={delYn} onChange={
                    (e) => setDelYn(e.target.value)
                  }>
                    <option value="ALL">
                      전체보기
                    </option>
                    <option value="N">회원보기</option>
                    <option value="Y">삭제된 회원보기</option>
                  </select>
                </li>
                <li>
                  <button type="button" className="btn btn-warning" onClick={search}>
                    검색
                  </button>
                </li>
                <li className="ms-auto">
                  <button
                    type="button"
                    className="btn btn-primary"
                    onClick={goWrite}
                  >
                    등록
                  </button>
                </li>
              </ul>
            </section>

            <section className="table-list">
              <input type="hidden" id="page" name="page" value="0" />
              <input type="hidden" id="size" name="size" value="10" />
              <table className="admin-table table">
                <colgroup>
                  <col width="8%" />
                  <col width="7%" />
                  <col width="4%" />
                  <col width="6%" />
                  <col width="9%" />
                  <col width="10%" />
                  <col width="12%" />
                  <col width="12%" />
                  <col width="6%" />
                  <col width="6%" />
                </colgroup>
                <thead className="table-dark">
                  <tr>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>성별</th>
                    <th>생년월일</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                    <th>생성일</th>
                    <th>수정일</th>
                    <th>사용여부</th>
                    <th>삭제여부</th>
                  </tr>
                </thead>
                <tbody id="userTbody">
                  {userList?.map((obj) => {
                    return (
                      <tr key={obj.userId}>
                        <td>
                          <a
                            href="javascript:void(0)"
                            onClick={() => goDetail(obj.userId)}
                          >
                            {obj.userId}
                          </a>
                        </td>
                        <td>{obj.userName}</td>
                        <td>{obj.gender}</td>
                        <td>{obj.birth}</td>
                        <td>{obj.phone}</td>
                        <td>{obj.email}</td>
                        <td>{new Date(obj.createDate).toLocaleString()}</td>
                        <td>{new Date(obj.updateDate).toLocaleString()}</td>
                        <td>{obj.useYn}</td>
                        <td>{obj.delYn}</td>
                      </tr>
                    )

                  })}
                </tbody>
              </table>
            </section>
            <section>
              <Pagination
                page={page}
                totalRows={totalRows}
                movePage={movePage}
              />
            </section>
          </section>
        </main>
      </div>
    </>
  );
}

export default UserList;
