import React, { useEffect, useRef } from "react";
import "../../assets/css/boardDetail.css";
import { RiDeleteBinLine } from "react-icons/ri";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import { boardAPI } from "../../service/boardService";
import { useNavigate, useParams } from "react-router";
import { useBoard } from "../../hooks/useBoard";

const schema = yup.object({
  title: yup.string().required("제목을 입력하세요"),
  contents: yup.string().required("내용을 입력하세요"),
  file: yup
    .mixed()
    .nullable()
    .test("fileSize", "파일은 2MB 이하여야 합니다.", (value) => {
      if (!value || value.length === 0) return true;
      return value[0].size <= 2 * 1024;
    }),
});

function BoardDetail() {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
    setValue,
  } = useForm({ resolver: yupResolver(schema) });

  const { deleteFile, updateBoard, deleteBoard } = useBoard();

  const { brdId } = useParams();

  // 기존 파일 있는지 확인
  const isFile = useRef();

  const queryClient = useQueryClient();
  const navigate = useNavigate();

  const { data, isLoading, error } = useQuery({
    queryKey: ["board", brdId],
    queryFn: () => boardAPI.get(brdId),
  });

  useEffect(() => {
    console.log(data);
    if (data) {
      reset({
        title: data.title,
        contents: data.contents,
      });
      isFile.current = data.fileList && data.fileList.length > 0;
    }
  }, [data, reset]);

  const deleteFileHandle = async (bfId) => {
    if (
      isFile.current &&
      confirm("파일이 실제로 삭제됩니다. 진행하겠습니까?")
    ) {
      try {
        const result = await deleteFile.mutateAsync(bfId);

        if (result.resultCode == 200) {
          alert("파일이 삭제되었습니다.");
        } else {
          alert("파일 삭제를 실패했습니다.");
        }

        // 게시글 보기 정보 캐쉬 초기화
        queryClient.invalidateQueries({ queryKey: ["board", brdId] });
        console.log(result);
      } catch (error) {
        alert(error);
      }
    }
  };

  const updateBoardHandler = async (frmData) => {
    const formData = new FormData();
    formData.append("brdId", data.brdId);
    formData.append("title", frmData.title);
    formData.append("contents", frmData.contents);
    if (frmData.file.length > 0) {
      if (isFile.current) {
        if (
          !confirm("파일을 등록하면 기존파일이 삭제됩니다. 진행하겠습니까?")
        ) {
          setValue("file", "");
          return false;
        }
      }
      formData.append("file", frmData.file[0]);
    }

    try {
      const result = await updateBoard.mutateAsync(formData);

      if (result.resultCode == 200) {
        alert("게시물이 수정되었습니다.");
        queryClient.invalidateQueries(["board", brdId]); // 캐시 업데이트
        navigate("/board");
      } else {
        alert("게시물 수정을 실패했습니다.");
      }
    } catch (error) {
      console.log(error);
    }
  };

  const goList = () => {
    navigate("/board");
  };

  const goDelete = async () => {
    if (confirm("정말 삭제하시겠습니까?")) {
      try {
        const result = await deleteBoard.mutateAsync(brdId);

        if (result.resultCode == 200) {
          alert("게시물이 삭제되었습니다.");
          navigate("/board");
        } else {
          alert("게시물 삭제를 실패했습니다.");
        }
      } catch (error) {
        console.log(error);
      }
    }
  };

  return (
    <main className="container">
      <header className="header text-center mt-5 mb-3">
        <h2>게시글 보기</h2>
      </header>
      <section className="detail contents">
        <form action="" onSubmit={handleSubmit(updateBoardHandler)}>
          <div className="sch">
            <button type="submit" className="btn btn-outline-primary">
              수정
            </button>
            <button
              type="button"
              className="btn btn-outline-secondary"
              onClick={goList}
            >
              목록
            </button>
            <button
              type="button"
              className="btn btn-outline-danger"
              onClick={goDelete}
            >
              삭제
            </button>
          </div>
          <div className="board">
            <div className="pb-3 mb-3 border-bottom">
              <label htmlFor="title" className="form-label">
                제목
              </label>
              <input
                type="text"
                className={`form-control ${errors.title ? "is-invalid" : ""}`}
                id="title"
                {...register("title")}
              />
              {errors.title && (
                <p className="invalid-feedback">{errors.title.message}</p>
              )}
            </div>
            <div className="row mb-3">
              <label className="col-2">글쓴이</label>
              <div className="col-4">
                <p>{data?.writer}</p>
              </div>
              <label className="col-2">조회수</label>
              <div className="col-4">
                <p>{data?.readCount}</p>
              </div>
            </div>
            <div className="row mb-3">
              <label htmlFor="contents" className="form-label">
                내용
              </label>
              <textarea
                id="contents"
                className={`form-text text-contents ${
                  errors.contents ? "is-invalid" : ""
                }`}
                {...register("contents")}
              ></textarea>
              {errors.contents && (
                <p className="invalid-feedback">{errors.contents.message}</p>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="file" className="form-label">
                첨부파일
              </label>
              <div>
                <input
                  type="file"
                  className={`form-control ${errors.file ? "is-invalid" : ""}`}
                  id="file"
                  {...register("file")}
                />
                {errors.file && (
                  <p className="invalid-feedback">{errors.file.message}</p>
                )}
              </div>
              <ul className="file-list mt-2">
                {data?.fileList?.map((file) => (
                  <li key={file.bfId}>
                    {file.fileName}
                    <a href="#" onClick={() => deleteFileHandle(file.bfId)}>
                      <RiDeleteBinLine />
                    </a>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </form>
      </section>
    </main>
  );
}

export default BoardDetail;
