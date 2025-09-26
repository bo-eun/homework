import "../../assets/css/boardDetail.css";
import * as yup from "yup";
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { useNavigate } from 'react-router';
import { useBoard } from '../../hooks/useBoard';

const schema = yup.object({
    title: yup.string().required('제목을 입력하세요'),
    contents: yup.string().required('내용을 입력하세요'),
    file: yup.mixed().nullable().test('fileSize', '파일은 2MB 이하여야 합니다.', (value) => {
        if (!value || value.length === 0) return true;
        return value[0].size <= 2 * 1024
    })
})

function BoardWrite() {

    const { register, handleSubmit, formState: { errors }, reset, setValue } = useForm({ resolver: yupResolver(schema) });

    const { createBoard } = useBoard();

    const navigate = useNavigate();

    const createBoardHandler = async (data) => {
        const formData = new FormData();
        formData.append('title', data.title);
        formData.append('contents', data.contents);

        if (data.file.length > 0) {
            formData.append('file', data.file[0]);
        }

        try {
            const result = await createBoard.mutateAsync(formData);

            if (result.resultCode == 200) {
                alert('게시물이 등록되었습니다.')
                navigate('/board');
            } else {
                alert('게시물 등록을 실패했습니다.')
            }

        } catch (error) {
            console.log(error);
        }
    };

    const goList = () => {
        navigate('/board');
    }

    return (
        <main className='container'>
            <header className='header text-center mt-5 mb-3'>
                <h2>게시글 쓰기</h2>
            </header>
            <section className='detail contents'>
                <form action="" onSubmit={handleSubmit(createBoardHandler)}>
                    <div className="sch">
                        <button type='submit' className='btn btn-outline-primary'>등록</button>
                        <button type='button' className='btn btn-outline-secondary' onClick={goList}>취소</button>
                    </div>
                    <div className="board">
                        <div className='pb-3 mb-3 border-bottom'>
                            <label htmlFor="title" className='form-label'>제목</label>
                            <input type="text" className={`form-control ${errors.title ? 'is-invalid' : ''}`} id="title" {...register('title')} />
                            {errors.title && <p className='invalid-feedback'>{errors.title.message}</p>}
                        </div>
                        <div className="row mb-3">
                            <label htmlFor="contents" className='form-label'>내용</label>
                            <textarea id="contents" className={`form-text text-contents ${errors.contents ? 'is-invalid' : ''}`} {...register('contents')}></textarea>
                            {errors.contents && <p className='invalid-feedback'>{errors.contents.message}</p>}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="file" className="form-label">첨부파일</label>
                            <div>
                                <input type="file" className={`form-control ${errors.file ? 'is-invalid' : ''}`} id="file" {...register('file')} />
                                {errors.file && <p className='invalid-feedback'>{errors.file.message}</p>}
                            </div>
                        </div>
                    </div>

                </form>
            </section>
        </main>
    );
}

export default BoardWrite;