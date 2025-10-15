import React, { useState } from "react";
import { Container } from "react-bootstrap";
import DaumPostcode from "react-daum-postcode";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { useLogin } from "../../hooks/useLogin";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import CommonModal from "../CommonModal";

const schema = yup.object({
  password: yup.string().required("비밀번호를 입력하세요"),
  name: yup.string().required("이름을 입력하세요"),
  email: yup.string().required("이메일 주소를 입력하세요"),
  phone: yup.string().required("휴대폰번호를 입력하세요"),
});

function UserForm(props) {
  const [show, setShow] = useState(false);

  const [address, setAddress] = useState("");
  const [zoneCode, setZonecode] = useState("");

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const { joinMutation } = useLogin();
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const completeHandler = (data) => {
    const { address, zonecode } = data;
    setAddress(address);
    setZonecode(zonecode);
  };

  const goJoin = async (formData) => {
    console.log(formData);
    try {
      // mutation 실행 -> onSuccess(또는 onError) -> 호출한 곳
      await joinMutation.mutate(formData);
    } catch (error) {
      alert("처리 중 오류가 발생했습니다. 다시 시도해 주세요");
    }
  };
  return (
    <section>
      <h2 className="text-center">회원정보 수정</h2>
      <Container className="" style={{ maxWidth: "500px" }}>
        <form
          action=""
          name="joinFrm"
          id="joinFrm"
          className="mb-5"
          onSubmit={handleSubmit(goJoin)}
          autoComplete="off"
        >
          <div className="mt-5">
            <label htmlFor="id" className="form-label">
              아이디
            </label>
            <input
              type="text"
              id="id"
              name="id"
              className="form-control"
              readOnly
              value=""
            />
          </div>

          <div className="mt-3">
            <label htmlFor="password" className="form-label">
              비밀번호
            </label>
            <input
              type="password"
              id="password"
              name="password"
              className={`form-control ${errors.password ? "is-invalid" : ""}`}
            />
            {errors.password && (
              <div className="invalid-feedback">{errors.password.message}</div>
            )}
          </div>
          <div className="mt-3">
            <label htmlFor="name" className="form-label">
              이름
            </label>
            <input
              type="text"
              id="name"
              name="name"
              className={`form-control ${errors.name ? "is-invalid" : ""}`}
            />
            {errors.name && (
              <div className="invalid-feedback">{errors.name.message}</div>
            )}
          </div>
          <div className="mt-3">
            <label htmlFor="email" className="form-label">
              이메일
            </label>
            <div className="row">
              <div className="col-7">
                <input
                  type="text"
                  name="email"
                  id="email"
                  className={`form-control w-100 ${
                    errors.email ? "is-invalid" : ""
                  }`}
                />
              </div>
              <div className="col-5">
                <select
                  name="domain"
                  id="domain"
                  className=" w-100 form-select"
                >
                  <option value="">직접 입력</option>
                  <option value="@naver.com">@naver.com</option>
                  <option value="">@gmail.com</option>
                  <option value="">@daum.net</option>
                </select>
              </div>
            </div>
            {errors.email && (
              <div className="invalid-feedback">{errors.email.message}</div>
            )}
          </div>
          <div className="mt-3">
            <label htmlFor="phone" className="form-label">
              휴대폰번호
            </label>
            <input
              type="text"
              id="phone"
              name="phone"
              className={`form-control w-100 ${
                errors.phone ? "is-invalid" : ""
              }`}
            />
            {errors.phone && (
              <div className="invalid-feedback">{errors.phone.message}</div>
            )}
          </div>
          <div className="mt-3">
            <label htmlFor="address" className="form-label">
              주소
            </label>
            <div className="row">
              <div className="col-9">
                <input
                  type="text"
                  id="address"
                  name="address"
                  className="form-control w-100"
                  value={address}
                  readOnly
                />
              </div>
              <div className="col-3">
                <button
                  type="button"
                  onClick={handleShow}
                  className="btn btn-dark w-100"
                >
                  주소찾기
                </button>
              </div>
            </div>
            <input
              type="text"
              id="subAddress"
              name="subAddress"
              className="form-control mt-2"
              placeholder="상세주소 입력"
            />
          </div>
          <div className="btn_box mt-5 text-center">
            <button type="submit" className="btn btn-lg btn-dark w-25">
              수정
            </button>
            <button
              type="button"
              className="btn btn-lg btn-light w-25 ms-2"
              onClick={() => navigate("/admin/user")}
            >
              취소
            </button>
          </div>
        </form>
      </Container>
      <CommonModal show={show} handleClose={handleClose} title="주소찾기">
        <DaumPostcode
          onComplete={completeHandler}
          onClose={() => setShow(false)}
        />
      </CommonModal>
    </section>
  );
}

export default UserForm;
