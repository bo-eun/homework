import React from 'react';

function PublishingForm(props) {
    return (
        <form action="" name="" id="" autoComplete='off'>
            <label htmlFor="publishingName" className='form-label mt-4'>출판사명</label>
            <input type="text" className='form-control w-100 mb-4' name="publishingName" id="publishingName" />

            <button type="submit" className='btn btn-dark btn-lg w-100 mt-4'>등록</button>
        </form>
    );
}

export default PublishingForm;