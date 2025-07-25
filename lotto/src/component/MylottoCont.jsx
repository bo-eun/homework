import React, { useState } from 'react';

function MylottoCont({ myLotto, lotto, compare }) {

    // 보너스 번호 여부 담는 배열
    let bonus = [];

    const countArr = myLotto?.map((arr, index) =>
        lotto.filter((num, idx) => {
            // 보너스 번호 여부 확인
            if (idx == lotto.length - 1 && arr.includes(num)) {
                bonus[index] = true;
            } else {
                bonus[index] = false;
            };
            return arr.includes(num)
        }).length
    );

    console.log(bonus);

    const resultArr = countArr.map((count, index) => {
        if (count == 6) {
            return '1등'
        } else if (count == 5 && bonus[index]) {
            return '2등'
        } else if (count == 5) {
            return '3등'
        } else if (count == 4) {
            return '4등'
        } else if (count == 3) {
            return '5등'
        } else if (count == 2) {
            return '6등'
        } else {
            return '꽝!'
        }
    })

    return (
        <div className='my_lotto_cont'>
            {myLotto?.map((lottoArr, index) =>
                <ul key={`lotto_${index}`} className='my_lotto_list'>
                    {lottoArr?.map((num) =>
                        <li
                            className={lotto.includes(num) && compare ? 'active' : ''}
                            key={num}>{num}
                        </li>
                    )}
                    <li>{compare && resultArr[index]}</li>
                </ul>)}
        </div>
    );
}

export default MylottoCont;