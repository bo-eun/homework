import React from 'react';

function AuthorForm(props) {
    return (
        <form action="" name="" id="" autoComplete='off'>
            <label htmlFor="" className='form-label mt-4'>사진첨부</label>
            <div className=''>
                <label htmlFor='file' className='file_img_box'>+</label>
                <input type="file" id="file" name="file" className='d-none' />
            </div>
            <label htmlFor="authorName" className='form-label mt-4'>저자명</label>
            <input type="text" className='form-control w-100' name="authorName" id="authorName" />

            <label htmlFor="" className='form-label mt-4'>생년월일</label>
            <input type="text" className='form-control w-100' />

            <label htmlFor="" className='form-label mt-4 w-100'>국적</label>
            <label htmlFor="DOMESTIC" className='form-label'>국내</label>
            <input type="radio" className='form-check-inline' id="DOMESTIC" name="nationality" value="DOMESTIC" />
            <label htmlFor="OVERSEAS" className='form-label'>해외</label>
            <input type="radio" className='form-check-inline' id="OVERSEAS" name="nationality" value="OVERSEAS" />

            <label htmlFor="" className='form-label mt-4 w-100'>저자소개</label>
            <textarea name="intro" id="intro" className='form-control'></textarea>

            <button type="submit" className='btn btn-dark btn-lg w-100 mt-4'>등록</button>
        </form >
    );
}

export default AuthorForm;