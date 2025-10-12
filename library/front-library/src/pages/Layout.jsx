import React from "react";
import Header from "../components/Header";
import { Outlet } from "react-router";
import Footer from "../components/Footer";

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
