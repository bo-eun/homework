import React from 'react';
import Header from '../../components/admin/Header';
import { Outlet } from 'react-router';

function AdminLayout(props) {
    return (
        <>
            <Header />
            <section>
                <Outlet />
            </section>
        </>
    );
}

export default AdminLayout;