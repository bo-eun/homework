import React, { useEffect, useState } from "react";
import * as yup from "yup";
import { Container } from "react-bootstrap";
import CommonModal from "../../../components/CommonModal";
import AuthorForm from "../../../components/admin/AuthorForm";
import PublishingForm from "../../../components/admin/PublishingForm";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { adminAPI } from "../../../service/adminService";
import { bookAPI } from "../../../service/bookService";
import { useQuery } from "@tanstack/react-query";
import { useNavigate, useParams } from "react-router";
import { useAdmin } from "../../../hooks/useAdmin";

const schema = yup.object({
  // bookImages: yup.mixed().required("도서 표지를 입력하세요") 
  // 값이 없을 경우 기존 파일을 사용하기 위해 required처리 안함,
  bookName: yup.string().required("도서 이름을 입력하세요"),
  publishingId: yup.string().required("출판사를 선택하세요"),
  authorId: yup.string().required("저자를 선택하세요"),
  price: yup.string().required("판매가를 입력하세요"),
  stock: yup.string().required("수량을 입력하세요"),
  bookType: yup.string().required("도서 분류를 선택하세요"),
  pageCount: yup.string().required("페이지수를 입력하세요"),
  publicationDate: yup.string().required("출간일을 입력하세요"),
});

function UpdateBook(props) {
  const [show, setShow] = useState(false);
  const [modalTitle, setModalTitle] = useState("");
  const [author, setAuthor] = useState([]);
  const [publishing, setPublishing] = useState([]);

  const [detail, setDetail] = useState({});
  const [checked, setChecked] = useState(false);
  const { bookId } = useParams();

  const navigate = useNavigate();

  const handleClose = () => setShow(false);
  const handleShow = (title) => {
    setShow(true);
    setModalTitle(title);
  };

  const { updateMutation } = useAdmin();

  const {
    register,
    handleSubmit,
    setValue,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const { data: authorData, error: authorError } = useQuery({
    queryKey: ["author"],
    queryFn: () => adminAPI.authorList(),
  });

  const { data: publishingData, error: publishingError } = useQuery({
    queryKey: ["publishing"],
    queryFn: () => adminAPI.publishingHouseList(),
  });

  const updateBook = async (formData) => {
    console.log(formData);
    try {

      // 파일이 있을 때만 FormData에 넣기, 안그려면 500에러남..
      if (formData.bookImages && formData.bookImages.length > 0) {
        formData.append("bookImages", formData.bookImages[0]); // 단일 파일 기준
      }

      // mutation 실행 -> onSuccess(또는 onError) -> 호출한 곳
      await updateMutation.mutate(formData);
    } catch (error) {
      alert("처리 중 오류가 발생했습니다. 다시 시도해 주세요");
    }
  };

  useEffect(() => {
    const fetchDetail = async () => {
      try {
        const data = await bookAPI.detail(bookId);
        console.log(data);
        setValue("bookId", data.bookId);
        setValue("authorId", data.author.authorId);
        setValue("bookName", data.bookName);
        setValue("bookType", data.bookType);
        setValue("intro", data.intro);
        setValue("pageCount", data.pageCount);
        setValue("price", data.price);
        setValue("publicationDate", data.publicationDate);
        setValue("publishingId", data.publishingId);
        setValue("recommendationStatus", data.recommendationStatus);
        setValue("shortIntro", data.shortIntro);
        setValue("stock", data.stock);
        setDetail(data);
        setChecked(data.recommendationStatus || false);
      } catch (err) {
        console.error(err);
      }
    };

    fetchDetail();
  }, []);

  useEffect(() => {
    if (publishingData && authorData) {
      setAuthor(authorData.content);
      setPublishing(publishingData.content);
    }
  }, [publishingData, authorData]);

  return (
    <>
      <h2 className="title text-center mb-5">도서 수정</h2>
      <Container style={{ maxWidth: "600px" }} className="add_book_container">
        <form onSubmit={handleSubmit(updateBook)}>
          {/* 표지등록 */}
          <div className="mt-3 row">
            <label htmlFor="bookImages" className="form-label">
              표지등록
            </label>
            <div className="col-4">
              <div className="img_box ">
                <img src={
                  Array.isArray(detail?.bookImages) && detail.bookImages.length > 0
                    ? `/static/images/${detail.bookImages[0].storedName}`
                    : '/static/images/default.png'
                } className="w-100" />
              </div>
            </div>
            <div className="col-8">
              <input
                type="file"
                id="bookImages"
                {...register("bookImages")}
                className={`form-control`}
              />
            </div>
          </div>

          {/* 도서제목 */}
          <div className="mt-3">
            <label htmlFor="bookName" className="form-label">
              도서제목
            </label>
            <input
              type="text"
              id="bookName"
              {...register("bookName")}
              className={`form-control ${errors.bookName ? "is-invalid" : ""}`}
            />
            {errors.bookName && (
              <div className="invalid-feedback">{errors.bookName.message}</div>
            )}
          </div>

          {/* 출판사 */}
          <div className="mt-3">
            <label htmlFor="publishingId" className="form-label">
              출판사
            </label>
            <div className="d-flex gap-2">
              <select
                id="publishingId"
                name="publishingId"
                {...register("publishingId", {
                  setValueAs: (v) => parseInt(v, 10),
                })}
                className={`form-select ${errors.publishingId ? "is-invalid" : ""
                  }`}
                value={detail.publishingId}
              >
                <option value="">선택</option>
                {publishing.length > 0 &&
                  publishing.map((data) => (
                    <option key={data.publishingId} value={data.publishingId}>
                      {data.publishingName}
                    </option>
                  ))}
              </select>
              <button
                type="button"
                className="btn btn-light w-25"
                onClick={() => handleShow("출판사 등록")}
              >
                추가
              </button>
            </div>
            {errors.publishingId && (
              <div className="invalid-feedback">
                {errors.publishingId.message}
              </div>
            )}
          </div>

          {/* 저자 */}
          <div className="mt-3">
            <label htmlFor="authorId" className="form-label">
              저자명
            </label>
            <div className="d-flex gap-2">
              <select
                id="authorId"
                {...register("authorId", {
                  setValueAs: (v) => parseInt(v, 10),
                })}
                className={`form-select ${errors.authorId ? "is-invalid" : ""}`}
                defaultValue={detail?.author?.authorId}
              >
                <option value="">선택</option>
                {author.length > 0 &&
                  author.map((data) => (
                    <option key={data.authorId} value={data.authorId}>
                      {data.authorName}
                    </option>
                  ))}
              </select>
              <button
                type="button"
                className="btn btn-light w-25"
                onClick={() => handleShow("저자 등록")}
              >
                추가
              </button>
            </div>
            {errors.authorId && (
              <div className="invalid-feedback">{errors.authorId.message}</div>
            )}
          </div>

          {/* 가격 / 재고 */}
          <div className="mt-3 row">
            <div className="col">
              <label htmlFor="price" className="form-label">
                판매가
              </label>
              <input
                type="text"
                id="price"
                {...register("price")}
                className={`form-control ${errors.price ? "is-invalid" : ""}`}
              />
              {errors.price && (
                <div className="invalid-feedback">{errors.price.message}</div>
              )}
            </div>
            <div className="col">
              <label htmlFor="stock" className="form-label">
                재고
              </label>
              <input
                type="text"
                id="stock"
                {...register("stock")}
                className={`form-control ${errors.stock ? "is-invalid" : ""}`}
              />
              {errors.stock && (
                <div className="invalid-feedback">{errors.stock.message}</div>
              )}
            </div>
          </div>

          <div className="col mt-3">
            <label htmlFor="" className="form-label w-100">
              도서 분류
            </label>
            <div className={`${errors.bookType ? "is-invalid" : ""}`}>
              <label htmlFor="domestic" className="form-label">
                국내도서
              </label>
              <input
                type="radio"
                name="bookType"
                id="domestic"
                className=" ms-2 me-5 ms-1 me-5"
                {...register("bookType")}
                value="DOMESTIC"
              />

              <label htmlFor="bookType" name="bookType" className="form-label">
                해외도서
              </label>
              <input
                type="radio"
                name="bookType"
                id="foreign"
                className=" ms-2 me-5
 ms-1 me-5"
                {...register("bookType")}
                value="FOREIGN"
              />
            </div>
            {errors.bookType && (
              <div className="invalid-feedback">{errors.bookType.message}</div>
            )}
          </div>

          <div className="col mt-3">
            <label htmlFor="" className="w-100 form-label">
              추천도서 분류
            </label>
            <label htmlFor="domestic" className="form-label">
              추천도서
            </label>
            <input
              type="checkbox"
              name="recommendationStatus"
              id="recommendationStatus"
              className=" ms-2 me-5
 ms-1 me-5"
              {...register("recommendationStatus")}
              value="true"
              checked={checked}
              onChange={(e) => setChecked(e.target.checked)}
            />
          </div>
          <span className="notice text-danger">
            * 메인페이지의 추천도서 영역에 노출됩니다.
          </span>

          {/* 페이지수 */}
          <div className="mt-3">
            <label htmlFor="pageCount" className="form-label">
              페이지수
            </label>
            <input
              type="text"
              id="pageCount"
              {...register("pageCount")}
              className={`form-control ${errors.pageCount ? "is-invalid" : ""}`}
            />
            {errors.pageCount && (
              <div className="invalid-feedback">{errors.pageCount.message}</div>
            )}
          </div>

          {/* 페이지수 */}
          <div className="mt-3">
            <label htmlFor="publicationDate" className="form-label">
              출간일
            </label>
            <input
              type="date"
              id="publicationDate"
              {...register("publicationDate")}
              className={`form-control ${errors.publicationDate ? "is-invalid" : ""
                }`}
            />
            {errors.publicationDate && (
              <div className="invalid-feedback">
                {errors.publicationDate.message}
              </div>
            )}
          </div>

          {/* 한줄소개 */}
          <div className="mt-3">
            <label htmlFor="shortIntro" className="form-label">
              한줄소개
            </label>
            <input
              type="text"
              id="shortIntro"
              {...register("shortIntro")}
              className="form-control"
            />
          </div>

          {/* 책소개 */}
          <div className="mt-3">
            <label htmlFor="intro" className="form-label">
              책소개
            </label>
            <textarea
              id="intro"
              {...register("intro")}
              className="form-control"
            />
          </div>

          <div className="d-flex justify-content-center gap-2 my-4">
            <button type="submit" className="btn btn-dark btn-lg w-25">
              수정
            </button>
            <button
              type="button"
              className="btn btn-outline-dark btn-lg w-25"
              onClick={() => navigate("/admin")}
            >
              취소
            </button>
          </div>
        </form>
      </Container>

      <CommonModal show={show} handleClose={handleClose} title={modalTitle}>
        {modalTitle === "저자 등록" ? <AuthorForm /> : <PublishingForm />}
      </CommonModal>
    </>
  );
}

export default UpdateBook;
