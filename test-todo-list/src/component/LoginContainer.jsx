import React, { useState } from 'react';

function LoginContainer({ users, loginUser, login }) {

    const [selectedUser, setSelectedUser] = useState('');

    return (
        <div className='login_cont row justify-content-end'>
            <div className='col-3'>
                <select
                    id="users"
                    className='form-select'
                    onChange={(e) => setSelectedUser(e.target.value)}
                    disabled={loginUser?.name}
                >
                    <option value="">로그인해주세요</option>
                    {users?.map((user, index) => <option key={`user_${index}`} value={user.name}>{user.name}</option>)}
                </select>
            </div>

            <div className='col-2 text-end'>
                {!loginUser?.name ?
                    <button type="button" onClick={() => login(selectedUser)} className='btn btn-warning'>로그인</button>
                    :
                    <button type="button" onClick={() => login('')} className='btn btn-warning'>로그아웃</button>
                }
                {loginUser?.name && <span>{loginUser?.name}</span>}
            </div>
        </div>
    );
}

export default LoginContainer;