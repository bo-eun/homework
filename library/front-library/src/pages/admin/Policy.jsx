import React from 'react';
import { Container } from 'react-bootstrap';
function Policy(props) {
    return (

        <Container>
            <h2 className='text-center mb-5' >교환/환불/반품 설정</h2>
            <form action="">
                <div className="row">
                    <div className="col-2">
                        <label htmlFor="exchange" className='form-label'>교환</label>
                    </div>
                    <div className="col-10">
                        <textarea name="exchange" id="exchange" className='form-control'></textarea>
                    </div>
                </div>
                <div className="row mt-2">
                    <div className="col-2">
                        <label htmlFor="refund" className='form-label'>환불</label>
                    </div>
                    <div className="col-10">
                        <textarea name="refund" id="refund" className='form-control'></textarea>
                    </div>
                </div>
                <div className="row mt-2">
                    <div className="col-2">
                        <label htmlFor="return" className='form-label'>반품</label>
                    </div>
                    <div className="col-10">
                        <textarea name="return" id="return" className='form-control'></textarea>
                    </div>
                </div>
                <div className="text-center mt-5">
                    <button type="submit" className='btn btn-dark btn-lg w-25'>등록</button>
                </div>
            </form>
        </Container>
    );
}

export default Policy;