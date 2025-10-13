import React from "react";
import Header from "../components/Header";
import { Outlet } from "react-router";
import Footer from "../components/Footer";
import "../assets/css/admin.css";

function Layout(props) {
  return (
    <>
      <Header />
      <section>
        <Outlet />
      </section>
      <Footer />
    </>
  );
}

export default Layout;
