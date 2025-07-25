import React, { useEffect, useState } from 'react';
import LottoCont from './lottoCont';
import MylottoCont from './MylottoCont';
import "../assets/css/common.css";

function Container(props) {

    // 정답 로또 배열에 저장
    const [lotto, setLotto] = useState([]);
    // 내 로또 배열에 저장(이중배열...)
    const [myLotto, setMyLotto] = useState([]);
    // 비교 버튼 누름 감지용 state
    const [compare, setCompare] = useState(false);

    // 로또 생성 도우미 함수
    const newLotto = (count) => {
        // Set을 써서 중복 제거
        let lottoSet = new Set();
        while (lottoSet.size < count) {
            const random = Math.floor(Math.random() * 44) + 1;
            lottoSet.add(random);
        }
        // Set을 배열로 변환해 리턴
        return Array.from(lottoSet);
    };

    // 로또 생성
    const createLotto = () => {
        setLotto(newLotto(7));
    };

    // 유저 로또 생성
    const createUserLotto = () => {
        let newArr = [];
        for (let i = 0; i < 5; i++) {
            newArr.push(newLotto(6));
        };
        setMyLotto(newArr);
    };

    const compareHandler = () => {
        if (lotto.length == 0) {
            alert('로또를 생성 후 비교해주세요.');
            return false;
        }
        if (myLotto.length == 0) {
            alert('유저 로또 생성 후 비교해주세요.');
            return false;
        }
        setCompare(prev => !prev);
    }

    // 로또와 내 로또가 생성될 때마다 비교 버튼 리셋
    useEffect(() => {
        setCompare(false);
    }, [lotto, myLotto])

    return (
        <div className='container'>
            <div className='button_box'>
                <button type="button" onClick={createLotto}>로또 생성</button>
                <button type="button" onClick={createUserLotto}>유저로또</button>
                <button type="button" onClick={compareHandler}>비교</button>
            </div>
            <LottoCont lotto={lotto} />
            <MylottoCont myLotto={myLotto} lotto={lotto} compare={compare} />
        </div>
    );
}

export default Container;