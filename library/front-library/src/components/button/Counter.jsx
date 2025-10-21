import React, { useState } from "react";

function Counter({ value, setValue }) {
  const countUp = () => {
    setValue(value + 1);
  };
  const countDown = () => {
    setValue(value > 1 ? value - 1 : 1);
  };

  return (
    <div className="counter d-flex">
      <button onClick={countDown}>-</button>
      <input
        type="number"
        value={value}
        className="form-range"
      />
      <button onClick={countUp}>+</button>
    </div>
  );
}

export default Counter;
