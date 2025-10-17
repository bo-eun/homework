import React, { useState } from "react";
import DaumPostcode from "react-daum-postcode";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { Link } from "react-router";
import { Container } from "react-bootstrap";
import { useForm } from "react-hook-form";
import CommonModal from "../../components/CommonModal";
import { useLogin } from "../../hooks/useLogin";

const schema = yup.object({
  userId: yup.string().required("아이디를 입력하세요"),
  password: yup.string().required("비밀번호를 입력하세요"),
  name: yup.string().required("이름을 입력하세요"),
  email: yup.string().required("이메일 주소를 입력하세요"),
  phone: yup.string().required("휴대폰번호를 입력하세요"),
});

function Join(props) {
  const [show, setShow] = useState(false);
  const [domain, setDomain] = useState("");
  const [email, setEmail] = useState("");

  const [address, setAddress] = useState("");
  const [zoneCode, setZonecode] = useState("");

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const { joinMutation } = useLogin();

  const {
    register,
    handleSubmit,
    setValue,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const completeHandler = (data) => {
    const { address, zonecode } = data;
    setAddress(address);
    setZonecode(zonecode);
    setValue("address", address);
  };

  const goJoin = async (formData) => {
    const finalData = {
      ...formData,
      email: `${email}${domain}`,
    };
    console.log(finalData);

    try {
      // mutation 실행 -> onSuccess(또는 onError) -> 호출한 곳
      await joinMutation.mutate(finalData);
    } catch (error) {
      alert("처리 중 오류가 발생했습니다. 다시 시도해 주세요");
    }
  };

  const domainChange = (e) => {
    setDomain(e.target.value);
  }

  const emailChange = (e) => {
    const value = e.target.value;

    if (value.includes('@')) {
      const [id, dom] = value.split('@');
      setEmail(id);
      setDomain(`@${dom}`);
    } else {
      setEmail(value);
    }
    setValue("email", value);
  }


  return (
    <>
      <Container className="mt-5" style={{ maxWidth: "500px" }}>
        <h2 className="text-center">회원가입</h2>
        <form
          action=""
          name="joinFrm"
          id="joinFrm"
          className="mb-5"
          onSubmit={handleSubmit(goJoin)}
          autoComplete="off"
        >
          <div className="mt-5">
            <label htmlFor="userId" className="form-label">
              아이디
            </label>
            <input
              type="text"
              id="userId"
              name="userId"
              {...register("userId")}
              className={`form-control ${errors.userId ? "is-invalid" : ""}`}
            />
            {errors.userId && (
              <div className="invalid-feedback">{errors.userId.message}</div>
            )}
          </div>

          <div className="mt-3">
            <label htmlFor="password" className="form-label">
              비밀번호
            </label>
            <input
              type="password"
              id="password"
              name="password"
              {...register("password")}
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
              {...register("name")}
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
                  value={`${email}${domain}`}
                  {...register("email")}
                  className={`form-control w-100 ${errors.email ? "is-invalid" : ""
                    }`}
                  onChange={emailChange}
                />
              </div>
              <div className="col-5">
                <select
                  name="domain"
                  id="domain"
                  className=" w-100 form-select"
                  onChange={domainChange}
                >
                  <option value="">직접 입력</option>
                  <option value="@naver.com">@naver.com</option>
                  <option value="@gmail.com">@gmail.com</option>
                  <option value="@daum.net">@daum.net</option>
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
              {...register("phone")}
              className={`form-control w-100 ${errors.phone ? "is-invalid" : ""
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
                  {...register("address")}
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
              {...register("addressDetail")}
              placeholder="상세주소 입력"
            />
          </div>
          <div className="btn_box mt-3">
            <button type="submit" className="btn btn-lg btn-dark w-100">
              회원가입
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
    </>
  );
}

export default Join;
