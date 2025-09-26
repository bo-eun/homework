import { createBrowserRouter } from "react-router";
import BoardList from "../pages/board/BoardList";
import Layout from "../pages/Layout";
import LoginForm from "../pages/login/LoginForm";
import BoardDetail from "../pages/board/BoardDetail";
import BoardWrite from "../pages/board/BoardWrite";

export const router = createBrowserRouter([
  {
    path: "/",
    Component: Layout,
    children: [
      {
        path: "board",
        children: [
          { index: true, Component: BoardList },
          { path: ":brdId", Component: BoardDetail },
          { path: "add", Component: BoardWrite },
        ],
      },
    ],
  },
  {
    path: "/login",
    Component: LoginForm,
  },
]);
