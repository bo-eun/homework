import React from "react";
import { useCart } from "../../hooks/useCart";
import { authStore } from "../../store/authStore";
import { useNavigate } from "react-router";

function CartAddButton({ bookId, quantity = 1 }) {
  const { createMutation } = useCart();
  const navigate = useNavigate();
  // 로그인 여부
  const isAuthenticated = authStore((state) => state.isAuthenticated);
  const userId = authStore((state) => state.userId);

  const handleAddCart = async () => {
    if (!isAuthenticated) {
      alert("로그인이 필요합니다.");
      navigate("/login");
      return;
    }

    try {
      const cartData = {
        userId,
        bookId,
        quantity,
      };

      console.log(cartData);
      await createMutation.mutateAsync(cartData);
    } catch (error) {
      console.error(error);
      alert("장바구니 추가 중 오류가 발생했습니다.");
    }
  };

  return (
    <button
      type="button"
      className="btn btn-outline-dark w-100"
      onClick={handleAddCart}
    >
      장바구니
    </button>
  );
}

export default CartAddButton;
