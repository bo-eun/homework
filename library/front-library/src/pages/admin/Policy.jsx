import React, { useEffect, useState } from 'react';
import * as yup from "yup";
import { Container } from 'react-bootstrap';
import { useAdmin } from '../../hooks/useAdmin';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { adminAPI } from '../../service/adminService';
import { useParams } from 'react-router';

const schema = yup.object().shape({
    exchange: yup.string().required('교환 정책을 입력해주세요'),
    refund: yup.string().required('환불 정책을 입력해주세요'),
    returnPolicy: yup.string().required('반품 정책을 입력해주세요'),
});

function Policy() {

    const [policy, setPolicy] = useState({});
    const [policyId, setPolicyId] = useState([]);

    const { createPolicyMutation } = useAdmin();
    const {
        register,
        handleSubmit,
        setValue,
        formState: { errors },
    } = useForm({ resolver: yupResolver(schema) });

    const updatePolicy = async (formData) => {
        try {
            console.log(policyId);
            if (policyId.length > 0) {
                // 기존 정책 삭제
                await Promise.all(
                    policyId.map(id => adminAPI.deletePolicy(id))
                );
            }
            // 새 정책 등록
            const res = await createPolicyMutation.mutateAsync(formData);
            console.log(res);
            if (res.response.content && res.response.content.policyId) {
                setPolicyId(res.response.content.policyId);
                alert('수정 완료!');
            }
        } catch (error) {
            console.error('수정 중 오류 발생', error);
        }
    };


    useEffect(() => {
        const fetchFirstPolicy = async () => {
            try {
                const resList = await adminAPI.getPolicy(); // 정책 리스트 가져오는 API, 정책 배열 반환 가정
                if (resList.length > 0) {
                    const firstPolicy = resList[0];
                    setPolicyId(resList.map((res) => res.policyId));

                    setPolicy({
                        exchange: firstPolicy.exchange || '',
                        refund: firstPolicy.refund || '',
                        returnPolicy: firstPolicy.returnPolicy || ''
                    })
                }
            } catch (error) {
                console.error('정책 로딩 실패', error);
            }
        };
        fetchFirstPolicy();
    }, [setPolicy]);

    return (
        <Container>
            <h2 className='text-center mb-5' >교환/환불/반품 설정</h2>
            <form action="" onSubmit={handleSubmit(updatePolicy)}>
                <div className="row">
                    <div className="col-2">
                        <label htmlFor="exchange" className='form-label'>교환</label>
                    </div>
                    <div className="col-10">
                        <textarea name="exchange" id="exchange" {...register("exchange")} className='form-control' defaultValue={policy.exchange}></textarea>
                    </div>
                </div>
                <div className="row mt-2">
                    <div className="col-2">
                        <label htmlFor="refund" className='form-label'>환불</label>
                    </div>
                    <div className="col-10">
                        <textarea name="refund" id="refund" {...register("refund")} defaultValue={policy.refund} className='form-control'></textarea>
                    </div>
                </div>
                <div className="row mt-2">
                    <div className="col-2">
                        <label htmlFor="returnPolicy" className='form-label'>반품</label>
                    </div>
                    <div className="col-10">
                        <textarea name="returnPolicy" id="returnPolicy" {...register("returnPolicy")} defaultValue={policy.returnPolicy} className='form-control'></textarea>
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