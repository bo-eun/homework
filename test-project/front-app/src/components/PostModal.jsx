import React, { useState } from 'react';
import DaumPostcode from 'react-daum-postcode';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

function PostModal({ setAddress, onHide, show }) {

    const handleComplete = (data) => {
        let fullAddress = data.address;
        let extraAddress = '';

        if (data.addressType === 'R') {
            if (data.bname !== '') {
                extraAddress += data.bname;
            }
            if (data.buildingName !== '') {
                extraAddress += (extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName);
            }
            fullAddress += (extraAddress !== '' ? ` (${extraAddress})` : '');
        }

        setAddress(fullAddress);
        // console.log(fullAddress);  // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'
        onHide();
    }

    return (
        <>
            <Modal
                show={show}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered
            >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        주소 찾기
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <DaumPostcode onComplete={handleComplete} />
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={onHide}>Close</Button>
                </Modal.Footer>
            </Modal>

        </>
    );
}

export default PostModal;