import React from "react";
import "../assets/css/common.css";
import InputCont from "./InputCont";
import Todo from "./Todo";
import { useState } from "react";
import { useReducer } from "react";

const todoInit = {
  id: "",
  done: false,
  text: "",
  checked: false,
};

function TodoCont() {
  // 입력한 값 저장
  const [inputText, setInputText] = useState("");

  const todoReducer = (state, action) => {
    switch (action.type) {
      case "add":
        return [...state, action.payload];
      case "done":
        return state.map((todo) =>
          todo.id == action.id ? { ...todo, ...action.payload } : todo
        );
      case "allDone":
        return state.map((todo) =>
          todo.checked ? { ...todo, done: true } : todo
        );
      case "delete":
        return state.filter((todo) => todo.id != action.id);
      case "checked":
        // 리스트의 n번째 요소와 action.todoIdx가 같으면 해당 요소 action.payload로 변경...
        return state.map((todo) =>
          todo.id == action.id ? { ...todo, ...action.payload } : todo
        );
      default:
        return state;
    }
  };

  // 투두리스트 저장
  const [todoList, todoDispatch] = useReducer(todoReducer, []);

  // 입력한 인풋값 상태 변경
  const changeInput = (e) => {
    setInputText(e.target.value);
  };

  // 입력한 값 list에 추가
  const addTodo = () => {
    // 입력값 없으면 함수 종료
    if (inputText === "") {
      alert("할 일을 입력해주세요.");
      return false;
    }

    const id = `todo_${todoList.length}`;
    todoDispatch({
      type: "add",
      payload: { ...todoInit, id: id, text: inputText },
    });
  };

  // 체크박스 클릭 했을 때 이벤트
  const todoCheck = (e) => {
    const { checked, id } = e.target;
    todoDispatch({ type: "checked", payload: { checked: checked }, id: id });
  };

  // 완료 버튼 눌렀을 때 이벤트
  const doneTodo = (e) => {
    const id = e.target.closest("label").firstElementChild.id;
    todoDispatch({ type: "done", payload: { done: true }, id: id });
  };

  // 삭제 버튼 눌렀을 때 이벤트
  const deleteTodo = (e) => {
    if (!confirm("삭제하시겠습니까?")) {
      // 아니오 눌렀을 때 삭제 코드 만나지 않게 리턴하여 함수종료
      return false;
    }

    const id = e.target.closest("label").firstElementChild.id;
    todoDispatch({ type: "delete", id: id });
  };

  // 일괄 완료 버튼 눌렀을 때 이벤트
  const allDone = () => {
    todoDispatch({ type: "allDone" });
  };

  return (
    <main>
      <h2>Todo List</h2>
      <div className="text-right">
        <p>
          할 일 : <b>{todoList.filter((todo) => !todo.done).length}</b>건
        </p>
        <p>
          한 일 : <b>{todoList.filter((todo) => todo.done).length}</b>건
        </p>
        <p>
          달성률 :
          <b>
            {/* 처음에 todoList가 빈 배열일 때 0/0은 NaN이라 빈 배열이 아닐 때 달성률 보이고 아니면 0보이게 */}
            {todoList.length > 0
              ? (todoList.filter((todo) => todo.done).length /
                  todoList.length) *
                100
              : 0}
          </b>
          %
        </p>
      </div>
      <InputCont
        changeInput={changeInput}
        addTodo={addTodo}
        allDone={allDone}
      />
      <div className="todo_list_cont">
        <ul className="todo_list" id="todoList">
          {todoList.length > 0 &&
            todoList?.map((todo, index) => (
              <Todo
                key={`key_${index}`}
                index={index}
                text={todo.text}
                isDone={todo.done}
                isChecked={todo.checked}
                todoCheck={todoCheck}
                doneTodo={doneTodo}
                deleteTodo={deleteTodo}
              />
            ))}
        </ul>
      </div>
    </main>
  );
}

export default TodoCont;
