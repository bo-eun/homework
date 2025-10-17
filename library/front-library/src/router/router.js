import { createBrowserRouter } from "react-router";
import Layout from "../pages/Layout";
import Main from "../pages/Main";
import Login from "../pages/login/Login";
import AdminBookList from "../pages/admin/book/BookList";
import BookList from "../pages/book/BestList";
import AdminLayout from "../pages/admin/AdminLayout";
import Join from "../pages/login/Join";
import UserList from "../pages/admin/users/UserList";
import UserForm from "../components/admin/userForm";
import Policy from "../pages/admin/Policy";
import AddBook from "../pages/admin/book/AddBook";
import MypageLayout from "../pages/mypage/MypageLayout";
import WishList from "../pages/mypage/WishList";
import Mypage from "../pages/mypage/Mypage";
import OrderList from "../pages/mypage/OrderList";
import UserUpdate from "../pages/mypage/UserUpdate";
import Detail from "../pages/book/Detail";
import Cart from "../pages/order/Cart";
import Order from "../pages/order/Order";

export const router = createBrowserRouter([
  {
    path: "/",
    Component: Layout,
    children: [
      {
        Component: Main,
        index: true,
      },
      {
        path: "login",
        Component: Login,
      },
      {
        path: "join",
        Component: Join,
      },
      {
        path: "mypage",
        Component: MypageLayout,
        children: [
          {
            Component: Mypage,
            index: true,
            handle: { title: "마이페이지" },
          },
          {
            Component: OrderList,
            path: "orderList",
            handle: { title: "주문목록" },
          },
          {
            Component: WishList,
            path: "wishList",
            handle: { title: "찜목록" },
          },
          {
            Component: UserUpdate,
            path: "user/update",
            handle: { title: "회원정보 수정" },
          },
        ],
      },
      {
        Component: Cart,
        path: "cart",
      },
      {
        Component: Order,
        path: "order",
      },
      {
        path: "list/best",
        Component: BookList,
      },
      {
        path: "detail",
        Component: Detail,
      },
    ],
  },

  {
    path: "/admin",
    Component: AdminLayout,
    children: [
      {
        index: true,
        Component: AdminBookList,
      },
      {
        path: "book/add",
        Component: AddBook,
      },
      {
        path: "user",
        Component: UserList,
      },
      {
        path: "user/update",
        Component: UserForm,
      },
      {
        path: "policy",
        Component: Policy,
      },
    ],
  },
]);
