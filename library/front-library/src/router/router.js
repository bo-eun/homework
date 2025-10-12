import { createBrowserRouter } from "react-router";
import Layout from "../pages/Layout";
import Main from "../pages/Main";
import Login from "../pages/login/Login";

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
        path: "/login",
        Component: Login,
      },
    ],
  },
]);
