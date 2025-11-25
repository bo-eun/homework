import React, { useEffect, useRef, useState } from 'react';
import Card from './Card';
import "../assets/css/index.css"

function CardContainer(props) {
    const [userCards, setUserCards] = useState({ cards: [], selectCards: [], sum: 0 });
    const [pcCards, setPcCards] = useState({ cards: [], sum: 0 });

    const gameStart = () => {
        gameReset();

        // 카드 중복을 막기 위해 Set사용
        const userArr = new Set();
        const pcArr = new Set();

        // 유저 카드 구하기
        while (userArr.size < 5) {
            const rand = Math.floor(Math.random() * 20) + 1;
            userArr.add(rand);
        }
        setUserCards(prev => ({ ...prev, cards: Array.from(userArr) }));

        // pc 카드 구하기
        while (pcArr.size < 2) {
            const rand = Math.floor(Math.random() * 20) + 1;
            pcArr.add(rand);
        }
        setPcCards(prev => ({ ...prev, cards: Array.from(pcArr) }));

        console.log(`pc 카드 : ${Array.from(pcArr)}`)
    };

    // 카드 선택 버튼 클릭
    const cardSelect = () => {
        if (userCards.selectCards.length != 2) {
            alert('카드 2장을 선택해 주세요');
            return;
        }
        setUserCards(prev => ({ ...prev, sum: prev.selectCards.reduce((sum, num) => sum + num, 0) }));
        setPcCards(prev => ({ ...prev, sum: prev.cards.reduce((sum, num) => sum + num, 0) }));
    };

    // 게임 리셋 버튼 클릭
    const gameReset = () => {
        setUserCards({ cards: [], selectCards: [], sum: 0 });
        setPcCards({ cards: [], sum: 0 });
    };

    // 카드 클릭 시 일어나는 이벤트
    const cardCheck = (e) => {
        const { value, checked } = e.target;

        let checkedCard;

        // 클릭한 요소가 체크되었을 경우 userCards.selectCards에 저장, 아닐 경우 빼기
        if (checked) {
            // 선택 카드가 2개 이하인 경우
            if (userCards.selectCards.length < 2) {
                checkedCard = [...userCards.selectCards, Number(value)]
            } else { // 선택 카드가 2개 보다 많은 경우 추가하지 않고 기존 선택한 카드 그대로
                checkedCard = [...userCards.selectCards];
            }
        } else {
            // 클릭한 요소가 체크된게 아닐 경우 빼기
            checkedCard = userCards.selectCards?.filter(card => card != value);
        }

        // 위에서 구한 배열 상태에 적용하기
        setUserCards(prev => ({ ...prev, selectCards: checkedCard }))
    };

    return (
        <div>
            <div className='container'>
                {userCards.cards.length > 0 && userCards.cards?.map((num, index) => {
                    // inputId > input과 label을 연결하기 위해 전달
                    // checked > input checked 시 label에 className="active" 주기 위해 전달
                    return <Card key={`key_${index}`} inputId={`card_${index}`} checked={userCards.selectCards?.some(select => select == num)}>
                        <input type="checkbox" id={`card_${index}`} onChange={e => cardCheck(e)} value={num} />
                        {num}
                    </Card>
                })}
            </div>
            <div style={{ textAlign: 'center', margin: '20px 0 0' }}>
                <button type="button" onClick={gameStart}>시작</button>
                <button type="button" onClick={cardSelect}>선택</button>
                <button type="button" onClick={gameReset}>리셋</button>
            </div>
            {/* 카드 선택 후 요소 노출 */}
            {userCards.sum + pcCards.sum > 0 &&
                <div style={{ textAlign: 'center', margin: '20px 0 0' }}>
                    <p>결과 : {userCards.sum > pcCards.sum ? '이겼습니다!' : (userCards.sum < pcCards.sum ? '졌습니다' : '비겼습니다')}</p>
                    <p>나 : {userCards.sum}, pc : {pcCards.sum}</p>
                </div>
            }

        </div>

    );
}

export default CardContainer;