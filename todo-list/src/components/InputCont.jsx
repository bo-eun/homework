import React from "react";

function InputCont({ changeInput, addTodo, allDone }) {
  return (
    <div className="input_cont">
      <input type="text" onChange={changeInput} />
      <div className="btn_box">
        <button type="button" className="add" onClick={addTodo}>
          등록
        </button>
        <button type="button" className="all done" onClick={allDone}>
          일괄완료
        </button>
      </div>
    </div>
  );
}

export default InputCont;
