import { createBrowserRouter } from "react-router";
import Layout from "../pages/Layout";
import Main from "../pages/Main";
import Login from "../pages/login/Login";
import BookList from "../pages/admin/list/BookList";
import AdminLayout from "../pages/admin/AdminLayout";
import AddBook from "../pages/admin/upload/AddBook";
import Join from "../pages/login/Join";

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
        Component: BookList,
        index: true,
      },
      {
        path: "add",
        Component: AddBook,
      },
    ],
  },
]);
