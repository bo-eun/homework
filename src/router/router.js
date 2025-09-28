import { createBrowserRouter } from "react-router";
import BoardList from "../pages/board/BoardList";
import Layout from "../pages/Layout";
import LoginForm from "../pages/login/LoginForm";
import BoardDetail from "../pages/board/BoardDetail";
import BoardWrite from "../pages/board/BoardWrite";
import UserList from "../pages/userBoard/UserList";
import UserDetail from "../pages/userBoard/UserDetail";
import UserWrite from "../pages/userBoard/UserWrite";

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
      {
        path: "admin",
        children: [
          { index: true, Component: UserList },
          { path: ":brdId", Component: UserDetail },
          { path: "add", Component: UserWrite },
        ],
      },
    ],
  },
  {
    path: "/login",
    Component: LoginForm,
  },
]);
