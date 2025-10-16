import React, { useEffect, useState } from "react";
import * as yup from "yup";
import { Container } from "react-bootstrap";
import CommonModal from "../../../components/CommonModal";
import AuthorForm from "../../../components/admin/AuthorForm";
import PublishingForm from "../../../components/admin/PublishingForm";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { useBooks } from "../../../hooks/useBooks";
import { bookAPI } from "../../../service/bookService";
import { useQuery } from "@tanstack/react-query";

const schema = yup.object({
  bookImages: yup.mixed().required("도서 표지를 입력하세요"),
  bookName: yup.string().required("도서 이름을 입력하세요"),
  publishingId: yup.string().required("출판사를 선택하세요"),
  authorId: yup.string().required("저자를 선택하세요"),
  price: yup.string().required("판매가를 입력하세요"),
  stock: yup.string().required("수량을 입력하세요"),
  bookType: yup.string().required("도서 분류를 선택하세요"),
  pageCount: yup.string().required("페이지수를 입력하세요"),
});

function AddBook(props) {
  const [show, setShow] = useState(false);
  const [modalTitle, setModalTitle] = useState("");
  const [author, setAuthor] = useState([]);
  const [publishing, setPublishing] = useState([]);

  const handleClose = () => setShow(false);
  const handleShow = (title) => {
    setShow(true);
    setModalTitle(title);
  };

  const { createMutation } = useBooks();

  const {
    register,
    handleSubmit,
    setValue,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const { data: authorData, error: authorError } = useQuery({
    queryKey: ["author"],
    queryFn: () => bookAPI.authorList(),
  });

  const { data: publishingData, error: publishingError } = useQuery({
    queryKey: ["publishing"],
    queryFn: () => bookAPI.publishingHouseList(),
  });

  const addBook = async (formData) => {
    console.log(formData);
    try {
      // mutation 실행 -> onSuccess(또는 onError) -> 호출한 곳
      await createMutation.mutate(formData);
    } catch (error) {
      alert("처리 중 오류가 발생했습니다. 다시 시도해 주세요");
    }
  };

  useEffect(() => {
    if (publishingData && authorData) {
      setAuthor(authorData.content);
      setPublishing(publishingData.content);
    }
  }, [publishingData, authorData]);

  return (
    <>
      <h2 className="title text-center mb-5">도서 등록</h2>
      <Container style={{ maxWidth: "600px" }}>
        <form onSubmit={handleSubmit(addBook)}>
          {/* 표지등록 */}
          <div className="mt-3">
            <label htmlFor="bookImages" className="form-label">
              표지등록
            </label>
            <input
              type="file"
              id="bookImages"
              {...register("bookImages")}
              className={`form-control ${errors.bookImages ? "is-invalid" : ""}`}
            />
            {errors.bookImages && (
              <div className="invalid-feedback">{errors.bookImages.message}</div>
            )}
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
              >
                <option value="">선택</option>
                {publishing.length > 0 &&
                  publishing.map((data) => (
                    <option value={data.publishingId}>
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
              >
                <option value="">선택</option>
                {author.length > 0 &&
                  author.map((data) => (
                    <option value={data.authorId}>{data.authorName}</option>
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
            <label htmlFor="" className="form-label w-100">도서 분류</label>
            <div className={`${errors.bookType ? "is-invalid" : ""}`}>
              <input type="radio" name="bookType" id="domestic" {...register("bookType")} value="DOMESTIC" />
              <label htmlFor="domestic" className="form-label ms-1 me-5">국내도서</label>

              <input type="radio" name="bookType" id="foreign" {...register("bookType")} value="FOREIGN" />
              <label htmlFor="bookType" name="bookType" className="form-label ms-1">해외도서</label>
            </div>
            {errors.bookType && (
              <div className="invalid-feedback">{errors.bookType.message}</div>
            )}

          </div>

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
              등록
            </button>
            <button type="button" className="btn btn-outline-dark btn-lg w-25">
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

export default AddBook;
