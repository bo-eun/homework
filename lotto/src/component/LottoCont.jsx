import React from 'react';

function LottoCont({ lotto }) {
    return (
        <div className='lotto_cont'>
            <ul className='lotto_list'>
                {lotto?.map((num, index) => {
                    return (index == 6 ? <>
                        <li key='plus0' className='plus'>+</li>
                        <li key={`key_${num}`}>{num}</li>
                    </>
                        :
                        <li key={`key_${num}`}>{num}</li>
                    )
                }
                )}
            </ul>
        </div>
    );
}

export default LottoCont;