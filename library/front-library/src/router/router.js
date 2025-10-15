import { createBrowserRouter } from "react-router";
import Layout from "../pages/Layout";
import Main from "../pages/Main";
import Login from "../pages/login/Login";
import BookList from "../pages/admin/book/BookList";
import AdminLayout from "../pages/admin/AdminLayout";
import Join from "../pages/login/Join";
import UserList from "../pages/admin/users/UserList";
import UserForm from "../components/admin/userForm";
import Policy from "../pages/admin/Policy";
import AddBook from "../pages/admin/book/AddBook";

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
    ],
  },

  {
    path: "/admin",
    Component: AdminLayout,
    children: [
      {
        index: true,
        Component: BookList,
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
