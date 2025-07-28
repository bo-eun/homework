import React, { useState } from 'react';

function InputContainer({ loginUser, addTodo }) {

    const [text, setText] = useState('');

    const clickHandler = () => {
        addTodo(text);
        setText(''); // input text 초기화
    }

    return (
        <div className="input_cont row mt-5">
            <div className="col-10">
                <input type="text" className='form-control' onChange={(e) => setText(e.target.value)} value={text} />
            </div>
            <div className="col-2 text-end">
                <button
                    type="button"
                    className='btn btn-success'
                    disabled={!loginUser?.name}
                    onClick={clickHandler}>등록</button>
            </div>
        </div>
    );
}

export default InputContainer;