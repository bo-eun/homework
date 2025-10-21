import React, { useEffect, useState } from 'react';
import { Container } from 'react-bootstrap';
import DaumPostcode from "react-daum-postcode";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import CommonModal from "../../components/CommonModal";
import { orderStore } from '../../store/orderStore';
import { Link } from 'react-router';
import { useOrder } from '../../hooks/useOrder';
import { authStore } from '../../store/authStore';

const schema = yup.object({
    userName: yup.string().required("주문자명을 입력하세요"),
    phone: yup.string().required("휴대폰번호를 입력하세요"),
    address: yup.string().required("주소를 입력하세요"),
});

function Order(props) {
    const [dataList, setDataList] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);

    const [address, setAddress] = useState("");
    const [show, setShow] = useState(false);

    // 로컬 스토리지에서 현재 주문 정보 가져오는 함수
    const { getOrderItems } = orderStore();

    // 주문 요청
    const { createMutation } = useOrder();

    // 유저 아이디 가져오기
    const { getUserId } = authStore();

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const {
        register,
        handleSubmit,
        setValue,
        formState: { errors },
    } = useForm({ resolver: yupResolver(schema) });

    const completeHandler = (data) => {
        const { address } = data;
        setAddress(address);
        setValue("address", address);
    };

    const addOrder = async (formData) => {
        const finalData = {
            ...formData,
            userId: getUserId(),
            shippingAddress: `${formData.address} ${formData.addressDetail}`,
            orderItems: dataList,
            totalPrice: totalPrice,
        }
        console.log(finalData)
        try {
            // mutation 실행 -> onSuccess(또는 onError) -> 호출한 곳
            await createMutation.mutate(finalData);
        } catch (error) {
            alert("처리 중 오류가 발생했습니다. 다시 시도해 주세요");
        }
    };

    // 로컬 스토리지에서 현재 주문 정보 가져오기
    useEffect(() => {
        const itemList = getOrderItems();
        setDataList(itemList);

        setTotalPrice(itemList.reduce((acc, item) => acc + item.price, 0));
    }, [])

    console.log(dataList)

    return (
        <>
            <h2 className='text-center'>주문</h2>
            <Container className='order_cont'>
                <div className="order_list_cont">
                    <h3 className='pb-3'>주문상품</h3>
                    <ul className="cart_list">
                        {dataList.length > 0 &&
                            dataList.map((book) => {
                                return (
                                    <li
                                        key={`${book.cartId}${book.bookId}`}
                                        className="d-flex align-items-center gap-4 py-3 border-bottom"
                                    >
                                        <div className="img_box">
                                            <img
                                                src={`/static/images/${book.bookImages[0]?.storedName}`}
                                            />
                                        </div>
                                        <div className="info_box d-flex align-items-center justify-content-between h-100 gap-4 w-100">
                                            <div className="d-flex flex-column gap-2">
                                                <Link
                                                    to={`/book/detail/${book.bookId}`}
                                                    className="title pt-1 pb-1"
                                                >
                                                    {book.bookTitle}
                                                </Link>
                                                <p className="writer">
                                                    {book.authorName} 저자 | {book.publishingHouseName}
                                                </p>
                                                <p className="pt-2 pb-4">{book.shortIntro}</p>
                                            </div>

                                            <div className="btn_box">
                                                <p className="price pb-1 text-end">{book.quantity}개 | {book.quantity * book.price}원</p>
                                            </div>
                                        </div>
                                    </li>
                                );
                            })}
                    </ul>
                    <div className="text-end pt-2 total_price_box">
                        <span>상품 금액 :  </span>
                        <strong className='total_price'>{totalPrice} 원</strong>
                    </div>
                </div>


                <form action="" autoComplete='off' onSubmit={handleSubmit(addOrder)}>
                    <h3 className='pt-5 mt-5'>주문정보</h3>
                    <div className="mt-5">
                        <label htmlFor="userName" className="form-label">
                            주문자명
                        </label>
                        <input
                            type="text"
                            id="userName"
                            name="userName"
                            {...register("userName")}
                            className={`form-control ${errors.userName ? "is-invalid" : ""}`}

                        />
                        {errors.userName && (
                            <div className="invalid-feedback">{errors.userName.message}</div>
                        )}
                    </div>
                    <div className="mt-5">
                        <label htmlFor="phone" className="form-label">
                            휴대폰번호
                        </label>
                        <input
                            type="text"
                            id="phone"
                            name="phone"
                            {...register("phone")}
                            className={`form-control ${errors.phone ? "is-invalid" : ""}`}
                        />
                        {errors.phone && (
                            <div className="invalid-feedback">{errors.phone.message}</div>
                        )}
                    </div>
                    <div className="mt-3">
                        <label htmlFor="address" className="form-label">
                            주소
                        </label>
                        <div className="row">
                            <div className="col-9">
                                <input
                                    type="text"
                                    id="address"
                                    name="address"
                                    className="form-control w-100"
                                    {...register("address")}
                                    value={address}
                                    readOnly
                                />
                            </div>
                            <div className="col-3">
                                <button
                                    type="button"
                                    onClick={handleShow}
                                    className="btn btn-dark w-100"
                                >
                                    주소찾기
                                </button>
                            </div>
                        </div>
                        <input
                            type="text"
                            id="subAddress"
                            name="subAddress"
                            className="form-control mt-2"
                            {...register("addressDetail")}
                            placeholder="상세주소 입력"
                        />
                    </div>
                    {errors.address && (
                        <div className="invalid-feedback">{errors.address.message}</div>
                    )}
                    <div className="btn_box text-center my-5 pb-5">
                        <button type="submit" className="btn btn-lg btn-dark w-25">
                            결제하기
                        </button>
                    </div>
                </form>

            </Container>
            <CommonModal show={show} handleClose={handleClose} title="주소찾기">
                <DaumPostcode
                    onComplete={completeHandler}
                    onClose={() => setShow(false)}
                />
            </CommonModal>
        </>
    );
}

export default Order;