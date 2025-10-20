import React, { useState } from "react";

function Counter() {
  const [value, setValue] = useState(1);
  const countUp = () => {
    setValue((prev) => prev + 1);
  };
  const countDown = () => {
    setValue((prev) => {
      if (prev <= 1) {
        return prev;
      }
      return prev - 1;
    });
  };
  return (
    <div className="counter d-flex">
      <button onClick={countDown}>-</button>
      <input
        type="number"
        value={value}
        onChange={() => {}}
        className="form-range"
        readOnly
      />
      <button onClick={countUp}>+</button>
    </div>
  );
}

export default Counter;
