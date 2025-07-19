import React from "react";

function Todo({
  index,
  text,
  isDone,
  isChecked,
  todoCheck,
  doneTodo,
  deleteTodo,
}) {
  return (
    <li>
      <label htmlFor={`todo_${index}`} className={isDone ? "done" : ""}>
        <input
          type="checkbox"
          id={`todo_${index}`}
          checked={isChecked}
          disabled={isDone}
          onChange={todoCheck}
        />
        <p>{text}</p>
        <div className="btn_box">
          <button type="button" className="done" onClick={doneTodo}>
            완료
          </button>
          <button type="button" className="delete" onClick={deleteTodo}>
            삭제
          </button>
        </div>
      </label>
    </li>
  );
}

export default Todo;
