import React, { useEffect, useState } from 'react';

function TodoContainer({ loginUser, successTodos, deleteTodos }) {

    // 체크한 아이디 저장
    const [checkList, setCheckList] = useState([]);

    const checkHandler = (e) => {
        const { id, checked } = e.target;
        if (checked) {
            setCheckList(prev => [...prev, Number(id)])
        } else {
            setCheckList(prev => prev.filter(el => el !== Number(id)))
        }
    };

    // 일괄완료나 완료 눌렀을 때 successTodos에 체크 리스트 전달,
    // 체크 리스트 초기화
    const successHandler = (list) => {
        successTodos(list)
        setCheckList([]);
    }

    // 로그인한 유저가 변경될 때 체크 리스트 초기화
    useEffect(() => {
        setCheckList([]);
    }, [loginUser])


    return (
        <div className="todo_list_cont mt-4">
            <div className="button_box text-end">
                <button
                    type="button"
                    className='btn btn-success me-1'
                    disabled={!loginUser?.name}
                    onClick={() => successHandler(checkList)}
                >일괄 완료</button>
                <button
                    type="button"
                    className='btn btn-danger'
                    disabled={!loginUser?.name}
                    onClick={() => deleteTodos(checkList)}
                >일괄 삭제</button>
            </div>
            <ul className='mt-3'>
                {loginUser?.todoList?.map(todo =>
                    <li key={`todo_${todo.id}`} className={todo.success ? 'done' : ''}>
                        <label htmlFor={todo.id}>
                            <input
                                type="checkbox"
                                id={todo.id}
                                onChange={e => checkHandler(e)}
                                checked={checkList.includes(todo.id)}
                            />
                            <span>{todo.text}</span>
                            <div className="button_box">
                                <button
                                    type="button"
                                    className='btn btn-primary me-1'
                                    disabled={todo.success}
                                    onClick={() => successHandler([todo.id])}
                                >완료</button>
                                <button
                                    type="button"
                                    className='btn btn-danger'
                                    onClick={() => deleteTodos([todo.id])}
                                >삭제</button>
                            </div>
                        </label>
                    </li>
                )}
            </ul>
        </div>
    );
}

export default TodoContainer;