import React, { useEffect, useReducer, useRef, useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import "../assets/css/common.css";
import LoginContainer from './LoginContainer';
import InputContainer from './InputContainer';
import { User, Todo } from "../data/index";
import TodoContainer from './TodoContainer';



function Container(props) {

    const [loginUser, setLoginUser] = useState({}); // 로그인 유저 저장

    const userReducer = (state, action) => {
        switch (action.type) {
            case 'init':
                return action.payload
            case 'add':
            case 'success':
            case 'delete':
                return state.map(user => {
                    if (user.name === loginUser.name) {
                        user.todoList = action.payload;
                    }
                    return user;
                })
            default:
                return state;
        };
    }

    const [users, userDispatch] = useReducer(userReducer, []); // 전체 유저 저장 배열
    const [doneCount, setDoneCount] = useState(0); // 한 일 개수 저장
    const [todoCount, setTodoCount] = useState(0); // 할 일 개수 저장
    const [todoPercent, setTodoPercent] = useState(0); // 달성률 저장

    const todoId = useRef(0); // todo id생성

    // 로그인 시 현재 로그인 유저 정보 업데이트
    const login = (name) => {
        setLoginUser(users.find(user => user.name === name));
    }

    // todo 등록
    const addTodo = (text) => {
        const newTodo = new Todo(text, todoId.current++);
        const updateList = [...loginUser.todoList, newTodo];
        userDispatch({ type: 'add', payload: updateList });
        setLoginUser(prev => ({ ...prev, todoList: updateList }));
    }

    // todo 완료
    const successTodos = (checkedArr) => {
        const updateList = loginUser.todoList.map(todo => {
            if (checkedArr.includes(todo.id)) {
                todo.success = true;
            }
            return todo;
        });

        userDispatch({ type: 'success', payload: updateList });
        setLoginUser(prev => ({ ...prev, todoList: updateList }));
    }

    // todo 삭제
    const deleteTodos = (deleteArr) => {
        const updateList = loginUser.todoList.filter(todo => !deleteArr.includes(todo.id));

        userDispatch({ type: 'delete', payload: updateList });
        setLoginUser(prev => ({ ...prev, todoList: updateList }));
    }

    // select 초기 값 설정
    useEffect(() => {
        userDispatch({ type: 'init', payload: [new User('김철수'), new User('이정은')] });
    }, [])

    // loginUser업데이트 시 할일, 한일, 달성률 구하기
    useEffect(() => {
        if (loginUser?.name) {
            const todoCount = loginUser.todoList.filter(todo => !todo.success).length;
            const doneCount = loginUser.todoList.filter(todo => todo.success).length;
            const todoLength = loginUser.todoList.length;
            const percent = isNaN(Math.floor(doneCount / todoLength * 100)) ? 0 : Math.floor(doneCount / todoLength * 100);

            setTodoCount(todoCount);
            setDoneCount(doneCount);
            setTodoPercent(percent)
        } else {
            setTodoCount(0);
            setDoneCount(0);
            setTodoPercent(0)
        }

    }, [loginUser])


    return (
        <div className='container'>
            <h2 className='text-center my-5'>Todo List</h2>

            <LoginContainer users={users} loginUser={loginUser} login={login} />

            <div className='list_info_cont text-end mt-4'>
                <p>할 일 : {todoCount} 건</p>
                <p>한 일 : {doneCount} 건</p>
                <p>달성률 : {todoPercent}%</p>
            </div>

            <InputContainer loginUser={loginUser} addTodo={addTodo} />

            <TodoContainer loginUser={loginUser} successTodos={successTodos} deleteTodos={deleteTodos} />



        </div>
    );
}

export default Container;