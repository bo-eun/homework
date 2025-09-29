import "../../assets/css/boardDetail.css";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { useNavigate } from "react-router";
import PostModal from "../../components/PostModal";
import { useEffect, useState } from "react";
import { useAdmin } from "../../hooks/useAdmin";

const schema = yup.object({
  userId: yup.string().required("아이디를 입력하세요"),
  passwd: yup.string().required("비밀번호를 입력하세요"),
  userName: yup.string().required("회원 이름을 입력하세요"),
  gender: yup.string().required("성별을 선택하세요"),
  birth: yup.string().required("생년월일을 입력하세요"),
  phone: yup.string().required("휴대폰번호를 입력하세요"),
  mailId: yup.string().required("이메일을 입력하세요"),
  agency: yup.string().required("이메일을 입력하세요"),
  addr: yup.string().required("주소를 입력하세요"),
});

function UserWrite() {
  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
  } = useForm({ resolver: yupResolver(schema) });

  const { createUser } = useAdmin();

  const [userRole, setUserRole] = useState('ADMIN');
  const [domain, setDomain] = useState('');
  const [mailId, setMailId] = useState('');
  const [agency, setAgency] = useState('');
  const [modalShow, setModalShow] = useState(false);
  const [address, setAddress] = useState('');
  const [useYn, setUseYn] = useState('');

  const navigate = useNavigate();

  const createUserHandler = async (formData) => {
    try {
      const result = await createUser.mutate(formData);
      console.log(result)

      if (result.resultCode == 200) {
        alert("회원이 등록되었습니다.");
        navigate("/board");
      } else {
        alert("회원 등록을 실패했습니다.");
      }
    } catch (error) {
      console.log(error);
    }
  };

  const goList = () => {
    navigate("/admin");
  };
  const selectDomain = (e) => {
    const value = e.target.value;
    if (value == "none") return false;
    setDomain(value);

    if (value != 'self') {
      setAgency(value);
      setValue("agency", value, { shouldValidate: true });
    } else {
      setAgency('');
      setValue("agency", '', { shouldValidate: true });
    }
  }

  useEffect(() => {
    if (address) {
      setValue("addr", address, { shouldValidate: true });
    }
  }, [address])


  return (
    <div className="content">
      <main className="container">
        <section className="contents">
          <header className="text-center mb-5">
            <h2>회원 등록</h2>
          </header>
          <section className="table-list">
            <form id="userForm" onSubmit={handleSubmit(createUserHandler)} autoComplete="off">
              <input type="hidden" id="page" name="page" value="0" />
              <input type="hidden" id="size" name="size" value="10" />
              <table className="admin-table table">
                <colgroup>
                  <col width="20%" />
                </colgroup>
                <tbody>

                  <tr>
                    <th><label className="form-label">아이디</label></th>
                    <td>
                      <input
                        type="text"
                        id="userId"
                        name="userId"
                        className={`form-control w-100 ${errors.userId ? "is-invalid" : ""}`}
                        {...register("userId")}
                      />
                      {errors.userId && (
                        <div className="invalid-feedback">
                          {errors.userId.message}
                        </div>
                      )}
                    </td>
                  </tr>
                  <tr>
                    <th><label className="form-label">비밀번호</label></th>
                    <td>
                      <input
                        type="password"
                        id="passwd"
                        name="passwd"
                        className={`form-control w-100 ${errors.passwd ? "is-invalid" : ""}`}
                        {...register("passwd")}
                      />
                      {errors.passwd && (
                        <div className="invalid-feedback">
                          {errors.passwd.message}
                        </div>
                      )}
                    </td>
                  </tr>
                  <tr>
                    <th><label htmlFor="userName" className="form-label">이름</label></th>
                    <td>
                      <input
                        type="text"
                        id="userName"
                        name="userName"
                        className={`form-control w-100 ${errors.userName ? "is-invalid" : ""}`}
                        {...register("userName")}
                      />
                      {errors.userName && (
                        <div className="invalid-feedback">
                          {errors.userName.message}
                        </div>
                      )}
                    </td>
                  </tr>
                  <tr>
                    <th><label className="form-label">성별</label></th>
                    <td>
                      <input type="radio" name="gender" value="남자" id="male"  {...register("gender")} />
                      <label htmlFor="male" className="form-label me-3">남자</label>
                      <input type="radio" name="gender" value="여자" id="female"  {...register("gender")} />
                      <label htmlFor="female" className={`form-label ${errors.userName ? "is-invalid" : ""}`}>여자</label>
                      {errors.gender && (
                        <div className="invalid-feedback">
                          {errors.gender.message}
                        </div>
                      )}
                    </td>
                  </tr>
                  <tr>
                    <th><label className="form-label">생년월일</label></th>
                    <td>
                      <input
                        type="text"
                        id="birth"
                        name="birth"
                        className={`form-control w-100 ${errors.birth ? "is-invalid" : ""}`}
                        {...register("birth")}
                      />
                      {errors.birth && (
                        <div className="invalid-feedback">
                          {errors.birth.message}
                        </div>
                      )}
                    </td>
                  </tr>
                  <tr>
                    <th><label htmlFor="phone" className="form-label">휴대폰번호</label></th>
                    <td>
                      <input
                        type="text"
                        id="phone"
                        name="phone"
                        className={`form-control w-100 ${errors.phone ? "is-invalid" : ""}`}
                        {...register("phone")}
                      />
                      {errors.phone && (
                        <div className="invalid-feedback">
                          {errors.phone.message}
                        </div>
                      )}
                    </td>
                  </tr>
                  <tr>
                    <th><label className="form-label">이메일</label></th>
                    <td>
                      <input
                        type="text"
                        id="mailId"
                        name="mailId"
                        onChange={e => setMailId(e.target.value)}
                        className={`form-control d-inline-block w-25 ${errors.mailId ? "is-invalid" : ""}`}
                        {...register("mailId")}
                      />
                      <span className="mx-2">@</span>
                      <input
                        type="text"
                        id="agency"
                        name="agency"
                        readOnly={domain != 'self'}
                        onChange={(e) => {
                          setAgency(e.target.value);
                          setValue("agency", e.target.value);
                        }}
                        className={`form-control d-inline-block w-25 ${errors.agency ? "is-invalid" : ""}`}
                        {...register("agency")}
                      />
                      <select
                        className="form-select d-inline-block w-auto ms-2"
                        onChange={selectDomain}
                        value={domain}
                      >
                        <option value="none">===== 선택 =====</option>
                        <option value="self">직접입력</option>
                        <option value="gmail.com">gmail.com</option>
                        <option value="naver.com">naver.com</option>
                        <option value="kakao.com">kakao.com</option>
                        <option value="yahoo.co.kr">yahoo.co.kr</option>
                      </select>
                      {(errors.domain || errors.mailId) && (
                        <div className="invalid-feedback">
                          {errors.domain ? errors.domain.message : errors.mailId.message}
                        </div>
                      )}
                      <input type="hidden" value={(agency && mailId) && `${mailId}@${agency}`} name="email" />
                    </td>
                  </tr>
                  <tr>
                    <th><label className="form-label">주소</label></th>
                    <td>
                      <input
                        type="text"
                        id="addr"
                        name="addr"
                        value={address}
                        readOnly
                        className={`form-control d-inline-block w-75 ${errors.addr ? "is-invalid" : ""}`}
                        {...register("addr")}
                      />
                      <button
                        type="button"
                        onClick={() => setModalShow(true)}
                        className="btn btn-warning ms-1 mb-1"
                      >
                        주소찾기
                      </button>
                      <input
                        type="text"
                        id="addrDetail"
                        name="addrDetail"
                        className={`form-control d-inline-block w-100 ${errors.addrDetail ? "is-invalid" : ""}`}
                        {...register("addrDetail")}
                      />
                      {errors.addr && (
                        <div className="invalid-feedback">
                          {errors.addr.message}
                        </div>
                      )}
                      <PostModal
                        show={modalShow}
                        onHide={() => setModalShow(false)}
                        setAddress={setAddress}
                      />
                    </td>
                  </tr>
                  <tr>
                    <th>
                      <label className="form-label">권한</label>
                    </th>
                    <td>
                      <select
                        name="userRole"
                        id="userRole"
                        className="form-select w-25"
                        value={userRole}
                        onChange={e => setUserRole(e.target.value)}
                      >
                        <option value="ADMIN">관리자</option>
                        <option value="USER">사용자</option>
                      </select>
                    </td>
                  </tr>
                  <tr>
                    <th>
                      <label className="form-label">사용여부</label>
                    </th>
                    <td>
                      <select name="useYn" id="useYn" className="form-select w-25" value={useYn} onChange={e => setUseYn(e.target.value)}>
                        <option value="Y">사용</option>
                        <option value="N">미사용</option>
                      </select>
                    </td>
                  </tr>
                </tbody>
              </table>
              <div className="text-center mt-3">
                <button
                  type="submit"
                  className="btn btn-primary me-1"
                >
                  등록
                </button>
                <button type="button" className="btn btn-secondary" onClick={goList}>
                  목록
                </button>
              </div>
            </form>


          </section>
        </section>
      </main>
    </div>
  );
}

export default UserWrite;
