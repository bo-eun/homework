import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../assets/css/layout.css';
import MenuBar from '../components/MenuBar';
import { Outlet } from 'react-router';

function Layout(props) {
    return (
        <div>
            <MenuBar />
            <section>
                <Outlet />
            </section>

        </div>
    );
}

export default Layout;