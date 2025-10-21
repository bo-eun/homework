import React from 'react';

function ReviewForm(props) {
    return (
        <form action="">
            <div className="d-flex gap-3 mb-5">
                <div className="img_box">
                    <img src="" alt="" />
                </div>
                <p className="title pb-2">책이름</p>
                <div className="stars">
                    ★★★★★
                </div>
            </div>
            <div className="review_write_box">
                <p className="modal_sub_title mb-2">
                    작성내용
                </p>
                <textarea name="" id="" className='form-control'></textarea>
            </div>
            <div className="btn_box pt-3">
                <button type="submit" className='w-100 btn btn-light btn-lg'>등록</button>
            </div>
        </form>
    );
}

export default ReviewForm;